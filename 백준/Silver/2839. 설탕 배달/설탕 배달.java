import java.util.Scanner;

/**
 * 
 * @author 김유나
 * 2023-08-11
 * 
 * [문제] 
 * [아이디어]
 * 
 * 메모리 : 
 */
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int answer;
        
        if (n % 5 == 0) answer = n / 5;
        else if (n % 5 == 1) answer = n / 5 + 1;
        else if (n % 5 == 2 && n >= 12) answer = n / 5 + 2;
        else if (n % 5 == 3) answer = n / 5 + 1;
        else if (n % 5 == 4 && n >= 9) answer = n / 5 + 2;
        else answer = -1;
        System.out.println(answer);
    }

}