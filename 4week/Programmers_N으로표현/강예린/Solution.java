import java.util.*;
class Solution {
    
    public int solution(int N, int number) {
        Set<Integer>[] dp= new HashSet[9];
        
        
        int maxNum=0;
        for(int i=1; i< dp.length; i++){
            
            maxNum= maxNum*10+N;
            dp[i]= new HashSet<>();
            dp[i].add(maxNum);
            
            for(int k= 1; k< i; k++){
                for(int num1: dp[i-k]){
                    for(int num2:dp[k]){
                        dp[i].add(num1+num2);
                        dp[i].add(num1-num2);
                        dp[i].add(num1*num2);
                        if(num2 != 0) dp[i].add(num1/num2);
                    }
                }
                
            }
            
            for(int num : dp[i]) {
                if(num == number) {
                    return i;
                }
            }
            
        }
        
        return -1;
    }
}