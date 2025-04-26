import java.util.*;

class Solution {
    class Food {
        int index, time;
        
        Food (int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
    
    public int solution(int[] food_times, long k) {
        PriorityQueue<Food> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.time, o2.time));
        for (int i = 0; i < food_times.length; i++) {
            pq.add(new Food(i, food_times[i]));
        }
        
        long prevTime = 0;
        
        while (!pq.isEmpty()) {
            int size = pq.size();
            
            Food first = pq.peek();
            
            if (k - (size * (long) (first.time - prevTime)) >= 0) {
                k -= size * (long) (first.time - prevTime);
                
                prevTime = first.time;
                pq.poll();
                //System.out.println(k);
            } else break;
        }
        
        List<Food> list = new ArrayList<>();
        
        if (pq.isEmpty()) return -1;
        
        while (!pq.isEmpty()) {
            list.add(pq.poll());
        }
        
        Collections.sort(list, (o1, o2) -> Integer.compare(o1.index, o2.index));
        
        return list.get((int) (k % list.size())).index + 1;
    }
}