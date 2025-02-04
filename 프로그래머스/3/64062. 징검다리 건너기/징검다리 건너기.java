/**
 * 프로그래머스 징검다리 건너기
 * - 매개 변수 탐색
 */

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        
        for (int i = 0; i < stones.length; i++) {
            left = Math.min(left, stones[i]);
            right = Math.max(right, stones[i]);
        }
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            int zeroCount = 0;
            
            for (int stone : stones) {
                if (stone - mid < 0) zeroCount++;
                else zeroCount = 0;
                
                if (zeroCount >= k) break;
            }
            
            if (zeroCount >= k) right = mid - 1;
            else {
                answer = mid;
                left = mid + 1;
            }
        }
        
        return answer;
    }
}