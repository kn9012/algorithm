import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 12015번 가장 긴 증가하는 부분 수열 2
 * - 이분탐색
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)num[i] = Integer.parseInt(st.nextToken());
		
		List<Integer> temp = new ArrayList<>();

		for (int x : num) {
		    int idx = Collections.binarySearch(temp, x);
		    if (idx < 0) {
		        idx = -(idx + 1); // insertion point
		    }
		    
		    if (idx == temp.size()) {
		        temp.add(x);
		    } else {
		        temp.set(idx, x);
		    }
		}

		System.out.println(temp.size());
	}
}
