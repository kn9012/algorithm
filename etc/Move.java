package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 김유나
 * 2023-08-13
 * [문제]
 * 직사각형의 놀이 공간에 두 자리 숫자가 적혀있는데 앞자리는 방향, 뒷자리는 이동횟수다.
 * 게임 참가자는 참가비 1000원을 지불하며 시작 좌표의 값을 분석하여 이동횟수만큼 이동한다.
 * 마지막 이동 좌표에 적어진 숫자에 100을 곱한 금액을 상금으로 받을 때, 테스트 케이스 내의 참가자들의 상금을 합하여 구하라.
 * [아이디어] 
 * 시작 위치의 숫자 앞 자리에 따른 방향을, 뒷 자리에 따른 이동 횟수를 계산하여 배열 밖으로 나간다면 상금이 0, 나가지 않는다면 최종 좌표의 수에 100을 곱한다.
 * 
 */

public class Move_이동_8방_김유나 {
	// 서북, 북, 동북, 동, 동남, 남, 서남, 서
	static int [][] deltas = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
	static int x, y, n;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스
		
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()); // 배열 행 크기
			y = Integer.parseInt(st.nextToken()); // 배열 열 크기
			n = Integer.parseInt(st.nextToken()); // 참가자 수
			
			arr = new int[x + 1][y + 1]; // 숫자판
			
			for (int i = 1; i <= x; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= y; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken()); // 숫자판 입력 받기
				}
			}
			
			int sum = 0; // 상금 합계
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int startX = Integer.parseInt(st.nextToken()); // 시작 위치 행
				int startY = Integer.parseInt(st.nextToken()); // 시작 위치 열 
				int moveN = Integer.parseInt(st.nextToken()); // 참가자 수
				
				sum += -1000; // 참가비 내기
				sum += move(startX, startY, moveN, i) * 100; // 상금 더하기
			}
			
			System.out.println("#" + tc + " " + sum);
		}
	}
	
	static int move(int startX, int startY, int moveN, int index) {
		for (int i = 0; i < moveN; i++) {
			int num = arr[startX][startY] % 10; // 이동 칸 수
			switch (arr[startX][startY] / 10) { // 방향
			// 북
			case 1:
				startX += deltas[1][0] * num;
				if (startX < 1) return 0;
				break;
			
				
			// 동북
			case 2:
				startX += deltas[2][0] * num;
				startY += deltas[2][1] * num;
				if (startX < 1 || startY > y) return 0;
				break;
				
			// 동
			case 3:
				startY += deltas[3][1] * num;
				if (startY > y) return 0;
				break;
			
			// 동남
			case 4:
				startX += deltas[4][0] * num; 
				startY += deltas[4][1] * num;
				if (startX > x || startY > y) return 0;
				break;
			
			// 남
			case 5:
				startX += deltas[5][0] * num;
				if (startX > x) return 0;
				break;
				
			// 서남
			case 6:
				startX += deltas[6][0] * num;
				startY += deltas[6][1] * num;
				if (startX > x || startY < 1) return 0;
				break;
				
			// 서
			case 7:
				startY += deltas[7][1] * num;
				if (startY < 1) return 0;
				break;
				
			// 서북
			case 8:
				startX += deltas[0][0] * num;
				startY += deltas[0][1] * num;
				if (startX < 1 || startY < 1) return 0;
				break;
				
			default:
				break;
			}
			
		}
		return arr[startX][startY]; // 마지막 이동 좌표 숫자 return
	}
}
