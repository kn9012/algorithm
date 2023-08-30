import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 11048번 이동하기
 * 준규는 NxM 크기의 미로를 (1, 1)에서 출발하여 (N, M)로 이동하려고 할때 준규가 (r, c)에 있으면 (r+1, c), (r, c+1), (r+1, c+1)로 이동할 수 있고 각 방에 놓여있는 사탕을 가져갈 수 있다.
 * 이때, 준규가 가져올 수 있는 사탕의 최댓값을 구하시오.
 * 
 * [아이디어]
 * 
 * 메모리 : 72,832kb
 * 실행 시간 : 568ms
 * 
 * @author 김유나
 * 2023-08-30
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 행 크기
		int m = Integer.parseInt(st.nextToken()); // 열 크기
		
		int arr[][] = new int[n + 1][m + 1]; // 미로와 그 안에 놓인 사탕 개수
		
		// 입력 받기
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				arr[i][j] += Math.max(arr[i - 1][j], Math.max(arr[i - 1][j - 1], arr[i][j - 1]));								
			}
		}
		
		
		System.out.println(arr[n][m]);
	}
}