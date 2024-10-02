/**
 * 프로그래머스 혼자 놀기의 달인
 * - DFS로 최댓값 구하기
 */

import java.util.*;

class Solution {
    List<Integer> list = new ArrayList<>();
    boolean isVisited[];
    
    public int solution(int[] cards) {
        isVisited = new boolean[cards.length];
        
        for (int i = 0; i < cards.length; i++) {
            if (!isVisited[i]) dfs(0, i, cards);
        }
        
        Collections.sort(list, (o1, o2) -> o2 - o1);
        
        if (list.size() == 1) return 0;
        else return list.get(0) * list.get(1);
    }
    
    public void dfs(int count, int num, int[] cards) {
        if (isVisited[num]) {
            list.add(count);
            return;
        }
        
        isVisited[num] = true;
        dfs(count + 1, cards[num] - 1, cards);

    }
}