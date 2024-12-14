import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1012번 유기농 배추
 * - DFS 
 */
public class Main {
	static int count, arr[][], M, N;
	static int deltas[][] = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
	static boolean isVisit[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			count = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			arr = new int[M][N];
			isVisit = new boolean[M][N];
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				
				arr[X][Y] = 1;
			}
			
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 1 && !isVisit[i][j]) {
						count++;
						isVisit[i][j] = true;
						dfs(i, j);
					}
				}
			}
			
			System.out.println(count);
		}
	}
	
	public static void dfs(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int X = x + deltas[i][0];
			int Y = y + deltas[i][1];
			
			if (X < 0 || Y < 0 || X >= M || Y >= N || arr[X][Y] == 0 || isVisit[X][Y]) continue;
			
			isVisit[X][Y] = true;
			dfs(X, Y);
		}
	}
}
