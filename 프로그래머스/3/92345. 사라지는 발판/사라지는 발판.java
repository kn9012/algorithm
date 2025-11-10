import java.util.*;

class Solution {
    public int N, M, INF = 987654321;
    public int[][] map;
    public int[] dx = {0,0,1,-1};
    public int[] dy = {1,-1,0,0};

    //turn : true -> A턴
    //turn : false -> B턴
    public int solve(int ax, int ay, int bx, int by, boolean turn) {
        int x = turn ? ax : bx;
        int y = turn ? ay : by;

        //최선의 경우 리턴
        List<Integer> list = new ArrayList<>();
        for (int d = 0; d < 4; d++) {
            int X = x + dx[d];
            int Y = y + dy[d];
            if (X < 0 || Y < 0 || X >= N || Y >= M || map[X][Y] == 0) continue;

            //현재 상대가 같은 발판에 있을 경우 (움직일 경우 바로 끝남)
            if (ax == bx && ay == by) {
                list.add(1);
                continue;
            }
            map[x][y] = 0;
            int res;
            if (turn) res = -solve(X, Y, bx, by, !turn);
            else res = -solve(ax, ay, X, Y, !turn);
            if (res >= 0) res++;
            else res--;
            list.add(res);
            map[x][y] = 1;
        }

        int result;
        int pMax = -INF, pMin = INF;
        int mMax = -INF, mMin = INF;
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);
            if (num > 0) {
                pMax = Math.max(pMax, num);
                pMin = Math.min(pMin, num);
            } else {
                mMax = Math.max(mMax, num);
                mMin = Math.min(mMin, num);
            }
        }

        if (pMax == -INF && mMax == -INF) return 0;
        else if (pMax == -INF) return mMin;
        else if (pMax != -INF) return pMin;
        else return 0;
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        N = board.length;
        M = board[0].length;
        map = board;
        return Math.abs(solve(aloc[0], aloc[1], bloc[0], bloc[1], true));
    }
}