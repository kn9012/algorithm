import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 4485번 녹색 옷 입은 애가 젤다지?
 * [아이디어] 우선순위큐?
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 * 
 * @author SSAFY
 * 2023-10-05
 *
 */
public class Main {
	static int map[][], arr[][], n, min;
	static boolean visited[][];
	
	static int deltas[][] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

	public static class Point implements Comparable<Point>{
		int x;
		int y;
		int sum;
		
		public Point(int x, int y, int sum) {
			this.x = x;
			this.y = y;
			this.sum = sum;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.sum, o.sum);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = 0;
		
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0) break;
						
			min = Integer.MAX_VALUE;
			map = new int[n][n];
			arr = new int[n][n];
			visited = new boolean[n][n];
			
			for (int i = 0; i < n; i++) {
				Arrays.fill(arr[i], Integer.MAX_VALUE);
			}
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs();
			
			sb.append("Problem " + ++tc + ": " + arr[n-1][n-1] + "\n");
		}
		
		System.out.println(sb);
	}
	
	public static void bfs() {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		queue.add(new Point(0, 0, map[0][0]));
		arr[0][0] = map[0][0];
		
		int l = map.length;
		
		while (!queue.isEmpty()) {
			Point current = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int dx = current.x + deltas[i][0];
				int dy = current.y + deltas[i][1];
							
				if (dx < 0 || dy < 0 || dx >= l || dy >= l) continue;
				
				if (arr[current.x][current.y] + map[dx][dy] < arr[dx][dy]) {
					arr[dx][dy] = arr[current.x][current.y] + map[dx][dy];
					queue.add(new Point(dx, dy, arr[dx][dy]));
				}
			}
		}
	}
}