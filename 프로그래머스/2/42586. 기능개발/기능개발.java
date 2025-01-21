import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();
        
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < progresses.length; i++) {
            int num = 100 - progresses[i];
            int quotient = 0;
            if (num % speeds[i] != 0) {
                quotient = num / speeds[i] + 1;
            } else quotient = num / speeds[i];
            
            System.out.println(quotient);
            queue.add(quotient);
        }
        
        while (!queue.isEmpty()) {
            int count = 1;
            int cur = queue.poll();
            
            while (!queue.isEmpty() && queue.peek() <= cur) {
                queue.poll();
                count++;
            }
            
            list.add(count);
        }
        
        return list;
    }
}