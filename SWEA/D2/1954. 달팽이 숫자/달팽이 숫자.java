
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * [문제] SWEA 1954번 달팽이 숫자
 * [아이디어] N으로 나눈 나머지가 0, N-1, N-2, ..., N-1, 0이 된다?
 * 우 하 좌 상
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 * @author 김유나
 * 2023-10-15
 *
 */

public class Solution {
	static int deltas[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= tc; t++) {
			int N = Integer.parseInt(br.readLine());
			int arr[][] = new int[N][N];
			boolean visited[][] = new boolean[N][N];
			
			int dx = 0, dy = 0;
			int count = 1;
			int dir = 0;
			while (count <= N * N) {
				arr[dx][dy] = count;
				visited[dx][dy] = true;
				
				dx += deltas[dir][0];
				dy += deltas[dir][1];
				
				
				if (dx < 0 || dy < 0 || dx >= N || dy >= N || visited[dx][dy]) {
					dx -= deltas[dir][0];
					dy -= deltas[dir][1];
					
					if (dir == 3) dir = 0;
					else dir++;
					
					dx += deltas[dir][0];
					dy += deltas[dir][1];
				}
				
				count++;
			}
			
			sb.append("#" + t + "\n");
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(arr[i][j] + " ");
				} sb.append("\n");
			}
		} System.out.println(sb);
	}
}