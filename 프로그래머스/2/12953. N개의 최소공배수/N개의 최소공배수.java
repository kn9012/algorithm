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
        int answer = 0;
        
        if (arr.length == 1) return arr[0];
        
        int g = gcd(arr[0], arr[1]);
        answer = arr[0] * arr[1] / g;
        
        if (arr.length > 2) {
            for (int i = 2; i < arr.length; i++) {
                g = gcd(answer, arr[i]);
                answer = answer * arr[i] / g;
            }
        }
                
        
        return answer;
    }
    
    public int gcd(int a, int b) {
        int r = a % b;
        if (r == 0) return b;
        else return gcd(b, r);
    }
}