
class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        
        //기사단 1~number
        //자신의 번호의 '약수 개수'에 해당하는 공격력을 가진 무기 구매
        //그러나 공격력이 제한수치를 초과하면 협약기관에서 정한 공격력의 무기를 구매해야
        //공격력 1 = 철 1
        
        int ironWeight = 0;
        
        for(int i = 1; i <= number; i++) {
            
            int str = getDivisorNum(i);
            if (str > limit) {
                ironWeight += power;
                continue;
            }
                
            ironWeight += str;
        }
        
        answer = ironWeight;
        return answer;
    }
    
    public int getDivisorNum(int number) {
        
        
        int count = 0;
        
        for (int i = 1; i * i <= number; i++) {
            if (i * i == number) {
                count++;
            }
            
            else if ((number % i) == 0)
                count += 2;
        }
        
        return count;
    }
}