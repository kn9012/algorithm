package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 김유나
 * 2023-08-13
 * [문제] N * N 크기의 미로와 도착시 밖으로 이동할 수 있는 점퍼의 좌표가 주어진다.
 * 주어진 이동 지시 개수만큼 방향과 칸 수를 이동한 후 마지막 좌표를 출력하여라.
 * 점퍼를 통해 밖으로 나왔거나 이동 시 N * N의 칸을 벗어날 경우의 좌표는 (0, 0)이다.
 * [아이디어] 방향 배열을 사용하여 입력받은 이동 방향에 따라 좌표를 이동한다.
 * 이때, 이동한 좌표가 배열을 벗어나거나 점퍼 좌표인 경우 (0, 0)을 출력하도록 한다. 
 *
 */
public class Maze_미로_4방_김유나 {
	static int [][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
	static int n, x, y, move, finalX, finalY;
	static int[][] jumpers, moves;
	static boolean[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스
		StringTokenizer st;
		
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken()); // 배열 크기
			x = Integer.parseInt(st.nextToken()); // 출발점 좌표 : 행
			y = Integer.parseInt(st.nextToken()); // 출발점 좌표 : 열
			int jumper = Integer.parseInt(st.nextToken()); // 점퍼 개수
			
			jumpers = new int[jumper][2]; // 점퍼 좌표
			arr = new boolean[n + 1][n + 1]; // 배열
			
			st = new StringTokenizer(br.readLine());
			
			// 점퍼 좌표 입력 받기
			for (int i = 0; i < jumper; i++) {
				jumpers[i][0] = Integer.parseInt(st.nextToken());
				jumpers[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// 배열에 점퍼 좌표 체크
			for (int i = 0; i <= n; i++) {
				for (int j = 0; j <= n; j++) {
					for (int k = 0; k < jumper; k++) {
						arr[jumpers[k][0]][jumpers[k][1]] = true;
					}
				}
			}
			
			move = Integer.parseInt(br.readLine()); // 이동지시 개수
			moves = new int[move][2]; // 이동방향 및 칸 수 배열
			
			st = new StringTokenizer(br.readLine());
			// 이동방향 및 칸 수 입력받기
			for (int i = 0; i < move; i++) {
				moves[i][0] = Integer.parseInt(st.nextToken());
				moves[i][1] = Integer.parseInt(st.nextToken());
			}

			// 미로 찾기 시작
			maze(x, y);
			
			System.out.println("#" + t + " " + finalX + " " + finalY);
		}
	}
	
	static void maze(int x, int y) {
		for (int i = 0; i < move; i++) {
			switch (moves[i][0]) {
			// 상측 이동
			case 1:
				x += deltas[0][0] * moves[i][1]; // 칸수만큼 이동
				// 배열을 벗어나거나 점퍼 좌표인 경우
				if (x < 0 || arr[x][y]) {
					// (0, 0) 넣기
					finalX = 0;
					finalY = 0;
					return;
				}

				break;
				
			// 우측 이동
			case 2:
				y += deltas[3][1] * moves[i][1]; // 칸수만큼 이동
				// 배열을 벗어나거나 점퍼 좌표인 경우
				if (y >= n || arr[x][y]) {
					// (0, 0) 넣기
					finalX = 0;
					finalY = 0;
					return;
				}
				break;
				
			// 하측 이동
			case 3:
				x += deltas[1][0] * moves[i][1]; // 칸수만큼 이동
				// 배열을 벗어나거나 점퍼 좌표인 경우
				if (x >= n || arr[x][y]) {
					// (0, 0) 넣기
					finalX = 0;
					finalY = 0;
					return;
				}
				break;
				
			// 좌측 이동
			case 4:
				y += deltas[2][1] * moves[i][1]; // 칸수만큼 이동
				// 배열을 벗어나거나 점퍼 좌표인 경우
				if (y < 0 || arr[x][y]) {
					// (0, 0) 넣기
					finalX = 0;
					finalY = 0;
					return;
				}
				break;
			}
		}
		// 최종 좌표
		finalX = x;
		finalY = y;
	}
}
