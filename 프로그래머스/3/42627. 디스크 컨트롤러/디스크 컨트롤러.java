/**
 * 프로그래머스 디스크 컨트롤러
 *
 * 아이디어 - PriorityQueue 사용
 */

import java.util.*;

class Solution {    
    int nums, min = Integer.MAX_VALUE;
    public int solution(int[][] jobs) {
        int answer = 0;
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int time = 0;
        int index = 0;
        int count = 0;
        
        while (count < jobs.length) {
            while (index < jobs.length && jobs[index][0] <= time) q.add(jobs[index++]);
            if (q.isEmpty()) time = jobs[index][0];
            else {
                int[] cur = q.poll();
                answer += cur[1] + time - cur[0];
                time += cur[1];
                count++;
            }
        }
        
        return answer / jobs.length;
    }
}