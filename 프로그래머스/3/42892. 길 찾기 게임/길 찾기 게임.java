/**
 * 프로그래머스 길 찾기 게임
 * - 각 노드들의 부모/자식을 정해준 뒤 전위, 후위 순회
 */

import java.util.*;

class Solution {
    class Node {
        int x, y, n;
        Node left, right;
        
        Node(int x, int y, int n) {
            this.x = x;
            this.y = y;
            this.n = n;
        }
    }
    
    List<Node> list;
    int[][] answer;
    int count = 0;
    
    public int[][] solution(int[][] nodeinfo) {
        int size = nodeinfo.length;
        
        answer = new int[2][size];
        list = new ArrayList<>();
        
        for (int i = 0; i < size; i++) {
            list.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }
        
        Collections.sort(list, (o1, o2) -> {
            return o2.y - o1.y;
        });
        
        Node root = list.get(0); // 루트 노드
        
        for (int i = 1; i < size; i++) {
            find(root, list.get(i));
        }
        
        pre(root);
        count = 0;
        post(root);
        
        return answer;
    }
    
    public void find(Node parent, Node child) {
        // 왼쪽 자식
        if (parent.x > child.x) {
            if (parent.left == null) parent.left = child;
            else find(parent.left, child);
        } else { // 오른쪽 자식
            if (parent.right == null) parent.right = child;
            else find(parent.right, child);
        }
    }
    
    // 전위 순회
    public void pre(Node cur) {
        if (cur != null) {
            answer[0][count++] = cur.n;
            pre(cur.left);
            pre(cur.right);
        }
        
    }
    
    // 후위 순회
    public void post(Node cur) {
        if (cur != null) {
            post(cur.left);
            post(cur.right);
            answer[1][count++] = cur.n;
        }
    }
}