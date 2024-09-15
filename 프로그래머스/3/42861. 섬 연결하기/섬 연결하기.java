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
            parent[i] = i;
        }
        
        for (int[] edge : costs) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];
            
            int fromParent = findParent(from);
            int toParent = findParent(to);
            
            if (fromParent == toParent) continue;
            
            answer += cost;
            parent[toParent] = fromParent;
        }
        
        return answer;
    }
    
    public int findParent(int node) {
        if (parent[node] == node) return node;
        return findParent(parent[node]);
    }
}