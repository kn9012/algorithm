/**
+인 부분집합 구한 뒤 합이 타겟 넘버인 경우만 count++
*/

public class Solution {
    static int answer;
    static boolean isSelected[]; // +인 정수 선택
    static public int solution(int[] numbers, int target) {
        answer = 0; // 타겟 넘버 만드는 방법 수
        isSelected = new boolean[numbers.length];
        subset(numbers, target, 0, 0); // 부분집합 만들기
        return answer;
    }
    
    static public void subset(int[] numbers, int target, int count, int sum) {
        if (count == numbers.length) {
            if (sum == target) answer++;
            
            return;
        }
        
        isSelected[count] = true;
        subset(numbers, target, count + 1, sum + numbers[count]);
        isSelected[count] = false;
        subset(numbers, target, count + 1, sum - numbers[count]);
    }
}