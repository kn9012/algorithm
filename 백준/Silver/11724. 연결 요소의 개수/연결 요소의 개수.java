import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 11724번 연결 요소의 개수 [아이디어] 방문 체크하여 BFS 사용
 * 
 * @author User 2023-10-22
 *
 */
public class Main {
	static int n, m, count = 0;
	static int arr[][];
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n + 1][n + 1];
		visited = new boolean[n + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());

			arr[num1][num2] = 1;
			arr[num2][num1] = 1;

		}
		

		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				bfs(i);
			}
		}

		System.out.println(count);
	}

	static void bfs(int i) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(i);
		visited[i] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (int k = 1; k <= n; k++) {
				if (!visited[k] && arr[current][k] == 1) {
					queue.add(k);
					visited[k] = true;
				}
			}

		}

		count++;
	}
}
