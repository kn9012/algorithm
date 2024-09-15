import java.util.*;

/**
 * 프로그래머스 섬 연결하기
 * - 최소 신장 트리(MST), 크루스칼 union find 사용
 */

class Solution {
    static int parent[];
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        // 비용을 기준으로 오름차순 정렬
        Arrays.sort(costs, (int[] c1, int[] c2) -> c1[2] - c2[2]);
        
        // 부모 저장할 배열
        parent = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i; // 처음엔 부모 노드를 본인으로 초기화
        }
        
        for (int[] edge : costs) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];
            
            // 부모 찾기
            int fromParent = findParent(from);
            int toParent = findParent(to);
            
            // from 부모와 to 부모가 같을 경우 같은 무리에 있는 것이므로 연결하지 않음
            if (fromParent == toParent) continue;
            
            // 부모가 다를 경우 연결
            answer += cost;
            
            // to의 부모를 from의 부모로 변경
            parent[toParent] = fromParent;
        }
        
        return answer;
    }
    
    public int findParent(int node) {
        if (parent[node] == node) return node;
        return findParent(parent[node]);
    }
}