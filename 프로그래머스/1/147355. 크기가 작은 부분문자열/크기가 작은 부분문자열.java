class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int pLen = p.length();
        int tLen = t.length();
        int index = 0;
        
        while (index + pLen <= tLen) {
            Long num = Long.parseLong(t.substring(index, index + pLen));
            if (num <= Long.parseLong(p)) answer++;
            
            index++;
        }
        
        return answer;
    }
}