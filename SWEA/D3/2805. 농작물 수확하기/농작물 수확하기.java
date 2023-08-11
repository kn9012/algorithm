import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 김유나
 * 2023-08-11
 * [문제] SWEA 2805번 농작물 수확하기
 * - 크기가 항상 홀수인 농장에서 수확은 항상 농자의 크기에 딱 맞는 정사각형 마름모 형태로만 가능할 때, 이때 얻을 수 있는 수익을 구하여라.
 * [아이디어]
 * - 마름모에서 위 삼각형, 가운데 줄, 아래 삼각형으로 구역을 나누어 각각의 수익을 구하여 더한다.
 *
 * 메모리 : 23,312kb 실행 시간 : 140ms
 */
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스
		StringTokenizer st;
		
		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine()); // 농장 크기
			int [][] arr = new int[n][n]; // 농장 배열
			int sum = 0; // 수익 합
			
			// 수익 배열 입력 받기
			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(str.substring(j, j + 1));
				}
			}
			
			// 위 삼각형
			for (int i = 0; i < n / 2; i++) {
				for (int j = n / 2 - i; j < n / 2 + 1 + i; j++) {
					sum += arr[i][j];
				}
			}
			
			
			// 가운데 줄
			for (int i = 0; i < n; i++) {
				sum += arr[n / 2][i];
			}
			
			
			// 아래 삼각형
			for (int i = n / 2 + 1; i < n; i++) {
				for (int j = i - n / 2; j < 3 * n / 2 - i; j++) {
					sum += arr[i][j];
				}
			}
			
			
			System.out.println("#" + tc + " " + sum);
		}
	}
}
