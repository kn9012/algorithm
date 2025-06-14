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
			
			left = Math.max(left, num[i]);
			right += num[i];
		}
		
		binarySearch();
		printGroupSize();
	}
	
	public static void binarySearch() {
		while (left <= right) {
			int mid = left + (right - left) / 2;
		
			int count = countBundle(mid);
			if (count > M) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		sb.append(left + "\n");
	}
	
	public static int countBundle(int mid) {
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
		int count = 0;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += num[i];
			
			if (sum > left) {
				M--;
				sum = num[i];
				sb.append(count + " ");
				count = 1;
			} else count++;
			
			if (M == N - i) break;
		}
		
		while (M-- > 0) {
			sb.append(count + " ");
			count = 1;
		}
		
		System.out.println(sb.toString());
	}
}
