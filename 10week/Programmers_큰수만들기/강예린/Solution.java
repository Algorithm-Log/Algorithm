import java.util.*;
class Solution {
    public String solution(String number, int k) {

        Deque<Integer> stack= new ArrayDeque<Integer>();
        char[] nums= number.toCharArray();

        StringBuilder sb= new StringBuilder();

        int left= k;

        for(int i=0; i< nums.length; i++){
            int now= nums[i]-'0';

            while(!stack.isEmpty() && stack.peekFirst()< now && left> 0) {
                left--;
                stack.pop();
            }
            stack.push(now);
        }

        while(!stack.isEmpty()) sb.append(stack.pop());


        return sb.reverse().substring(0,number.length()-k);
    }
}