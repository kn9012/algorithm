/**
 * 프로그래머스 단어 변환
 * 
 * 아이디어 - DFS 사용
 * 변환할 수 없는 경우
 * 1. target 단어가 words에 없을 때
 * 2. target 단어로 갈 수 없을 때, 즉 과정이 없을 때
*/

class Solution {
    int min = Integer.MAX_VALUE;
    boolean isVisited[];
    public int solution(String begin, String target, String[] words) {
        isVisited = new boolean[words.length];
        dfs(begin, target, words, 0);
        
        if (min == Integer.MAX_VALUE) return 0;
        else return min;
    }
    
    public void dfs(String begin, String target, String[] words, int count) {
        if (begin.equals(target)) {
            //System.out.println(count);
            min = Math.min(min, count);
            return;
        }
        
        for (int i = 0; i < words.length; i++) {
            if (isVisited[i]) continue;
            
            int diff = 0;
            
            for (int j = 0; j < begin.length(); j++) {
                // 특정 위치의 글자가 다르다면
                if (begin.charAt(j) != words[i].charAt(j)) diff++;
            }
            if (diff == 1) {
                isVisited[i] = true;
                dfs(words[i], target, words, count + 1);
                isVisited[i] = false;
            }
        }
        
        return;
    }
}