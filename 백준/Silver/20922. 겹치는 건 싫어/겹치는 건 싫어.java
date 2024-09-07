import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 20922번 겹치는 건 싫어
 * - 투 포인터 사용?
 * 
 * 메모리 : kb 시간 : ms
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 수열 길이
		int K = Integer.parseInt(st.nextToken()); // 같은 정수 제한 개수
		
		int arr[] = new int[N]; // 수열 넣는 배열
		int num[] = new int[100001]; // 
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		int start = 0;
		int end = 0;
		
		while (end < N) {
			while (end < N && num[arr[end]] < K) {
				num[arr[end]]++;
				end++;
			}
			
			int len = end - start;
			max = Math.max(max, len);
			num[arr[start]]--;
			start++;
		}
		
		System.out.println(max);
	}
}
