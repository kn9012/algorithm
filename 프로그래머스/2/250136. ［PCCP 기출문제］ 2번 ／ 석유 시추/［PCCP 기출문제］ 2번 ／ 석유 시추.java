/**
 * 프로그래머스 석유 시추
 *
 * dfs로 각각의 크기를 구하고 시추관의 위치에 따라 더하기?
 */

import java.util.*;

public class Solution {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static class Area {
        int start, end, area;
        Area(int start, int end, int area) {
            this.start = start;
            this.end = end;
            this.area = area;
        }
    }
    
    static int deltas[][] = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int xLen, yLen;
    static boolean isVisited[][];
    static ArrayList<Area> list = new ArrayList<>();
    
    public static int solution(int[][] land) {
        int answer = Integer.MIN_VALUE;
        xLen = land.length;
        yLen = land[0].length;
        isVisited = new boolean[land.length][land[0].length];
        
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (land[i][j] == 1 && !isVisited[i][j]) {
                    list.add(bfs(new Point(i, j), land));
                }
            }
        }
        
        for (int i = 0; i < land[0].length; i++) {
            int nowArea = 0;
            for (int j = 0; j < list.size(); j++) {
            	
                Area area = list.get(j);
                //System.out.println(area.start + " " + area.)
                if (i >= area.start && i <= area.end) {
                    nowArea += area.area;
                }
            }
            
            //System.out.println(i + " " + nowArea);
            answer = Math.max(answer, nowArea);
        }

        
        return answer;
    }
    
    public static Area bfs(Point p, int[][] land) {
    	Queue<Point> queue = new ArrayDeque<>();
    	queue.add(p);
    	isVisited[p.x][p.y] = true;
    	
    	int count = 0;
    	int minY = Integer.MAX_VALUE;
    	int maxY = Integer.MIN_VALUE;
    	
    	while (!queue.isEmpty()) {
    		Point cur = queue.poll();
    		int X = cur.x;
    		int Y = cur.y;
    		
            count++;
            
    		minY = Math.min(minY, Y);
            maxY = Math.max(maxY, Y);
    		
            for (int i = 0; i < 4; i++) {
                int dx = X + deltas[i][0];
                int dy = Y + deltas[i][1];
                
                if (dx >= xLen || dy >= yLen || dx < 0 || dy < 0 || isVisited[dx][dy] || land[dx][dy] == 0) continue;
                
                isVisited[dx][dy] = true;

                queue.add(new Point(dx, dy));
                
                //System.out.println(dx + " " + dy);
            }
    	}
        
        //System.out.println();
    	
    	//System.out.println(minY + " " +  maxY + " " + count);
    	return new Area(minY, maxY, count);
    }
}