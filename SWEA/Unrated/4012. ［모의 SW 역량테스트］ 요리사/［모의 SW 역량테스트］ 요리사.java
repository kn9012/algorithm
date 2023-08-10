import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 김유나
 * 2023-08-10
 * [문제] SWEA 4012번 요리사
 * - N개의 식재료가 있을 때 각각 N/2개씩 나누어 두 개의 요리를 할 때 재료끼리의 시너지가 나는데 두 요리의 시너지 차가 가장 작은 경우를 구하여라. 
 * [아이디어]
 * - N개의 재료 중 N/2개를 순서 없이 골라야하므로 재귀를 이용한 조합을 구현한다.
 * - 이때, 선택된 재료들이 A의 재료, 선택되지 않은 재료들이 B의 재료라 하고 각각의 시너지 합을 구한 뒤 가장 작은 차를 구한다.
 * 
 * 메모리 : 26,960kb 실행 시간 : 278ms
 */

public class Solution {
    static int N, min, diff;
    static int[][] synergy;
    static int[] power;
    static boolean[] selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            synergy = new int[N][N]; // 시너지 배열
            selected = new boolean[N]; // 선택됐는지 체크
            
            min = Integer.MAX_VALUE; // 전력 차의 가장 작은 값
            
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            powerSet(0, 0); // 재귀 호출
            
            System.out.println("#" + tc + " " + min);
        }
    }
    
    static void powerSet(int count, int start) {
        if (count == N / 2) { // N/2만큼 반복됐을 때
        	power = new int[2]; // A와 B의 전력
        	
            // A 음식 전력 구하기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (selected[i] && selected[j]) power[0] += synergy[i][j]; // 선택된 재료들 : A의 전력에 더하기
                }
            }
            
            // B 음식 전력 구하기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!selected[i] && !selected[j]) power[1] += synergy[i][j]; // 선택되지 않은 재료들 : B의 전력에 더하기
                }
            }
            
            // min과 |A 전력 - B 전력|의 최솟값 구하기
            min = Math.min(min, Math.abs(power[0] - power[1]));
            return;
        }
        
        // 조합 부분
        for (int i = start; i < N; i++) {
            selected[i] = true;
            powerSet(count + 1, i + 1);
            selected[i] = false;
        }
    }
}