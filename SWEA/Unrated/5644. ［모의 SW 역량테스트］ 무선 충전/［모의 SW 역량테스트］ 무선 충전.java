import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * [문제] SWEA 5644번 무선충전
 * [아이디어] 좌표를 저장하는 Point 클래스와 BC의 정보를 저장하는 BC 클래스를 만들어 BC A와 B를 동시에 움직이며 충전이 가능한지 체크한다.
 * A와 B가 충전 가능한 충전기기 리스트를 만든 후 동시에 충전 가능할때, 둘 중 하나라도 다른 충전기와 충전이 가능하다면 다른 충전기로 충전할 수 있도록 한다.
 * 이때, 중요한 것은 초기 좌표에서도 충전이 가능한지 확인해야하므로 충전 가능 체크를 이동하기 전애 먼저 해준다.
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 * 
 * @author 김유나
 * 2023-10-08
 *
 */

public class Solution {	
	// 좌표 클래스 
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// BC 정보를 갖고 있는 클래스
	static class BC {
		Point p;
		int c;
		int power;
		
		public BC(Point p, int c, int power) {
			this.p = p;
			this.c = c;
			this.power = power;
		}
	}
	
	static int m, a, sum;
	static int move[][];
	static BC BCs[];
	static int deltas[][] = {{0, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스
		
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken()); // 총 이동 시간
			a = Integer.parseInt(st.nextToken()); // BC의 개수
			
			move = new int[2][m]; // 이동 배열
			BCs = new BC[a]; // BC 정보 (x 좌표, y 좌표, 충전 범위, 성능)
			sum = 0; // 총 처리량 초기화
			
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					move[i][j] = Integer.parseInt(st.nextToken()); // 이동 경로 입력 받기
				}
			}
			
			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				
				// BC 정보 입력 받기
				BCs[i] = new BC(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			// A, B 초기 좌표 입력
			Point pA = new Point(1, 1);
			Point pB = new Point(10, 10);

			charge(pA, pB); // 초기 좌표 처리량 계산
			
			
			for (int i = 0; i < m; i++) {
				// A와 B 이동
				int xA = pA.x + deltas[move[0][i]][0];
				int yA = pA.y + deltas[move[0][i]][1];
				int xB = pB.x + deltas[move[1][i]][0];
				int yB = pB.y + deltas[move[1][i]][1];
				
				pA = new Point(xA, yA);
				pB = new Point(xB, yB);
				
				charge(pA, pB); // 처리량 계산
			}
			
			sb.append("#" + t + " " + sum + "\n");
		}
		
		System.out.println(sb); // 출력
	}
	
	// 거리 계산
	static int distance(int xA, int xBC, int yA, int yBC) {
		return Math.abs(xA - xBC) + Math.abs(yA - yBC);
	}
	
	static void charge(Point pA, Point pB) {
		List<Integer> listA = new ArrayList<>();
		List<Integer> listB = new ArrayList<>();
		
		for (int i = 0; i < a; i++) {
			// A, B가 i번째 BC에서 충전 가능한지 체크 : 충전 가능할 경우 각각의 list에 넣기
			if (distance(pA.x, BCs[i].p.x, pA.y, BCs[i].p.y) <= BCs[i].c) {
				listA.add(i);
			} 
			if (distance(pB.x, BCs[i].p.x, pB.y, BCs[i].p.y) <= BCs[i].c) {
				listB.add(i);
			}
		}
		
		int max = 0;
		int power = 0;
		
		// A와 B 모두 1개 이상이라면
		if (listA.size() > 0 && listB.size() > 0) {
			// 가능한 조합 모두 찾아 최대 처리량 구하기
			for (int a : listA) {
				for (int b : listB) {
					power = 0;
					
					// a와 b가 같다면 처리량은 절반이 되므로 한번만 더하기
					if (a == b) power = BCs[a].power;
					// a와 b가 같지 않다면 a와 b의 처리량 각각 더하기
					else {
						power += BCs[a].power;
						power += BCs[b].power;
					}
					
					max = Math.max(max, power);
				}
			}
		}
		
		// A가 1개 이상이라면
		else if (listA.size() > 0) {
			// 접속 가능한 BC 중 최대 처리량 구하기
			for (int a : listA) {
				max = Math.max(max, BCs[a].power);
			}
		}
		
		// B가 1개 이상이라면
		else if (listB.size() > 0) {
			for (int b : listB) {
				max = Math.max(max, BCs[b].power);
			}
		}
		
		// 총 처리량 누적
		sum += max;
	}
}
