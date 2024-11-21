import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, A, B, C, answer = Integer.MAX_VALUE;
	static List<Node> list[];
	static boolean isVisit[];
	
	public static class Node implements Comparable<Node>{
		int node, value;
		
		Node(int node, int value) {
			this.node = node;
			this.value = value;
		}
		
		@Override
		public int compareTo(Node other) {
			return Integer.compare(this.value, other.value);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			list[num1].add(new Node(num2, value));
			list[num2].add(new Node(num1, value));
		}
		
		isVisit = new boolean[N + 1];
		
		isVisit[A] = true;
		dfs(A, C, 0);
		isVisit[A] = false;
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
	
	static void dfs(int node, int money, int max) {
		if (node == B) {
			answer = Math.min(answer, max);
			return;
		}
		
		for (int i = 0; i < list[node].size(); i++) {
			int next = list[node].get(i).node;
			int cost = list[node].get(i).value;
			
			if (isVisit[next]) continue;
			if (cost > money) continue;
			
			isVisit[next] = true;
			int nextMax = Math.max(max, cost);
			dfs(next, money - cost, nextMax);
			isVisit[next] = false;
		}
	}
}
