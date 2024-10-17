import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int dp[] = new int[D + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		int[][] highway = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				highway[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= D; i++) {
			for (int j = 0; j < N; j++) {
				if (highway[j][1] == i) {
					dp[i] = Math.min(dp[i], Math.min(dp[i - 1] + 1, dp[highway[j][0]] + highway[j][2]));
				} else {
					dp[i] = Math.min(dp[i], dp[i - 1] + 1);
				}
			}
		}
		
		
		System.out.println(dp[D]);
	}
}
