import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {

        // 숫자가 낮은 순으로 정렬(오름차순) -> 만약 특정 번호가 접두사인 번호가 있다면 서로 붙어있게끔 정렬된다.
        Arrays.sort(phone_book);
        
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i+1].startsWith(phone_book[i])) {
                return false;
            }
        }

        return true;
    }
}