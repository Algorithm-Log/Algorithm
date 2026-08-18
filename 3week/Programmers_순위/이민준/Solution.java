import java.util.*;
class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        List<List<Integer>> winList = new ArrayList<>();
        List<List<Integer>> loseList = new ArrayList<>();
        
        for(int i = 0; i <= n; i++){
            winList.add(new ArrayList<>());
            loseList.add(new ArrayList<>());
        }
        
        for(int[] game : results){
            winList.get(game[0]).add(game[1]);
            loseList.get(game[1]).add(game[0]);
        }
        
        for(int i = 1; i <= n; i++){
            int winCount = dfs(i, new boolean[n+1], winList);
            int loseCount = dfs(i, new boolean[n+1], loseList);
            if(loseCount + winCount == n - 1) answer++;
        }
        
        return answer;
    }
    
    public int dfs(int i, boolean []visited, List<List<Integer>> list) {
        visited[i] = true;
        
        int cnt = 0;
        for(int num : list.get(i)){
            if(visited[num]) continue;
            cnt += 1 + dfs(num, visited, list);
        }
        return cnt;
    }
}