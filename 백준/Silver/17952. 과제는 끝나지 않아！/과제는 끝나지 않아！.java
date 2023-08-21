import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제]
 * 김삼성을 위한 3가지 규칙을 가지고 업무를 진행하는 프로그램을 구현한다.
 * 1. 업무는 최근에 주어진 순서대로, 받자마자 바로 처리한다.
 * 2. 업무를 하던 중 새로운 업무 추가 -> 하던 업무 중단 후 새로운 업무를 진행한다.
 * 3. 새로운 업무가 끝나면 직전에 하던 업무를 이어서 한다.
 * 
 * 업무를 끝낸다면, 각각의 업무에 주어진 점수를 더하여 김삼성이 한 분기에 받을 수 있는 점수를 구하여라.
 * 
 * [아이디어]
 * 가장 최근에 했던 업무의 인덱스를 저장하는 것이 중요하다고 생각했다.
 * 입력받은 x가 1일 경우 그 인덱스를 받아 저장한 후, 받자마자 -1분을 했으며 x가 0일 경우에는 한 싸이클마다 -1을 해주었다.
 * 이때, 업무가 끝났다면 업무 만점을 score 변수에 더한 후 시간이 남아있는 업무를 찾아 그 업무의 인덱스를 저장해주었다.
 * 
 * @author 김유나
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine()); // 이번 분기가 몇분인지
		int[] a = new int[n]; // 업무의 만점을 담을 배열
		int[] t = new int[n]; // 업무를 해결하는데 걸리는 시간을 담을 배열
		int newWork = -1; // 가장 최근에 했던 업무 인덱스
		int score = 0; // 업무 평가 점수
		
		for (int i = 0 ; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 1 : 업무가 주어짐, 0 : 업무가 주어지지 않음
			
			// 업무가 주어지지 않았을 때
			if (x == 0) {
				if (newWork != -1) { // 주어진 업무가 있다면(초기값이 -1인데 -1이 아니라면)
					t[newWork]--; // 그 업무의 시간 --
					
					if (t[newWork] == 0) { // 업무가 끝났다면
						score += a[newWork]; // 업무 만점 더하기
						a[newWork] = 0; // 업무 만점 0으로 초기화
						
						// 뒤에서부터 시간이 남아있는 업무가 있다면 그 업무의 인덱스 저장
						for (int j = newWork - 1; j >= 0; j--) {
							if (t[j] != 0) {
								newWork = j;
								break;
							}
						}
					}
				}
			}
			
			// 과제가 주어졌을 때
			else {
				newWork = i; // 가장 최근에 주어진 업무 인덱스 저장
				a[i] = Integer.parseInt(st.nextToken()); // 주어진 업무의 만점
				t[i] = Integer.parseInt(st.nextToken()) - 1; // 업무를 해결하는데 걸리는 시간 : 받자마자 바로 시작하므로 -1분
				
				if (t[i] == 0) { // 업무가 끝났다면
					score += a[i]; // 업무 만점 더하기
					a[i] = 0; // 업무 만점 0으로 초기화
					
					// 뒤에서부터 시간이 남아있는 업무가 있다면 그 업무의 인덱스 저장
					for (int j = newWork - 1; j >= 0; j--) {
						if (t[j] != 0) {
							newWork = j;
							break;
						}
					}
				}
			}
		}
		
		// 점수 출력
		System.out.println(score);
	}
}
