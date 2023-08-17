import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 김유나
 * 2023-08-17
 * 
 * [문제] SWEA 1247번 최적 경로
 * - 김대리의 회사와 집, 그리고 N명의 고객 집 좌표를 입력 받고 가장 짧은 경로의 이동거리를 구하라.
 * - 이때, 경로는 |x1-x2| + |y1-y2|로 계산되며 효율적으로 찾지 않아도 된다.
 * [아이디어]
 * - 가장 짧은 경로를 "호율적으로" 찾지 않아도 된다고 했으니 n명의 고객 집을 순열을 이용하여 나열한 뒤, 가장 짧은 경로를 찾았다.
 * - static 배열과 변수를 어디에 선언하느냐에 따라 답이 다르게 나와 생각보다 헤맸다.
 * 
 * 메모리 : 21,252kb 실행 시간 : 2,172ms
 * 
 */
public class Solution {
	static int[][] arr;
	static int n, minPath, sum, startX, startY, endX, endY;
	static boolean[] isSelected;
	static int[] order;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스
		
		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine()); // 고객의 수
			arr = new int[n][2]; // n x 2의 고객 집 좌표 (x, y)
			isSelected = new boolean[n]; // 순열에 사용할 배열 : 선택되었는지 확인
			order = new int[n]; // 선택된 인덱스 담을 배열
			minPath = Integer.MAX_VALUE; // 가장 짧은 경로
			
			
			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken()); // 회사 x좌표
			startY = Integer.parseInt(st.nextToken()); // 회사 y좌표
			endX = Integer.parseInt(st.nextToken()); // 집 x좌표
			endY = Integer.parseInt(st.nextToken()); // 집 y좌표
			
			for (int i = 0; i < n; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken()); // 고객 x좌표
				arr[i][1] = Integer.parseInt(st.nextToken()); // 고객 y좌표
			}
			
			optimalPath(0);
			
			System.out.println("#" + t + " " + minPath);
		}
	}
	
	static void optimalPath(int count) {
		sum = 0; // 이동거리 합
		
		// n명의 순서를 모두 정했을 때
		if (count == n) {
			// 회사에서 첫번째 고객 집 이동거리
			sum += Math.abs(startX - arr[order[0]][0]) + Math.abs(startY - arr[order[0]][1]);
			
			// 마지막 고객 집에서 김대리 집 이동거리
			sum += Math.abs(endX - arr[order[n - 1]][0]) + Math.abs(endY - arr[order[n - 1]][1]);
			
			// 나머지 고객 집간의 이동거리
			for (int i = 0; i < n - 1; i++) {
				sum += Math.abs(arr[order[i]][0] - arr[order[i + 1]][0]) + Math.abs(arr[order[i]][1] - arr[order[i + 1]][1]);
			}
			
			minPath = Math.min(sum, minPath); // 가장 짧은 이동거리
			return;
		}
		
		else {
			for (int i = 0; i < n; i++) {
				if (isSelected[i]) continue; // 이미 선택되었으면 continue
				order[count] = i; // 아직 안 쓰였다면 i 대입
				isSelected[i] = true; // 쓰였다고 체크 후
				optimalPath(count + 1); // 재귀함수 호출 : count + 1을 넣어 다음 수 탐색
				isSelected[i] = false; // 체크 해제
			}
		}
	}
}