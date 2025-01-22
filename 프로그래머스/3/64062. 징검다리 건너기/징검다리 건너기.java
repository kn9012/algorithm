/**
 * 프로그래머스 징검다리 건너기
 * - 한 번에 건너뛸 수 있는 디딤돌의 최대 칸수 : k -> 0이 k-1개 연속으로 나와야 함
 * - 이분 탐색
 */

import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int max = Integer.MIN_VALUE;
        
        for (int stone : stones) {
            max = Math.max(max, stone);
        }
        
        int left = 1;
        int right = max;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            int zeroCount = 0;
            boolean isZero = false;
            
            for (int stone : stones) {
                if (stone - mid <= 0) zeroCount++;
                else zeroCount = 0;
                
                if (zeroCount >= k) isZero = true;
            }
            
            if (isZero) right = mid - 1;
            else {
                answer = Math.max(answer, mid);
                left = mid + 1;
            }
        }
        
        return answer + 1;
    }
}