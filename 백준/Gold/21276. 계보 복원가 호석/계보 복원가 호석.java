import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<String, Set<String>> graph = new HashMap<>(); // 자손 담을 map
    static Map<String, Integer> indegree = new HashMap<>(); // 차수 저장할 map
    static Map<String, List<String>> children = new HashMap<>(); // 자식만 담을 map

    static List<String> names = new ArrayList<>(); // 이름 리스트
    static List<String> roots = new ArrayList<>(); // 조상 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            String name = st.nextToken();

            names.add(name);
            graph.put(name, new HashSet<>());
            children.put(name, new ArrayList<>());
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            String child = st.nextToken();
            String parent = st.nextToken();
            graph.get(parent).add(child);
        }

        buildIndegree();
        solve();
        print();
    }

    /**
     * 차수 계산 메서드
     */

    static void buildIndegree() {
        for (String name : names) indegree.put(name, 0);

        for (String parent : graph.keySet()) {
            for (String child : graph.get(parent)) {
                indegree.put(child, indegree.get(child) + 1);
            }
        }

        for (String name : names) {
            if (indegree.get(name) == 0) roots.add(name); // 조상 저장
        }

        Collections.sort(roots);
    }

    /**
     * 위상 정렬 메서드
     */

    static void solve() {
        Queue<String> q = new ArrayDeque<>();
        for (String root : roots) q.offer(root);

        while (!q.isEmpty()) {
            String parent = q.poll();

            for (String child : graph.get(parent)) {
                indegree.put(child, indegree.get(child) - 1);

                // indegree 0이 되는 순간 parent → child 직계 확정
                if (indegree.get(child) == 0) {
                    children.get(parent).add(child);
                    q.offer(child);
                }
            }
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();

        // root 출력
        sb.append(roots.size()).append("\n");
        for (String r : roots) sb.append(r).append(" ");
        sb.append("\n");

        // 이름 정렬 후 각각 직계 출력
        Collections.sort(names);

        for (String name : names) {
            List<String> list = children.get(name);
            Collections.sort(list);

            sb.append(name).append(" ").append(list.size());
            for (String child : list) sb.append(" ").append(child);
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
