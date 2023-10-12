import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 17471번 게리맨더링 [아이디어]
 * 
 * 메모리 : kb 실행 시간 : ms
 * 
 * @author 김유나 2023-10-11
 * 
 */
public class Main {
	static int n, people[], min = Integer.MAX_VALUE;
	static List<List<Integer>> graph;
	static boolean selected[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		people = new int[n + 1];
		graph = new ArrayList<>();
		selected = new boolean[n];

		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());

			for (int j = 0; j < count; j++) {
				graph.get(i).add(Integer.parseInt(st.nextToken()) - 1);
			}
		}

		subset(0);

		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	static void subset(int count) {
		if (count == n) {
			isLink();
			return;
		}

		selected[count] = true;
		subset(count + 1);
		selected[count] = false;
		subset(count + 1);
	}

	static void isLink() {
		List<Integer> alist = new ArrayList<>();
		List<Integer> blist = new ArrayList<>();
		
		int area1 = 0;
		int area2 = 0;
		
		for (int i = 0; i < n; i++) {
			if (selected[i]) {
				alist.add(i);
				area1 += people[i];
			}
			if (!selected[i]) {
				blist.add(i);
				area2 += people[i];
			}
		}
		
		if (area1 == 0 || area2 == 0) return;
		
		if (!isConnected(alist) || !isConnected(blist)) return;
		
		min = Math.min(min, Math.abs(area1 - area2));
	}

	static boolean isConnected(List<Integer> list) {
		boolean isConnected = false;
		
		Queue<Integer> queue = new ArrayDeque<>();
		boolean visited[] = new boolean[n];
		
		queue.add(list.get(0));
		visited[list.get(0)] = true;

		int cnt = 1;
		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int i : graph.get(cur)) {
				if (list.contains(i) && !visited[i]) {
					queue.add(i);
					visited[i] = true;
					cnt++;
				}
			}
		}
		
		if (cnt == list.size()) isConnected = true;
		return isConnected;
	}
}