/**
 * 프로그래머스 야근 지수
 * 
 */

import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int work : works) {
            queue.add(work);
        }
        
        while (n > 0) {
            int cur = queue.poll();
            if (cur - 1 > 0) queue.add(cur - 1);
            else queue.add(0);
            n--;
        }
        
        int length = queue.size();
        for (int i = 0; i < length; i++) {
            int num = queue.poll();
            answer += num * num;
        }
        
        return answer;
    }
}