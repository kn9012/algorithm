import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 우수 마을 조건
 * 1. 마을 주민 수 총 합이 최대
 * 2. 우수 마을끼리 인접 불가
 * 3. 비우수 마을은 하나 이상의 우수 마을이랑 인접해 있어야 함
 *
 * dp 사용.
 * dp[i][0]은 i번째 마을을 선택하지 않음, dp[i][1]은 i번째 마을을 선택함
 *
 * 1차 시도 : 루트 마을을 1번이라고 두고 루트를 선택했을 경우, 안했을 경우를 나누어서 했는데 탐색 방향이 틀림.
 * 자식 dp를 계산한 뒤에 부모 dp를 계산하고 dp[1][0], dp[1][1] 중 큰 값이 답임
 *
 * ★ 트리 dp에서는 부모 dp는 자식들의 dp가 모두 계산된 뒤에야 게산할 수 있다! -> 후위 순회
 */

public class Main {
    static int[][] dp;
    static int[] people;
    static boolean[] visited;
    static List<List<Integer>> tree = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        people = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        visited[1] = true;
        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dfs(int node) {
        dp[node][1] = people[node];

        for (int next : tree.get(node)) {
            if (visited[next]) continue;

            visited[next] = true;
            dfs(next);

            // node 마을을 선택하지 않을 경우 next 마을은 선택하거나, 안하거나 둘 중 하나
            dp[node][0] += Math.max(dp[next][0], dp[next][1]);

            // node 마을을 선택했을 경우 next는 선택하지 않아야 함
            dp[node][1] += dp[next][0];
        }
    }
}
