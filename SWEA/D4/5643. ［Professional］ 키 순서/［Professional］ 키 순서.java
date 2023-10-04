import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * [문제] SWEA 5643번 키 순서
 * [아이디어] 유방향 그래프의 인접 행렬을 순방향과 역방향 두 개 만들어 키 작은 순, 키 큰 순으로 BFS 탐색한다.
 * 
 * 메모리 : 96,628kb
 * 실행 시간 : 2,450ms
 * 
 * @author 김유나
 * 2023-10-04
 *
 */

public class Solution {
	static int n, m;
	static int arr[][], reArr[][];
	static boolean visited[];
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스

		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine()); // 학생 수
			m = Integer.parseInt(br.readLine()); // 키를 비교한 횟수

			arr = new int[n + 1][n + 1]; // 순방향 인접 배열
			reArr = new int[n + 1][n + 1]; // 역방향 인접 배열

			// 입력 받기
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				arr[a][b] = 1; // 순방향 배열 입력
				reArr[b][a] = 1; // 역방향 배열 입력
			}

			int studentCnt = 0; // 키가 확실한 학생
			for (int i = 1; i <= n; i++) {
				int count = 0; // 키가 확실한 학생 판별 위한 count
				visited = new boolean[n + 1]; // 방문 배열
				small(i); // 작은 경우
				big(i); // 큰 경우
				
				for (int j = 1; j <= n; j++) {
					if (visited[j]) count++; 
				}
				
				if (count == n) studentCnt++; // 키가 확실한 학생 ++
			}
			
			sb.append("#" + t + " " + studentCnt + "\n");
		}

		System.out.println(sb);
	}

	// BFS 1
	public static void small(int parent) {
		queue.add(parent); // 처음 값 queue에 넣기
		visited[parent] = true; // 방문 체크
		
		while (!queue.isEmpty()) { // queue가 비지 않을 때까지
			int current = queue.poll(); // 최근 값 poll
			
			for (int i = 1; i <= n; i++) {
				if (arr[current][i] == 1 && !visited[i]) { // 최근 값 행이 존재하는 값이 있고 아직 방문하지 않았다면
					visited[i] = true; // 방문 체크
					queue.add(i); // queue에 넣기
				}
				
			}
		}
	}

	// BFS 2
	public static void big(int child) {
		queue.add(child); // queue에 넣기
		
		while (!queue.isEmpty()) { // queue가 비지 않을 때까지
			int current = queue.poll(); // 최근 값 poll
			
			for (int i = 1; i <= n; i++) {
				if (reArr[current][i] == 1 && !visited[i]) { // 최근 값 행이 존재하는 값이 있고 아직 방문하지 않았다면
					visited[i] = true; // 방문 체크
					queue.add(i); // queue에 넣기
				}				
			}
		}
	}
}