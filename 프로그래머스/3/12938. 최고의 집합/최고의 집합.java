import java.util.*;

class Solution {
    public List<Integer> solution(int n, int s) {
        List<Integer> list = new ArrayList<>();
        int count = 0;
                
        while (n > 0 && s / n > 0) {
                int num = s / n--;
                list.add(num);
                s -= num;
        }
        
        if (list.size() == 0) list.add(-1);
        
        return list;
    }
}