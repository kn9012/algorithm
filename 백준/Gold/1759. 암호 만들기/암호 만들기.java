import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 1759번 암호 만들기
 * 암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음과 최소 두 개의 자음으로 구성되어 있다.
 * 또한, 암호를 이루는 알파벳이 증가하는 순서로 배열되었을 때 암호들을 모두 구하는 프로그램을 작성해라.
 * 
 * [아이디어]
 * 암호 후보 문자 수를 오름차순으로 정렬한 후 조합을 사용하여 모음이 최소 1개, 자음이 최소 2개인 암호를 만든다.
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 * 
 * @author 김유나
 * 2023-08-22
 * 
 */
public class Main {
	static char[] vowel = {'a', 'e', 'i', 'o', 'u'};
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
		
		for (int i = 0; i < c; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(arr);
		
		comb(0, 0);
		
		System.out.println(sb);
	}
	
	static void comb(int start, int count) {
		if (count == l) {
			if (check()) {
				for (int i = 0; i < l; i++) {
					sb.append(code[i]);
				}
				sb.append("\n");
			}
			return;
		}
		
		for (int i = start; i < c; i++) {
			code[count] = arr[i];
			comb(i + 1, count + 1);
		}
	}
	
	static boolean check() {
		int vCnt = 0;
		int cCnt = 0;
		
		for (int i = 0; i < l; i++) {
			boolean isVowel = false;
			for (int j = 0; j < 5; j++) {
				if (code[i] == vowel[j]) {
					vCnt++;
					isVowel = true;
				}
			}
			if (!isVowel) cCnt++;
			if (vCnt >= 1 && cCnt >= 2) return true;
		}
		
		return false;
	}
}