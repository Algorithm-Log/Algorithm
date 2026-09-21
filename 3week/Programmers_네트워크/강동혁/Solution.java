class Solution {
    int[] visited;
    int n;
    int[][] computers;
    public int solution(int n, int[][] computers) {
        this.n=n;
        this.computers=computers;
        visited=new int[n];
        int answer = 0;
        
        for(int i=0;i<n;i++){
            if(visited[i]==0){
                visited[i]=1;
                answer++;
                dfs(computers[i]);
            }
        }
        return answer;
    }
    
    void dfs(int[] arr){
        for(int i=0;i<n;i++){
            if(visited[i]==0 && arr[i]==1){
                visited[i]=1;
                dfs(computers[i]);
            }
        }
    }
}