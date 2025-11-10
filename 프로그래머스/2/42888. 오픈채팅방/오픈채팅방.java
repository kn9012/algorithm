import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        List<String> result = new ArrayList<>();
        
        for (String r : record) {
            String[] str = r.split(" ");
            if (!str[0].equals("Leave")) map.put(str[1], str[2]);
        }
        
        for (String r : record) {
            String[] str = r.split(" ");
            
            if (str[0].equals("Enter")) {
                result.add(map.get(str[1]) + "님이 들어왔습니다.");
            } else if (str[0].equals("Leave")) {
                result.add(map.get(str[1]) + "님이 나갔습니다.");
            }
        }
        
        return result.toArray(new String[0]);
    }
}