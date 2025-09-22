/**
 * 프로그래머스 n+1 카드게임
 * 
 * 1. 카드 cards.length/3개와 동전 coin개
 * 2. 매 라운드마다 카드 2장씩 뽑고 필요한 카드 있으면 카드 1개 당 동전 1개로 교환 가능
 * 필요 없다면 카드 버리기 가능 but 합이 n+1가 되는 카드 2장을 매 라운드마다 내야 종료가 안됨
 *
 * 최대 라운드가 될 수 있도록 진행 => dp?
 */

import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int target = n + 1;
        
        // 초기 손패
        Set<Integer> hand = new HashSet<>();
        for (int i = 0; i < n / 3; i++) {
            hand.add(cards[i]);
        }

        // 덱
        List<Integer> deck = new ArrayList<>();
        for (int i = n / 3; i < n; i++) {
            deck.add(cards[i]);
        }

        // 사용 가능한 카드 저장 (매 라운드마다 덱에서 2장씩 추가됨)
        List<Integer> pool = new ArrayList<>();

        int round = 1;
        for (int i = 0; i < deck.size(); i += 2) {
            // 이번 라운드에서 새로 뽑은 카드 2장 pool에 추가
            pool.add(deck.get(i));
            if (i + 1 < deck.size()) pool.add(deck.get(i + 1));

            // 현재 라운드에서 쌍을 만들 수 있는지 확인
            boolean success = false;

            // 1. 손 안에서 바로 만들 수 있는 경우 (코인 0)
            for (int x : hand) {
                if (hand.contains(target - x) && (target - x) != x) {
                    hand.remove(x);
                    hand.remove(target - x);
                    success = true;
                    break;
                }
            }

            // 2. 손 + pool (코인 1)
            if (!success && coin > 0) {
                for (int x : hand) {
                    if (pool.contains(target - x)) {
                        hand.remove(x);
                        pool.remove(Integer.valueOf(target - x));
                        coin--;
                        success = true;
                        break;
                    }
                }
            }

            // 3. pool + pool (코인 2)
            if (!success && coin > 1) {
                for (int x : pool) {
                    if (pool.contains(target - x) && (target - x) != x) {
                        pool.remove(Integer.valueOf(x));
                        pool.remove(Integer.valueOf(target - x));
                        coin -= 2;
                        success = true;
                        break;
                    }
                }
            }

            if (!success) break; // 더 이상 라운드 진행 불가
            round++;
        }

        return round;
    }
}
