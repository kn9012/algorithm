/**
 * 프로그래머스 혼자 놀기의 달인
 *
 * 아이디어 - DFS
 * visit 배열을 이용해서 이미 연 상자는 체크
 * ArrayList에 상자의 수를 입력해서 오름차순 정렬한 후 가장 앞의 두 값을 곱하면 최고 점수
*/

import java.util.*;

class Solution {
    boolean isVisited[];
    ArrayList<Integer> list = new ArrayList<>();
    public int solution(int[] cards) {
        int answer = 0;
        isVisited = new boolean[cards.length];
        
        for (int i = 0; i < cards.length; i++) {
            if (!isVisited[i]) dfs(i, 0, cards);
        }
        
        int size = list.size();
        Collections.sort(list);
        if (size != 1) {
            answer = list.get(size - 1) * list.get(size - 2);
        } else answer = 0;
        return answer;
    }
    
    public void dfs(int index, int count, int[] cards) {
        if (isVisited[index]) {
            list.add(count);
            return;
        }
        
        int value = cards[index] - 1;
        isVisited[index] = true; 
        dfs(value, count + 1, cards);
    }
}