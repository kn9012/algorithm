import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author 김유나
 * 2023-08-08
 * [문제] SWEA 9229번 한빈이와 Spot Mart
 * - 최대한 양이 많은, 그러나 M 그램을 초과하면 안되도록 과자 두 봉지를 사는 프로그램을 구현하라.
 * [아이디어]
 * - 과자 무게 배열을 오름차순으로 정렬하여 투포인터를 사용하여 한빈이가 들 수 있는, 가장 큰 값을 구한다.
 */
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine()); // 테스트 케이스
		
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 과자 봉지 개수
			int M = Integer.parseInt(st.nextToken()); // 무게 합 제한
			int arr[] = new int[N]; // 과자 봉지 무게 배열
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 투포인터 사용
			int start = 0; // 시작 포인터
			int end = N - 1; // 끝 포인터
			int max = -1; // 방법이 없는 경우 -1 출력 위해
			
			Arrays.sort(arr); // 과자 무게 배열 오름차순 정렬
			
			while (start != end) { // start와 end가 같지 않을때까지 : start는 가장 앞, end는 가장 뒤에서부터 오므로
				if (arr[start] + arr[end] > M) { // 무게 합 제한보다 클 경우
					end--; // end를 하나씩 앞으로
				}
				else { // 무게 합 제한보다 작거나 같을 경우
					max = Math.max(max, arr[start] + arr[end]); // max와 비교
					start++; // start를 하나씩 앞으로
				}
			}
			
			System.out.println("#" + t + " " + max);
		}
	}
}