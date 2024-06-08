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
			
			isVisited = new boolean[1000000];
			order = new int[1000000];
			
			dfs(A, 0);
	        
	        System.out.println(nums);
		}
		
		static void dfs(int num, int count) {
			if (!isVisited[num]) {
				isVisited[num] = true;
				order[count] = num;
			}
			
			else {
				for (int i = 0; i <= count; i++) {
					if (order[i] == num) {
						nums = i;
						break;
					}
				}
				return;
			}
			
			int sum = 0;
			while (num > 0) {
				sum += Math.pow(num % 10, P);
				num /= 10;
			}
			
			dfs(sum, count + 1);
		}
	}
