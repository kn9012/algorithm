import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 백준 1602번 나는야 포켓몬 마스터 이다솜
 * - HashMap 2개 사용..?
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> name = new HashMap<>();
		Map<Integer, String> num = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			
			name.put(str, i + 1);
			num.put(i + 1, str);
		}
		
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			
			if (str.charAt(0) >= 65 && str.charAt(0) <= 122) {
				sb.append(name.get(str) + "\n");
			} else {
				sb.append(num.get(Integer.parseInt(str)) + "\n");
			}
		}
		
		System.out.println(sb);
	}
}
