import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/**
 * 
 * @author 김유나
 * 2023-08-10
 * 
 * [문제] SWEA 5215번 햄버거 다이어트
 * [아이디어]
 * - 재료를 몇개 넣을지 모르기 때문에 부분집합을 이용한다.
 * - isSelected를 통해 체크를 한 뒤, 체크되어 있다면 점수의 합과 칼로리의 합을 구하여 칼로리가 제한 칼로리보다 낮다면, 가장 큰 점수를 구한다.
 * 
 * 메모리 : 21,380kb 실행 시간 : 840ms
 */
 
public class Solution {
    static int N, L, tMax, tSum = 0, kSum = 0;
    static int Ti[], Ki[];
    static boolean[] isSelected;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스
         
        for (int tc = 1; tc <= T; tc++) {
             
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 재료의 수
            L = Integer.parseInt(st.nextToken()); // 제한 칼로리
             
            Ti = new int[N]; // 맛 점수 배열
            Ki = new int[N]; // 칼로리 배열
            isSelected = new boolean[N];
            tMax = 0; // 점수 최대값
             
            for (int i = 0; i < N; i++) { // 재료
                st = new StringTokenizer(br.readLine());
                Ti[i] = Integer.parseInt(st.nextToken());
                Ki[i] = Integer.parseInt(st.nextToken());
            }
             
            hamburger(0); // 재귀함수 호출
             
            System.out.println("#" + tc + " " + tMax);
        }
         
    }
     
    static void hamburger(int count) {
        if (count == N) {
            tSum = 0; // 점수 합
            kSum = 0; // 칼로리 합
             
            for (int i = 0; i < N; i++) {
                if(isSelected[i]) { // 선택됐다면 점수와 칼로리의 합 각각 더하기
                    tSum += Ti[i];
                    kSum += Ki[i];
                }
            }
             
            if (kSum <= L) { // 칼로리의 합이 제한 칼로리 이하라면
                tMax = Math.max(tMax, tSum); // 최대 점수 구하기
            }
            return;
        }
         
        isSelected[count] = true;
        hamburger(count + 1);
        isSelected[count] = false;
        hamburger(count + 1);
    }
}