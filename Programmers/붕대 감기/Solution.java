
class Solution {

    // ** 붕대 감기**
    // 기본 t * x 체력 회복
    // t 초 연속 붕대 감기 성공 시 -> (t * x) + y 체력 회복
    // 몬스터에게 피격 시 기술 취소
    // 몬스터가 공격할 때 체력 회복 불가

    // 모든 공격이 끝난 직후 남은 체력을 return
    // 만약 몬스터의 공격을 받고 캐릭터의 체력이 0 이하가 되어 죽는다면 -1을 return

    // 1. 몬스터의 마지막 공격 시간을 구함 (0초 ~ 마지막 공격 시간)
    // 2. 시전 시간 count. 몬스터 공격 시간일 시, 시전 시간 초기화 & health 깎음
    // 3. 초마다 x 만큼 health를 회복
    // 4. 시전 시간이 다 차면 y를 추가 회복 -> 시전 시간 0으로 초기화
    

    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;

        int castTime = bandage[0];
        int recoverPerSec = bandage[1];
        int addedRecover = bandage[2];

        int currentHealth = health;

        int lastAttackTime = attacks[0][0];
        for (int i = 1; i < attacks.length; i++) {
            lastAttackTime = Math.max(lastAttackTime, attacks[i][0]);
        }

        int count = 1;
        for (int i = 1; i <= lastAttackTime; i++) {
            
            int damage = getAttackDamage(attacks, i);

            if (damage == 0) { //공격 시간 아님
                currentHealth = recover(currentHealth, health, recoverPerSec);
                if (++count == castTime) { // 시전 시간 다 차면 초기화
                    count = 0;
                    currentHealth = recover(currentHealth, health, addedRecover);
                }
                continue;
            }
            
            currentHealth = takeDamage(currentHealth, damage);

            if (currentHealth == -1) {
                break;
            }

            count = 0;
        }

        answer = currentHealth;
        System.out.println(answer);
        return answer;
    }

    public int getAttackDamage(int[][] attacks, int currentTime) {

        for (int[] attack : attacks) {
            if (attack[0] == currentTime)
                return attack[1];
        }
        return 0;
    }

    public int recover(int currentHealth, int maxHealth,int amtRecovery) {

        currentHealth += amtRecovery;
        if (currentHealth > maxHealth)
            return maxHealth;
        return currentHealth;
    }

    public int takeDamage(int currentHealth, int damage) {

        currentHealth -= damage;
        if (currentHealth < 1)
            return -1;

        return currentHealth;
    }
}