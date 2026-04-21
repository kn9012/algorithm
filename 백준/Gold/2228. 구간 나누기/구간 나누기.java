import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 음수의 합이 매우 작아질 수 있으므로 충분히 작은 값을 설정합니다.
    // N(100) * 최소값(-32768) 보다 작은 값이어야 안전합니다.
    static final int INF = -100_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] nums = new int[n + 1];
        int[] sum = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            sum[i] = sum[i - 1] + nums[i]; // 누적합 계산
        }

        // dp[i][j] : i번째 숫자까지 확인했을 때, j개의 구간을 확정 지은 최댓값
        int[][] dp = new int[n + 1][m + 1];

        // 초기화: 0개 구간을 제외한 모든 상태를 INF로 설정
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0; 
        }

        for (int j = 1; j <= m; j++) {
            for (int i = 1; i <= n; i++) {
                // Case 1: i번째 원소를 어느 구간에도 포함시키지 않는 경우
                // 이전까지 j개를 만든 최댓값을 그대로 가져옴
                dp[i][j] = dp[i - 1][j];

                // Case 2: i번째 원소를 포함하여 j번째 구간을 새로 만드는 경우
                // 구간의 시작점 k를 1부터 i까지 탐색
                for (int k = i; k >= 1; k--) {
                    int currentRangeSum = sum[i] - sum[k - 1];

                    if (k >= 2) {
                        // j-1번째 구간과 인접하지 않아야 하므로 dp[k-2]를 참조
                        if (dp[k - 2][j - 1] != INF) {
                            dp[i][j] = Math.max(dp[i][j], dp[k - 2][j - 1] + currentRangeSum);
                        }
                    } else if (k == 1 && j == 1) {
                        // 첫 번째 구간이 맨 처음부터 시작하는 특수 케이스
                        dp[i][j] = Math.max(dp[i][j], currentRangeSum);
                    }
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}