import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author 김유나 2023-08-18
 * 
 *         [문제] 백준 2178번 미로 탐색 [아이디어] 그래프의 BFS(너비 우선 탐색)을 이용한 풀이 Queue에 인접 정점들을
 *         체크하며 대기열에 넣고 가장 앞의 값을 Queue에서 빼면 또 인접값을 체크하여 넣어준다. 이때, 넣는 값들은 0이 아니여야
 *         하며 이미 넣은 값이 아니여야 한다. (중복 제거)
 *
 *         메모리 : kb 실행 시간 : ms
 *
 */
public class Main {
	static int[][] arr;
	static int n, m, count = 1, x = 0, y = 0;
	static int deltas[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // n 입력받기
		m = Integer.parseInt(st.nextToken()); // m 입력받기

		arr = new int[n][m]; // n X m 배열

		// charAt 사용하여 String -> char
		// '0'을 빼서 char -> int
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}

		bfs();
	}

	static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];

		// 탐색의 시작점 (0, 0)
		queue.offer(new int[] { x, y, count });
		// queue에 넣었음을 표시
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			int[] current = queue.poll(); // 가장 앞의 값 poll
//			System.out.println(Arrays.toString(current));
			
			for (int i = 0; i < 4; i++) {
				int dx = current[0] + deltas[i][0];
				int dy = current[1] + deltas[i][1];

				if (dx >= n || dy >= m || dx < 0 || dy < 0)
					continue;
				

				if (arr[dx][dy] != 0 && !visited[dx][dy]) {
							count = current[2] + 1;
							queue.offer(new int[] { dx, dy, count }); // queue에 넣기
							visited[dx][dy] = true; // queue에 넣었음 체크
				}
			}

			if (current[0] == n - 1 && current[1] == m - 1)
				System.out.println(current[2]);
		}
	}
}
