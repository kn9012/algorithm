import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * [문제] SWEA 7465번 창용 마을 무리의 개수
 * [아이디어] 
 * 
 * 메모리 : 24,276kb
 * 실행 시간 : 139ms
 *  
 * @author 김유나
 * 2023-10-10
 *
 */

public class Solution {
	static int n, m, count;
	static int arr[][];
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스
		
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 창용 마을에 사는 사람의 수
			m = Integer.parseInt(st.nextToken()); // 서로를 알고 있는 사람의 관계 수
			
			arr = new int[n + 1][n + 1]; // 관계 배열
			
			// 관계 입력 받기
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int count = st.countTokens();
				
				if (count == 2) { // 관계가 없는 사람도 입력될 수 있으므로 입력의 수를 count해준다.
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					arr[a][b] = 1;
					arr[b][a] = 1;					
				}
				
			}
			
			count = 0; // 무리의 수
			visited = new boolean[n + 1]; // 체크 배열
			
			for (int i = 1; i <= n; i++) {
				if (!visited[i]) bfs(i); // 아직 방문되지 않은 사람이라면
			}
			
			sb.append("#" + t + " " + count + "\n");
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int x) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(x); // 현재 사람 넣기
		visited[x] = true; // 방문 체크
		
		while (!queue.isEmpty()) {
			int cur = queue.poll(); // 최근 값 가져오기
			
			for (int i = 1; i <= n; i++) {
				if (arr[cur][i] == 1 && !visited[i]) { // 관계가 있고 아직 방문되지 않았다면
					queue.add(i); // 큐에 넣기
					visited[i] = true; // 방문 체크
				}
			}
		}
		
		count++;
	}
}