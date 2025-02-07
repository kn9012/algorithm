import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1766번 문제집
 * - 위상정렬
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int indegree[] = new int[N + 1];
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			list.get(v1).add(v2);
			indegree[v2]++;
		}
		
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int node = queue.poll();
			
			for (Integer i : list.get(node)) {
				indegree[i]--;
				
				if (indegree[i] == 0) queue.add(i);
			}
			
			System.out.print(node + " ");
		}
	}
}
