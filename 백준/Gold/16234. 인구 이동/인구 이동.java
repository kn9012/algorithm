import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 16234번 인구 이동
 * [아이디어]
 * 모든 나라들을 한번씩 방문할 수 있도록 방문 배열을 만들어 체크하고 연합하는 나라 별로 리스트에 넣는다.
 * 이때, 리스트의 크기가 1보다 크다면 연합을 하는 것으로 평균을 구해 평균값을 넣어주면 된다.
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 * 
 * @author 김유나
 * 2023-10-02
 *
 */

public class Main {
	static int arr[][];
	static int n, l, r;
	static boolean visited[][];
	static int deltas[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static List<Point> list;
	
	public static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 땅 크기
		l = Integer.parseInt(st.nextToken()); // 인구 차이 최소
		r = Integer.parseInt(st.nextToken()); // 인구 차이 최대
		
		arr = new int[n][n]; // 각 나라 인구수 배열
		
		// 입력 받기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(move()); // 인구 이동 시작
	}
	
	public static int move() {
		int days = 0; // 인구 이동 발생한 날의 수
		
		while (true) {
			boolean isMove = false;
			visited = new boolean[n][n]; // 방문한 나라 체크
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					
					// 아직 방문하지 않은 나라일 경우
					if (!visited[i][j]) {					
						int sum = bfs(i, j); // 연합의 총 인구 수
						
						if (list.size() > 1) { // 리스트 크기가 1보다 큰 경우 -> 연합 나라가 2개 이상인 경우
							int avg = sum / list.size();
							for (Point p : list) {
								arr[p.x][p.y] = avg;
							}
							isMove = true;
						}
					}
				}
			}
			
			if (!isMove) return days;
			days++;
		}
	}
	
	public static int bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		list = new ArrayList<>(); // bfs를 실행시킬때마다 초기화 : 연합 별 리스트 생성
		
		queue.offer(new Point(x, y));
		list.add(new Point(x, y));
		visited[x][y] = true;
		
		int sum = arr[x][y];
		
		while (!queue.isEmpty()) {
			Point current = queue.poll();
	
			// 사방탐색
			for (int i = 0; i < 4; i++) {
				int dx = current.x + deltas[i][0];
				int dy = current.y + deltas[i][1];
				
				if (dx < 0 || dy < 0 || dx >= n || dy >= n) continue;
				
				if (!visited[dx][dy]) {
					int diff = Math.abs(arr[current.x][current.y] - arr[dx][dy]); // 인접한 나라의 인구 차이
					
					// 두 나라 인구 차이가 l명 이상, r명 이하일때
					if (diff >= l  && diff <= r) {
						visited[dx][dy] = true;
						queue.offer(new Point(dx, dy));
						list.add(new Point(dx, dy)); // 리스트에 넣기
						sum += arr[dx][dy];
					}
				}
			}
		}
		return sum;
	}
}
