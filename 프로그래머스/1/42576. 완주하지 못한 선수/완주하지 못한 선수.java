import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {        
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String person : participant) {
            int num = map.getOrDefault(person, 0);
            map.put(person, num + 1);
        }
        
        for (String person : completion) {
            int num = map.get(person);
            
            if (num - 1 > 0) map.put(person, num - 1);
            else map.remove(person);
        }
        
        Set<String> set = map.keySet();
        Iterator<String> iter = set.iterator();
        
        return iter.next();
    }
}