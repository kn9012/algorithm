
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * [문제] SWEA 2105번 디저트 카페
 * [아이디어] DFS 사용
 * 디저트 수 중복 x -> Set의 contains 사용하여 확인
 * 
 * @author 김유나
 * 2023-11-03
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 *
 */
public class Solution {
	static class Point {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int n, max = 0;
	static int arr[][];
	static int deltas[][] = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}}; // 우하 좌하 좌상 우상 순서
	static Set<Integer> set;
	static boolean visit[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= tc; t++) {
			max = 0;
			
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 대각선으로 사각형을 만들려면 출발할 카페에서 최소 양옆, 아래 두 칸은 있어야하므로
			for (int i = 0; i < n - 2; i++) {
				for (int j = 1; j < n - 1; j++) {
					visit = new boolean[n][n];
					set = new HashSet<Integer>();
					visit[i][j] = true;
					set.add(arr[i][j]);
					dfs(new Point(i, j), 0, new Point(i, j), 1);
									
				}
			}
			
			if (max == 0) max = -1;
			sb.append("#" + t + " " + max + "\n");
		}
		System.out.print(sb);
	}
	
	// 해당 좌표, 방향, 원래 좌표, count 수
	static void dfs(Point p, int dir, Point first, int count) {
		for (int i = dir; i < 4; i++) {
			int dx = p.x + deltas[i][0];
			int dy = p.y + deltas[i][1];
			
			if (dx >= n || dy >= n || dx < 0 || dy < 0) continue;
			if (dx == first.x && dy == first.y && count > 2) {
				if (count == 2) return;
				max = Math.max(max, count);
				return;
			}
			if (!visit[dx][dy] && !set.contains(arr[dx][dy])) {
				visit[dx][dy] = true;
				set.add(arr[dx][dy]);
				
				dfs(new Point(dx, dy), i, first, count + 1);
				
				visit[dx][dy] = false;
				set.remove(arr[dx][dy]);
			}
		}
	}
}
