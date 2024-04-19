/**
 중복 제외를 위해 Set 사용
 DFS 사용해서 선택
*/

import java.util.*;

class Solution {
    static Set<Integer> set;
    static boolean isSelected[];
    static int answer = 0;
    public static int solution(String numbers) {
        set = new HashSet<>();
        isSelected = new boolean[numbers.length()];
        
        dfs(numbers, "", 0);
        
        for (int n : set) {
            if (isPrime(n)) answer++;   
        }
        
        return answer;
    }
    
    public static void dfs(String numbers, String s, int count) {
        if (count == numbers.length()) {          
            return;
        }
        
        for (int i = 0; i < numbers.length(); i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                set.add(Integer.parseInt(s + numbers.charAt(i)));
                dfs(numbers, s + numbers.charAt(i), count + 1);
                isSelected[i] = false;                
            }
        }
    }
    
    public static boolean isPrime(int n) {
        System.out.println(n);
        if (n < 2) return false;
        
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        
        return true;
    }
}