import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제] 백준 3055번 탈출 [아이디어] BFS
 * 
 * 실행 시간 : ms 메모리 : kb
 * 
 * @author 김유나 2023-11-14
 *
 */
public class Main {
	static String map[][];
	static int R, C, min, deltas[][] = { { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 0 } };
	static Queue<int[]> queue;
	static Queue<int[]> water;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new String[R][C];
		queue = new ArrayDeque<>();
		water = new ArrayDeque<>();

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j) + "";

				if (map[i][j].equals("S"))
					queue.add(new int[] { i, j, 0 });
				else if (map[i][j].equals("*"))
					water.add(new int[] { i, j, 0 });
			}
		}

		min = Integer.MAX_VALUE;
		bfs();

		System.out.println(min == Integer.MAX_VALUE ? "KAKTUS" : min);
	}

	static void bfs() {
		while (!queue.isEmpty()) {
			int len = water.size();
			for (int i = 0; i < len; i++) {
				int waters[] = water.poll();
				for (int j = 0; j < 4; j++) {
					int wx = waters[0] + deltas[j][0];
					int wy = waters[1] + deltas[j][1];

					if (wx >= 0 && wy >= 0 && wx < R && wy < C) {
						if (map[wx][wy].equals(".")) {
							map[wx][wy] = "*";
							water.add(new int[] { wx, wy, 0 });
						}
					}
				}
			}

			len = queue.size();
			for (int i = 0; i < len; i++) {
				int cur[] = queue.poll();
				for (int j = 0; j < 4; j++) {
					int dx = cur[0] + deltas[j][0];
					int dy = cur[1] + deltas[j][1];

					if (dx >= 0 && dy >= 0 && dx < R && dy < C) {
						if (map[dx][dy].equals("D")) {
							min = Math.min(min, cur[2] + 1);
							return;
						}
						else if (map[dx][dy].equals(".")) {
							map[dx][dy] = "S";
							queue.add(new int[] { dx, dy, cur[2] + 1 });
						}
					}
				}
			}
			
		}
	}
}