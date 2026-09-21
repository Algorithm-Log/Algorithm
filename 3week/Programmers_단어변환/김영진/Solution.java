import java.util.*;
import java.io.*;

class Solution {
    int answer;
    boolean[] visit;
    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        visit = new boolean[words.length];
        
        if(Arrays.asList(words).contains(target)){
            dfs(begin, target, words, 0);
        }
        else return 0;
        
        return answer;
    }
    public void dfs(String begin, String target, String[] words, int count){
        if(begin.equals(target)){
            answer = Math.min(answer, count);
            return;
        }
        
        for(int i = 0; i < words.length; i++){
        int cnt = 0;
            for(int j = 0; j < begin.length(); j++){
                if(begin.charAt(j) != words[i].charAt(j)){
                    cnt++;
                }
            }
            if(cnt == 1 && !visit[i]){
                visit[i] = true;
                dfs(words[i], target, words, count + 1);
                visit[i] = false;
            }
        }
    }
}