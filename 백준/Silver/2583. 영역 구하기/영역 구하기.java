import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int x, y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static boolean isVisited[][];
	static int arr[][], area[], count = 0, M, N, deltas[][] = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
	static ArrayList<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int num = Integer.parseInt(st.nextToken());
		
		arr = new int[M][N];
		isVisited = new boolean[M][N];
		area = new int[100];
		
		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			
			int lx = Integer.parseInt(st.nextToken());
			int ly = Integer.parseInt(st.nextToken());
			int rx = Integer.parseInt(st.nextToken());
			int ry = Integer.parseInt(st.nextToken());
			
			
			for (int k = lx; k < rx; k++) {
				for (int j = ly; j < ry; j++) {
					arr[j][k] = 1;
				}
			}
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0 && !isVisited[i][j]) {
					list.add(bfs(i, j));
					count++;
				}
			}
		}
		
		Collections.sort(list);
		
		sb.append(count + "\n");
		for (int i = 0 ; i < list.size(); i++) {
			sb.append(list.get(i) + " ");
		}
		
		System.out.println(sb);
	}
	
	
	static int bfs(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(x, y));
		isVisited[x][y] = true;
		int cnt = 1;
	
		while (!q.isEmpty()) {
			Point cur = q.poll();
			int X = cur.x;
			int Y = cur.y;
			
			for (int i = 0; i < 4; i++) {
				int dx = X + deltas[i][0];
				int dy = Y + deltas[i][1];
				
				if (dx >= 0 && dy >= 0 && dx < M && dy < N) {
					if (!isVisited[dx][dy] && arr[dx][dy] == 0) {
						q.add(new Point(dx, dy));
						isVisited[dx][dy] = true;
						cnt++;
					}
				}
			}
		}
		
		return cnt;
	}
}
