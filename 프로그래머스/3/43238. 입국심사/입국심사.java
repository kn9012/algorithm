/**
 * 프로그래머스 입국심사
 * - 이분탐색 사용
 */

class Solution {
    public long solution(int n, int[] times) {
        long start = 0; 
        long end = 1000000000 * 1000000000L; // 최대로 주어질 수 있는 시간
        
        while (end > start) {
            long mid = (start + end) / 2;
            
            if (isVaild(mid, n, times)) end = mid;
            else start = mid + 1;
        }
        
        return start;
    }
    
    public boolean isVaild(long mid, int n, int[] times) {
        long count = 0;
        for (int time : times) {
            count += mid / time;
        }
        
        return count >= n;
    }
}