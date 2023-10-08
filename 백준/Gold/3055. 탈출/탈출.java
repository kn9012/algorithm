import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 3055번 탈출 R행 C열의 티떱숲에서 고슴도치가 비버의 굴로 안전하게 이동하기 위한 가장 빠른 시간을 구해라. . : 비어
 * 있는 곳 * : 물이 차 있는 지역 X : 돌 D : 비버의 굴 S : 고슴도치 위치
 * 
 * 고슴도치와 물은 매 분마다 사방탐색을 함 물과 고슴도치는 돌을 통과할 수 없으며 고슴도치는 물이 차 있는 구역, 물이 찰 구역으로 이동할
 * 수 없음 물도 비버의 소굴로 이동할 수 없음
 * 
 * [아이디어] 최단시간 -> BFS 사용 물을 먼저 확산시켜 map을 바꾼 뒤 고슴도치의 이동시 BFS 사용
 * 
 * 메모리 : kb 실행 시간 : ms
 * 
 * @author 김유나 2023-10-08
 *
 */

public class Main {
	static class Point {
		int x;
		int y;
		int count;

		public Point(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}

	static int r, c, time = Integer.MAX_VALUE;
	static int deltas[][] = { { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 0 } };
	static String map[][];
	static Queue<Point> queue = new LinkedList<>();
	static Queue<Point> water = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new String[r][c];

		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i] = str.split("");
				if (map[i][j].equals("S")) {
					queue.add(new Point(i, j, 0));
				} else if (map[i][j].equals("*")) {
					water.add(new Point(i, j, 0));

				}
			}
		}

		move();

		System.out.println(time == Integer.MAX_VALUE ? "KAKTUS" : time);
	}

	static void move() {
		while (!queue.isEmpty()) {
			int len = water.size();
			for (int i = 0; i < len; i++) {
				Point w = water.poll();

				for (int k = 0; k < 4; k++) {
					int dx = w.x + deltas[k][0];
					int dy = w.y + deltas[k][1];

					if (dx < 0 || dy < 0 || dx >= r || dy >= c)
						continue;

					if (map[dx][dy].equals(".")) {
						map[dx][dy] = "*";
						water.add(new Point(dx, dy, 0));
					}

				}
			}

			len = queue.size();

			for (int q = 0; q < len; q++) {
				Point cur = queue.poll();

				for (int i = 0; i < 4; i++) {
					int dx = cur.x + deltas[i][0];
					int dy = cur.y + deltas[i][1];

					if (dx < 0 || dy < 0 || dx >= r || dy >= c)
						continue;

					if (map[dx][dy].equals("D")) {
						time = Math.min(time, cur.count + 1);
						return;
					}

					else if (map[dx][dy].equals(".")) {
						map[dx][dy] = "S";
						queue.add(new Point(dx, dy, cur.count + 1));
					}
				}
			}
		}
	}
}