import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2533번 사회망 서비스(SNS)
 * - DFS + DP
 */

public class Main {
	static int N, earlyAdopter;
	static int[][] dp;
	static List<List<Integer>> list;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
		
		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			
			list.get(num1).add(num2);
			list.get(num2).add(num1);
		}
		
		// dp[i][0] : i가 얼리 아답터가 아닌 경우 / dp[i][1] : i가 얼리 아답터인 경우
		dp = new int[N + 1][2];
		visited = new boolean[N + 1];
		
		dfs(1);
		
		System.out.println(Math.min(dp[1][1], dp[1][0]));
	}
	
	public static void dfs(int n) {
		visited[n] = true;
		dp[n][0] = 0;
		dp[n][1] = 1;
		
		for (int people : list.get(n)) {
			if (!visited[people]) {
				dfs(people);
				dp[n][0] += dp[people][1];
				dp[n][1] += Math.min(dp[people][0], dp[people][1]);
			}
		}
		
	}
}
