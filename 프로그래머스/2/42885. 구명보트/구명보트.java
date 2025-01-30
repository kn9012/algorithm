import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int start = 0;
        int end = people.length - 1;
        
        // 50 50 70 80
        while (start <= end) {
            if (people[end] + people[start] <= limit) {
                end--;
                start++;
                answer++;
            } else {
                end--;
                answer++;
            }
        }
        
        return answer;
    }
}