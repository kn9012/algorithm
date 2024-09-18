/**
 * 프로그래머스 다리를 지나는 트럭
 * - Queue 2개 사용
 * 
 * bridge_length : 다리에 올라갈 수 있는 트럭 수
 * weight : 다리가 견딜 수 있는 무게
 * truck_weights : 트럭 별 무게
 */

import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<int []> bridge = new ArrayDeque<>();
        Queue<Integer> trucks = new ArrayDeque<>();
        
        for (int truckWeight : truck_weights) {
            trucks.add(truckWeight);
        }
        
        
        int time = 0;
        int wSum = 0;
        
        while (!trucks.isEmpty() || !bridge.isEmpty()) {
            if (!bridge.isEmpty() && bridge.peek()[0] <= time) {
                int[] temp = bridge.poll();
                wSum -= temp[1];
            }
            
            if (!trucks.isEmpty() && wSum + trucks.peek() <= weight && bridge.size() + 1 <= bridge_length) {
                int temp = trucks.poll();
                wSum += temp;
                bridge.add(new int[] { time + bridge_length, temp});
            }

            time++;
        }
        
        return time;
    }
}