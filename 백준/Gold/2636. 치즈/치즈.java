import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2636번 치즈
 * 
 */

public class Main {
	static int h, w, count = 0;
	static int[][] map, deltas = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	static boolean[][] visited;
	static List<int []> edge;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		
		map = new int[h][w];
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int hour = 0;
		int cheeseCnt = 0;
		
		while (true) {
			edge = new ArrayList<>();			
			findAir();
			
			if (edge.size() == 0) break;
			
			cheeseCnt = edge.size();
			hour++;
			
			for (int[] cheese : edge) {
				map[cheese[0]][cheese[1]] = 0;
			}
		}
		
		System.out.println(hour + " \n" + cheeseCnt);
	}
	
	public static void findAir() {
		visited = new boolean[h][w];
		Queue<int []> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0});
		visited[0][0] = true;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int X = cur[0];
			int Y = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int dx = X + deltas[i][0];
				int dy = Y + deltas[i][1];
				
				if (dx < 0 || dy < 0 || dx >= h || dy >= w || visited[dx][dy]) continue;
				
				if (map[dx][dy] == 0) {
					queue.offer(new int[] {dx, dy});
					visited[dx][dy] = true;
				} else {
					edge.add(new int[] {dx, dy});
					visited[dx][dy] = true;
				}
			}
		}
	}
}
