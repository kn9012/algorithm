import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean isVisited[];
	static int count = 0, answer, n, m, arr[][], b;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine()); // 전체 사람 수
		
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()); // 계산할 첫번재 사람
		b = Integer.parseInt(st.nextToken()); // 계산할 두번째 사람
		
		m = Integer.parseInt(br.readLine()); // 부모 자식들 간의 관계 수
		
		arr = new int[n + 1][n + 1];
		isVisited = new boolean[n + 1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			arr[x][y] = 1; // x가 y의 부모임을 표시
		}
		
		isVisited[a] = true;
		dfs(a, 0);
		
		if (answer == 0) System.out.println(-1);
		else System.out.println(answer);
	}
	
	static void dfs(int num, int count) {
		if (num == b) {
			answer = count;
			return;
		}
		
		for (int i = 1; i <= n; i++) {
			if ((arr[num][i] == 1 || arr[i][num] == 1) && !isVisited[i]) {
				isVisited[i] = true;
				dfs(i, count + 1);
				isVisited[i] = false;
			}
		}
	}
}
