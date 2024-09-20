import java.util.*;
import java.util.stream.*;

class Solution {
    public List<Integer> solution(String[] genres, int[] plays) {
        Map<String, Long> genrePlay = new HashMap<>();
        Map<String, ArrayList<int []>> sing = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < genres.length; i++) {            
            genrePlay.put(genres[i], genrePlay.getOrDefault(genres[i], 0L) + plays[i]);
            
            if (sing.containsKey(genres[i])) {
                sing.get(genres[i]).add(new int[] {i, plays[i]});
            } else {
                ArrayList<int []> temp = new ArrayList<>();
                temp.add(new int[] {i, plays[i]});
                sing.put(genres[i], temp);
            }
        }
        
        List<Map.Entry<String, Long>> collect = genrePlay.entrySet().stream().sorted((o1, o2) -> {
                    return -(o1.getValue().compareTo(o2.getValue()));
                }).collect(Collectors.toList());
        
        for (int i = 0; i < genrePlay.size(); i++) {
            Map.Entry<String, Long> entry = collect.get(i);
            ArrayList<int[]> temp = sing.get(entry.getKey());
            Collections.sort(temp, (o1, o2) -> {
                return -(o1[1] - o2[1]);
            });
            
            for(int j = 0; j < temp.size(); j++){
                if(j == 2) break;
                
                int [] check = temp.get(j);
                list.add(check[0]);
            }
        }
        
        
        return list;
    }
}