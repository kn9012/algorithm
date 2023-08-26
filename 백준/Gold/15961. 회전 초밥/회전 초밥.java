import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 15961번 회전 초밥 회전 초밥 벨트에 놓은 접시의 수와 초밥의 가짓수, 연속해서 먹는 접시의 수와 쿠폰 번호가 주어지고
 * 초밥의 종류를 나타내는 1 이상 d 이하의 정수가 각 줄마다 하나씩 주어질 때 주어진 회전 초밥 벨트에서 먹을 수 있는 초밥 가짓수의
 * 최댓값을 구하라.
 * 
 * [아이디어] 다양한 종류의 초밥을 먹는 것이 중요 일단 초밥을 k개씩 선택한 후 선택된 초밥 중 쿠폰 번호의 초밥이 있는지 확인 -> 있다면 +1 슬라이딩 윈도우 사용
 * 
 * 메모리 : kb
 * 실행 시간
 * 
 * @author 김유나
 * 2023-08-24
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken()); // 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		int[] sushi = new int[n]; // 초밥의 종류를 저장할 배열
		int[] num = new int[d + 1]; // 먹은 초밥의 종류를 저장할 배열
		int count = 0;

		for (int i = 0; i < n; i++) {
			sushi[i] = Integer.parseInt(br.readLine()); // 입력 받기
		}
		
		for (int i = 0; i < k; i++) {
			if (num[sushi[i]] == 0) count++;  
			num[sushi[i]]++;
		}
		
		int max = count;
		
		for (int i = 0; i < n; i++) {
			if (max <= count) {
				if (num[c] == 0) max = count + 1;
				else max = count;
			}
			
			num[sushi[i]]--; // 처음에 먹은 스시는  빼주고
			if (num[sushi[i]] == 0) count--;
			
			if (num[sushi[(i + k) % n]] == 0) count++;
			// 만약 처음에 먹은 스시와 다음에 먹을 스시가 같은 번호가 아니라면
			num[sushi[(i + k) % n]]++; // 다음에 먹을 스시 ++
		}
		
		System.out.println(max);
	}
}