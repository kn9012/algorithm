/**
 *
 * 프로그래머스 N개의 최소공배수
 * 
 * 아이디어
 * 최소공배수는 두 수의 곱에서 두 수의 최대공약수를 나눈 값과 같다
 * 최대공약수 구하는 법은 유클리드 호제법 사용
 * 
 */

import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int answer = 1;

        for (int i = 0; i < arr.length; i++) {
            answer = answer * arr[i] / gcd(answer, arr[i]);
        }
                
        return answer;
    }
    
    public int gcd(int a, int b) {
        int r = a % b;
        if (r == 0) return b;
        else return gcd(b, r);
    }
}