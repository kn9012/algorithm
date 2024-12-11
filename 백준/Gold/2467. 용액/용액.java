import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2467번 용액
 * - 투 포인터
 */

public class Main  {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr[] = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 양 끝에서부터 비교
		int start = 0;
		int end = N - 1;
		
		int startN = 0;
		int endN = 0;
		
		long min = Long.MAX_VALUE;
		
		while (start < end) {
			if (min > Math.abs(arr[end] + arr[start])) {
				min = Math.abs(arr[end] + arr[start]);
				startN = arr[start];
				endN = arr[end];
			}
			
			if (arr[end] + arr[start] >= 0) end--;
			else start++;
		}
		
		System.out.println(startN + " " + endN);
	}
}
