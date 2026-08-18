import java.util.*;

class Solution {
    List<List<Integer>> list = new ArrayList<>();
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        for(int i = 0; i < n; i++){
            list.add(new ArrayList<>());
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j && computers[i][j] == 1){
                    list.get(i).add(j);
                }
            }
        }
        
        visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(visited[i]) continue;
            visited[i] = true;
            answer++;
            dfs(list.get(i));
        }
        
        return answer;
    }
    
    public void dfs(List<Integer> li){
        
        for(int i : li){
            if(visited[i]) continue;
            visited[i] = true;
            dfs(list.get(i));
        }
        
    }
}