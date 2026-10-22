import java.util.*;

class Solution {
    private static int[] num;
    private static Stack<Integer> stack;
    
    public static String solution(String number, int k) {
        String answer = "";
        num = new int[number.length()];
        for (int i = 0; i < number.length(); i++)
            num[i] = number.charAt(i) - '0';
        
        stack = new Stack<>();
        k = remove(k);
        
        if (k > 0) {	// 추가 제거
        	while(k > 0) {
        		stack.pop();
        		k -= 1;
        	}
        }
        
        for (int i = 0; i < stack.size(); i++) 
			answer += stack.get(i);
            
        return answer;
    }
    
    private static int remove(int k) {
    	int idx = 1;
        stack.push(num[0]);
    	
    	while (k > 0 && idx < num.length) {
    		while (!stack.isEmpty() && k > 0) {
    			if (stack.lastElement() < num[idx]) {
        			stack.pop();
        			k -= 1;
        		} else {
        			break;
        		}
    		}
            
        	stack.push(num[idx]);
    		idx += 1;
    	}
    	
    	if (idx < num.length) {	// k개 제거 후 남은 수 추가
    		while(idx < num.length) {
    			stack.push(num[idx]);
    			idx += 1;
    		}
    	}
    	
    	return k;
    }
}