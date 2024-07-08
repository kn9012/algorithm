class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int lastAttackTime = attacks[attacks.length - 1][0];
        int count = 1;
        int curHealth = health;
        int index = 0;
        
        for (int i = 1; i <= lastAttackTime; i++) { 
            System.out.println(i + " " + curHealth);
            // 공격 시간일 경우
            if (index < attacks.length && attacks[index][0] == i) {
                count = 0; // 연속 성공 시간 0으로 초기화
                curHealth -= attacks[index][1]; // 몬스터 공격으로 체력 깎임
                index++;
            } else { // 공격 시간이 아닐 경우
                if (curHealth + bandage[1] < health) {
                    
                    count++;
                    if (count != bandage[0]) curHealth += bandage[1];
                    if (count == bandage[0]) { // 연속 성공 시간일 경우
                        curHealth += bandage[2] + bandage[1];
                        if (curHealth >= health) curHealth = health;
                        count = 0; // 연속 성공 시간 0으로 초기화
                    }
                } else curHealth = health;
            }
            // 캐릭터 체력이 0 이하라면 -1 return하고 종료
            if (curHealth <= 0) {
                answer = -1;
                break;
            }
        }
        
        if (answer != -1) answer = curHealth;
        return answer;
    }
}