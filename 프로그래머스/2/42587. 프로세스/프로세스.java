import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<int []> wait = new ArrayDeque<>();
        
        for (int i = 0; i < priorities.length; i++) {
            wait.add(new int[] { priorities[i], i }); // 우선순위, 인덱스
        }
        
        while (!wait.isEmpty()) {
            int[] first = wait.peek();
        
            int max = 0;
            int maxIndex = -1;
            
            for (int i = 0; i < priorities.length; i++) {
                if (max < priorities[i]) {
                    max = priorities[i];
                    maxIndex = i;
                }
            }
            
            if (max > first[0]) wait.add(wait.poll());
            else {
                answer++;
                int execution[] = wait.poll();
                priorities[maxIndex] = 0;
                
                if (execution[1] == location) break;
            }
        }
        
        return answer;
    }
}