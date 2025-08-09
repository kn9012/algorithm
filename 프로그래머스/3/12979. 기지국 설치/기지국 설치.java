/**
 * 프로그래머스 기지국 설치
 * - 매개변수 탐색?
 */

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int start = 1;
        
        for (int station : stations) {
            if (station - w > start) {
                int section = station - w - start;
                if (section % (2 * w + 1) != 0) {
                    answer += section / (2 * w + 1) + 1;
                } else answer += section / (2 * w + 1);
            }
            
            start = Math.max(start, station + w + 1);
        }
        
        if (n >= start) {
            int section = n - start + 1;
            if (section % (2 * w + 1) != 0) {
                answer += section / (2 * w + 1) + 1;
            } else answer += section / (2 * w + 1);
        }
        
        return answer;
    }
}