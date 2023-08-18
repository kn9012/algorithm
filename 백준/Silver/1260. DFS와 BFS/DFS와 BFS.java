import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author 김유나
 * 2023-08-18
 * 
 * [문제] 백준 1260번 DFS와 BFS
 * [아이디어] 정점과 간선 개수, 탐색을 시작할 정점 번호와 간선이 연결하는 정점 번호 2개를 간선 개수만큼 받아 boolean 2차원 배열에 true 해준다.
 * 인접 행렬의 DFS, BFS를 구현하여 각각 실행해준다. 
 * 
 * 메모리 : kb 실행 시간 : 
 *
 */
public class Main {
	static int n, m, v;
	static boolean arr[][];
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 정점 개수
		m = Integer.parseInt(st.nextToken()); // 간선 개수
		v = Integer.parseInt(st.nextToken()); // 탐색 시작할 정점 번호

		arr = new boolean[n + 1][n + 1]; // 간선이 연결하는 두 정점의 번호 배열

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 정점 번호 1
			int b = Integer.parseInt(st.nextToken()); // 정점 번호 2
			arr[a][b] = true; // true로 체크
			arr[b][a] = true; // true로 체크
		}
		
		// 방문 배열 선언
		visited = new boolean[n + 1];
		dfs(v); // 깊이 우선 탐색
		
		System.out.println();
		
		// 방문 배열 재선언
		visited = new boolean[n + 1];
		bfs(); // 너비 우선 탐색
	}

	// DFS 메소드 : 탐색 정점을 매개변수로 하는 재귀 함수
	static void dfs(int current) {
		System.out.print(current + " "); // current 출력
		visited[current] = true; // 방문 체크
		
		for (int i = 1; i <= n; ++i) {
			// arr[current][i]가 true고, 아직 방문되지 않았다면
			if (arr[current][i] && !visited[i]) {
				dfs(i); // 재귀 호출
			}
		}
	}
	
	// BFS 메소드 : Queue를 사용하여 구현
	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		// 탐색 정점 넣기
		queue.offer(v);
		visited[v] = true; // 방문 체크
		
		while (!queue.isEmpty()) { // Queue가 비어있지 않을때까지
			int current = queue.poll(); // 가장 앞의 것 poll
			System.out.print(current + " "); // current 출력
			
			for (int i = 1; i <= n; i++) {
				// arr[current][i]가 true고, 아직 방문되지 않았다면
				if (arr[current][i] && !visited[i]) {
					queue.offer(i); // Queue에 i 넣기
					visited[i] = true; // 방문 체크
				}
			}
		}
	}
}
