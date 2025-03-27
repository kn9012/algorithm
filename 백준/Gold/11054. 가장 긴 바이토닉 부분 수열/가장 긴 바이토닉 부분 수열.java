import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 11054번 가장 긴 바이토닉 부분 수열
 * 
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp1 = new int[N]; // 왼 -> 오
		int[] dp2 = new int[N]; // 오 -> 왼
		
		int answer = 1;
		
		dp1[0] = 1;
		for (int i = 1; i < N; i++) {
			dp1[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp1[i] = Math.max(dp1[i], dp1[j] + 1);
					//answer = Math.max(answer, dp1[i]);
				}
			}
		}
		
		dp2[N - 1] = 1;
		for (int i = N - 2; i >= 0; i--) {
			dp2[i] = 1;
			for (int j = N - 1; j > i; j--) {
				if (nums[i] > nums[j]) {
					dp2[i] = Math.max(dp2[i], dp2[j] + 1);
					//answer = Math.max(answer, dp2[i]);
				}
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.print(dp1[i] + " ");
//		} System.out.println();
//		
//		for (int i = 0; i < N; i++) {
//			System.out.print(dp2[i] + " ");
//		} System.out.println();

		
		for (int i = 0; i < N; i++) {
			answer = Math.max(answer, dp1[i] + dp2[i] - 1);
		}
		
		System.out.println(answer);
	}
	
}
