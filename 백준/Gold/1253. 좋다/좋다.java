import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1253번 좋다
 * - 투 포인터
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int nums[] = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int count = 0;
		
		Arrays.sort(nums);
		
		for (int i = 0; i < N; i++) {
			int left = 0;
			int right = N - 1;
			
			while (true) {
				if (i == left) left++;
				if (i == right) right--;
				
				if (left >= right) break;
				
				if (nums[left] + nums[right] == nums[i]) {
					count++;
					break;
				}
				else if (nums[left] + nums[right] > nums[i]) right--;
				else left++;
			}
		}
		
		System.out.println(count);
	}
}
