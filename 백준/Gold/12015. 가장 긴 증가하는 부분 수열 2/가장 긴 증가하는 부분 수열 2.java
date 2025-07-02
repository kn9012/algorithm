import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)num[i] = Integer.parseInt(st.nextToken());
		
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			if (list.isEmpty() || list.get(list.size() - 1) < num[i]) list.add(num[i]);
			else {
				int left = 0;
				int right = list.size() - 1;
				
				while (left < right) {
					int mid = left + (right - left) / 2;
						
					if (list.get(mid) >= num[i]) {
						right = mid;
					} else {
						left = mid + 1;
					}
				}
					
				list.set(left, num[i]);
			}
		}
		
		System.out.println(list.size());
	}
}
