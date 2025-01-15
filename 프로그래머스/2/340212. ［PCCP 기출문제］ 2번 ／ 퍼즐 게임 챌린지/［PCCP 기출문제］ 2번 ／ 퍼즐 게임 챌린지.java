/**
 * 프로그래머스 퍼즐 게임 챌린지
 *
 */

import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        
        for (int i = 0; i < diffs.length; i++) {
            left = Math.min(left, diffs[i]);
            right = Math.max(right, diffs[i]);
        }
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        int mid = 0;
        
        while (left <= right) {
            long spentTime = 0;
            long timePrev = 0;
            
            mid = (left + right) / 2;
            
            for (int i = 0; i < diffs.length; i++) {
                if (mid >= diffs[i]) {
                    spentTime += times[i];
                    timePrev = times[i];
                } else {
                    spentTime += (diffs[i] - mid) * (times[i] + timePrev) + times[i];
                    timePrev = times[i];
                }
            }
            
            if (spentTime <= limit) {
                right = mid - 1;
                queue.add(mid);
            } else {
                left = mid + 1;
            }
        }
        
        return queue.poll();
    }
}