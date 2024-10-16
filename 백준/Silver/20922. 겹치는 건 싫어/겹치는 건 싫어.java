import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int arr[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int num[] = new int[100001];
		int max = 0;
		int start = 0;
		int end = 1;
		
		num[arr[start]]++;
		
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
