import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 백준 18870번 좌표 압축
 * - 오름차순 정렬해서 위치 반환?
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int arr[] = new int[N];
		int sortArr[] = new int[N];
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			arr[i] = sortArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(sortArr);
		
		int rank = 0;
		for (int num : sortArr) {
			if (!map.containsKey(num)) {
				map.put(num, rank);
				rank++;
			}
		}
		
		for (int num : arr) {
			sb.append(map.get(num) + " ");
		}
		
		System.out.println(sb);
	}
}
