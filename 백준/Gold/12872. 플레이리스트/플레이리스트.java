
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 12872번 플레이리스트
 * - DP
 */

public class Main {
    public static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        // dp[i][j] : 길이가 i인 플레이리스트, j개의 서로 다른 노래 사용
        long[][] dp = new long[P + 1][N + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= P; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] += dp[i - 1][j - 1] * (N - (j - 1));
                dp[i][j] %= MOD;
                
                if (j > M) {
                    dp[i][j] += dp[i - 1][j] * Math.max(0, j - M);
                    dp[i][j] %= MOD;
                }
            }
        }

        System.out.println(dp[P][N]);
    }
}
