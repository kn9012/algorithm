import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2624번 동전 바꿔주기
 * - dp
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int[][] coins = new int[k][2];
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			coins[i][0] = Integer.parseInt(st.nextToken());
			coins[i][1] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[k + 1][T + 1];
		
		for (int i = 0; i < k; i++) dp[i][0] = 1;		
		
		for (int i = 0; i < k; i++) {
			int amount = coins[i][0];
			int count = coins[i][1];
			
			for (int j = 1; j <= count; j++) {
				int cost = amount * j;
				
				for (int l = 0; l + cost <= T; l++) {
					dp[i + 1][l + cost] += dp[i][l]; 
				}
			}
			
			for (int j = 1; j <= T; j++) {
				dp[i + 1][j] += dp[i][j];
			}
		}
		
		System.out.println(dp[k][T]);
	}
}
