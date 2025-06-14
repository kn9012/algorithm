import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2613번 숫자구슬
 * - 매개 변수 탐색
 */

public class Main {
	public static int N, M, left, right;
	public static int[] num;
	public static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		left = 0;
		right = 0;
	
		num = new int[N];
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			
			left = Math.max(left, num[i]); // 구슬 중 가장 큰 값
			right += num[i]; // 모든 구슬 값의 합
		}
		
		binarySearch(); // 매개 변수 탐색
		printGroupSize();
	}
	
	public static void binarySearch() {
		while (left <= right) {
			int mid = left + (right - left) / 2;
		
			int group = countBeadGroup(mid);
			
			if (group > M) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		sb.append(left + "\n");
	}
	
	public static int countBeadGroup(int mid) {
		int group = 1;
		int sum = 0;
		
		for (int n : num) {
			sum += n;
			
			if (sum > mid) {
				group++;
				sum = n;
			}
		}
		
		return group;
	}
	
	public static void printGroupSize() {
		int beadsInGroup = 0;
		int groupSum = 0;
		
		for (int i = 0; i < N; i++) {
			groupSum += num[i];
			
			if (groupSum > left) { // 구슬의 합이 최댓값을 넘어갈 경우
				M--; // 그룹이 만들어졌으므로 M--
				groupSum = num[i]; // 합 초기화
				sb.append(beadsInGroup + " "); // 현재 그룹의 구슬 수 출력
				beadsInGroup = 1; // 구슬 수 초기화
			} else beadsInGroup++;
			
			if (M == N - i) break; // 남은 그룹 수 == 남은 구슬 수
		}
		
		while (M-- > 0) {
			sb.append(beadsInGroup + " ");
			beadsInGroup = 1;
		}
		
		System.out.println(sb.toString());
	}
}
