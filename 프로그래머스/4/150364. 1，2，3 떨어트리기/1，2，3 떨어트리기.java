import java.util.*;

class Solution {
    class Node {
        int idx;
        List<Node> children = new ArrayList<>();
        int nextIdx = 0;

        Node(int idx) { this.idx = idx; }
        
        // 다음 자식 노드의 '번호'를 반환
        int next() {
            if (children.isEmpty()) return -1;
            int childNodeNumber = children.get(nextIdx).idx;
            nextIdx = (nextIdx + 1) % children.size();
            return childNodeNumber;
        }
    }

    public int[] solution(int[][] edges, int[] target) {
        Map<Integer, Node> map = new HashMap<>();
        // 1번 노드부터 target.length번 노드까지 미리 생성
        for (int i = 1; i <= target.length; i++) {
            map.put(i, new Node(i));
        }

        for (int[] edge : edges) {
            map.get(edge[0]).children.add(map.get(edge[1]));
        }

        // 자식 노드들을 번호 순으로 정렬
        for (Node n : map.values()) {
            n.children.sort(Comparator.comparingInt(a -> a.idx));
        }
        
        // 1. 시뮬레이션: 숫자를 떨어뜨려 각 리프에 도달하는 순서와 횟수 기록
        List<Integer> dropOrder = new ArrayList<>(); // 떨어진 순서대로 '노드 번호'를 기록
        int[] hits = new int[target.length + 1];      // 각 노드에 도달한 횟수 (인덱스=노드번호)

        while (true) {
            // 종료 조건: 모든 target을 만족시킬 최소 횟수에 도달했는지 확인
            boolean canStop = true;
            for (int i = 0; i < target.length; i++) {
                int nodeNum = i + 1;
                // 리프 노드이고, 아직 target을 만들 가능성이 없다면 계속 진행
                if (target[i] > 0 && map.get(nodeNum).children.isEmpty()) {
                    if (hits[nodeNum] * 3 < target[i]) {
                        canStop = false;
                        break;
                    }
                }
            }
            
            // 모든 타겟을 만들 수 있는 최소 횟수에 도달했고, 한 번이라도 떨어뜨렸다면 종료
            if (canStop && !dropOrder.isEmpty()) {
                break;
            }

            // 1번 노드부터 숫자 떨어뜨리기 시작
            Node cur = map.get(1);
            while (!cur.children.isEmpty()) {
                cur = map.get(cur.next());
            }

            int leafNodeNum = cur.idx;
            hits[leafNodeNum]++;
            dropOrder.add(leafNodeNum);

            // target[i]는 i+1번 노드의 목표값
            // 1만 넣어도 target을 초과하면 불가능
            if (hits[leafNodeNum] > target[leafNodeNum - 1]) {
                return new int[]{-1};
            }
        }
        
        // 2. 값 할당 (Greedy): 뒤에서부터 가능한 작은 값을 할당
        int[] result = new int[dropOrder.size()];
        int[] remainingTarget = Arrays.copyOf(target, target.length); // target 배열 복사

        // 여기를 수정! 역순이 아닌 정방향으로 순회합니다.
        for (int i = 0; i < dropOrder.size(); i++) {
            int nodeNum = dropOrder.get(i);
            int targetIdx = nodeNum - 1;

            boolean assigned = false;
            for (int v = 1; v <= 3; v++) {
                long remTarget = remainingTarget[targetIdx] - v;
                long remHits = hits[nodeNum] - 1;

                if (remTarget < 0) continue;

                if (remHits <= remTarget && remTarget <= remHits * 3) {
                    result[i] = v;
                    remainingTarget[targetIdx] -= v;
                    hits[nodeNum]--;
                    assigned = true;
                    break;
                }
            }

            if (!assigned) {
                return new int[]{-1};
            }
        }
        
        return result;
    }
}