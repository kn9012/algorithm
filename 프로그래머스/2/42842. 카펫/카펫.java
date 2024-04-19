/**
    brown/2한 값에 2를 더하면 가로+세로 길이
    가로는 항상 세로보다 길다
    가로 x 세로 = brown + yellow
    가로 + 세로 = brown/2 + 2
*/

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        for (int i = 3; i < brown / 2; i++) {
            for (int j = 1; j < brown / 2; j++) {
                if (i * j == brown + yellow && i + j == brown / 2 + 2) {
                    answer[0] = Math.max(i, j);
                    answer[1] = Math.min(i, j);
                }
            }
        }
        
        return answer;
    }
}