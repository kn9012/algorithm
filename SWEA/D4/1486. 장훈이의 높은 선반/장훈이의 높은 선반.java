import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * [문제] SWEA 1486번 장훈이의 높은 선반
 * [아이디어] dfs를 이용한 
 * @author SSAFY
 *
 */

public class Solution {
	static int N, B, min;
	static int arr[], select[];
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 직원 수
			B = Integer.parseInt(st.nextToken()); // 탑의 높이
			 
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			min = Integer.MAX_VALUE;
			visited = new boolean[N];
			subset(0);
			
			System.out.println("#" + t + " " + min);
		}
	}
	
	static void subset(int count) {
		if (count == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					sum += arr[i];
					if (sum - B > min) return;
				}
			}
			
			if (sum >= B) {
				int diff = sum - B;
				min = Math.min(diff, min);
			}
			return;
		}
		
		visited[count] = true;
		subset(count + 1);
		visited[count] = false;
		subset(count + 1);
	}
	
//	static void comb(int X, int start, int count) {
//		if (count == X) {
//			for (int i = 0; i < select.length; i++) {
//				sum += select[i];
//			}
//			
//			if (sum >= B) {
//				min = Math.min(min, sum);
//				return; 
//			}
//		}
//		
//		for (int i = start; i < X; i++) {
//			select[count] = i;
//			comb(X, i + 1, count + 1);
//		}
//	}
	
	static void comb(int start, int count, int sum) {
		if (sum >= B) {
			min = Math.min(min, sum);
			return;
		}
		if (count == N) {
			if (sum >= B) {
				min = Math.min(min, sum);
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			comb(i + 1, count + 1, sum + arr[count]);
		}
		
	}
}