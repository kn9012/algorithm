import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * @author 김유나
 * 2023-08-07
 * 
 * [문제] SWEA 1228번 SW 문제해결 기본 8일차 - 암호문1
 * 원본 암호문의 길이와 원본 암호문, 명령어의 개수, 명령어(I x, y, s)가 주어졌을 때
 * 원본 암호문의 x 위치 바로 다음에 y개의 숫자를 삽입하고 처음 10개 숫자만 출력해라. 이때, s는 덧붙일 숫자들이다.
 * 
 * [아이디어]
 * - 숫자들의 중간에 삽입해야 될 때, 용이한 LinkedList를 사용
 * - "I"가 나오는지 판별하는 방법은 equals 사용
 * 
 * 메모리 : 18,216KB, 시간 : 106ms
 */

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 시간 단축을 위해 BufferedReader 사용
		
		for (int T = 1; T <= 10; T++) {
			int N = Integer.parseInt(br.readLine()); // 원본 암호문의 길이 입력 받기
			StringTokenizer st = new StringTokenizer(br.readLine()); // 원본 암호문
			
			LinkedList<Integer> list = new LinkedList<>(); // LinkedList 선언
			
			for (int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken())); // LinkedList에 원본 암호문 넣기
			}
			
			int C = Integer.parseInt(br.readLine()); // 명령어 개수 입력 받기
			st = new StringTokenizer(br.readLine()); // 명령어
			
			for (int i = 0; i < C; i++) {
				if (st.nextToken().equals("I")) { // 만약, nextToken()이 I와 같다면
					int x = Integer.parseInt(st.nextToken()); // x는 다음 token
					int y = Integer.parseInt(st.nextToken()); // y는 다음 token
					for (int j = 0; j < y; j++) {
						list.add(x, Integer.parseInt(st.nextToken())); // 덧붙일 숫자들
						x++;
					}
				}
			}
			System.out.print("#" + T + " ");
			
			for (int i = 0; i < 10; i++) { // 10개만 출력
				System.out.print(list.get(i) + " ");
			}
			System.out.println();
		}
	}
}
