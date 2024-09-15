/**
 * 프로그래머스 괄호 회전하기
 * 
 * 
 */

import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for (int i = 0; i < s.length(); i++) {
            Stack<Character> stack = new Stack<>();
            String str = s.substring(i) + s.substring(0, i);
            
            for (char c : str.toCharArray()) {
                if (stack.isEmpty()) {
                    stack.push(c);
                    continue;
                }
                
                if (c == ')' && stack.peek() == '(') {
                    stack.pop();
                    continue;
                } else if (c == '}' && stack.peek() == '{') {
                    stack.pop();
                    continue;
                } else if (c == ']' && stack.peek() == '[') {
                    stack.pop();
                    continue;
                }
                
                stack.push(c);
            }
            
            if (stack.size() == 0) answer += 1;            
        }
        
        return answer;
    }
}