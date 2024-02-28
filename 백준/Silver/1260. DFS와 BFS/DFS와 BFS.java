import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제] 백준 1260번 DFS와 BFS
 *
 */
public class Main {
	static int N, M, V, arr[][], dfs[];
	static List<Integer> bfs;
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		dfs = new int[N];
		visited = new boolean[N + 1];
		
		dfs(V);
		System.out.println();
		bfs(V);
		for (int i : bfs) {
			System.out.print(i + " ");
		}
	}
	
	static void dfs(int start) {
		System.out.print(start + " ");
		visited[start] = true;
		
		for (int i = 1; i <= N; i++) {
			if (arr[start][i] == 1 && !visited[i]) {
				visited[i] = true;
				dfs(i);
			}			
		}
	}
	
	static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		bfs = new ArrayList<>();
		visited = new boolean[N + 1];
		
		queue.add(start);
		visited[start] = true;
		bfs.add(start);
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int i = 1; i <= N; i++) {
				if (arr[cur][i] == 1 && !visited[i]) {
					queue.add(i);
					visited[i] = true;
					bfs.add(i);
				}
			}
		}
	}
}
