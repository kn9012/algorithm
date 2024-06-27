/**
 * 프로그래머스 가장 큰 정사각형 찾기
 * 
 * 아이디어
 * DP로 풀이
 */

class Solution {
    public int solution(int [][]board) {
        int answer = -1;
        
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                if (board[i][j] > 0) board[i][j] += Math.min(board[i - 1][j - 1], Math.min(board[i - 1][j], board[i][j - 1]));
                
                answer = Math.max(answer, board[i][j]);           
            }
        }
    
        if (answer == 0) return 0;
        return answer * answer;
    }
}