import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 김유나
 * 2023-08-02
 * [문제] 백준 15650번 N과 M(2)
 * - 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열을 구하는 프로그램을 작성해라.
 * [아이디어] 
 * - 중복 없이 M개를 고른 수열은 중복이 아닌 순열이다. 재귀 함수를 이용한 중복 순열을 구현하면 된다. 
 */

public class Main {
	static int numbers[];
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		numbers = new int[M];
		
		comb(0, 1);
		System.out.println(sb);
	}
	static void comb(int cnt, int start) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(numbers[i]);
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			numbers[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}
}
