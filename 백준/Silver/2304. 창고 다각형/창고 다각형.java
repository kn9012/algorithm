import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		List<int []> list = new ArrayList<>();
		int max = -1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int index = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());

			list.add(new int[] {index, height});
			
			if (max < height) {
				max = height;
			}
		}
		
		Collections.sort(list, (o1, o2) -> o1[0] - o2[0]);
		
		int maxIndex = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)[1] == max) {
				maxIndex = i;
			}
		}
		
		int sum = 0;
		for (int i = 0; i < maxIndex; i++) {
			for (int j = i + 1; j <= maxIndex; j++) {
				if (list.get(i)[1] <= list.get(j)[1]) {
					sum += (list.get(j)[0] - list.get(i)[0]) * list.get(i)[1];
					i = j;
				}
			}
		}
		
		for (int i = N - 1; i > maxIndex; i--) {
			for (int j = i - 1; j >= maxIndex; j--) {
				if (list.get(i)[1] <= list.get(j)[1]) {
					sum += (list.get(i)[0] - list.get(j)[0]) * list.get(i)[1];
					i = j;
				}
			}
		}
		
		System.out.println(sum + max);
	}
}
