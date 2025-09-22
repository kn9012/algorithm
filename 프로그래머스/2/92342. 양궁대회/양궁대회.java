import java.util.*;

class Solution {
    int maxDiff = 0;            // 라이언과 어피치의 점수 차이 최대
    int[] bestAnswer = {-1};    // 최적의 라이언 화살 배치 (아직 없으면 {-1})
    int n;                       // 라이언이 쏠 수 있는 화살 수
    int[] info;                  // 어피치가 쏜 화살 정보

    public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;
        // 라이언이 쏠 화살 수를 저장할 배열
        int[] lion = new int[11];
        // dfs로 0점부터 10점 구역 탐색
        dfs(0, lion, 0);
        return bestAnswer;
    }

    /**
     * @param idx 현재 고려하는 과녁 점수 인덱스 (0은 10점, 10은 0점)
     * @param lion 라이언이 지금까지 맞춘 화살 개수 정보
     * @param used 라이언이 지금까지 사용한 화살 개수
     */
    private void dfs(int idx, int[] lion, int used) {
        // 기저 케이스: 모든 점수 구역 고려했거나 화살 다 쓴 경우
        if (idx == 11) {
            if (used > n) return;  // 화살을 초과 사용하면 불가능
            // 남은 화살이 있으면 0점 구역(idx=10)에 몰아쓰기
            if (used < n) {
                lion[10] += (n - used);
            }
            // 점수 차 계산
            int diff = calcScoreDiff(lion);
            if (diff > 0) {
                // 더 좋은 결과 갱신 조건
                if (diff > maxDiff) {
                    maxDiff = diff;
                    bestAnswer = lion.clone();
                } else if (diff == maxDiff) {
                    // 점수 차 같으면, 가장 낮은 점수를 더 많이 맞힌 배치를 선택
                    if (isBetter(lion, bestAnswer)) {
                        bestAnswer = lion.clone();
                    }
                }
            }
            // 남은 화살 다시 되돌리기 (0점 구역으로 더해준 것)
            if (used < n) {
                lion[10] -= (n - used);
            }
            return;
        }

        // 두 가지 경우 탐색
        // 1) 라이언이 해당 점수(idx)에 어피치보다 많이 맞춰서 점수 획득
        int required = info[idx] + 1;  // 어피치를 이기려면 info[idx] + 1 발 필요
        if (used + required <= n) {
            lion[idx] = required;
            dfs(idx + 1, lion, used + required);
            lion[idx] = 0;
        }

        // 2) 해당 구역 포기 → 화살 0발 쏨
        // 그냥 lion[idx] = 0 (원래 0일 테지만 명확히)
        dfs(idx + 1, lion, used);
    }

    /**
     * 어피치와 라이언의 점수를 비교해서 차이 계산
     * 라이언 점수 – 어피치 점수 반환
     */
    private int calcScoreDiff(int[] lion) {
        int apeachScore = 0;
        int ryanScore = 0;
        for (int i = 0; i < 11; i++) {
            int score = 10 - i;
            if (info[i] == 0 && lion[i] == 0) {
                // 둘 다 못 쏜 경우, 아무도 점수 없음
                continue;
            }
            if (lion[i] > info[i]) {
                ryanScore += score;
            } else {
                apeachScore += score;
            }
        }
        return ryanScore - apeachScore;
    }

    /**
     * 같은 점수 차이라면, 가장 낮은 점수를 더 많이 맞힌 배열이 더 좋음
     * lion1 vs lion2 비교
     * @return lion1 쪽이 더 좋은 경우 true
     */
    private boolean isBetter(int[] lion1, int[] lion2) {
        // 낮은 점수는 배열의 인덱스가 클수록 낮은 점수
        for (int i = 10; i >= 0; i--) {
            if (lion1[i] > lion2[i]) return true;
            else if (lion1[i] < lion2[i]) return false;
        }
        return false;
    }
}
