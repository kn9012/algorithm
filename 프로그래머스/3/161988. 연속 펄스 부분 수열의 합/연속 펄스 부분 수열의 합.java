/**
 * 프로그래머스 연속 펄스 부분 수열의 합
 * - DFS 그리디?
 * 최대가 되려면 연속한 숫자들이 +, -, +...와 같이 반복되는 수열 중 가장 큰 절댓값을 가질때
 * - 그럼 오히려 투포인터일려나
 */

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        int[] arr1 = new int[sequence.length];
        int[] arr2 = new int[sequence.length];
        
        for (int i = 0; i < sequence.length; i++) {
            if (i % 2 == 0) arr1[i] = sequence[i];
            else arr1[i] = -sequence[i];
        }
        
        for (int i = 0; i < sequence.length; i++) {
            if (i % 2 == 0) arr2[i] = -sequence[i];
            else arr2[i] = sequence[i];
        }
        
        long total = 0;
        for (int start = 0, end = 0; start < sequence.length; start++) {
            while (total >= 0 && end < sequence.length) {
                total += arr1[end];
                answer = Math.max(answer, total);
                end++;
            }
            total -= arr1[start];
        }
        
        total = 0;
        for (int start = 0, end = 0; start < sequence.length; start++) {
            while (total >= 0 && end < sequence.length) {
                total += arr2[end];
                answer = Math.max(answer, total);
                end++;
            }
            total -= arr2[start];
        }
        
        
        return answer;
    }
}