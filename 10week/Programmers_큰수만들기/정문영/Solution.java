import java.util.*;

class Solution {
    public static String solution(String number, int k) {
        String answer = "";
        StringBuilder sb = new StringBuilder();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < number.length(); i++) {
        	while (!stack.isEmpty() && k > 0) {
    			if (stack.peek() < number.charAt(i)) {
        			stack.pop();
        			k -= 1;
        		} else {
        			break;
        		}
    		}
            
        	stack.push(number.charAt(i));
        }
        
        if (k > 0) {	// 추가 제거
        	while(k > 0) {
        		stack.pop();
        		k -= 1;
        	}
        }
        
        for (char c : stack) {
        	sb.append(c);
        }
        
        answer += sb;
            
        return answer;
    }
}