/**
 * 프로그래머스 디스크 컨트롤러
 * 
 * 1. jobs의 요청 시점이 오름차순대로 들어오지 않을수도 있기 때문에 오름차순 정렬
 * 2. PriorityQueue 사용해서 소요 시간 오름차순 정렬
 * 3. 종료 시간 (end), 현재 인덱스 (index), 총 걸린 시간의 합(answer), 현재 진행한 작업 개수(count)
 */

import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        PriorityQueue<int []> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int count = 0;
        int index = 0;
        int end = 0;
        
        while (count < jobs.length) {
            // 요청 시점이 end보다 작거나 같은 것들 다 넣기
            while (index < jobs.length && jobs[index][0] <= end) pq.add(jobs[index++]);
            
            // 큐가 비어있으면 end를 요청 시간으로 초기화
            if (pq.isEmpty()) end = jobs[index][0];
            else {
                int[] cur = pq.poll();
                answer += cur[1] + end - cur[0];
                end += cur[1];
                count++;
            }
        }
        
        return answer / jobs.length;
    }
}