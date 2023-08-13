import java.util.Scanner;

/**
 * 
 * @author 김유나
 * 2023-08-11
 * 
 * [문제] 백준 2839번 설탕 배달
 * - 상근이가 정확히 N킬로그램을 배달해야 할 때, 최소로 가져가야 하는 봉지 개수를 알려주는 프로그램을 작성하라.
 * [아이디어] 5의 배수라면 5로 나눈만큼, 그리고 나머지가 1, 2, 3, 4일 때를 나누어 생각하였다.
 * 
 * 메모리 : 17,736kb 실행 시간 : 232ms
 * 
 */
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int answer;
        
        // 5의 배수인 경우 : 5로 나눈만큼
        if (n % 5 == 0) answer = n / 5;
        
        // 5로 나눈 나머지가 1인 경우 : 5로 나눈 수에 +1
        else if (n % 5 == 1) answer = n / 5 + 1;
        
        // 5로 나눈 나머지가 2고 12 이상인 경우 : 5로 나눈 수에 +2 
        else if (n % 5 == 2 && n >= 12) answer = n / 5 + 2;
        
        // 5로 나눈 나머지가 3인 경우 : 5로 나눈 수에 +1
        else if (n % 5 == 3) answer = n / 5 + 1;
        
        // 5로 나눈 나머지가 4고 9 이상인 경우 : 5로 나눈 수에 +2
        else if (n % 5 == 4 && n >= 9) answer = n / 5 + 2;
        
        // 어떤 경우도 해당하지 않는 경우 -1
        else answer = -1;
        System.out.println(answer);
    }

}