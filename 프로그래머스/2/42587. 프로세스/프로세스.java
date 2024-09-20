/**
 * 프로그래머스 프로세스
 * - Queue 사용
 */

import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        // 실행 대기 큐
        Queue<int []> wait = new ArrayDeque<>();
        
        for (int i = 0; i < priorities.length; i++) {
            wait.add(new int[] { priorities[i], i }); // 우선순위, 인덱스
        }
        
        while (!wait.isEmpty()) {
            int[] first = wait.poll(); // 가장 앞의 값
        
            int max = 0;
            int maxIndex = -1;
            
            for (int i = 0; i < priorities.length; i++) {
                if (max < priorities[i]) {
                    max = priorities[i];
                    maxIndex = i;
                }
            }
            
            // 우선순위가 높은 프로세스가 있으면 방금 꺼낸거 다시 넣기
            if (max > first[0]) wait.add(first);
            else {
                answer++;
                priorities[maxIndex] = 0; // 꺼낸 값 초기화
                
                if (first[1] == location) break;
            }
        }
        
        return answer;
    }
}