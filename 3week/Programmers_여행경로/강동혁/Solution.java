import java.util.*;


// 정렬을 언제 할것인가 dfs 에 넣기 전에 정렬을 하면
// 정렬을 하면 dfs 로 생성된 제일처음 경로가 정답이 된다
// 정렬을 하지 않으면 생성된 경로들 중에서 알파벳 순으로 정렬을 해줘야한다
// visited 방문검사를 출발지+도착지+티켓의 위치  를 set 에 넣어서 해주는걸로
// dfs() 의 매개변수로는 깊이와,현재경로배열,현재티켓
// 깊이가 티켓의 갯수이면 종료 하고
// 사용한적이 없는 티켓인지 검사후 



class Solution {
    int ticketsSize;
    List<List<String>> result;
    Boolean[] visited;
    String[][] tickets;
    public String[] solution(String[][] tickets) {
        ticketsSize=tickets.length;
        this.tickets=tickets;
        result= new ArrayList<>();
        visited=new Boolean[ticketsSize];

        for(int i=0;i<ticketsSize;i++){
            if(tickets[i][0].equals("ICN")){
                dfs(1,tickets[i],new ArrayList<String>());
            }
        }
        result.sort((a,b)-> a.get(1).compareTo(b.get(1)));
        String[] answer = result.get(0).toArray(new String[0]);
        return answer;
    }

    void dfs(int depth,String[] ticket,List<String> route){
        if(depth==ticketsSize){
            route.add(ticket[1]);
            result.add(route);
            return;
        }

        for(int i=0;i<ticketsSize;i++){
            String temp = ticket[0]+ticket[1]+Integer.toString(i);
            if(!visited && tickets[i][0].equals(ticket[1])){
                //사용한적 없는 티켓이고 그티켓의 도착지가 출발지인 티켓이 있을때
                
                visited[i]=true;
                route.add(ticket[0]);
                dfs(depth+1,tickets[i],route);
                visited[i]=false;
                //route.add(ticket[0]);
            }
        }
    }
}