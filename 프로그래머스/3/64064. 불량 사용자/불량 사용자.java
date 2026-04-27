import java.util.*;

class Solution {
    int banCount;
    Set<String> set = new HashSet<>();
    boolean[] isVisited;

    public int solution(String[] user_id, String[] banned_id) {
        isVisited = new boolean[user_id.length];
        
        dfs(0, user_id, banned_id);
        
        return set.size();
    }
    
    public void dfs(int count, String[] user_id, String[] banned_id) {
        if (count == banned_id.length) {
            StringBuilder sb = new StringBuilder();
            for (boolean visited : isVisited) {
                sb.append(visited ? "1" : "0");
            }
            set.add(sb.toString());
            return;
        }
        
        // 순열 ㄹㅊㄱ
        for (int i = 0; i < user_id.length; i++) {
            if (isVisited[i]) continue;
            
            if (isSame(user_id[i], banned_id[count])) {
                isVisited[i] = true;
                dfs(count + 1, user_id, banned_id);
                isVisited[i] = false;
            }
        }
    }
    
    public boolean isSame(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        
        for (int i = 0; i < s1.length(); i++) {
            if (s2.charAt(i) != '*' && s1.charAt(i) != s2.charAt(i)) return false;
        }
        
        return true;
    }
}