import java.util.*;

/**
 * 프로그래머스 2단계 의상
 * 
 * HashMap 사용
 */

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, List<String>> map = new HashMap<>();
        
        for (int i = 0; i < clothes.length; i++) {
            if (map.containsKey(clothes[i][1])) {
                map.get(clothes[i][1]).add(clothes[i][0]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(clothes[i][0]);
                map.put(clothes[i][1], list);
            }
        }
        
        for (String key: map.keySet()) {
            answer *= (map.get(key).size() + 1);
        }
        
        return answer - 1;
    }
}