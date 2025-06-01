import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 10836번 여왕벌
 * 
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken()); // 한 변의 크기
		int N = Integer.parseInt(st.nextToken()); // 날짜
		
		int[][] honeycomb = new int[M][M];
		for (int i = 0; i < M; i++) Arrays.fill(honeycomb[i], 1);
				
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			int x = M - 1;
			int y = 0;
			
			for (int j = 0; j < zero; j++) {
				if (x > 0) x--;
				else y++;
			}
			
			for (int j = 0; j < one; j++) {
				if (x > 0) {
					honeycomb[x][y] += 1;
					x--;
				} else {
					honeycomb[x][y] += 1;
					
					if (y > 0) {
						for (int k = 1; k < M; k++) honeycomb[x + k][y] += 1;
					}
					
					y++;
				}
			}
			
			for (int j = 0; j < two; j++) {
				if (x > 0) {
					honeycomb[x][y] += 2;
					x--;
				} else {
					honeycomb[x][y] += 2;
					
					if (y > 0) {
						for (int k = 1; k < M; k++) honeycomb[x + k][y] += 2;
					}
					
					y++;
				}
			}
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(honeycomb[i][j] + " ");
			} System.out.println();
		}
	}
}
