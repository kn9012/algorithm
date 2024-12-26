import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        int left = 1;
        int right = distance;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            int deleteRocks = 0;
            int preRock = 0;
            
            for (int rock : rocks) {
                if (Math.abs(rock - preRock) < mid) deleteRocks++;
                else preRock = rock;
            }
            
            if (distance - preRock < mid) {
                deleteRocks++;
            }
            
            if (deleteRocks > n) right = mid - 1;
            else {
                left = mid + 1;
                answer = mid;
            }
        }
        
        return answer;
    }
}