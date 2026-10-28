import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int cnt= 0;
        Arrays.sort(people);
        int n= people.length;
        int minidx= 0;

        for(int i= n-1; i>=0 ; i--){
            if(i< minidx) break;
            int left= limit- people[i];
            cnt++;

            if(people[minidx]<= left) minidx++;
        }

        return cnt;
    }
}