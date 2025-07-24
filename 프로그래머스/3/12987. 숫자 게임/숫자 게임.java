/**
 * 프로그래머스 숫자 게임
 * - 정렬 + 투 포인터
 */

import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {        
        int answer = 0;
            
        Arrays.sort(A);
        Arrays.sort(B);
        
        int left = 0; // A 인덱스
        int right = 0; // B 인덱스
        
        while (right < A.length) {
            if (A[left] < B[right]) {
                left++;
                right++;
                answer++;
            } else {
                while (right < A.length) {
                    if (A[left] >= B[right]) right++;
                    else {
                        left++;
                        right++;
                        answer++;
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
}