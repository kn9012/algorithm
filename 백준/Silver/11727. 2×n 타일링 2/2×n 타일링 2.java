import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 1x2 타일 2개는 2x2 타일로 간주
        // n이 홀수일 경우 2x1 타일은 무조건 하나 이상 잇어야 함
        // 길이가 2인 경우 1x2 2개, 2x2 1개, 2x1 2개 가능

        int[] dp = new int[n + 1];
        dp[1] = 1;

        if (n >= 2) dp[2] = 3;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
        }

        System.out.println(dp[n] % 10007);
    }
}
