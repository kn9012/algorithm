import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 리프 노드 <-> 리프 노드의 가중치 중 가장 큰 합을 구해야 하므로
 */

public class Main {
    static int n, answer = 0, last;
    static List<int[]>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        list = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            list[parent].add(new int[] {child, value});
            list[child].add(new int[] {parent, value});
        }

        visited = new boolean[n + 1];
        visited[1] = true;
        dfs(1, 0);

        visited = new boolean[n + 1];
        visited[last] = true;
        dfs(last, 0);

        System.out.println(answer);
    }

    public static void dfs(int node, int sum) {
        for (int[] arr : list[node]) {
            if (visited[arr[0]]) continue;

            int next = arr[0];
            int value = arr[1];

            if (sum + value > answer) {
                answer = sum + value;
                last = next;
            }
            
            visited[next] = true;
            dfs(next, sum + value);
        }
    }
}
