import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * [문제] 백준 2239번 스도쿠
 * [아이디어]
 * 가로, 세로, 정사각형에서 같은 수가 있는지 체크해줘야 하므로 boolean 배열 3개를 선언해준다.
 * 1. row[i][j] : i번째 row에 j가 있는지
 * 2. col[i][j] : i번째 col에 j가 있는지
 * 3. square[i][j] : i번째 사각형에 j가 있는지
 * 입력을 받으며 체크해주고 몇번째 사각형인지 구하는 법은 (x/3)*3 + (y/3) + 1
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 * 
 * @author 김유나
 * 2023-09-26
 *
 */
public class Main {
	static boolean[][] row, col, square;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		row = new boolean[9][10];
		col = new boolean[9][10];
		square = new boolean[9][10];
		arr = new int[9][9];
		
		for (int i = 0; i < 9; i++) {
			String str = br.readLine();
			for (int j = 0; j < 9; j++) {
				arr[i][j] = str.charAt(j) -'0';
				if (arr[i][j] != 0) {
					row[i][arr[i][j]] = true;
					col[j][arr[i][j]] = true;
					square[(i / 3) * 3 + (j / 3)][arr[i][j]] = true;
				}
			}
		}
		
		dfs(0);
	}
	
	public static void dfs(int count) {
		int x = count / 9;
		int y = count % 9;
		
		if (count == 81) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(arr[i][j] + "");
				} sb.append("\n");
			}
			System.out.println(sb.toString());
			System.exit(0);
		}
		
		if (arr[x][y] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (!row[x][i] && !col[y][i] && !square[(x / 3) * 3 + (y / 3)][i]) {
					row[x][i] = true;
					col[y][i] = true;
					square[(x / 3) * 3 + (y / 3)][i] = true;
					arr[x][y] = i;
					dfs(count + 1);
					arr[x][y] = 0;
					row[x][i] = false;
					col[y][i] = false;
					square[(x / 3) * 3 + (y / 3)][i] = false;
				}
			}
			
		}
		
		else dfs(count + 1);
	}
}
