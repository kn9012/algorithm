
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 김유나
 * 2023-08-02
 * [문제] 백준 15650번 N과 M(3)
 * - 1부터 N까지 자연수 중에서  M개를 고른 수열을 구하는 프로그램을 작성해라. -> 중복 가능!
 * [아이디어] 
 * - 같은 수를 여러 번 골라도 되므로  중복조합이다. 재귀 함수를 이용한 중복 없는 조합을 구현하면 된다. 
 */

public class Main {
	static int numbers[]; // 수열을 저장할 배열
	static int N, M;
	static StringBuilder sb = new StringBuilder(); // StringBuilder 사용
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // N 입력 받기
		M = Integer.parseInt(st.nextToken()); // M 입력 받기
	
		numbers = new int[M]; // M 길이의 numbers 배열 생성
		
		comb(0, 1); // cnt : 0, start : 1
		System.out.println(sb); // StringBuilder 출력
	}
	static void comb(int cnt, int start) {
		if (cnt == M) { // if cnt가 M의 크기에 도달했을때
			for (int i = 0; i < M; i++) {
				sb.append(numbers[i]); // 출력 위해 StringBuilder에 append
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			numbers[cnt] = i; // 수열의 첫 자리에 1부터 N까지 넣기
			comb(cnt + 1, start); // cnt에만 +1 해주고 start는 그대로(중복이 가능하므로)
		}
	}
}