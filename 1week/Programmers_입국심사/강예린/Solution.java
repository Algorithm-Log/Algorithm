import java.util.*;

class Solution {
    
    public long solution(int n, int[] times) {
        
        Arrays.sort(times);
        
        long right=  n * (long) times[times.length-1];
        long left= 0;
        
        while(left <= right) {
            long mid= (left+right)/2;
            long cnt =0;
            
            for(int i=0; i< times.length; i++) {
                cnt+= mid /  times[i]; 
                
            }
           
            if(cnt>= n) right = mid -1;
            else left= mid +1;
            
        }

        return right+1;
    }
}