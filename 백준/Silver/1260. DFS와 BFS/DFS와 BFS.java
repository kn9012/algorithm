import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, v;
	static boolean arr[][];
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 정점 개수
		m = Integer.parseInt(st.nextToken()); // 간선 개수
		v = Integer.parseInt(st.nextToken()); // 탐색 시작할 정점 번호

		arr = new boolean[n + 1][n + 1]; // 간선이 연결하는 두 정점의 번호 배열

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 정점 번호 1
			int b = Integer.parseInt(st.nextToken()); // 정점 번호 2
			arr[a][b] = true;
			arr[b][a] = true;
		}

		visited = new boolean[n + 1];
		
		dfs(v); // 깊이 우선 탐색
		System.out.println();
		visited = new boolean[n + 1];
		bfs(); // 너비 우선 탐색
	}

	static void dfs(int current) {
		System.out.print(current + " ");
		visited[current] = true;
		
		for (int i = 1; i <= n; ++i) {
			if (arr[current][i] && !visited[i]) {
				dfs(i);
			}
		}
	}
	
	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		queue.offer(v);
		visited[v] = true;
		
		while (!queue.isEmpty()) {
			int current = queue.poll();
			System.out.print(current + " ");
			for (int i = 1; i <= n; i++) {
				if (arr[current][i] && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
	}
}
