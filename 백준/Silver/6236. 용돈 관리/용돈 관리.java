import java.util.*;
import java.io.*;

/**
 * 백준 6236번 용돈 관리
 * - 매개변수 이분탐색
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int money[] = new int[N];
		int left = 1;
		int right = 0;
		
		for (int i = 0; i < N; i++) {
			money[i] = Integer.parseInt(br.readLine());
			left = Math.max(left, money[i]);
			right += money[i];
		}

		int mid = 0;
		
		while (left <= right) {
			mid = (left + right) / 2;
			int count = 1;
			int wallet = 0;
			
			for (int i = 0; i < N; i++) {
				wallet += money[i];
				
				if (wallet > mid) {
					count++;
					wallet = money[i];
				}
			}
            
			if (count <= M) {
				right = mid - 1;
			} else if (count > M) {
				left = mid + 1;
			}
		}
		
		System.out.println(left);
	}
}
