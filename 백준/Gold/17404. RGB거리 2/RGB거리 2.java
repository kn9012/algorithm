import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * i번 집의 색은 i-1, i+1번 집의 색과 같지 않아야 함
 * 중요한건 1번은 2, N번과 같지 않아야 하며 N번은 N-1번, 1번이랑 같지 않아야 함
 */

public class Main {
    static int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 2 <= N <= 1000

        int[][] cost = new int[N + 1][3];
        StringTokenizer st;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N + 1][3];
        dp[1][0] = cost[1][0]; // 시작 빨로 고정
        dp[1][1] = INF;
        dp[1][2] = INF;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = cost[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = cost[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = cost[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        int dpR = Math.min(dp[N][1], dp[N][2]);

        dp = new int[N + 1][3];
        dp[1][1] = cost[1][1]; // 시작 초로 고정
        dp[1][0] = INF;
        dp[1][2] = INF;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = cost[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = cost[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = cost[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        int dpG = Math.min(dp[N][0], dp[N][2]);

        dp = new int[N + 1][3];
        dp[1][2] = cost[1][2]; // 시작 파로 고정
        dp[1][0] = INF;
        dp[1][1] = INF;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = cost[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = cost[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = cost[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        int dpB = Math.min(dp[N][0], dp[N][1]);

        System.out.println(Math.min(dpR, Math.min(dpG, dpB)));
    }
}
