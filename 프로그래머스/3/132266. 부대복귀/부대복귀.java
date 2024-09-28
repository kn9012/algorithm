/**
 * 프로그래머스 부대복귀
 * - 처음엔 sources에 있는 값들을 기준으로 BFS 돌았더니 시간 초과 남
 * - 인접행렬을 만들어 destination에서 BFS를 시작해서 연결된 지역 찾으면 시간초과 안남
 */

import java.util.*;

class Solution {
    int cost[];
    List<Integer>[] list;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int answer[] = new int[sources.length];
        list = new List[n + 1];
        cost = new int[n + 1];
        
        Arrays.fill(cost, -1); // 도달하지 못하는 지역은 -1
        
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int[] road : roads) {
            list[road[0]].add(road[1]);
            list[road[1]].add(road[0]);
        }
        
        bfs(destination);
        
        for (int i = 0; i < sources.length; i++) {
            // sources에 있는 지역들만 answer에 넣기
            answer[i] = cost[sources[i]];
        }
        
        return answer;
    }
    
    public void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        cost[start] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int i = 0; i < list[cur].size(); i++) {
                int next = list[cur].get(i);
                
                // 아직 갱신되지 않은 지역이면 갱신해주기
                if (cost[next] == -1) {
                    queue.add(next);
                    cost[next] = cost[cur] + 1;
                }
            }
        }
     }
}