import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 2461번 대표 선수 
 * 반별로 오름차순 정렬 후 투 포인터 사용?
 * O(NM)만큼 수행
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] student = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				student[i][j] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(student[i]); // 반별로 오름차순 정렬
		}
		
		// 값, 반 번호, 인덱스 저장
		PriorityQueue<int []> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
		int maxValue = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			pq.add(new int[] {student[i][0], i, 0});
			maxValue = Math.max(maxValue, student[i][0]);
		}
		
		int minDiff = Integer.MAX_VALUE;
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int ability = cur[0];
			int classIdx = cur[1];
			int idx = cur[2];
			
			minDiff = Math.min(minDiff, maxValue - ability);
			
			if (idx + 1 == M) break;
			
			int nextAbility = student[classIdx][idx + 1];
			pq.add(new int[] {nextAbility, classIdx, idx + 1});
			maxValue = Math.max(maxValue, nextAbility);
		}
		
		System.out.println(minDiff);
	}
}
