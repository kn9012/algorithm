import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author 김유나
 * 2023-08-16
 * 
 * [문제] 백준 1992번 쿼드트리
 * [아이디어]
 * 
 * 메모리 : kb 실행 시간 : ms
 *
 */
public class Main {
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		
		quadTree(0, 0, n);
		System.out.println(sb);
		
	}
	static void quadTree(int r, int c, int size) {
		if (isSame(r, c, size)) sb.append(arr[r][c]);
		
		else {
			int half = size / 2;
			sb.append("(");
			quadTree(r, c, half);
			quadTree(r, c + half, half);
			quadTree(r + half, c, half);
			quadTree(r + half, c + half, half);
			sb.append(")");
		}
	}
	
	static boolean isSame(int r, int c, int size) {
		if (size == 1) return true;
		else {
			int num = arr[r][c];
			
			for (int i = r; i < r + size; i++) {
				for (int j = c; j < c + size; j++) {
					if (arr[i][j] != num) return false;
				}
			}
			return true;
		}
		
	}
}
