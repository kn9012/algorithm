import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김유나
 * [문제] SWEA 1225번 암호생성기
 * 
 * [아이디어] 
 *
 */
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int T = 1; T <= 10; T++) {
			int tc = Integer.parseInt(br.readLine());
			Queue<Integer> q = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < 8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			
			int count = 1;
			
			while (count <= 5) {
				int first = q.poll() - count++;
				
				if (first <= 0) {
					q.offer(0);
					break;
				}
				
				if (count == 6) {
					count = 1;
				}
				
				q.offer(first);
			}
			
			sb.append("#").append(T).append(" ");
			
			for (int i = 0; i < 8; i++) {
				sb.append(q.poll()).append(" ");
			}
			
			System.out.println(sb);
		}
	}

}
