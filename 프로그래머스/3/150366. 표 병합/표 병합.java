import java.util.*;

/**
 * 프로그래머스 표 병합
 * 어떤 자료구조를 사용할지 고민했는데 Union-Find를 사용할 생각을 못했다
 * 
 */

class Solution {
    static final int SIZE = 51;
    static int[] parent = new int[SIZE * SIZE];
    static String[] value = new String[SIZE * SIZE];

    public String[] solution(String[] commands) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            parent[i] = i;
            value[i] = "";
        }

        List<String> answer = new ArrayList<>();

        for (String cmd : commands) {
            String[] parts = cmd.split(" ");
            String type = parts[0];

            if (type.equals("UPDATE")) {
                if (parts.length == 4) { // (r, c)의 값 변경일 경우
                    updateCell(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]);
                } else // value1을 모두 value2로 바꾸는 경우
                    updateAll(parts[1], parts[2]);
            } else if (type.equals("MERGE")) {
                union(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
            } else if (type.equals("UNMERGE")) {
                unmerge(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            } else if (type.equals("PRINT")) {
                String res = print(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                answer.add(res);
            }
        }

        return answer.toArray(new String[0]);
    }

    private int idx(int r, int c) {
        return r * SIZE + c;
    }

    private int find(int n) {
        if (parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }

    private void union(int r1, int c1, int r2, int c2) {
        int a = find(idx(r1, c1));
        int b = find(idx(r2, c2));
        if (a == b) return;

        // 값 결정 로직
        String v = !value[a].isEmpty() ? value[a] : value[b];

        // 병합
        parent[b] = a;
        value[a] = v;
        value[b] = "";
    }

    private void updateCell(int r, int c, String val) {
        int root = find(idx(r, c));
        value[root] = val;
    }

    private void updateAll(String v1, String v2) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            if (value[i].equals(v1)) value[i] = v2;
        }
    }

    private void unmerge(int r, int c) {
        int target = idx(r, c);
        int root = find(target);
        String keep = value[root];

        List<Integer> group = new ArrayList<>();
        for (int i = 0; i < SIZE * SIZE; i++) {
            if (find(i) == root) group.add(i);
        }

        for (int cell : group) {
            parent[cell] = cell;
            value[cell] = "";
        }

        value[target] = keep;
    }

    private String print(int r, int c) {
        int root = find(idx(r, c));
        return value[root].isEmpty() ? "EMPTY" : value[root];
    }
}
