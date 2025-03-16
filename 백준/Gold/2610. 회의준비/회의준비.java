import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] dist;
    static final int INF = 100000000;
    static int[] parent;
    static boolean[] visited;
    static List<Integer>[] list;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // 유니온 파인드 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        // 인접 리스트 초기화
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        // 거리 행렬 초기화
        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) Arrays.fill(dist[i], INF);
        for (int i = 1; i <= N; i++) dist[i][i] = 0;

        // 그래프 입력 및 유니온 파인드
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
            dist[a][b] = dist[b][a] = 1; // 거리는 1

            union(a, b);
        }

        // 플로이드-워셜 알고리즘 수행 (모든 쌍 최단 거리)
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // 그룹별 대표자 찾기
        visited = new boolean[N + 1];
        int groupCount = 0;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                int leader = findLeader(i); // 대표 노드 찾기
                pq.add(leader);
                groupCount++;
            }
        }

        // 결과 출력
        System.out.println(groupCount);
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }

    // 유니온-파인드: find 함수 (경로 압축)
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // 유니온-파인드: union 함수
    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    // 그룹 내 대표 노드 찾기
    public static int findLeader(int start) {
        int root = find(start);
        visited[start] = true;

        List<Integer> group = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (find(i) == root) {
                group.add(i);
                visited[i] = true;
            }
        }

        // 대표자 선정: eccentricity(가장 먼 거리 중 최소)
        int minEccentricity = INF;
        int leader = -1;

        for (int node : group) {
            int maxDist = 0;
            for (int other : group) {
                maxDist = Math.max(maxDist, dist[node][other]);
            }

            // 더 작은 eccentricity를 가지거나, 같은 경우 번호가 작은 것 선택
            if (maxDist < minEccentricity) {
                minEccentricity = maxDist;
                leader = node;
            } else if (maxDist == minEccentricity && node < leader) {
                leader = node;
            }
        }

        return leader;
    }
}
