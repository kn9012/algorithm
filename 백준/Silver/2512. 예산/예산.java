import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 2512번 예산
 * 
 * 지방의 요구 예산액 합이 정해진 예산보다 작다면 모두 배정해주지만
 * 만약 초과한다면 상한액을 정하여 가장 큰 예산액을 출력하라
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		int left = 0;
		int right = -1;
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
			right = Math.max(right, arr[i]);
		}
		int money = Integer.parseInt(br.readLine());
		
		while (left <= right) {
			int mid = (left + right) / 2;
			long budget = 0;
			
			for (int i = 0; i < N; i++) {
				if (arr[i] > mid) budget += mid;
				else budget += arr[i];
			}
			
			if (budget <= money) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(right);
	}
}
