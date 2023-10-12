import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 17144번 미세먼지 안녕!
 * 1. 미세먼지 확산
 * - 사방탐색
 * - 공기청정기가 있거나 배열을 벗어날 경우 확산 x
 * - 1/5만큼 확산되며 소수점은 버림
 * - 남은 양은 Ar/c - (Ar/c / 5) * 확산된 방향의 수
 * 
 * 2. 공기청정기 작동
 * - 위쪽은 반시계, 아래쪽은 시계방향으로 한 칸씩 이동
 * - 공기청정기로 들어감녀 정화 즉, 사라짐
 * 
 * [아이디어]
 * 공기청정기로 들어갈 미세먼지 먼저 삭제 후 반시계, 시계 방향으로 한칸씩 밀기
 * 확산된 방향의 수 count 해주기
 * 
 * 메모리  : 39,500kb
 * 실행 시간 : 322ms
 * 
 * @author 김유나
 * 2023-10-11
 *
 */

public class Main {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int r, c, t, map[][], newMap[][];
	static int deltas[][] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	static List<Point> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken()); // 행
		c = Integer.parseInt(st.nextToken()); // 열
		t = Integer.parseInt(st.nextToken()); // 확산할 시간
		
		map = new int[r][c]; // 지도 배열
		
		// 배열 입력 받기
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) { // 공기청정기 좌표 list에 넣기
					list.add(new Point(i, j));
				}
			}
		}
		
		for (int i = 0; i < t; i++) {
			newMap = new int[r][c]; // 배열 초기화 : 이걸 안해줘서 몇시간이 걸렸다!!!1
			diffusion(); // 확산
			airPurification(); // 공기청정기 발동!
			
			// 배열 복사
			for (int j = 0; j < r; j++) {
				map = newMap.clone();
			}
		}
		
		// 합 구하기
		int sum = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] != -1) {
					sum += map[i][j];
					
				}
			}
		}
		
		System.out.println(sum); // 출력
	}
	
	// 미세먼지 확산
	static void diffusion() {		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] != -1 && map[i][j] != 0) {
					int num = map[i][j] / 5; // 확산되는 양
					int count = 0; // 확산된 방향의 수
					
					// 사방탐색
					for (int k = 0; k < 4; k++) {
						int dx = i + deltas[k][0];
						int dy = j + deltas[k][1];
						
						// 배열을 벗어나거나 공기청정기가 위치할 경우 확산 x
						if (dx < 0 || dy < 0 || dx >= r || dy >= c || map[dx][dy] == -1) continue;
						
						
						newMap[dx][dy] += num; // 1/5 값 더하기
						
						count++; // 확산된 방향의 수++
					}
					
					// Ar/c - (Ar/c / 5) * 확산된 방향의 수
					newMap[i][j] += map[i][j] - num * count;
				}
			}
		}
		
		// 공기청정기 위치 넣어주기
		for (int i = 0; i < r; i++) {
			if (map[i][0] == -1) newMap[i][0] = -1;
		}
	}
	
	// 공기청정기 작동!
	static void airPurification() {
		int top = list.get(0).x; // 공기청정기 윗부분 x좌표
		
		// 위쪽 : 반시계방향		
		
		// 위 -> 아래
		for (int i = top - 1; i > 0 ; i--) {
			newMap[i][0] = newMap[i - 1][0];
		}
		
		// 오 -> 왼
		for (int i = 0; i < c - 1; i++) {
			newMap[0][i] = newMap[0][i + 1];
		}
		
		// 아래 -> 위
		for (int i = 0; i < top; i++) {
			newMap[i][c - 1] = newMap[i + 1][c - 1];
		}
		
		// 왼 -> 오
		for (int i = c - 1; i > 1; i--) {
			newMap[top][i] = newMap[top][i - 1];
		}
		
		newMap[top][1] = 0; // 공기청정기 발동이 시작된 부분은 0
		
		int bottom = list.get(1).x; // 공기청정기 윗부분 y좌표
		
		// 아래쪽 : 시계방향
		
		// 아래 -> 위
		for (int i = bottom + 1; i < r - 1; i++) {
			newMap[i][0] = newMap[i + 1][0];
		}
			
		// 오 -> 왼
		for (int i = 0; i < c - 1; i++) {
			newMap[r - 1][i] = newMap[r - 1][i + 1];
		}
			
		// 위 -> 아래
		for (int i = r - 1; i > bottom; i--) {
			newMap[i][c - 1] = newMap[i - 1][c - 1];
		}
		
		// 왼 -> 오
		for (int i = c - 1; i > 1; i--) {
			newMap[bottom][i] = newMap[bottom][i - 1];
		}
		
		newMap[bottom][1] = 0; // 공기청정기 발동이 시작된 부분은 0
	}
}