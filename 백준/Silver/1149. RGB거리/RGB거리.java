import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 1149 RGB거리
 * RGB거리에는 집이 N개 있다. 1번 집부터 N번 집까지 순서대로 있을 때 집을 빨강, 초록, 파랑 중 하나의 색으로 칠해야 된다.
 * 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 다음과 같은 규칙을 만족하면서 최소 비용을 구하여라.
 * 1. 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
 * 2. N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
 * 3. i번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
 * 
 * [아이디어]
 * 만약 i번째 집(i는 0부터 n)을 빨강으로 골랐다면 i-1번째 집은 초록, 파랑 중에 최소, 초록으로 골랐다면 i-1번째 집은 파랑, 빨강 중에 최소...와 같이 풀이하면 된다.
 * 이때, 3 이상이 되는 것을 방지하기 위해 % 3을 해준다.
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 * 
 * @author 김유나
 * 2023-08-29
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine()); // 집의 개수
		int house[][] = new int[n][3]; // 각 집을 칠하는 비용 (1~N번)
		
		// 입력 받기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// i번째 집이 어느 색일 경우 다른 두 색 중 최소 비용을 구하여 더하는 것을 반복하면 됨
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				house[i][j] += Math.min(house[i - 1][(j + 1) % 3],  house[i - 1][(j + 2) % 3]);
			}
		}
		
		// 빨강, 초록, 파랑 중 최소값 출력 (3개 값 비교)
		System.out.println(Math.min(house[n - 1][0], Math.min(house[n - 1][1], house[n - 1][2])));
		
	}
}