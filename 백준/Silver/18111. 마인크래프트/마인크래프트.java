import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 18111 마인크래프트
 * - 블록 제거할 땐 2초, 놓을 땐 1초
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 세로
		int M = Integer.parseInt(st.nextToken()); // 가로
		int B = Integer.parseInt(st.nextToken()); // 인벤토리에 들어있는 블록
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		int map[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(min, map[i][j]);
				max = Math.max(max, map[i][j]);
			}
		}
		
		int time = Integer.MAX_VALUE;
		int height = 0;
		
		for (int i = min; i <= max; i++) {
			int count = 0;
			int block = B;
			
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (i < map[j][k]) {
						count += (map[j][k] - i) * 2;
						block += (map[j][k] - i);
					} else {
						count += (i - map[j][k]);
						block -= (i - map[j][k]);
					}
				}
				
				
			}
			if (block < 0) break;
			
			if (time >= count) {
				time = count;
				height = i;
			}
		}
		
		System.out.println(time + " " + height);
	}
}
