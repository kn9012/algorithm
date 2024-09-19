/**
 * 프로그래머스 단속카메라
 * 그리디
 */

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        // 진출 지점을 기준으로 오름차순 정렬
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        
        int max = routes[0][1];
        int answer = 1;
        
        for (int[] route : routes) {
            if (max < route[0]) {
                max = route[1];
                answer++;
            }
        }
        
        return answer;
    }
}