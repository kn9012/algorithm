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
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int arr[] = new int[N];
		int num[] = new int[100001];
		
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
			
			max = Math.max(max, end - start);
			num[arr[start]]--;
			start++;
		}
		
		System.out.println(max);
	}
}
