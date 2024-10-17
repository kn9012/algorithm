import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		int arr[] = new int[N];
		int sushi[] = new int[d + 1];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int start = 0;
		int end = start + k - 1;
		
		int count = 0;
		for (int i = start; i <= end; i++) {
			if (sushi[arr[i]] == 0) count++;
			sushi[arr[i]]++;
		}
		
		int max = 0;
		
		while (start < N) {
			if (max <= count) {
				if (sushi[c] == 0) max = count + 1;
				else max = count;
			}
			
			if (sushi[arr[start]] == 1) count--;
			sushi[arr[start]]--;
			
			start++;
			end = (end + 1) % N;
			
			if (sushi[arr[end]] == 0) count++;
			sushi[arr[end]]++;
		}
		
		System.out.println(max);
	}
}
