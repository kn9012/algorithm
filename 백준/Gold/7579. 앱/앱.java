import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] memory = new int[N + 1][2];
		
		// 차지하는 메모리 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			memory[i][0] = Integer.parseInt(st.nextToken());
		}
		
		int costSum = 0;
		
		// 비용 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			memory[i][1] = Integer.parseInt(st.nextToken());
			costSum += memory[i][1];
		}
		
		int[][] dp = new int[N + 1][costSum + 1];
		
		int answer = Integer.MAX_VALUE;
		
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= costSum; j++) {
				if (i == 0) {
					if (j >= memory[i][1]) dp[i][j] = memory[i][0];
				} else {
					if (j >= memory[i][1]) dp[i][j] = Math.max(dp[i - 1][j - memory[i][1]] + memory[i][0], dp[i - 1][j]);
					else dp[i][j] = dp[i - 1][j];
				}
				
				
				if (dp[i][j] >= M) answer = Math.min(answer, j);
			}
		}
		
		System.out.println(answer);
	}
}
