/**
 * 프로그래머스 조이스틱
 * 
 * 해당 알파벳이 앞, 뒤 중 어떤게 더 가까운지 판별하고 구현
 * A : 0, Z : 1, B : 1, Y : 2, C : 2...
 */

class Solution {
    public int solution(String name) {
        int answer = 0;
        int move = name.length() - 1;
        int index;
        
        for (int i = 0; i < name.length(); i++) {
            answer += Math.min((int)(name.charAt(i) - 65), (int)(90 - name.charAt(i) + 1));
            
            index = i + 1;
            
            while (index < name.length() && name.charAt(index) == 65) index++;
            
            move = Math.min(move, i * 2 + name.length() - index);
            move = Math.min(move, (name.length() - index) * 2 + i);
            
        }
        
        return answer + move;
    }
}