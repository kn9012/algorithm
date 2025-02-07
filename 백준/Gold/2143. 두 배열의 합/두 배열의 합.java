import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 백준 2143번 두 배열의 합 
 * - 누적합 + 해시맵?
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int N = Integer.parseInt(br.readLine());
		int A[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		int B[] = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		List<Integer> listA = new ArrayList<>();
		List<Integer> listB = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = i; j < N; j++) {
				sum += A[j];
				listA.add(sum);
			}
		}
		
		for (int i = 0; i < M; i++) {
			int sum = 0;
			for (int j = i; j < M; j++) {
				sum += B[j];
				listB.add(sum);
			}
		}
		
		Collections.sort(listA);
		Collections.sort(listB);
		
		long answer = 0;
		
		int left = 0;
		int right = listB.size() - 1;
		
		while (left < listA.size() && right >= 0) {
			int sum = listA.get(left) + listB.get(right);
			
			if (sum == T) {
				long aCount = 1;
				long bCount = 1;
				
				while (left + 1 < listA.size() && listA.get(left).equals(listA.get(left + 1))) {
					left++;
					aCount++;
				}
				
				while (right - 1 >= 0 && listB.get(right).equals(listB.get(right - 1))) {
					right--;
					bCount++;
				}
				
				answer += aCount * bCount;
						
				right--;
				left++;
			} else if (sum < T) left++;
			else right--;
		}
		
		System.out.println(answer);
	}
}
