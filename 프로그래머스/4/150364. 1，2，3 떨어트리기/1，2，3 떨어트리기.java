import java.util.*;

class Solution {

    public class Node {
        int num;
        Queue<Node> child;
        List<Integer> tmpChild;

        public Node(int n) {
            this.num = n;
            child = new ArrayDeque<>();
            tmpChild = new ArrayList<>();
        }

        public void makeQueue() {
            Collections.sort(tmpChild);
            for (Integer cur : tmpChild)
                child.add(node[cur]);
        }
    }

    int N;
    Node[] node; //인덱스로 접근
    Node root; //트리 루트
    List<Integer> leaves = new ArrayList<>(); //리프 노드 번호 저장
    List<Integer> sequence = new ArrayList<>(); //떨어뜨린 순서 저장
    Map<Integer, Integer> dropCntMap = new HashMap<>(); //각 리프 노드에 떨어진 횟수 저장
    Map<Integer, Integer> targetMap = new HashMap<>(); //각 리프 노드에 떨어뜨려야할 결과값 저장

    public int[] solution(int[][] edges, int[] target) {
        //1. 초기화 단계
        init(edges, target);
        //2. 시뮬레이션 진행
        if (!simulate()) return new int[] { - 1};
        //3. 얻은 결과값으로 결과 리스트 구성
        return makeResult();
    }

    private int[] makeResult() {
        int[] result = new int[sequence.size()];
        for (int i = 0; i < sequence.size(); i++) {
            int num = sequence.get(i);
            int dropCntValue = dropCntMap.get(num);
            int targetValue = targetMap.get(num);
            int res;
            if (targetValue - 1 <= (dropCntValue - 1) * 3) res = 1;
            else if (targetValue - 2 <= (dropCntValue - 1) * 3) res = 2;
            else res = 3;
            result[i] = res;
            dropCntMap.put(num, dropCntMap.get(num) - 1);
            targetMap.put(num, targetMap.get(num) - res);
        }
        return result;
    }

    //시뮬레이션 진행
    //return : 가능 여부 판별
    //떨어지는 순서 (sequence) 만들기
    //각 리프 노드에 떨어진 횟수(dropCntMap) 만들기
    private boolean simulate() {
        //각 숫자 떨어뜨리기
        while(true) {
            Node cur = root;
            while(true) {
                if (cur.child.isEmpty()) break;
                Node next = cur.child.poll();
                cur.child.add(next);
                cur = next;
            }
            //리프 노드 얻음
            sequence.add(cur.num);
            dropCntMap.put(cur.num, dropCntMap.get(cur.num) + 1);
            boolean contFlag = false;
            //validation
            for (Integer leafNum : leaves) {
                int targetValue = targetMap.get(leafNum);
                int dropCntValue = dropCntMap.get(leafNum);
                //1. 타겟값이 0일 때
                if (targetValue == 0) {
                    if (dropCntValue != 0) return false;
                }
                //2. 타겟값이 0이상 일 때
                else {
                    if (dropCntValue == 0 || targetValue / dropCntValue > 3 || ( targetValue / dropCntValue == 3 && targetValue % dropCntValue != 0)) { //숫자 더 떨어뜨려야함
                        contFlag = true;
                        break;
                    } else if (targetValue / dropCntValue == 0) { //불가능
                        return false;
                    }
                }
            }
            if (!contFlag) break;
        }
        return true;
    }

    private void init(int[][] edges, int[] target) {
        //기본값 초기화
        N = target.length;
        node = new Node[N];
        for (int i = 0; i < N; i++) node[i] = new Node(i);
        root = node[0];
        //트리 구성
        for (int[] edge : edges) {
            int parent = edge[0] - 1;
            int child = edge[1] - 1;
            node[parent].tmpChild.add(child);
        }
        for (int i = 0; i < N; i++) node[i].makeQueue();
        //리프 노드 정보 구성
        for (int i = 0; i < N; i++) {
            if (!node[i].child.isEmpty()) continue;
            leaves.add(i);
            dropCntMap.put(i, 0);
            targetMap.put(i, target[i]);
        }
    }
}