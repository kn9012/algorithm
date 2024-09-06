import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1446번 지름길
 * - dp로 풀이
 * - 고속도로를 역주행할 수는 없음
 * 
 * 메모리 : kb 시간 : ms
 */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 지름길 개수
		int D = Integer.parseInt(st.nextToken()); // 고속도로 길이
		int shortcut[][] = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 3; j++) {
				shortcut[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int dp[] = new int[D + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for (int i = 1; i <= D; i++) {
			for (int j = 0; j < N; j++) {
				if (shortcut[j][1] == i) {
					dp[i] = Math.min(dp[i], Math.min(dp[i - 1] + 1, dp[shortcut[j][0]] + shortcut[j][2]));
				} else {
					dp[i] = Math.min(dp[i], dp[i - 1] + 1);
				}
			}
		}
		
		System.out.println(dp[D]);
	}
}
