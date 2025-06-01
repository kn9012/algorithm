import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 10836번 여왕벌
 * - 시뮬레이션 + 최적화
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken()); // 한 변의 크기
		int N = Integer.parseInt(st.nextToken()); // 날짜
		
		int[] growth = new int[2 * M - 1];
				
		for (int day = 0; day < N; day++) {
			st = new StringTokenizer(br.readLine());
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			int idx = 0;
            while (zero-- > 0) idx++;
            while (one-- > 0) growth[idx++] += 1;
            while (two-- > 0) growth[idx++] += 2;
		}
		
		int[][] honeycomb = new int[M][M];
		
        for (int i = 0; i < M; i++) {
            honeycomb[M - 1 - i][0] = 1 + growth[i];
        }

        for (int j = 1; j < M; j++) {
            honeycomb[0][j] = 1 + growth[M - 1 + j];
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < M; j++) {
                honeycomb[i][j] = honeycomb[i - 1][j];
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(honeycomb[i][j] + " ");
            } System.out.println();
        }
	}
}
