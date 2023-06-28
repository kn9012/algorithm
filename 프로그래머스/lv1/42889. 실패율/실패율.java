class Solution {
    public int[] solution(int N, int[] stages) {
        double[] stageCnt = new double[N];

        double[] answer = new double[N];
        double tempD = 0;
        int tempI = 0;
        int[] result = new int[N];

        for (int i = 0; i < stages.length; i++) {
            for (int j = 0; j < N; j++) {
                if (stages[i] == j + 1) {
                    stageCnt[j]++;
                }
            }
        }


        double denomi = stages.length;
        for (int i = 0; i < N; i++) {
            answer[i] = stageCnt[i] / denomi;
            denomi = denomi - stageCnt[i];
            result[i] = i + 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N - i; j++) {
                if (answer[j - 1] < answer[j]) {
                    tempD = answer[j - 1];
                    answer[j - 1] = answer[j];
                    answer[j] = tempD;

                    tempI = result[j - 1];
                    result[j - 1] = result[j];
                    result[j] = tempI;
                }
            }
        }
        return result;
    }
}