/**
 * 프로그래머스 모음 사전
 * - 완탐
 */

import java.util.*;

class Solution {
    static List<String> words = new ArrayList<>();
    public int solution(String word) {
        createWord("");
        return words.indexOf(word);
    }
    
    public void createWord(String word) {
        if (word.length() > 5) return;
        
        words.add(word);
        
        for (int i = 0; i < 5; i++) {
            createWord(word + "AEIOU".charAt(i));
        }
    }
}