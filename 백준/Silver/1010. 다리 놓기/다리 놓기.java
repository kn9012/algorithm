
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 1010번 다리 놓기
 * 
 * [아이디어] 
 * 
 * 
 * 메모리 : 15,284kb
 * 실행 시간 : 164ms
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
			int west = Integer.parseInt(st.nextToken()); // 동쪽 사이트 개수
			int east = Integer.parseInt(st.nextToken()); // 서쪽 사이트 개수
			
			int B[][] = new int[east + 1][west + 1]; // 
			
			for (int i = 0; i <= east; i++) {
				for (int j = 0, end = Math.min(i, west); j <= end; j++) {
					if (j == 0 || i == j) B[i][j] = 1;
					else B[i][j] = B[i - 1][j - 1] + B[i - 1][j];
				}
			}
			
			System.out.println(B[east][west]);
		}
	}
}
