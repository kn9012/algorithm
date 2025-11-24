import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Node {
        Node left, right, parent;
        boolean isMolder;
        int val;
        int dfsIn, dfsOut;

        Node (int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 정점 개수

        String binaryNum = br.readLine();

        // X Y가 같을 수도 있다??
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[N + 1]; // 1부터 N까지
        for (int i = 1; i <= N; i++) nodes[i] = new Node(i);

        Deque<Node> stack = new ArrayDeque<>();
        Node root = null;
        int index = 1;

        // 중위순회
        for (int i = 0; i < 2 * N; i++) {
            // 0이면 stack에 넣기
            if (binaryNum.charAt(i) == '0') {
                Node cur = nodes[index++];
                cur.dfsIn = i + 1;
                stack.push(cur);
            } else { // 1이면 stack에서 빼기
                Node cur = stack.pop();
                cur.dfsOut = i + 1;

                if (!stack.isEmpty()) {
                    Node parent = stack.peek();
                    cur.parent = parent;

                    if (parent.left == null) parent.left = cur;
                    else parent.right = cur;
                } else {
                    root = cur;
                }

                if (cur.dfsIn == X || cur.dfsOut == X || cur.dfsIn == Y || cur.dfsOut == Y)
                    cur.isMolder = true;
            }
        }

        // 썩은 사과 노드 찾기
        Node a = null, b = null;
        for (Node node : nodes) {
            if (node == null) continue;

            if (node.isMolder) {
                if (a == null) a = node;
                else b = node;
            }
        }

        if (b == null) b = a; // 두 썩은 사과가 같은 경우

        // 최소 공통 조상 찾기
        Node lca = findLCA(a, b);

        // 제거할 노드 DFS 방문, 리턴 위치 출력
        System.out.println(lca.dfsIn + " " + lca.dfsOut);
    }

    static Node findLCA(Node a, Node b) {
        Set<Node> ancestors = new HashSet<>();

        while (a != null) {
            ancestors.add(a);
            a = a.parent;
        }

        while (b != null) {
            if (ancestors.contains(b)) return b;
            b = b.parent;
        }

        return null; // 루트까지 올라가도 없으면 null
    }
}
