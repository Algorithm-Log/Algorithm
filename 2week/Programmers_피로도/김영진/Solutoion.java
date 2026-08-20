class Solution {
    static int answer;
    static boolean[] visited;
    public int solution(int k, int[][] dungeons){
        answer = 0;
        visited = new boolean[dungeons.length];
        dfs(k,dungeons.length,0,dungeons);
        
        return answer;
    }
    public static void dfs(int k, int len, int cnt,int[][] arr){
        answer = Math.max(answer, cnt);
        
        for(int i = 0; i < len; i++){
             if(!visited[i] && arr[i][0] <= k){
                 visited[i] = true;
                 dfs(k - arr[i][1], len, cnt + 1, arr);
                 visited[i] = false;
            }
        }
    }
}