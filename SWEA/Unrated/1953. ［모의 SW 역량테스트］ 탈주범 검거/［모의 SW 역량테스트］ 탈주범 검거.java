import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * [문제] SWEA 1953번 탈주범 검거
 * [아이디어] 백트래킹 사용
 * 방문 체크 해주는 boolean 배열 1개, 탈주범이 위치할 수 있는 장소 boolean 배열 1개
 * L-1시간
 * 
 * 
 * 중요한건 터널 구조물을 어떻게 확인할 것인지?
 * 
 * 실행 시간 : ms
 * 메모리 : kb
 * 
 * @author 김유나
 * 2023-11-09
 *
 */

public class Solution {
	// 순서대로 세로 크기, 가로 크기, 맨홀 세로 위치, 맨홀 가로 위치, 탈출 후 소요된 시간
	// 상 우 좌 하
	static int N, M, R, C, L, map[][], sum, deltas[][] = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
	static boolean visited[][], isPossible[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 세로 크기
			M = Integer.parseInt(st.nextToken()); // 가로 크기
			R = Integer.parseInt(st.nextToken()); // 맨홀 세로 위치
			C = Integer.parseInt(st.nextToken()); // 멘홀 가로 위치
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요한 시간
			
			map = new int[N][M];
			visited = new boolean[N][M];
			isPossible = new boolean[N][M];
			sum = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs();
			
			sb.append("#" + tc + " " + sum + "\n");
		}
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int[] {R, C, map[R][C], L - 1});
		visited[R][C] = true;
		isPossible[R][C] = true;
		
		while (!queue.isEmpty()) {
			int cur[] = queue.poll();
//			System.out.println(cur[0] + " " + cur[1]);
			
			if (cur[3] == 0) break;
			for (int i = 0; i < 4; i++) {
				int dx = cur[0] + deltas[i][0];
				int dy = cur[1] + deltas[i][1];
//				System.out.println(dx + " " + dy + " : " + checkTunnel(i, map[cur[0]][cur[1]], map[dx][dy]));
//				System.out.println("-> " + i + " " + map[cur[0]][cur[1]] + " " + map[dx][dy]);
				if (dx < 0 || dy < 0 || dx >= N || dy >= M || map[dx][dy] == 0 || !checkTunnel(i, map[cur[0]][cur[1]], map[dx][dy]) || visited[dx][dy]) continue;
				
//				System.out.println(L - cur[3] + " : " + dx + " " + dy);
				queue.add(new int[] {dx, dy, map[dx][dy], cur[3] - 1});
				visited[dx][dy] = true;
				isPossible[dx][dy] = true;
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				if (isPossible[i][j]) System.out.print(1 + " ");
//				else System.out.print(0 + " ");
//			} System.out.println();
//		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (isPossible[i][j]) sum++;
			}
		}
	}
	
	// 방향, 전 터널, 다음 터널 입력받아서 갈 수 있는 터널인지 확인 
	static boolean checkTunnel(int dir, int pre, int next) {
		switch (pre) {
		case 1:
			if (dir == 0) {
				if (next == 3 || next == 4 || next == 7) return false;
			}
			else if (dir == 1) {
				if (next == 2 || next == 4 || next == 5) return false;
			}
			else if (dir == 2) {
				if (next == 2 || next == 6 || next == 7) return false;
			}
			else if (dir == 3) {
				if (next == 3 || next == 5 || next == 6) return false;
			}
			break;
		case 2:
			if (dir == 1 || dir == 2) return false;
			if (dir == 0) {
				if (next == 3 || next == 4 || next == 7) return false;
			}
			else if (dir == 3) {
				if (next == 3 || next == 5 || next == 6) return false;
			}
			break;
		case 3:
			if (dir == 0 || dir == 3) return false;
			if (dir == 1) {
				if (next == 2 || next == 4 || next == 5) return false;
			}
			else if (dir == 2) {
				if (next == 2 || next == 6 || next == 7) return false;
			}
			break;
		case 4:
			if (dir == 2 || dir == 3) return false;
			if (dir == 0) {
				if (next == 3 || next == 4 || next == 7) return false;
			}
			else if (dir == 1) {
				if (next == 2 || next == 4 || next == 5) return false;
			}
			break;
		case 5:
			if (dir == 0 || dir == 2) return false;
			if (dir == 1) {
				if (next == 2 || next == 4 || next == 5) return false;
			}
			else if (dir == 3) {
				if (next == 3 || next == 5 || next == 6) return false;
			}
			break;
		case 6:
			if (dir == 0 || dir == 1) return false;
			if (dir == 2) {
				if (next == 2 || next == 6 || next == 7) return false;
			}
			else if (dir == 3) {
				if (next == 3 || next == 5 || next == 6) return false;
			}
			break;
		case 7:
			if (dir == 1 || dir == 3) return false;
			if (dir == 0) {
				if (next == 3 || next == 4 || next == 7) return false;
			}
			else if (dir == 2) {
				if (next == 2 || next == 6 || next == 7) return false;
			}
			break;
		}
		
		return true;
	}
}