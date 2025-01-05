import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o1 - o2);
        for (int i = 0; i < scoville.length; i++) {
            queue.add(scoville[i]);
        }
        
        while (queue.peek() < K) {
            if (queue.size() == 1) {
                answer = -1;
                break;
            }
            
            int min = queue.poll();
            int nextMin = queue.poll();
            
            queue.add(min + (nextMin * 2));
            
            answer++;
        }
        
        return answer;
    }
}