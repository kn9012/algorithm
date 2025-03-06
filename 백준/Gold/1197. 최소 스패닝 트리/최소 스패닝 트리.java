import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1197번 최소 스패닝 트리
 * 1. 프림 알고리즘 : BFS?
 * 2. 크루스칼 알고리즘 : Union Find
 */

public class Main {
	public static class Node implements Comparable<Node> {
		int to, from, value;
		
		Node (int to, int from, int value) {
			this.to = to;
			this.from = from;
			this.value = value;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}
	}
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			
			queue.add(new Node(n1, n2, n3));
		}
		
		parent = new int[V + 1];
		
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		
		int size = queue.size();
		int sum = 0;
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			
			int toParent = findParent(cur.to);
			int fromParent = findParent(cur.from);
			
			if (toParent != fromParent) {
				sum += cur.value;
				union(cur.to, cur.from);
			}
		}
		
		System.out.println(sum);
	}
	
	public static int findParent(int node) {
		if (parent[node] == node) return node;
		
		return parent[node] = findParent(parent[node]);
	}
	
	public static void union(int n1, int n2) {
		int n1Parent = findParent(n1);
		int n2Parent = findParent(n2);
		
		if (n1Parent != n2Parent) parent[n2Parent] = n1Parent;
	}
}
