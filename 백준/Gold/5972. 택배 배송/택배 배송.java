import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 5972번 택배 배송
 * 
 *  
 */

public class Main {
	public static class Node {
		int node, value;
		
		Node(int node, int value) {
			this.node = node;
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Node>[] list = new ArrayList[N + 1];
		
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<Node>(); 
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			list[num1].add(new Node(num2, value));
			list[num2].add(new Node(num1, value));
		}

		int[] dist = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		dist[1] = 0;		
		
		PriorityQueue<int []> queue = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));
		queue.add(new int[] {1, 0});
		
		
		while (!queue.isEmpty()) {
			int cur[] = queue.poll();
			int value = cur[0];
			int sum = cur[1];
			
			//System.out.println(value + " " + sum);
			
			if (sum > dist[value]) continue;
			
			
			for (int i = 0; i < list[value].size(); i++) {
				int v = list[value].get(i).node;
				int weight = list[value].get(i).value;
				
				if (dist[v] > dist[value] + weight) {
					dist[v] = dist[value] + weight;
					queue.add(new int[] {v, dist[v]});
				}
			}
		}
		
		System.out.println(dist[N]);
	}
}
