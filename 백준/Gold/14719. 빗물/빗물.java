import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 14719번 빗물
 * - 작아지는 부분을 고려하기?
 * - 빗물이 고이는 경우 : 무조건 양 기둥이 있고 파인 부분이 있어야 함  그리고 더 작은 기둥의 높이를 넘을 수 없음
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		List<int []> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		
		int max = -1;
		int maxIndex = -1;
		int sum = 0;
		
		for (int i = 0; i < W; i++) {
			int height = Integer.parseInt(st.nextToken());
			list.add(new int [] {i, height});
			
			sum += height;
			
			if (max < height) {
				max = height;
				maxIndex = i;
			}
		}
		
		int skeleton = 0;
		
		for (int i = 0; i < maxIndex; i++) {
			for (int j = i + 1; j <= maxIndex; j++) {
				if (list.get(i)[1] <= list.get(j)[1]) {
					skeleton += (list.get(j)[0] - list.get(i)[0]) * list.get(i)[1];
					i = j;
				}
			}
		}
		
		for (int i = W - 1; i > maxIndex; i--) {
			for (int j = i - 1; j >= maxIndex; j--) {
				if (list.get(i)[1] <= list.get(j)[1]) {
					skeleton += (list.get(i)[0] - list.get(j)[0]) * list.get(i)[1];
					i = j;
				}
			}
		}
		
		if (skeleton == 0) System.out.println(0);
		else System.out.println(skeleton + max - sum);
	}
}
