/**
 * 프로그래머스 주차 요금 계산
 * - 
 */

import java.util.*;

class Solution {
    final int FINAL_TIME = 23 * 60 + 59; // 23:59
    
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> timeMap = new HashMap<>(); // 비용 저장 map
        Map<String, List<Integer>> recordMap = new HashMap<>(); // 기록 관리 map
        
        // 순환하면서 recoreMap에 저장
        for (String record : records) {
            String[] arr = record.split(" ");
            
            // arr[0] : 시각, arr[1] : 차 번호, arr[2] : IN or OUT
            String[] hourAndMin = arr[0].split(":");
            int sec = Integer.parseInt(hourAndMin[0]) * 60 + Integer.parseInt(hourAndMin[1]);
            
            recordMap.computeIfAbsent(arr[1], k -> new ArrayList<>()).add(sec);
        }
        
        Set<String> keySet = recordMap.keySet();

        for (String key : keySet) {
            List<Integer> times = recordMap.get(key);
            
            // 마지막 출차 내역이 없을 경우 마지막 시간 더해주기
            if (times.size() % 2 != 0) times.add(FINAL_TIME);
            
            for (int i = 0; i < times.size(); i += 2) {
                timeMap.put(key, timeMap.getOrDefault(key, 0) + times.get(i + 1) - times.get(i));
            }
        }
        
        List<String> keyList = new ArrayList<>(keySet);
        Collections.sort(keyList);
        
        int[] answer = new int[keySet.size()];
        
        int index = 0;
        for (String key : keyList) {
            int totalTime = timeMap.get(key);
            
            if (totalTime <= fees[0]) {
                answer[index] = fees[1];
            } else {
                answer[index] = fees[1] + (int) Math.ceil(((double) totalTime - fees[0]) / fees[2]) * fees[3];
            }
            
            index++;
        }
        
        return answer;
    }
}