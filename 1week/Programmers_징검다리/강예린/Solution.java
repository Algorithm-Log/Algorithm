import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int max= Integer.MIN_VALUE;
        
        int [] diff = new int [rocks.length+1];
        diff[0]= rocks[0];
        
        for(int i=1; i< rocks.length; i++){
            diff[i]= rocks[i]- rocks[i-1];
        }
        
        diff[rocks.length]= distance- rocks[rocks.length-1];
        
        int left=0;
        int right= distance;
        
        
        while(left<= right){
            int remove=0;
            int mid = (left+ right)/2;
            int now=0;
            
            for(int i=0; i< diff.length; i++){
                
                if (now+ diff[i] < mid) {
                    now += diff[i]; 
                    remove ++;
                } else now =0;
            }
            
            if(remove > n) {
                right= mid-1;
                
            }
            else left= mid+1;
            
        }
        
       return right;
    }
}