import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 1010번 다리 놓기
 * 
 * [아이디어] 
 * 
 * 
 * 메모리 : kb
 * 실행 시간 : ms 
 * 
 * @author 김유나
 * 2023-08-30
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스
		
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			int east = Integer.parseInt(st.nextToken()); // 서쪽 사이트 개수
			int west = Integer.parseInt(st.nextToken()); // 동쪽 사이트 개수
			
			int B[][] = new int[west + 1][east + 1];
			
			for (int i = 0; i <= west; i++) {
				for (int j = 0, end = Math.min(i, east); j <= end; j++) {
					if (j == 0 || i == j) B[i][j] = 1;
					else B[i][j] = B[i - 1][j - 1] + B[i - 1][j];
				}
			}
			
			System.out.println(B[west][east]);
		}
	}
}