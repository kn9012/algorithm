import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1446번 지름길
 * 
 * 
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 지름길 개수
		int D = Integer.parseInt(st.nextToken()); // 고속도로 길이
		
		int arr[][] = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 3; j++) {
				// 지름길 시작 위치, 도착 위치, 지름길 길이
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int dp[] = new int[10001];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		
		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[j][1] == i) {
					dp[i] = Math.min(dp[i], Math.min(dp[i - 1] + 1, dp[arr[j][0]] + arr[j][2]));
				} else {
					dp[i] = Math.min(dp[i], dp[i - 1] + 1);
				}
			}
		}
		
		System.out.println(dp[D]);
	}
}
