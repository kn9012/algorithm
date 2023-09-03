import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 11660번 구간 합 구하기 5
 * [아이디어] 2차원 배열의 누적합을 구하고 (x2, y2)의 누적합에서 (x2, y1-1), (x1-1, y2)를 빼고 (x1-1, y1-1)를 더해준다.
 * 
 * 2차원 배열에서 누적합 구하는 법
 * - prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + arr[i][j]
 * 
 * 메모리 : 124,812kb
 * 실행 시간 : 1,752ms
 * 
 * @author 김유나
 * 2023-09-02
 *
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken()); // 표의 크기
		int m = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수
		
		int arr[][] = new int[n + 1][n + 1]; // nXn 크기의 배열
		
		// 표 입력 받기 및 누적합 구하기
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				arr[i][j] += arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1]; // 누적합 구하는 공식
			}
		}
		
		// 이동할 횟수만큼 좌표 입력 받기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()); // x1 좌표
			int y1 = Integer.parseInt(st.nextToken()); // y1 좌표
			int x2 = Integer.parseInt(st.nextToken()); // x2 좌표
			int y2 = Integer.parseInt(st.nextToken()); // y2 좌표
			
			// (x2, y2)에서 (x2, y1-1), (x1-1, y2)를 빼주고 (x1-1, y1-1), 즉 겹치는 부분을 더해준다.
			sb.append(arr[x2][y2] - arr[x2][y1 - 1] - arr[x1 - 1][y2] + arr[x1 - 1][y1 - 1] + "\n");	
		}
		
		System.out.println(sb);
	}
}
