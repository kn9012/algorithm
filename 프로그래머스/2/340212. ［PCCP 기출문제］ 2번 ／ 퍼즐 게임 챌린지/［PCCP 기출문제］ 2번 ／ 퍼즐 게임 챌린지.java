/**
 * 프로그래머스 퍼즐 게임 챌린지
 * - 값의 이분 탐색?
 */

import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < diffs.length; i++) {
            min = Math.min(min, diffs[i]);
            max = Math.max(max, diffs[i]);
        }
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        while (min <= max) {
            long time = 0;
            long preTime = 0;
            
            int mid = (min + max) / 2;
            
            for (int i = 0; i < diffs.length; i++) {
                if (mid >= diffs[i]) {
                    time += times[i];
                    preTime = times[i];
                } else {
                    time += (diffs[i] - mid) * (times[i] + preTime) + times[i];
                    preTime = times[i];
                }
            }
                             
            if (time > limit) {
                min = mid + 1;
            } else {
                queue.add(mid);
                max = mid - 1;
            }
        }
        
        return queue.poll();
    }
}