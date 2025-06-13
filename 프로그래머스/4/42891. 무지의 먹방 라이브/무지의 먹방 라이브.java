import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        PriorityQueue<int []> foods = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
        
        int index = 0;
        for (int times : food_times) {
            foods.offer(new int[] {times, index++});
        }
        
        long prevFoodTime = 0;
        
        while (!foods.isEmpty()) {
            int time = foods.peek()[0];
            
            if ((time - prevFoodTime) * foods.size() <= k) {
                k -= (time - prevFoodTime) * foods.size();
                prevFoodTime = time;
                foods.poll();
            } else {
                break;
            }
        }
        
        if (foods.isEmpty()) return -1;
        
        List<int []> remain = new ArrayList<>();
        while (!foods.isEmpty()) {
            remain.add(foods.poll());
        }
        
        Collections.sort(remain, (o1, o2) -> Integer.compare(o1[1], o2[1]));
        
        return remain.get((int) (k % remain.size()))[1] + 1;
    }
}