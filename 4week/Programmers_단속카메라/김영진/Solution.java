import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        
        Arrays.sort(routes, (a,b) -> Integer.compare(a[0],b[0])); //출발 지점 기준으로 정렬
        
        int now = routes[0][1]; //오른쪽으로 두어야지 유리하니까
        
        for(int i = 1; i < routes.length; i++){
            if(now < routes[i][0]){ //새로운 카메라 설치
                now = routes[i][1]; //얘도 똑같이 오른쪽
                answer++; //카메라 추가
            }
            else{
                now = Math.min(now, routes[i][1]); //더 오른쪽에 있는 애가 유리하니까
            }
        }
        
        return answer;
    }
}