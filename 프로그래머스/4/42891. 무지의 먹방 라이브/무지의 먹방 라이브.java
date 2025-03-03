import java.util.*;

class Solution {
    public class Food implements Comparable<Food> {
        int index, time;

        Food(int index, int time) {
            this.index = index;
            this.time = time;
        }

        @Override
        public int compareTo(Food o) {
            return Integer.compare(this.time, o.time);
        }
    }

    public int solution(int[] food_times, long k) {
        long sum = 0;

        PriorityQueue<Food> queue = new PriorityQueue<>();

        for (int i = 0; i < food_times.length; i++) {
            queue.add(new Food(i, food_times[i]));
        }

        int prevTime = 0;

        while (!queue.isEmpty()) { // 🛠 queue가 비어있는지 체크
            int size = queue.size();
            Food cur = queue.peek();

            long cost = (long) size * (cur.time - prevTime);
            if (sum + cost <= k) {
                sum += cost;
                prevTime = cur.time;
                queue.poll();
            } else {
                k -= sum;
                break;
            }
        }

        // 🛠 queue가 비어있다면 모든 음식이 소진된 것이므로 -1 반환
        if (queue.isEmpty()) return -1;

        List<Food> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }

        list.sort(Comparator.comparingInt(o -> o.index));

        return list.get((int) (k % list.size())).index + 1; // 🛠 k를 list 크기로 나눈 나머지를 사용
    }
}
