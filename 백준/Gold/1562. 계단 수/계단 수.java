import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 0부터 9까지의 수가 모두 등장해야 함 -> 이전에 어떤 숫자가 나왔는지 체크해야 하므로 비트마스킹
 */

public class Main {
    static long MOD = 1_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N < 10) { // N이 10 미만이면 0부터 9까지 나올 수 없음
            System.out.println(0);
            return;
        }

        // dp[길이][마지막 숫자][비트마스크]
        long[][][] dp = new long[N + 1][10][1024];

        // 길이가 1인 계단 수 (0은 앞에 올 수 없기 때문에 1부터)
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < 1024; k++) {
                    if (dp[i][j][k] == 0) continue;

                    // 다음 숫자가 j - 1인 경우
                    if (j > 0) {
                        int nextMask = k | (1 << (j - 1));
                        dp[i + 1][j - 1][nextMask] = (dp[i + 1][j - 1][nextMask] + dp[i][j][k]) % MOD;
                    }

                    // 다음 숫자가 j + 1인 경우
                    if (j < 9) {
                        int nextMask = k | (1 << (j + 1));
                        dp[i + 1][j + 1][nextMask] = (dp[i + 1][j + 1][nextMask] + dp[i][j][k]) % MOD;
                    }
                }
            }
        }

        // 길이가 N이고 비트마스크가 1023인 경우의 합
        long result = 0;
        for (int i = 0; i <= 9; i++) {
            result = (result + dp[N][i][1023]) % MOD;
        }

        System.out.println(result);;
    }
}
