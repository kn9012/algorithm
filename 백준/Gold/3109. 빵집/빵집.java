import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제] 백준 3109번 빵집
 * [아이디어] 첫째 열부터 마지막 열로만 이동이 가능하며 건물에는 파이프를 놓을 수 없음
 * 오른쪽, 오른쪽 위, 오른쪽 아래만 가능
 * 
 * 실행 시간 : ms
 * 메모리 : kb
 * 
 * @author 김유나
 * 2023-11-16
 *
 */

public class Main {
	static String map[][];
	static int R, C, result, deltas[][] = {{-1, 1}, {0, 1}, {1, 1}};
	static boolean visited[][], flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new String[R][C];
		visited = new boolean[R][C];
		result = 0;
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j) + "";
			}
		}
		
		for (int i = 0; i < R; i++) {
			flag = false;
			dfs(i, 0);
		}
		
		System.out.println(result);
	}
	static void dfs(int x, int y) {
		if (y == C - 1) {
			result++;
			flag = true;
			visited[x][y] = true;
			return;
		}
		
	
		for (int i = 0; i < 3; i++) {
			int dx = x + deltas[i][0];
			int dy = y + deltas[i][1];
			
			if (dx < 0 || dy < 0 || dx >= R || dy >= C || visited[dx][dy] || map[dx][dy].equals("x")) continue;
			
			dfs(dx, dy);
				
			if (flag) {
				visited[x][y] = true;
				return;
			}
			else visited[x][y] = true;
		}
	}
}