// import java.util.*;

// class Solution {
//     List<Integer>[] win; //내가 이긴 선수
//     List<Integer>[] lose; //내가 진 선수
//     public int solution(int n, int[][] results) {
//         win = new ArrayList[n+1];
//         lose = new ArrayList[n+1];
        
//         for(int i = 1; i <= n; i++){
//             win[i] = new ArrayList<>();
//             lose[i] = new ArrayList<>();
//         }
        
//         for(int[] edge : results){
//             int winner = edge[0];
//             int loser = edge[1];
            
//             win[winner].add(loser);
//             lose[loser].add(winner);
//         }
        
//         int answer = 0;
        
//         for(int i = 1; i <= n; i++){
//             int wincnt = dfs(i, win, new boolean[n+1]) - 1;
//             int losecnt = dfs(i,lose, new boolean[n+1]) - 1;
            
//             if(wincnt + losecnt == n -1 ){
//                 answer++;
//             }
//         }
//         return answer;
//     }
//     public int dfs(int node, List<Integer>[] gra, boolean[] visited){
//         visited[node] = true;
//         int cnt = 1;
        
//         for(int next : gra[node]){
//             if(!visited[next]){
//                 cnt += dfs(next, gra, visited);
//             }
//         }
//         return cnt;
//     }
// }


class Solution {
    public int solution(int n, int[][] results) {
        int[][] graph = new int[n + 1][n + 1];
        
        for (int[] edge : results) {
            int winner = edge[0];
            int loser = edge[1];
            
            graph[winner][loser] = 1;  
            graph[loser][winner] = -1; 
        }
        
        for (int k = 1; k <= n; k++) {          
            for (int i = 1; i <= n; i++) {      
                for (int j = 1; j <= n; j++) {  
                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;  
                        graph[j][i] = -1; 
                    }
                }
            }
        }
        
        int answer = 0;
        
        for (int i = 1; i <= n; i++) {
            int count = 0; 
            
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] != 0) {
                    count++;
                }
            }
            
            if (count == n - 1) {
                answer++;
            }
        }
        
        return answer;
    }
}