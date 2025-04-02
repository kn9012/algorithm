import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 1285번 동전 뒤집기
 * 1. N개의 각 행을 뒤집을지 안 뒤집을지 결정
 * 2. 각 경우에 따라 각 열이 뒷면이 더 많을 경우 뒤집기
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] coins = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split("");
			
			for (int j = 0; j < N; j++) {
				if (str[j].equals("H")) coins[i][j] = 1;
				else coins[i][j] = 0;
			} 
		}
		
		int answer = Integer.MAX_VALUE;
		
		// 1<<N = Math.pow(2, N) = 2의 N제곱
		for (int bit = 1; bit < (1 << N); bit++) {
			int sum = 0;
			
			for (int col = 0; col < N; col++) {
				int back = 0;
				
				for (int i = 0; i < N; i++) {
					int cur = coins[i][col];
					
					if ((bit & (1 << i)) != 0) {
						cur ^= 1;
					}
					
					if (cur == 0) back++;
					
				}
				sum += Math.min(back, N - back);
			}
			answer = Math.min(answer, sum);
			
		}
		
		System.out.println(answer);
	}
}