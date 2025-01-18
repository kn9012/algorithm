import java.util.*;

/**
 * 프로그래머스 구명보트
 * - 또 투포인터..?
 */

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int min = 0;
        boolean visited[] = new boolean[people.length];
        
        for (int i = people.length - 1; i >= 0; i--) {
            if (visited[i]) continue;
            
            if (people[i] + people[min] <= limit) {
                visited[i] = true;
                visited[min] = true;
                
                answer++;
                min++;
            } else {
                visited[i] = true;
                answer++;
            }
        }
        
        return answer;
    }
}