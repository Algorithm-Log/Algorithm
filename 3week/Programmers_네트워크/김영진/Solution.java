class Solution {
    boolean[] visit;
    int answer;
    public int solution(int n, int[][] computers) {
        visit = new boolean[n];
        answer = 0;
        
        for(int i = 0; i < n; i++){
            if(!visit[i]){
                answer++;
                dfs(n, computers,i);   
            }
        }
        
        return answer;
    }
    public void dfs(int n, int[][] computers,int index){
        visit[index] = true;
        
        for(int i = 0; i < n; i++){
            if(computers[index][i] == 1 && !visit[i]){
                dfs(n, computers, i);
            }
        }
    }
}