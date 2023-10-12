import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * [문제] SWEA 4013번 특이한 자석
 * [아이디어] 회전하기 전에 각각의 자석이 회전 가능한지 판별해야 하므로 각각의 자석의 회전 방향을 저장하는 배열을 만들어준다.
 * 선택된 자석을 기준으로 오른쪽, 왼쪽으로 돌아가면서 회전 방향을 체크하고 (0이면 회전하지 않음, 1이면 시계 방향, -1이면 반시계 방향)
 * 이 배열을 이용하여 회전시켜준다.
 * 
 * 메모리 : 19,688kb
 * 실행 시간 : 105ms
 * 
 * @author 김유나
 * 2023-10-11
 *
 */

public class Solution {
	static int magnet[][], arr[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스
		
		for (int t = 1; t <= tc; t++) {
			int k = Integer.parseInt(br.readLine()); // 회전할 횟수
			magnet = new int[4][8]; // 자석 저장할 배열
			
			// 입력 받기
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < k; i++) {
				arr = new int[4]; // 각 자석의 회전 방향 저장할 배열
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken()) - 1; // 회전할 자석 - 1
				int direct = Integer.parseInt(st.nextToken()); // 회전할 방향
				turn(num, direct); // 자석 돌리기 시작!
			}
			
			int score = 0; // 총 점수
			for (int i = 0; i < 4; i++) {
				if (magnet[i][0] == 1) {
					score += Math.pow(2, i); // 2의 i 제곱만큼 더한다.
				}
			}
			
			
			sb.append("#" + t + " " + score + "\n"); // 출력
			
		}
		System.out.println(sb);
	}
	
	static void turn(int num, int direct) {
		arr[num] = direct; // 선택된 자석 방향 지정
		
		// 선택된 자석 기준 오른쪽 탐색 -> 회전할 자석 있는지 확인
		for (int i = num + 1; i < 4; i++) {
			if (magnet[i][6] != magnet[i - 1][2]) { // 다를 경우
				arr[i] = -arr[i - 1]; // -1 곱해서 넣어주기
			} else break; // 같을 경우 break
		}
		
		// 선택된 자석 기준 왼쪽 탐색 -> 회전할 자석 있는지 확인
		for (int i = num - 1; i >= 0; i--) {
			if (magnet[i + 1][6] != magnet[i][2]) { // 다를 경우
				arr[i] = -arr[i + 1]; // -1 곱해서 넣어주기
			} else break; // 같을 경우 break
		}
		
		// 회전할 자석을 회전 시작!
		for (int i = 0; i < 4; i++) {
			if (arr[i] == 0) continue; // 0이면 회전 x
			
			// 시계 방향 : 맨 뒤의 값 맨 앞으로
			else if (arr[i] == 1) {
				int tmp = magnet[i][7];
				for (int j = 7; j > 0; j--) {
					magnet[i][j] = magnet[i][j - 1];
				}
				magnet[i][0] = tmp;
			}
			
			// 반시계 방향 : 맨 앞의 값 맨 뒤로
			else if (arr[i] == -1) {
				int tmp = magnet[i][0];
				for (int j = 0; j < 7; j++) {
					magnet[i][j] = magnet[i][j + 1];
				}
				magnet[i][7] = tmp;
			}
		}
	}
}