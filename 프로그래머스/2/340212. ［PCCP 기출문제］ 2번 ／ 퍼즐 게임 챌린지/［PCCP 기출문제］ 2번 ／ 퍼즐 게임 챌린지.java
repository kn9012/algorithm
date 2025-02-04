/**
 * 프로그래머스 퍼즐 게임 챌린지
 * - 이분 탐색
 */

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int len = diffs.length;
        int left = 0, right = 0;
        for (int i = 0; i < len; i++) {
            left = Math.min(left, diffs[i]);
            right = Math.max(right, diffs[i]);
        }
        
        while (left <= right) {
            int mid = (left + right) / 2;
            long totalTime = 0;
            int prevTime = 0;
            
            boolean isExceed = false;
            
            for (int i = 0; i < len; i++) {
                if (diffs[i] <= mid) {
                    totalTime += times[i];
                    prevTime = times[i];
                } else {
                    totalTime += (diffs[i] - mid) * (times[i] + prevTime) + times[i];
                    prevTime = times[i];
                }
            }
            
            if (totalTime > limit) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        
        return answer + 1;
    }
}