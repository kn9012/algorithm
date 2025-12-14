import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. 한 번에 한 계단씩 또는 두 계단씩
 * 2. 연속된 세 개의 계단을 밟을 수 x (시작점은 포함 x)
 * 3. 마지막 도착 계단은 반드시 밟아야 함
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stairs = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        // dp[i][j] : i번째 계단에서 계단을 연속해서 j개 밟는 경우
        int[][] dp = new int[n + 1][3];

        // 첫번째 계단에서 계단을 연속해서 1개 밟은 경우
        dp[1][1] = stairs[1];

        if (n >= 2) {
            // 두번째 계단에서 계단을 연속해서 1개 밟은 경우
            dp[2][1] = stairs[2];

            // 두번째 계단에서 계단을 연속해서 2개 밟은 경우
            dp[2][2] = dp[1][1] + stairs[2];
        }

        for (int i = 3; i <= n; i++) {
            // i번째 계단에서 계단을 연속해서 1개 밟은 경우
            dp[i][1] = Math.max(dp[i - 2][2], dp[i - 2][1]) + stairs[i];

            // i번째 계단에서 계단을 연속해서 2개 밟은 경우
            dp[i][2] = dp[i - 1][1] + stairs[i];
        }

        System.out.println(Math.max(dp[n][1], dp[n][2]));
    }
}
