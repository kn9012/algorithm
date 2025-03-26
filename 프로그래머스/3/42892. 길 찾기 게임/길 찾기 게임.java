import java.util.*;

class Solution {
    public class Node implements Comparable<Node> {
        int x, y, idx;
        Node left, right;
        
        Node (int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
        
        // y 좌표값이 가장 큰 좌표가 root 노드이므로 y 좌표값 기준 내림차순 정렬
        @Override
        public int compareTo(Node o) {
            return Integer.compare(o.y, this.y);
        }
    }
    
    public int[][] answer;
    public int index = 0;
    
    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            pq.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }
        
        Node root = pq.poll();
        
        while (!pq.isEmpty()) findChild(root, pq.poll());
        
        preorder(root);
        index = 0;
        postorder(root);
        
        return answer;
    }
    
    public void findChild(Node root, Node child) {
        // x 좌표가 root 노드보다 작으면 왼쪽 자식
        if (child.x < root.x) {
            if (root.left == null) root.left = child;
            else findChild(root.left, child);
        } else { // x 좌표가 root 노드보다 크다면 오른쪽 자식
            if (root.right == null) root.right = child;
            else findChild(root.right, child);
        }
    }
    
    // 전위순회 : root -> 왼 -> 오
    public void preorder(Node node) {
        if (node != null) {
            answer[0][index++] = node.idx;
            preorder(node.left);
            preorder(node.right);
        }
    }
    
    // 후위순회 : 왼 -> 오 -> root
    public void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            answer[1][index++] = node.idx;
        }
    }
}