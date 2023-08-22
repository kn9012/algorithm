
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 1759번 암호 만들기
 * 암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음과 최소 두 개의 자음으로 구성되어 있다.
 * 또한, 암호를 이루는 알파벳이 증가하는 순서로 배열되었을 때 암호들을 모두 구하는 프로그램을 작성해라.
 * 
 * [아이디어]
 * 조합을 이용하여 c개의 암호 후보 문자 중 l개를 골라 암호를 만든 후, 이 암호가 모음 최소 1개, 자음 최소 2개를 만족하는지 체크해준다.
 * 이때, 모음 개수와 자음 개수를 셀 때 주의해주기!
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 * 
 * @author 김유나
 * 2023-08-22
 * 
 */
public class Main {
	static char[] vowel = {'a', 'e', 'i', 'o', 'u'}; // 모음 a, e, i, o, u
	static char[] arr, code;
	static int l, c;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		l = Integer.parseInt(st.nextToken()); // 암호의 자리 수
		c = Integer.parseInt(st.nextToken()); // 암호 후보 문자 수
		arr = new char[c]; // 암호 후보 문자 배열
		code = new char[l]; // 조합할 암호 배열
		
		st = new StringTokenizer(br.readLine());
		
		// 암호 후보 문자 배열 입력 받기
		for (int i = 0; i < c; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(arr); // 오름차순 정렬
		
		comb(0, 0); // 조합
		
		System.out.println(sb);
	}
	
	static void comb(int start, int count) {
		if (count == l) { // l개를 골랐을 경우
			if (check()) { // 조건을 만족하는지 확인
				// 만족한다면 StringBuilder에 암호 한 글자씩 넣기
				for (int i = 0; i < l; i++) {
					sb.append(code[i]);
				}
				sb.append("\n"); // 줄 띄어쓰기
			}
			return; // 종료
		}
		
		// 조합 부분
		for (int i = start; i < c; i++) {
			code[count] = arr[i];
			comb(i + 1, count + 1);
		}
	}
	
	// 모음 최소 1개, 자음 최소 2개를 만족하는지 체크하는 함수
	static boolean check() {
		int vCnt = 0; // 모음 개수
		int cCnt = 0; // 자음 개수
		
		for (int i = 0; i < l; i++) {
			boolean isVowel = false; // 모음 체크 boolean 변수
			for (int j = 0; j < 5; j++) {
				// 만약 모음이라면
				if (code[i] == vowel[j]) {
					vCnt++; // count++
					isVowel = true; // 모음인지 체크 후
				}
			}
			if (!isVowel) cCnt++; // 안쪽 for문 밖에서 count++를 해줘야 함
			
			// 모음 최소 1개, 자음 최소 2개를 만족한다면 true return
			if (vCnt >= 1 && cCnt >= 2) return true;
		}
		
		return false; // 만족하지 않는다면 false return
	}
}