import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2146번 다리 만들기
 * - BFS로 각 덩어리 확인 후 조합으로 가장 짧은 거리 탐색?
 */

public class Main {
	public static int N;
	public static int[][] map, deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	public static boolean[][] visited;
	
	public static class Point {
		int x, y, dist, islandId;
		
		Point(int x, int y, int dist, int islandId) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.islandId = islandId;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N][N];
		
		int count = 2;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					labelIsland(i, j, count++);
				}
			}
		}
		
		int result = buildBridge(count);
		System.out.println(result);
	}
	
	public static void labelIsland(int x, int y, int id) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {x, y});
		visited[x][y] = true;
		map[x][y] = id;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int X = cur[0] + deltas[i][0];
				int Y = cur[1] + deltas[i][1];
				
				if (X < 0 || Y < 0 || X >= N || Y >= N || visited[X][Y] || map[X][Y] == 0) continue;
				
				queue.offer(new int [] {X, Y});
				visited[X][Y] = true;
				map[X][Y] = id;
			}
		}
	}
	
	public static int buildBridge(int islandCount) {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][N][islandCount];

        // 모든 섬의 육지에서 BFS 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 1) {
                    q.offer(new Point(i, j, 0, map[i][j]));
                    visited[i][j][map[i][j]] = true;
                }
            }
        }

        int min = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int[] d : deltas) {
                int nx = cur.x + d[0];
                int ny = cur.y + d[1];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    // 바다이면 계속 확장
                    if (map[nx][ny] == 0 && !visited[nx][ny][cur.islandId]) {
                        visited[nx][ny][cur.islandId] = true;
                        q.offer(new Point(nx, ny, cur.dist + 1, cur.islandId));
                    }
                    // 다른 섬을 만나면 다리 길이 계산
                    else if (map[nx][ny] != 0 && map[nx][ny] != cur.islandId) {
                        min = Math.min(min, cur.dist);
                    }
                }
            }
        }

        return min;
    }
}
