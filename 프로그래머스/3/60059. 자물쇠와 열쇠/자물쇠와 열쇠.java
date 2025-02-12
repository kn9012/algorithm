/**
 * 프로그래머스 자물쇠와 열쇠
 * 1. 길이가 N + (M - 1) * 2만큼의 자물쇠 배열 새로 만들기
 * 2. 시계 방향으로 90도로 회전하는 함수
 * 3. 
 */

class Solution {
    int N, M, len, newLock[][];
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        
        N = lock.length;
        M = key.length;
        
        len = N + (M - 1) * 2;
        
        newLock = new int[len][len];
        
        for (int i = M - 1; i < M - 1 + N; i++) {
            for (int j = M - 1; j < M - 1 + N; j++) {
                newLock[i][j] = lock[i - M + 1][j - M + 1];
            }
        }
        
        for (int i = 0; i < 4; i++) {
            if (check(key)) {
                return true;
            } else key = rotation(key);
        }
        
        return answer;
    }
    
    // 열쇠 90도 회전 함수
    public int[][] rotation(int[][] key) {
        int[][] newKey = new int[M][M];
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                newKey[i][j] = key[M - 1 - j][i];
            }
        }
        
        return newKey;
    }
    
    // 키 이동하면서 자물쇠의 홈이 다 채워지는지 확인
    public boolean check(int[][] key) {        
        for (int i = 0; i < N + M - 1; i++) {
            for (int j = 0; j < N + M - 1; j++) {
                for (int k = 0; k < M; k++) {
                    for (int l = 0; l < M; l++) {
                        newLock[i + k][j + l] += key[k][l];
                    }
                }
                
                boolean isOne = true;
                for (int k = M - 1; k < M + N - 1; k++) {
                    for (int l = M - 1; l < M + N - 1; l++) {
                        if (newLock[k][l] != 1) {
                            isOne = false;
                            break;
                        }
                    }
                }
                
                if (isOne) return true;
                
                for (int k = 0; k < M; k++) {
                    for (int l = 0; l < M; l++) {
                        newLock[i + k][j + l] -= key[k][l];
                    }
                }
                
            }
        }
        
        return false;
    }
}