import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int M, N, H, map[][][];
	// 앞, 뒤, 왼쪽, 오른쪽, 앞, 뒤
	static int X[] = {-1, 0, 1, 0, 0, 0}, Y[] = {0, 1, 0, -1, 0, 0}, Z[] = {0, 0, 0, 0, 1, -1};
	static Queue<Point> queue;

	static class Point {
		int x, y, z;

		public Point(int z, int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}		
	}

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		M = s.nextInt();
		N = s.nextInt();
		H = s.nextInt();

		map = new int[H][N][M];
		queue = new ArrayDeque<>();
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					map[i][j][k] = s.nextInt();
					
					if (map[i][j][k] == 1) {
						queue.add(new Point(i, j, k));
					}
				}
			}
		}

		System.out.println(bfs());
	}

	static int bfs() {
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			// 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
			for (int i = 0; i < 6; i++) {
				int dx = cur.x + X[i];
				int dy = cur.y + Y[i];
				int dz = cur.z + Z[i];
				
				if (dx >= 0 && dy >= 0 && dz >= 0 && dx < N && dy < M && dz < H) {
					if (map[dz][dx][dy] == 0) {
						queue.add(new Point(dz, dx, dy));
						map[dz][dx][dy] = map[cur.z][cur.x][cur.y] + 1;
					}
				}				
			}
		}
		
		int result = Integer.MIN_VALUE;
		
		// while문이 종료했는데 익지 않은 토마토가 있다면 
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (map[i][j][k] == 0) return -1;
					
					result = Math.max(result, map[i][j][k]);
				}
			}
		}
		
		if (result == 1) return 0;
		else return result - 1;
	}
}