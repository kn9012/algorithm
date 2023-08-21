import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int n, complex = 0, cnt;
	static int arr[][];
	static boolean isVisited[][];
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		isVisited = new boolean[n][n];
		ArrayList<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!isVisited[i][j] && arr[i][j] == 1) {
					cnt = 0;
					dfs(i, j);
					complex++;
					list.add(cnt);
				}
			}
		}
		
		Collections.sort(list);
		System.out.println(complex);
		for (int i = 0; i < complex; i++) System.out.println(list.get(i));
	}
	
	static void dfs(int x, int y) {
		isVisited[x][y] = true;
		int current = arr[x][y];
		cnt++;
		
		for (int i = 0; i < 4; i++) {
			int dx = x + deltas[i][0];
			int dy = y + deltas[i][1];
			
			if (dx < 0 || dy < 0 || dx >= n || dy >= n) continue;

			if (!isVisited[dx][dy] && arr[dx][dy] == current) {
				dfs(dx, dy);
			}
		}
	}
}