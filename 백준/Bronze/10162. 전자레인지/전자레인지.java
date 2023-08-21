import java.util.Scanner;

/**
 * [문제]
 * 지정된 시간이 5분인 버튼 A, 1분인 버튼 B, 10초인 버튼 C가 달려있는 오븐이 있을 떄, 주어진 시간 T초를 정확히 맞출 수 있는 최소 횟수를 구하여라.
 * 이떄, 3개의 버튼으로 T초를 맞출 수 없다면 -1을 출력해라.
 * 
 * [아이디어]
 * 5분은 300초, 1분은 60초로 환산하여 T초가 각각의 시간보다 클때, T초에서 각각의 시간을 빼주고 뺀 값으로 자신을 호출하는 재귀함수를 구현했다.
 * 300초는 60초의 배수이며, 60초는 10초의 배수기 때문에 이렇게 풀이하였다.
 * 
 * @author 김유나
 * 2023-08-21
 *
 */
public class Main {
	static int t, aCount = 0, bCount = 0, cCount = 0; // T초와 A, B, C 버튼 누른 횟수
	static boolean isPossible = false; // T초를 맞출 수 있는지
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in); // Scanner 사용
		t = s.nextInt(); // 요리시간 입력 받기
		
		cooking(t);
		
		if (isPossible) System.out.println(aCount + " " + bCount + " " + cCount); // 가능할 경우 A B C 횟수 출력
		else System.out.println(-1); // 불가능할 경우 -1 출력
	}
	
	static void cooking(int t) {
		int fiveMin = 300; // 5분
		int oneMin = 60; // 1분
		int tenSec = 10; // 10초
		
		if (t == 0) { // 기저조건 : (t가 0이 될 경우 즉, T초를 맞출 경우)
			isPossible = true; // true
			return; // 종료
		}
		
		else {
			if (t >= fiveMin) { // t가 5분보다 크거나 같을 경우
				aCount++; // A 버튼 횟수 ++
				t -= fiveMin;
				cooking(t); // 5분을 뺀 값으로 다시 호출
			}
			else if (t >= oneMin) { // t가 1분보다 크거나 같을 경우
				bCount++; // B 버튼 횟수 ++
				t -= oneMin;
				cooking(t); // 1분을 뺀 값으로 다시 호출
			}
			else if (t >= tenSec) { // t가 10초보다 크거나 같을 경우
				cCount++; // C 버튼 횟수 ++
				t -= tenSec;
				cooking(t); // 10초을 뺀 값으로 다시 호출
			}
		}
	}
}
