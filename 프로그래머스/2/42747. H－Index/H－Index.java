/**
    오름차순 정렬 후 
*/

import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations); // 오름차순 정렬 [0, 1, 3, 5, 6]
        
        for (int i = 0; i < citations.length; i++) {
            // h : citations[i] 이상인 논문 개수
            int h = citations.length - i;
            
            if (citations[i] >= h) {
                answer = h;
                break;
            }
        }
        
        return answer;
    }
}