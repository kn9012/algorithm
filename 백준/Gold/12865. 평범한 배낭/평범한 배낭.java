import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 12865번 평범한 배낭
 * N개의 물건이 있고 무게 W와 가치 V를 가짐
 * 배낭에 최대 K 무게까지 넣을 수 있을 때 가치합의 최댓값 구하기
 * 
 * - dp 사용
 * 배낭에 넣을 수 있는 최대 무게와 물건의 번호가 인덱스인 2차원 dp 배열  사용 : 가치합 저장
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] product = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			product[i][0] = Integer.parseInt(st.nextToken());
			product[i][1] = Integer.parseInt(st.nextToken());
		}
	
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (j >= product[i][0]) dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - product[i][0]] + product[i][1]);
				else dp[i][j] = dp[i - 1][j];
			}
		}
		
		System.out.println(dp[N][K]);
	}
}