import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String person : participant) {
            int num = map.getOrDefault(person, 0);
            map.put(person, num + 1);
        }
        
        for (String person : completion) {
            int num = map.get(person);
            
            if (num - 1 >= 0) map.put(person, num - 1);
        }
        
        for (String person : map.keySet()) {
            if (map.get(person) != 0) answer = person;
        }
        
        return answer;
    }
}