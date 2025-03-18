import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1253번 좋다
 * 어떤 수가 다른 두 개의 수의 합으로 나타낼 수 있으면 좋은 수
 * 수의 위치가 다르면 값이 같아도 다른 수 
 * => 이분탐색 사용
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			int left = 0;
			int right = N - 1;
			
			while (left < right) {
				int sum = nums[left] + nums[right];
				
				if (i == left) {
					left++;
					continue;
				}
				if (i == right) {
					right--;
					continue;
				}
				
				if (sum < nums[i]) {
					left++;
				} else if (sum > nums[i]) {
					right--;
				} else {
					answer++;
					break;
				}
			} 
		}
		
		System.out.println(answer);
	}
}
