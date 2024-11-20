import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	static class Node implements Comparable<Node> {
		int node;
        long value;

        Node(int node, long value) {
            this.node = node;
            this.value = value;
        }
        
        @Override
        public int compareTo(Node other) {
        	return Long.compare(this.value, other.value);
        }
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean isShow[] = new boolean[N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			if (Integer.parseInt(st.nextToken()) == 1) isShow[i] = true;
		}
		isShow[N - 1] = false;
		
		List<Node> list[] = new ArrayList[N];
		for (int i = 0; i < N; i++) {
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
		
		long dist[] = new long[N];
		Arrays.fill(dist, Long.MAX_VALUE);
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(0, 0));
		dist[0] = 0;
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int node = cur.node;
			long value = cur.value;
			
			if (value > dist[node]) continue;
			
			for (int i = 0; i < list[node].size(); i++) {
				if (!isShow[list[node].get(i).node] &&
						dist[list[node].get(i).node] > dist[node] + list[node].get(i).value) {
					dist[list[node].get(i).node] = dist[node] + list[node].get(i).value;
					queue.add(new Node(list[node].get(i).node, dist[list[node].get(i).node]));
				}
			}
		}
		
		if (dist[N - 1] == Long.MAX_VALUE) System.out.println(-1);
		else System.out.println(dist[N - 1]);
	}
}

