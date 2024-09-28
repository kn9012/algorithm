/**
 * 프로그래머스 가장 긴 팰린드롬
 * - 투 포인터 cdeaba
 */

class Solution {
    public int solution(String s) {
        int answer = 0;
        

        
        for (int i = 0; i < s.length(); i++) {
            int start = i;
            int end = s.length() - 1;
            int len = 0;
            int idx = 1;
            
            while (start < end) {
                if (s.charAt(start) == s.charAt(end)) {
                    start++;
                    end--;
                    len += 2;
                } else {
                    len = 0;
                    start = i;
                    end = s.length() - 1 - (idx++);
                }
            }
            
            if (start == end) len++;
            
            answer = Math.max(len, answer);
        }
        
        
        return answer;
    }
}