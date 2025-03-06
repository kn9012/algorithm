class Solution {
    int[][][] map;

    public int[][] solution(int n, int[][] build_frame) {
        map = new int[n + 1][n + 1][2];

        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int type = frame[2];
            int work = frame[3];

            if (type == 0) { // 기둥
                if (work == 1) { // 설치
                    if (isPillarValid(x, y, n)) {
                        map[x][y][0] = 1;
                    }
                } else { // 삭제
                    map[x][y][0] = 0;
                    if (!canDelete(n)) map[x][y][0] = 1;
                }
            } else { // 보
                if (work == 1) { // 설치
                    if (isBeamValid(x, y, n)) {
                        map[x][y][1] = 1;
                    }
                } else { // 삭제
                    map[x][y][1] = 0;
                    if (!canDelete(n)) map[x][y][1] = 1;
                }
            }
        }

        int count = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (map[i][j][0] == 1) count++;
                if (map[i][j][1] == 1) count++;
            }
        }

        int[][] answer = new int[count][3];
        int index = 0;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (map[i][j][0] == 1) {
                    answer[index++] = new int[]{i, j, 0};
                }
                if (map[i][j][1] == 1) {
                    answer[index++] = new int[]{i, j, 1};
                }
            }
        }

        return answer;
    }

    public boolean isPillarValid(int x, int y, int n) {
        return y == 0 || (y > 0 && map[x][y - 1][0] == 1) || 
               (x > 0 && map[x - 1][y][1] == 1) || map[x][y][1] == 1;
    }

    public boolean isBeamValid(int x, int y, int n) {
        return (y > 0 && map[x][y - 1][0] == 1) || (y > 0 && map[x + 1][y - 1][0] == 1) ||
               (x > 0 && map[x - 1][y][1] == 1 && map[x + 1][y][1] == 1);
    }

    public boolean canDelete(int n) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (map[i][j][0] == 1 && !isPillarValid(i, j, n)) return false;
                if (map[i][j][1] == 1 && !isBeamValid(i, j, n)) return false;
            }
        }
        return true;
    }
}
