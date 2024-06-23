import java.util.*;

class Solution {
    class Point {
        int x, y;
        Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    int deltas[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int count = 0;
    boolean isVisited[][];
    ArrayList<Integer> list = new ArrayList<>();
    
    public ArrayList<Integer> solution(String[] maps) {
        isVisited = new boolean[maps.length][maps[0].length()];
        
        for (int i = 0 ; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) != 'X' && !isVisited[i][j]) {
                    count++;
                    list.add(bfs(i, j, maps));
                }
            }
        }
        
        if (count == 0) {
            list.add(-1);
        } else {
            Collections.sort(list);
        }

        return list;
    }
    
    public int bfs(int i, int j, String[] maps) {
        int sum = 0;
        Queue<Point> queue = new ArrayDeque<>();
        isVisited[i][j] = true;
        queue.add(new Point(i, j));
        
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int X = cur.x;
            int Y = cur.y;
        
            sum += maps[X].charAt(Y) - '0';
            
            for (int k = 0; k < 4; k++) {
                int dx = X + deltas[k][0];
                int dy = Y + deltas[k][1];
            
                if (dx >= maps.length || dx < 0 || dy >= maps[0].length() || dy < 0 || isVisited[dx][dy] || maps[dx].charAt(dy) == 'X') continue;
                isVisited[dx][dy] = true;
                queue.add(new Point(dx, dy));
            }
        }
        
        return sum;
    }
}