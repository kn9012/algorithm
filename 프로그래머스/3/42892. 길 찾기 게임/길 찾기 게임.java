import java.util.*;

class Solution {
    class Node {
        int x, y, v;
        Node left, right;
        
        Node(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }
    
    int[][] answer;
    int count = 0;
    List<Node> list;
    
    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        list = new ArrayList<>();
        
        for (int i = 0; i < nodeinfo.length; i++) {
            list.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }
        
        Collections.sort(list, (o1, o2) -> Integer.compare(o2.y, o1.y));
        
        Node root = list.get(0);
        
        for (int i = 1; i < list.size(); i++) {
            find(root, list.get(i));
        }
        
        preorder(root);
        count = 0;
        postorder(root);
        
        return answer;
    }
    
    public void find(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) parent.left = child;
            else find(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else find(parent.right, child);
        }
    }
    
    public void preorder(Node root) {
        if (root != null) {
            answer[0][count++] = root.v;
            preorder(root.left);
            preorder(root.right);
        }
    }
    
    public void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            answer[1][count++] = root.v;
        }
    }
}