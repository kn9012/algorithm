import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        
        Stack<Integer> removed = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        
        next[n - 1] = -1;
        
        for (String c : cmd) {
            String command = String.valueOf(c.charAt(0));
            
            switch (command) {
                case "U":
                    int move = Integer.parseInt(c.substring(2));
                    while (move-- > 0) k = prev[k];
                    break;
                
                case "D":
                    move = Integer.parseInt(c.substring(2));
                    while (move-- > 0) k = next[k];
                    break;
                    
                case "C":
                    int size = removed.size();
                    removed.push(k);
                    
                    if (prev[k] != -1) next[prev[k]] = next[k];
                    if (next[k] != -1) prev[next[k]] = prev[k];
                    
                    k = (next[k] != -1) ? next[k] : prev[k];
                    break;
                    
                case "Z":
                    int num = removed.pop();
                    if (prev[num] != -1) next[prev[num]] = num;
                    if (next[num] != -1) prev[next[num]] = num;
                    break;
            }
        }
        
        StringBuilder sb = new StringBuilder("O".repeat(n));
        while (!removed.isEmpty()) {
            sb.setCharAt(removed.pop(), 'X');
        }
        
        return sb.toString();
    }
}