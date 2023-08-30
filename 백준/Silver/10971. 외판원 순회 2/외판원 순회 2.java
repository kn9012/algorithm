import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
  * 
  * [문제] 백준 10971번 외판원순회2
  * 1부터 N번까지의 번호가 매겨져 있는 도시가 있을 때 어느 한 도시에서 출발해 N개의 도시를 모두 돌아오는 순회 여행을 하려는데 이때, 한 번 갔던 경로로는 다시 갈 수 없다.
  * 각 도시간에 이동하는데 드는 비용이 주어질 때, 도시 i에서 j로 갈 수 없는 경우가 있으며 이럴 경우 W[i][j] = 0로 주어지며 여행 최소 비용을 구하여라.
  * 
  * [아이디어] 순열을 이용하여 0부터 n-1까지 도시의 순서를 정해주고 i-1번째 도시에서 i번째 도시로 갈 때 드는 비용
  * 
  * @author 김유나
  * 2023-08-30
  *
  */
public class Main {
	static int arr[][], n, choiceCity[], cityNum[], min = Integer.MAX_VALUE;
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine()); // 도시 개수
		
		arr = new int[n][n]; // 각 도시간에 이동하는데 드는 비용 배열
		visited = new boolean[n]; // 방문 체크 배열
		choiceCity = new int[n]; // 도시 순서 저장 배열
		
		// 입력 받기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		perm(0);
		
		System.out.println(min);
	}
	
	static void perm(int count) {
		if (count == n) {
			int sum = 0; // 비용 합
			for (int i = 1; i < n; i++) {
				if (arr[choiceCity[i - 1]][choiceCity[i]] == 0) return; // 0일 경우 종료
				else sum += arr[choiceCity[i - 1]][choiceCity[i]]; // 0이 아닐 경우 i-1번째 도시에서 i번째 도시로 가는 비용
			}
			
			if (arr[choiceCity[n - 1]][choiceCity[0]] == 0) return; // 0일 경우 종료
			else sum += arr[choiceCity[n - 1]][choiceCity[0]]; // 0이 아닐 경우 마지막 도시에서 처음 도시로 가는 비용
			min = Math.min(sum, min); // 최소값 구하기
			return;
		}
		
		// 순열 구하기
		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			choiceCity[count] = i;
			visited[i] = true;
			perm(count + 1);
			visited[i] = false;			
		}
	}
}