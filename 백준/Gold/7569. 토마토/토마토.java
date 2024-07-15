import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 7569번 토마토
 * 
 * 3차원 배열과 BFS 사용
 * 이때, 익어있는 토마토를 ArrayList에 넣고 동시에 진행되도록 함
 * 
 * 1 : 익은 토마토
 * 0 : 익지 않은 토마토
 * -1 : 토마토가 들어있지 않은 칸
 * 모든 토마토가 익기까지 며칠이 걸리는지 출력
 * 이때, 모든 토마토가 익어 있으면 0, 모두 익지 못하면 -1
 */

public class Main {
	static class Point {
		int m, n, h, count;
		
		public Point (int m, int n, int h, int count) {
			this.m = m;
			this.n = n;
			this.h = h;
			this.count = count;
		}
	}
	
	static int N, M, H, box[][][], answer = 0, notRipeCount = 0;
	static boolean isSelected[][][];
	static int deltas[][] = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
	static ArrayList<Point> tomato = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 상자의 가로 칸 수
		M = Integer.parseInt(st.nextToken()); // 상자의 세로 칸 수
		H = Integer.parseInt(st.nextToken()); // 상자의 수
		
		box = new int[M][N][H];
		isSelected = new boolean[M][N][H];
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					box[j][k][i] = Integer.parseInt(st.nextToken());
					
					if (box[j][k][i] == 1) tomato.add(new Point(j, k, i, 0));
					else if (box[j][k][i] == 0) notRipeCount++;
				}
			}
		}
		
		if (notRipeCount == 0) {
			System.out.println(0);
			return;
		}
		
		bfs();
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < N; k++) {
					if (!isSelected[j][k][i] && box[j][k][i] == 0) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
	public static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		
		for (Point point : tomato) {
			queue.add(point);
			isSelected[point.m][point.n][point.h] = true;
		}
		
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			
			int X = cur.m;
			int Y = cur.n;
			int Z = cur.h;
			int count = cur.count;
			answer = count;
			
			
			for (int i = 0; i < 6; i++) {
				int dx = X + deltas[i][0];
				int dy = Y + deltas[i][1];
				int dz = Z + deltas[i][2];
				
				if (dx >= M || dy >= N || dz >= H || dx < 0 || dy < 0 || dz < 0 || isSelected[dx][dy][dz] || box[dx][dy][dz] != 0) continue;
				
				queue.add(new Point(dx, dy, dz, count + 1));
				isSelected[dx][dy][dz] = true;
			}
		}
	}
}
