import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        
        next[n - 1] = -1;
        
        for (String c : cmd) {
            String command = c.substring(0, 1);
            
            if (command.equals("U")) {
                int X = Integer.parseInt(c.substring(2));
                while (X-- > 0) k = prev[k];
            } else if (command.equals("D")) {
                int X = Integer.parseInt(c.substring(2));
                while (X-- > 0) k = next[k];
            } else if (command.equals("C")) {
                stack.push(k);
                
                if (next[k] != -1) prev[next[k]] = prev[k];
                if (prev[k] != -1) next[prev[k]] = next[k];
                
                k = (next[k] != -1) ? next[k] : prev[k];
            } else {
                int K = stack.pop();
                
                if (prev[K] != -1) next[prev[K]] = K;
                if (next[K] != -1) prev[next[K]] = K;
            }
        }
        
        StringBuilder sb = new StringBuilder("O".repeat(n));
        
        while (!stack.isEmpty()) {
            sb.setCharAt(stack.pop(), 'X');
        }
        
        return sb.toString();
    }
}