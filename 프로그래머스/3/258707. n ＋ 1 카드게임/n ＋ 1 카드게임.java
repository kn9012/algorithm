/**
 * 프로그래머스 n+1 카드게임
 * 
 * 1. 카드 cards.length/3개와 동전 coin개
 * 2. 매 라운드마다 카드 2장씩 뽑고 필요한 카드 있으면 카드 1개 당 동전 1개로 교환 가능
 * 필요 없다면 카드 버리기 가능 but 합이 n+1가 되는 카드 2장을 매 라운드마다 내야 종료가 안됨
 *
 * 최대 라운드가 될 수 있도록 진행 => dp?
 */

class Solution {
    
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        
        boolean[] mycards = new boolean[n];
        boolean[] newcards = new boolean[n];
        
        for (int i=0; i<n/3; i++) {
            mycards[cards[i]-1] = true;
        }
        int life = 0;
        int templife = 0;
        
        for (int i=0; i<n/2; i++) {
            if (mycards[i] && mycards[n-i-1]) life++;
        }
        
        for (int i=1; i<=n/3+1; i++) {
            if (i==n/3+1) return i;
            
            int card1 = cards[n/3+2*(i-1)];
            int card2 = cards[n/3+2*(i-1)+1];
            
            if (mycards[n-card1] && coin>0) {
                coin--;
                life++;
            }
            if (mycards[n-card2] && coin>0) {
                coin--;
                life++;
            }
            
            if (newcards[n-card1]) templife++;
            else newcards[card1-1] = true;
            
            if (newcards[n-card2]) templife++;
            else newcards[card2-1] = true;
            // 짝이 되는 경우가 확인되면 그 이후론 절대 짝이 등장하지 않으므로
            // newcards에 표시할 필요도 없음
            
            if (life==0 && coin>=2 && templife>0) {
                templife--;
                coin -= 2;
                life++;
            }
            
            if (life==0) return i;
            life--;
        }
        return -1;
    }
    
}