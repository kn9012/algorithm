import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int node, value;
		
		Node(int node, int value) {
			this.node = node;
			this.value = value;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		List<Node> list[] = new ArrayList[n + 1];
		
		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			list[num1].add(new Node(num2, value));
			list[num2].add(new Node(num1, value));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int dist[] = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		PriorityQueue<int []> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		queue.add(new int[] {start, 0});
		dist[start] = 0;
		
		while (!queue.isEmpty()) {
			int cur[] = queue.poll();
			int node = cur[0];
			int value = cur[1];
			
			if (dist[node] > value) continue;
			
			for (int i = 0; i < list[node].size(); i++) {
				if (dist[list[node].get(i).node] > dist[node] + list[node].get(i).value) {
					dist[list[node].get(i).node] = dist[node] + list[node].get(i).value;
					queue.add(new int[] {list[node].get(i).node, dist[list[node].get(i).node]});
				}
			}
		}
		
		System.out.println(dist[end]);
	}
}
