import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 21610번 마법사 상어와 비바라기
 * 비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생김
 * 1. 모든 구름이 di 방향으로 si칸 이동
 * 2. 각 구름이 있는 칸의 바구니의 물의 양이 1 증가
 * 3. 구름이 사라짐
 * 4. 물이 증가한 칸에 물복사버그 마법 시전
 * - 대각선 사방 칸에 물이 있는(0이 아닌) 바구니 수만큼 (r, c)에 있는 바구니의 물의 양 증가
 * - 단, 이때 경계를 넘어가는 칸은  고려하지 않는다.
 * 5. 물의 양이 2 이상인 모든 칸에 구름이 생기고 물의 양이 2 줄어든다. 이때, 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 함
 * 
 * [아이디어]
 * 구름의 초기 위치를 Queue에 넣어 이동한 후 물의 양을 증가시키고 Queue에 넣은 좌표를 
 * 
 * 메모리  : kb
 * 실행 시간 : ms
 * 
 * @author 김유나
 * 2023-10-03
 *
 */

public class Main {
	static int n, m;
	static int arr[][], move[][];
	static int deltas[][] = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
	static boolean visited[][];
	static Queue<Cloud> clouds = new LinkedList<>();
	
	static class Cloud {
		int x;
		int y;
		
		public Cloud(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 칸의 크기
		m = Integer.parseInt(st.nextToken()); // 이동할 횟수
		int sum = 0; // 물의 양의 합
		
		arr = new int[n][n]; // 칸 배열
		move = new int[m][2]; // 이동할 방향, 횟수 담은 배열
		visited = new boolean[n][n]; // 방문 체크 배열
		
		// 칸에 있는 물의 양 입력 받기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 이동할 방향, 횟수 입력 받기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				move[i][j] = Integer.parseInt(st.nextToken());	
			}
		}
		
		clouds.add(new Cloud(n - 1, 0));
		clouds.add(new Cloud(n - 1, 1));
		clouds.add(new Cloud(n - 2, 0));
		clouds.add(new Cloud(n - 2, 1));
		
		for (int i = 0; i < m; i++) {
			// 비바라기!!!
			rainDance(move[i][0] - 1, move[i][1]);			
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sum += arr[i][j];
			}
		}
		
		System.out.println(sum);
	}
	
	// 비바라기 시전
	public static void rainDance(int d, int s) {
		
		// 1. 모든 구름이 di 방향으로 si칸 이동
		for (Cloud cloud : clouds) {
			// 경계를 벗어나는 경우도 고려해야 하기 때문에
			cloud.x = (n + cloud.x + deltas[d][0] * (s % n)) % n;
			cloud.y = (n + cloud.y + deltas[d][1] * (s % n)) % n;
			
			// 2. 각 구름이 있는 칸의 바구니의 물의 양이 1 증가
			arr[cloud.x][cloud.y]++;
		}
		
		while (!clouds.isEmpty()) {
			// 3. 구름이 사라짐
			Cloud current = clouds.poll();
			
			/*
			 * 4. 물이 증가한 칸에 물복사버그 마법 시전
			 * - 대각선 사방 칸에 물이 있는(0이 아닌) 바구니 수만큼 (r, c)에 있는 바구니의 물의 양 증가
			 * - 단, 이때 경계를 넘어가는 칸은  고려하지 않는다.
			 */
			
			int count = 0; // 바구니 수 count
			
			for (int i = 1; i < 8; i += 2) {
				// 대각선 사방탐색
				int dx = current.x + deltas[i][0];
				int dy = current.y + deltas[i][1];

				// 경계를 넘어가는 칸은 고려하지 않으므로 건너뛰기
				if (dx < 0 || dy < 0 || dx >= n || dy >= n) continue;
				
				// 대각선에 물이 있는 경우 즉, 0이 아닌 경우 count++
				if (arr[dx][dy] != 0) count++;
			}
			
			// 대각선 사방 칸에 물어 있는 바구니 수만큼 물의 양 증가
			arr[current.x][current.y] += count;
			
			// 방문 체크 : 5단계에서 구름이 생기지 않게 하기 위해서
			visited[current.x][current.y] = true;
		}
		
		// 5. 물의 양이 2 이상인 모든 칸에 구름이 생기고 물의 양이 2 줄어든다. 이때, 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 함
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && arr[i][j] >= 2) {
					arr[i][j] -= 2;
					clouds.add(new Cloud(i, j));
				}
			}
		}
		
		visited = new boolean[n][n];
	}
}
