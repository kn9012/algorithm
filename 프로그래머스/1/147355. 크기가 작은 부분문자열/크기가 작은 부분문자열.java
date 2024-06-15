class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int len = p.length();
        int index = 0;
        
        while (index + len <= t.length()) {
            String num = t.substring(index, index + len);
            if (Long.parseLong(num) <= Long.parseLong(p)) answer++;
            
            index++;
        }
        
        return answer;
    }
}