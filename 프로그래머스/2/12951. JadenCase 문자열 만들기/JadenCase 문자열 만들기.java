class Solution {
    public String solution(String s) {
        String answer = "";
        
        boolean isStart = true;
        
        for (int i = 0; i < s.length(); i++) {            
            if (isStart) {
                answer += (s.charAt(i) + "").toUpperCase();
                isStart = false;
            } else {
                answer += (s.charAt(i) + "").toLowerCase();
            }
            
            if (s.charAt(i) == (' ')) {
                isStart = true;
            }
            
            //System.out.println(answer + " " + s.charAt(i));
        }
        
        
        return answer;
    }
}