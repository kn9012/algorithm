import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] time = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
            int[] inDegree = new int[N + 1];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                adj.get(v1).add(v2);
                inDegree[v2]++;
            }

            int W = Integer.parseInt(br.readLine()); // 승리하기 위해 지어야 할 건물

            // 위상 정렬 및 DP 수행
            int[] result = new int[N + 1];
            Queue<Integer> q = new LinkedList<>();

            for (int i = 1; i <= N; i++) {
                result[i] = time[i];
                if (inDegree[i] == 0) q.offer(i);
            }

            while (!q.isEmpty()) {
                int curr = q.poll();

                for (int next : adj.get(curr)) {
                    // 다음 건물의 최소 시작 시간 갱신 (선행 건물 중 가장 늦게 끝나는 시간)
                    result[next] = Math.max(result[next], result[curr] + time[next]);
                    inDegree[next]--;

                    if (inDegree[next] == 0) q.offer(next);
                }
            }
            System.out.println(result[W]);
        }
    }
}