import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int arr[] = new int[N + 1];
		
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		List<Integer>[] list = new ArrayList[N + 1];
		
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
		
			arr[n2]++;
			list[n1].add(n2);
		}
		
		for (int i = 1; i <= N; i++) {
			if (arr[i] == 0) queue.add(i);
		}
		
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			sb.append(cur + " ");
			
			for (Integer node : list[cur]) {
				arr[node]--;
				
				if (arr[node] == 0) queue.add(node);
			}
		}
		
		System.out.println(sb);
	}
}
