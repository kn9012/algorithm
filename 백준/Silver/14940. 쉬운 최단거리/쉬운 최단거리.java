import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr, distance;
	static int n, m;
	static int deltas[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] isVisited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		distance = new int[n][m];
		
		int goalX = 0;
		int goalY = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] == 2) {
					goalX = i;
					goalY = j;
				}
			}
		}
		
		isVisited = new boolean[n][m];
		bfs(new int[] {goalX, goalY});
		
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (distance[i][j] == 0 && arr[i][j] == 1) distance[i][j] = -1;
				if (arr[i][j] == 2) distance[i][j] = 0;
				System.out.print(distance[i][j] + " ");
			} System.out.println();
		}
	}
	
	public static void bfs(int[] point) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {point[0], point[1]});
		
		while (!queue.isEmpty()) {
			int cur[] = queue.poll();
			int dx = cur[0];
			int dy = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = dx + deltas[i][0];
				int ny = dy + deltas[i][1];
				
				if (nx >= n || nx < 0 || ny >= m || ny < 0 || isVisited[nx][ny] || arr[nx][ny] == 0) continue;
				
				queue.add(new int[] {nx, ny});
				distance[nx][ny] = distance[dx][dy] + 1;
				isVisited[nx][ny] = true;
			}
		}
	}
}
