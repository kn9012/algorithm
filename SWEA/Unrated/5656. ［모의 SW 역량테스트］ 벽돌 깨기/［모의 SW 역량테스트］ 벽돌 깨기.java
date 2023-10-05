import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 
 * [문제] SWEA 5656번 벽돌 깨기
 * [아이디어]
 * 1. 0부터 w-1까지 n개 뽑는 중복 순열 구하기
 * 2. 1에서 구한 순열로 열을 선택해서 벽돌 깨기 시작 : BFS 사용
 * 2-1. 이때, 깨는 도중 계속 아래 벽돌이 빌 경우 벽돌 아래로 내려오도록 하기 : Deque 사용
 * 3. 가장 많은 벽돌을 깬, 즉 남은 벽돌 개수가 최소로 되도록 한다.
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 * 
 * @author User
 * 2023-10-05
 *
 */
public class Solution {
	static int n, w, h, min = Integer.MAX_VALUE;
	static int map[][], mapCopy[][], p[];
	static int deltas[][] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스
		
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 벽돌을 떨어뜨릴 횟수
			w = Integer.parseInt(st.nextToken()); // 가로 길이
			h = Integer.parseInt(st.nextToken()); // 세로 길이
			
			map = new int[h][w]; // 원래 게임판
			mapCopy = new int[h][w]; // 새 게임판 : BFS시 다시 돌아오기 위함
			p = new int[n]; // 순열 저장할 배열
			min = Integer.MAX_VALUE;

			
			// 게임판 입력 받기
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					mapCopy[i][j] = map[i][j];
				}
			}
			
			perm(0); // 중복 순열 만들기

			sb.append("#" + t + " " + min + "\n");
		}
		
		System.out.println(sb);
	}
	
	// 중복 순열 만들기
	public static void perm(int count) {
		if (count == n) { // n개 뽑았을 경우
			gameStart(); // game start!
			
			int cnt = 0; // 남은 블록 개수
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] > 0) cnt++;
				}
			}
			
			min = Math.min(min, cnt); // 최소값 구하기
			
			// 게임판 복사해주기
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					map[i][j] = mapCopy[i][j];
				}
			}
			
			return;
		}
		
		for (int i = 0; i < w; i++) {
			p[count] = i;
			perm(count + 1);
		}
	}
	
	public static void gameStart() {
		int x = n - 1;
		
		for (int i = 0; i < n; i++) {
			int turn = p[i]; // 구슬 떨어뜨릴 열
			for (int j = 0; j < h; j++) { // 구슬 떨어뜨릴 행
				if (map[j][turn] > 0) { // 0이 아니라면
					x = j;
					break;
				}
			}
			
			dropMarble(x, turn); // 구슬 떨어뜨리기
			dropBox(); // 구슬 떨어뜨리고 난 후 벽돌 아래로 떨어뜨리기
		}
	}
	
	// 구슬 떨어뜨리는 method
	public static void dropMarble(int x, int y) {
		Deque<int []> deque = new ArrayDeque<>();
		// 초기값 deque에 넣고 0으로 초기화
		deque.add(new int[] {x, y, map[x][y]});
		map[x][y] = 0;
		
		int dx;
		int dy;
		
		while (!deque.isEmpty()) {
			int [] cur = deque.poll(); // 최근값
			int power = cur[2]; // 최근값의 좌표 값 즉, 세기
			
			for (int i = 1; i < power; i++) {
				for (int j = 0; j < 4; j++) {
					dx = cur[0] + deltas[j][0] * i;
					dy = cur[1] + deltas[j][1] * i;
					
					// 이동한 좌표가 map에서 벗어나거나 값이 0일 경우
					if (dx < 0 || dy < 0 || dx >= h || dy >= w || map[dx][dy] == 0) continue;
					
					if (map[dx][dy] > 0) { // 값이 0보다 클 경우 deque에 넣고 값 0으로 초기화
						deque.add(new int[] {dx, dy, map[dx][dy]});
						map[dx][dy] = 0;
						continue;
					}
				}
			}
		}
	}
	
	// 벽돌 아래로 내리는 method
	public static void dropBox() {
		Deque<Integer> deque = new ArrayDeque<Integer>();
		
		for (int j = 0; j < w; j++) { // 열
			// 모든 값 deque에 넣기
			for (int i = 0; i < h; i++) { // 행
				if(map[i][j] > 0) {
					deque.add(map[i][j]);	
				}
			}
			
			// 
			for(int i = h - 1; i >= 0; i--) { 
				if(deque.isEmpty()) { // 비어 있을 경우
					map[i][j] = 0; // 0으로 초기화
				} else { // 비어있지 않을 경우
					map[i][j] = deque.pollLast(); // 마지막 값 빼기 	
				}
			}
		}
		
	}
}