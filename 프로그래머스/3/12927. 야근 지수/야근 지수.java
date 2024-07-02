/**
 * 프로그래머스 야근 지수
 *
 * 야근 피로도는 남은 일의 작업량을 제곱하여 더한 것
 * 즉, 각 작업의 수가 가장 작은 것이 야근 피로도를 최소로 만들 수 있다
 * 각 작업의 최댓값을 찾아서 n시간만큼 빼준다
*/

import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < works.length; i++) {
            queue.add(works[i]);
        }
        
        for (int i = 0; i < n; i++) {
            int cur = queue.poll();
            if (cur > 0) queue.add(cur - 1);
            else if (cur == 0) queue.add(0);
        }
        
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int cur = queue.poll();
            answer += cur * cur;
        }
        
        return answer;
    }
}