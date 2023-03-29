class Solution {
    public int solution(int[] common) {
        int answer = 0;
        
        int x = common[2] - common[1];
        int y = common[1] - common[0];
        
        if(x == y) {
            answer = common[common.length - 1] + x;
        }
        else {
            answer = common[common.length - 1] * common[2] / common[1];
        }
        
        
        return answer;
    }
}