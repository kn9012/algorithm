/**
 * 프로그래머스 여행경로
 *
 * 아이디어 - DFS?
 */

import java.util.*;

class Solution {
    static boolean isVisited[];
    static ArrayList<String> list = new ArrayList<>();
    
    public static String[] solution(String[][] tickets) {
    	String[] answer = {};
        isVisited = new boolean[tickets.length];
        dfs("ICN", "ICN", 0, tickets);
        Collections.sort(list);
        answer = list.get(0).split(" ");
        return answer;
    }
    
    public static void dfs(String start, String route, int count, String[][] tickets) {
        if (count == tickets.length) {
        	list.add(route);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
        	if (start.equals(tickets[i][0]) && !isVisited[i]) {
        		isVisited[i] = true;
        		dfs(tickets[i][1], route + " " + tickets[i][1], count + 1, tickets);
        		isVisited[i] = false;
        	}
        }
    }
}