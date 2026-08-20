class Solution {
    static int answer;
    static int[][] dungeons;
    static boolean[] visited;
    
    static void play(int cnt, int now){
        answer= Math.max(answer, cnt);
        
        for(int i=0; i< dungeons.length; i++){
            if(!visited[i]){
                if(dungeons[i][0]<= now){
                    visited[i]= true;
                    
                    play(cnt+1, now- dungeons[i][1]);
                    visited[i]= false;
                }
            }
            
        }
    }
    
    public int solution(int k, int[][] dungeons) {
        visited= new boolean[dungeons.length];
        this.dungeons= dungeons;
        play(0, k);
        
        return answer;
    }
}