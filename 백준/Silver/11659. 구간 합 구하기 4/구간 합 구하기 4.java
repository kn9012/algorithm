
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 김유나
 * [문제] 백준 11659번 구간 합 구하기 4
 * [아이디어]
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int n;

		int sum[] = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N ; i++) {
			n = Integer.parseInt(st.nextToken());
			sum[i] = sum[i - 1] + n;
		}
		
		int start[] = new int[M];
		int end[] = new int[M];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			start[i] = Integer.parseInt(st.nextToken());
			end[i] = Integer.parseInt(st.nextToken());
			sb.append(sum[end[i]] - sum[start[i] - 1]);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
