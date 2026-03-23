import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 인접 리스트 + 유클리드 호제법
 */

public class Main {
    static int N;
    static List<Node>[] graph;
    static long[] ingredient;
    static boolean[] visited;

    static class Node {
        int to;
        int p, q;

        Node (int to, int p, int q) {
            this.to = to;
            this.p = p;
            this.q = q;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        long lcm = 1; // 최소공배수

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, p, q));
            graph[b].add(new Node(a, q, p)); // 비율도 반대로 바꿔줘야 함

            long gcd = gcd(p, q); // 최대공약수
            lcm *= (p * q / gcd); // 모든 p, q의 조합을 고려한 값
        }

        ingredient = new long[N]; // 재료의 양 배열
        visited = new boolean[N];

        ingredient[0] = lcm;

        dfs(0);

        long mgcd = ingredient[0]; // 전체 최대공약수
        for (int i = 1; i < N; i++) {
            mgcd = gcd(mgcd, ingredient[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(ingredient[i] / mgcd).append(" ");
        }

        System.out.println(sb);
    }

    static void dfs(int cur) {
        visited[cur] = true;

        for (Node next : graph[cur]) {
            if (!visited[next.to]) {
                // cur : next.to = p : q
                ingredient[next.to] = ingredient[cur] * next.q / next.p;
                dfs(next.to);
            }
        }
    }

    // 유클리드 호제법
    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b); // a와 b의 공약수는 b와 a % b의 공약수와 같음
        // a = 9, b = 6라면 3과 6의 공약수와 같음
    }
}