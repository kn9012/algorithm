import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 14940번 쉬운 최단거리
 * 
 * BFS로 최단거리를 구하면 되는데 1인 좌표에서 모든 최단거리를 각각 구해서 계속 메모리 초과가 났다
 * 그냥 2인 지점에서 시작해서 모든 좌표를 돌면서 2에서부터의 거리를 구해주면 된다
 * 
 * 메모리 : kb 시간 : ms
 */

public class Main {
	static int deltas[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[n][m];
		int[][] distance = new int[n][m];
		
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] == 2) {
					queue.add(new int[] {i, j});
					isVisited[i][j] = true;
				}
			}
		}
		
		while (!queue.isEmpty()) {
			int cur[] = queue.poll();
			int X = cur[0];
			int Y = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int dx = X + deltas[i][0];
				int dy = Y + deltas[i][1];
				
				if (dx >= n || dx < 0 || dy >= m || dy < 0 || isVisited[dx][dy] || arr[dx][dy] == 0) continue;
				
				queue.add(new int[] {dx, dy});
				distance[dx][dy] = distance[X][Y] + 1;
				isVisited[dx][dy] = true;
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (distance[i][j] == 0 && arr[i][j] == 1) distance[i][j] = -1;
				//if (arr[i][j] == 2) distance = 0;
				sb.append(distance[i][j] + " ");
			} sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
