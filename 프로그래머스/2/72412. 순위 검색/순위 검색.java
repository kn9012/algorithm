import java.util.*;

/**
 * 프로그래머스 순위 검색
 * info에 들어온 값들을 바탕으로 -를 포함한 모든 경우의 수를 만들어서 map에 저장 -> score도 저장
 * query문 돌면서 해당하는 키 찾고 이분 탐색으로 원하는 점수 이상인 사람 수 찾기
 */

class Solution {
    static Map<String, List<Integer>> map = new HashMap<>();    
    
    public int[] solution(String[] info, String[] query) {        
        for (String i : info) {
            String[] words = i.split(" ");
            
            dfs(0, words, "", Integer.parseInt(words[4]));
        }
        
        for (List<Integer> scores : map.values()) {
            Collections.sort(scores);
        }
        
        int[] answer = new int[query.length];
        int index = 0;
        
        for (String q : query) {
            String[] words = q.replaceAll(" and ", "").split(" ");
            
            String key = words[0];
            
            List<Integer> scores = map.get(key);
            
            if (scores == null) {
                answer[index++] = 0;
                continue;
            }
            
            int score = Integer.parseInt(words[1]);
            
            // 이분탐색 : score보다 큰 점수 세기
            int left = 0;
            int right = scores.size();
            
            while (left < right) {
                int mid = left + (right - left) / 2;
                
                if (scores.get(mid) < score) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            answer[index++] = scores.size() - left;
        }
        
        return answer;
    }
    
    public void dfs(int depth, String[] words, String key, int score) {
        if (depth == 4) {
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
            return;
        }
        
        dfs(depth + 1, words, key + words[depth], score);
        dfs(depth + 1, words, key + "-", score);
    }
}