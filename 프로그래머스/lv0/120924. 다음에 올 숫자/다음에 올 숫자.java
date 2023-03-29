class Solution {
    public int solution(int[] common) {
        int answer = 0;
        int i = 0;
        if (common[i + 1] - common[i] == common[common.length - 1] - common[common.length - 2] && i + 1 < common.length) {
            answer = 2 * common[common.length - 1] - common[common.length - 2];
        } else if (common[i + 1] / common[i] == common[common.length - 1] / common[common.length - 2] && i + 1 < common.length) {
            answer = common[common.length - 1] * common[common.length - 1] / common[common.length - 2];
        }

        return answer;
    }

    public static void main(String[] args) {
        int common[] = {1, 2, 3, 4};
        Solution so = new Solution();
        System.out.println(so.solution(common));
    }
}