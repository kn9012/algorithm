import java.util.*;
import java.io.*;

/**
 * 1차 시도 : 입력 받은 쿼리(노드)마다 BFS 돌려서 해당 노드 아래 몇개의 노드가 있는지 계산
 * -> Q * N = 200,000 * 100,000으로 메모리 초과
 *
 * 쿼리를 받을때마다 BFS를 실행하지 말고 DFS를 한번 돌려서 각 노드의 서브트리 크기를 미리 저장해두자.
 */

public class Main {
    static List<List<Integer>> tree = new ArrayList<>();
    static int N, R, dp[];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점 수
        R = Integer.parseInt(st.nextToken()); // 루트 번호
        int Q = Integer.parseInt(st.nextToken()); // 쿼리 수

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            // 연결
            tree.get(U).add(V);
            tree.get(V).add(U);
        }

        StringBuilder sb = new StringBuilder();

        dp = new int[N + 1];
        visited = new boolean[N + 1];
        visited[R] = true;
        dfs(R);

        for (int i = 1; i <= Q; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());

            if (node == R) sb.append(N).append("\n");
            else sb.append(dp[node]).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int node) {
        dp[node] = 1;

        for (int next : tree.get(node)) {
            if (visited[next]) continue;

            visited[next] = true;
            dfs(next);
            dp[node] += dp[next];
        }
    }

    /**
     * 메모리 초과 난 bfs 코드
     */

//    static int bfs(int node, int root) {
//        Queue<Integer> queue = new ArrayDeque<>();
//        queue.add(root);
//        boolean[] visited = new boolean[N + 1];
//        visited[root] = true;
//        boolean isNode = false;
//        int cnt = 0;
//
//        while (!queue.isEmpty()) {
//            int cur = queue.poll();
//
//            if (cur == node) {
//                queue = new ArrayDeque<>();
//                isNode = true;
//            }
//
//            if (isNode) {
//                cnt++;
//            }
//
//            for (int i = 0; i < tree.get(cur).size(); i++) {
//                int next = tree.get(cur).get(i);
//
//                if (!visited[next]) {
//                    queue.add(next);
//                    visited[next] = true;
//                }
//            }
//        }
//
//        return cnt;
//    }
}
