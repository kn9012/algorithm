import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        String answer[] = new String[players.length];
        HashMap<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);
            answer[i] = players[i];
        }
        
       for (int i = 0; i < callings.length; i++) {
           int index = map.get(callings[i]);
           String tmp = answer[index - 1];
           
           map.replace(callings[i], index - 1);
           map.replace(tmp, index);
           
           answer[index - 1] = callings[i];
           answer[index] = tmp;
        }     
        
        return answer;
    }
}