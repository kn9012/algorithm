	import java.io.*;
	import java.util.*;
	
	/**
	 * 백준 2331번 반복수열
	 * DFS 사용
	 */	

	public class Main {
		static int A, P, nums = 0, order[];
		static boolean isVisited[];
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			A = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			
			// (9999, 5)일 경우 최대 수는 236196이므로 30만으로 잡기
			isVisited = new boolean[300000];
			order = new int[300000];
			
			dfs(A, 0);
	        
	        System.out.println(nums);
		}
		
		static void dfs(int num, int count) {
			if (!isVisited[num]) {
				isVisited[num] = true;
				order[count] = num;
			}
			
			else { // 반복된 숫자일 경우
				for (int i = 0; i <= count; i++) {
					if (order[i] == num) {
						nums = i; // 해당 순서를 찾아 return
						break;
					}
				}
				return;
			}
			
			int sum = 0;
			while (num > 0) {
				// 각 자리 거듭제곱 구하여 더하기
				sum += Math.pow(num % 10, P);
				num /= 10;
			}
			
			dfs(sum, count + 1);
		}
	}
