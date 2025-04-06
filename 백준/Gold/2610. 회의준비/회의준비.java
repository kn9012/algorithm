import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 2610번 회의준비
 * - Union-Find + 플로이드 워셜 + 우선순위 큐 사용
 */

public class Main {
	static int N, dist[][];
	static int[] parent;
	static boolean[] selected;
	static PriorityQueue<Integer> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 사람의 수
		int M = Integer.parseInt(br.readLine()); // 관계의 수
		
		dist = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
		for (int i = 1; i <= N; i++) dist[i][i] = 0;
		
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) parent[i] = i;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			union(a, b);
			
			dist[a][b] = dist[b][a] = 1;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) continue;
					
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		pq = new PriorityQueue<>();
		selected = new boolean[N + 1];
		int groupCount = 0;
		
		for (int i = 1; i <= N; i++) {
			if (!selected[i]) {
				int leader = findLeader(i);
				pq.add(leader);
				groupCount++;
			}
		}
		
		System.out.println(groupCount);
		
		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
	}
	
	public static int find(int n) {
		if (parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a != b) {
			parent[b] = a;
		}
	}
	
	public static int findLeader(int n) {
		int root = find(n);
		selected[n] = true;
		
		List<Integer> group = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (root == find(i)) { // 부모가 같으면 같은 그룹
				group.add(i);
				selected[i] = true;
			}
		}
		
		int minCommunicationTime = Integer.MAX_VALUE;
		int leader = -1;
		
		for (int node : group) {
			int maxDist = 0;
			
			for (int other : group) {
				maxDist = Math.max(maxDist, dist[node][other]);
			}
			
			if (maxDist < minCommunicationTime) {
				minCommunicationTime = maxDist;
				leader = node;
			}
		}
		
		return leader;
	}
	
}
