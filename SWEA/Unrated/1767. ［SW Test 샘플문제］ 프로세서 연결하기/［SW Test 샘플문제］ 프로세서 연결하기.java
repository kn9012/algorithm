
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [문제] SWEA 1767번 프로세서 연결하기
 * [아이디어] 가장 바깥쪽은 이미 전원 연결 -> 가장자리 제외한 Core 수 세서 부분집합 구하기
 * 
 * 
 * 실행시간 : ms
 * 메모리 : kb
 * 
 * @author 김유나
 * 2023-11-06
 *
 */
public class Solution {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int n, map[][], coreSum, min, deltas[][] = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
	static boolean visited[];
	static List<Point> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			list = new ArrayList<>();
			coreSum = 0;
			min = Integer.MAX_VALUE;
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
	
				}
			}
			
			for (int i = 1; i < n - 1; i++) {
				for (int j = 1; j < n - 1; j++) {
					if (map[i][j] == 1) {
						list.add(new Point(i, j));
						coreSum++;
					}
				}
			}
			
			visited = new boolean[coreSum];
			
			for (int i = coreSum; i >= 0; i--) {
				subset(0, 0, i);
				
				if (min < Integer.MAX_VALUE) break;
			}
			
			
			sb.append("#" + tc + " " + min + "\n");
		}
		System.out.println(sb);
	}
	
	static void subset(int index, int count, int n) {
		if (count == n) {
			dfs(0, 0);
			return;
		}
		
		for (int i = index; i < coreSum; i++) {
			visited[i] = true;
			subset(i + 1, count + 1, n);
			visited[i] = false;
		}
	}
	
	static void dfs(int index, int sum) {
		if (index == coreSum) {
			min = Math.min(min, sum);
			return;
		}
		
		if (!visited[index]) {
			dfs(index + 1, sum);
			return;
		}
		
		
		for (int i = 0; i < 4; i++) {
			int dx = list.get(index).x;
			int dy = list.get(index).y;
			
			boolean flag = false;
			int len = 0;
			
			while (true) {
				dx += deltas[i][0];
				dy += deltas[i][1];
				
				if (dx < 0 || dy < 0 || dx >= n || dy >= n) {
					flag = true;
					break;
				}
				
				if (map[dx][dy] != 0) break;
				
				map[dx][dy] = 2;
				len++;
			}
			
			if (flag) dfs(index + 1, sum + len);
			
			while (true) {
				dx -= deltas[i][0];
				dy -= deltas[i][1];
				
				if (dx == list.get(index).x && dy == list.get(index).y) break;
				map[dx][dy] = 0;
			}
		}
	}
}
