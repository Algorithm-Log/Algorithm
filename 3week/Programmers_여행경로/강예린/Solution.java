import java.util.*;

class Solution {
    private static String[][] tickets;
    private static boolean[] visited;
    private static List<String> answer= new ArrayList<>();
    
    private static void travel(int depth, String[] now, String path){
            if(depth == tickets.length-1){
                answer.add(path+" "+ now[1]); // 갈 수 있는 경로 여러개 모음
                return;
            }
            for(int i=0; i< tickets.length; i++){
                
                if(!visited[i] && now[1].equals(tickets[i][0])){ // 이 티켓 안썼고 출발지가 지금 도착지라면
                    visited[i]= true;
                    travel(depth+1, tickets[i], path+" "+tickets[i][0]);
                    visited[i]= false;
                }
            }
    }
    
    public String[] solution(String[][] tickets) {
        
        this.tickets= tickets;
        
        int idx=0;
        for(int i=0; i< tickets.length; i++) {
            if(tickets[i][0].equals("ICN")) idx= i;
        }
        
        visited= new boolean[tickets.length];
        
        visited[idx]= true;
        travel(0, tickets[idx] ,tickets[idx][0]);
        Collections.sort(answer);
        
        return answer.get(0).split(" ");
    }
}