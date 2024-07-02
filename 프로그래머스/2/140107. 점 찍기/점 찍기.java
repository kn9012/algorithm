/**
 * 프로그래머스 점 찍기
 *
 * 아이디어
 * 도저히 무슨 말인지 모르겠어서(...) 풀이 참고
 * x가 0부터 d까지의 크기일때의 y의 최댓값을 구한다.
 * 이때, y는 k의 배수고 0일때도 포함해야 하므로 y / k + 1개
 */

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        for (long i = 0; i <= d; i+=k) {
            int y = (int)Math.sqrt((long)d * d - (long)i * i);
            answer += y / k + 1;
        }
        return answer;
    }
}