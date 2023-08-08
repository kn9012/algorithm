import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/**
 * 
 * @author 김유나
 * 2023-08-08
 * 
 * [문제] SWEA 1233번 SW 문제해결 기본 9일차 사칙연산 유효성 검사
 * 사칙연산 "+, -, *, /"와 양의 정수로만 구성된 임의의 이진 트리가 주어질 때, 이 식의 유효성을 검사하는 프로그램을 작성하여라.
 * 
 * [아이디어] 
 * 노드가 단말 노드인지 아닌지 검사 후, 번호 뒤에 오는 것이 연산자인지, 숫자인지 판별
 * - 단말 노드일 경우 숫자가 와야하며, 단말 노드가 아닐 경우 연산자가 와야 함
 * 
 * 메모리 : 19,648KB 실행 시간 : 116ms
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력의 수가 많아 BufferedReader 사용
                 
        for (int i = 1; i <= 10; i++) {
            int N = Integer.parseInt(br.readLine()); // 트리의 정점 총 개수
            boolean isAvailable = true; // 유효성 검사
             
            for (int j = 1; j <= N; j++) { // N번 반복
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken(); // 첫번째 수는 번호이므로 넘김
                char c = st.nextToken().charAt(0);
                // 연산자, 숫자 중에 하나가 오므로 char 타입
                 
                if (st.hasMoreTokens()) { // 단말 노드가 아닐 경우(뒤에 token이 남아있을 경우) : 연산자가 와야 함
                    if (c >= 48 && c <= 57) isAvailable = false; // 숫자가 오면 false
                }
                else { // 단말 노드일 경우 : 숫자가 와야 함
                    if (c > 57 || c < 48) isAvailable = false; // 연산자가 옴
                }
            }
             
            System.out.print("#" + i + " ");
            if (isAvailable) System.out.println(1);
            else System.out.println(0);
        }
    }
}