/**
 * 프로그래머스 후보키
 * 유일성, 최소성을 모두 만족하는지 확인 후 Set에 넣기
 */

import java.util.*;

class Solution {
    int answer = 0, n;
    boolean[] selected;
    List<Set<Integer>> candidateKeys = new ArrayList<>(); // 후보키 리스트
    
    public int solution(String[][] relation) {
        n = relation[0].length;
        
        for (int i = 1; i <= n; i++) {
            selected = new boolean[n];
            comb(relation, i, 0, 0);
        }
        
        return answer;
    }
    
    void comb(String[][] relation, int size, int count, int start) {
        if (count == size) {
            candidateKey(relation);
            return;
        }
        
        for (int i = start; i < n; i++) {
            if (selected[i]) continue;
            
            selected[i] = true;
            comb(relation, size, count + 1, i);
            selected[i] = false;
        }
    }
    
    void candidateKey(String[][] relation) {
        Set<Integer> current = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            if (selected[i]) current.add(i);
        }
        
        // 최소성 테스트
        for (Set<Integer> key : candidateKeys) {
            if (current.containsAll(key)) return;
        }
        
        // 유일성 테스트
        Set<String> set = new HashSet<>();
            
        for (int i = 0; i < relation.length; i++) {
            StringBuilder sb = new StringBuilder();
                
            for (int j = 0; j < relation[0].length; j++) {
                if (selected[j]) sb.append(relation[i][j] + " ");
            }
                
            set.add(sb.toString());
        }
            
        if (set.size() == relation.length) {
            candidateKeys.add(current); // 후보키 등록
            answer++;
        }
    }
}