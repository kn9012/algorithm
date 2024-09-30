import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int copy[][] = new int[scores.length][3];
        
        for (int i = 0; i < scores.length; i++) {
            copy[i][0] = scores[i][0];
            copy[i][1] = scores[i][1];
            copy[i][2] = i;
        }
        
        Arrays.sort(copy, (o1, o2) -> {
            if (o2[0] != o1[0]) return o2[0] - o1[0];
            return o2[1] - o1[1];
        });
        
        int min = copy[0][0];
        int max = copy[0][1];
        
        int sum = scores[0][0] + scores[0][1];
        int rank = 1;
        
        for (int i = 0; i < copy.length; i++) {
            boolean safe = true;
            
            if (min > copy[i][0]) {
                if (max > copy[i][1]) safe = false;
                min = copy[i][0];
            } else {
                for (int j = 0; j < i; j++) {
                    if (copy[j][0] == copy[i][0]) break;
                    if (copy[j][1] <= copy[i][1]) continue;
                    
                    safe = false;
                    break;
                }
            }
            
            if (max < copy[i][1]) max = copy[i][1];
            if (safe && sum < copy[i][0] + copy[i][1]) rank++;
            if (!safe && copy[i][2] == 0) return -1;
        }
        return rank;
    }
}