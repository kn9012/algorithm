import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N + 1][10]; // i자리 계단 수 중 마지막 자리가 j인 경우의 개수

        // N = 1, dp[1][1] ~ dp[1][9]
        // 1 2 3 4 5 6 7 8 9
        // N = 2
        // 12 23 34 45 56 67 78 89 98 87 76 65 54 43 32 21 10
        // N = 3
        // 123 121 234 232 345 343 456 454 567 565 678 676 789 787 898
        // 989 987 876 878 765 767 654 656 543 545 432 434 321 323 212 210 101
        // 위와 같이 계단 수는 하나의 자리수가 정해지면 뒤에 따라오는 수는 둘 중 하나다
        // N = 1 -> 9, N = 2 -> 17, N = 3 -> 32

        for (int i = 1; i < 10; i++) dp[1][i] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                if (j > 0) dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % 1000000000;
                if (j < 9) dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % 1000000000;
            }
        }

        long answer = 0;
        for (int i = 0; i < 10; i++) answer = (answer + dp[N][i]) % 1000000000;

        System.out.println(answer);
    }
}
