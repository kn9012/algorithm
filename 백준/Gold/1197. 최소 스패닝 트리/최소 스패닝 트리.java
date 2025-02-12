import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int parent[];
	
	public static class Node implements Comparable<Node> {
		int to, from, value;
		
		Node(int to, int from, int value) {
			this.to = to;
			this.from = from;
			this.value = value;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		parent = new int[V + 1];
		
		for (int i = 1; i <= V; i++) parent[i] = i;
		
		Queue<Node> queue = new PriorityQueue<>();
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			queue.add(new Node(A, B, C));
		}
		
		int size = queue.size();
		int sum = 0;
		
		for (int i = 0; i < size; i++) {
			Node node = queue.poll();
			
			int to = find(node.to);
			int from = find(node.from);
			
			if (to != from) {
				sum += node.value;
				union(node.to, node.from);
			}
		}
		
		System.out.println(sum);
	}
	
	public static int find(int n) {
		if (parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}
	
	public static void union(int n, int m) {
		n = find(n);
		m = find(m);
		
		if (n != m) parent[m] = n;
	}
}
