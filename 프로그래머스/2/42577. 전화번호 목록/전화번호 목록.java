import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        
        Arrays.sort(phone_book);
        
        for(int i = 0; i < phone_book.length - 1; i++) {
            
            String phone = phone_book[i];
            String postPhone = phone_book[i + 1];
            
            if (postPhone.startsWith(phone)) {
                return false;
            }
        }
        
        return true;
    }
}