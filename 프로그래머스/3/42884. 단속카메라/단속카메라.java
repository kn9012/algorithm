/**
 * 프로그래머스 단속카메라
 * - 그리디 사용
 */

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        // 진출 지점을 기준으로 오름차순 정렬
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        
        // 첫 카메라는 첫 차량의 진출 지점
        int max = routes[0][1];
        int answer = 1;
        
        for (int[] route : routes) {
            // 다음 차량의 진입 지점이 카메라 위치보다 크다면 갱신하고 카메라 설치
            if (max < route[0]) {
                max = route[1];
                answer++;
            }
        }
        
        return answer;
    }
}