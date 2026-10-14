
import java.util.*;
import java.io.*;

// 13:35

// 2 차원 배열 좌표를 순회 하면서
// 그 값이 * 이면 continue
// 그 값이 . 이면 해당 좌표를 큐에 넣고
// 그 값이 숫자이면 continue
// 
// 8방향 검사를 진행 dx dy 로 
// 그 8방향 탐색 한 값이 지뢰인지 아닌지 검사하고
// 지뢰이면 count++
// 8방향 검사 끝난후 현재 좌표값을 count 로 갱신
// 만약에 count 가 0 이면 8방향 좌표를 큐에 다시 넣고
// 해당 좌표마다 8방향 검사를 다시 반복
// 음 근데 처음에 8방향 탐색을 한번하고 count 가 0이면
// 8방향 좌표를 다시 큐에 넣어주기 위해서 8방향 좌표를 구하는 과정을
// 또 반복하는게 좀 비효율적인데
// 처음에 8방향 탐색을 하면서 8방향 좌표값을 미리 저장을 해두고
// count 가 0 이면 저장해둔 좌표를 반복문으로 넣어준다?
// 근데 그래도 반복문써서 연산 횟수는 동일하겠네
// 음 그러면 int[] 에 저장했다가 count 0 이면 반복문으로 큐에 넣어주는걸로
// 
class Solution {
    static int[] dx = {-1,0,1,1,1,0,-1,-1}; // 좌상 상 우상 우 우하 하 좌하 좌
    static int[] dy = {-1,-1,-1,0,1,1,1,0};
    static int n;
    static StringBuilder sb = new StringBuilder();
    static char[][] map;
    static int[][] countMap;
    static Deque<int[]> que;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int test_case=1;test_case<T+1;test_case++){
            n = Integer.parseInt(br.readLine());

            map = new char[n][n];
            countMap = new int[n][n];

            for(int row=0;row<n;row++){
                String temp = br.readLine();
                for(int col=0;col<n;col++){
                    map[row][col]=temp.charAt(col);    
                }
            }

            for(int row=0;row<n;row++){
                for(int col=0;col<n;col++){
                    // 주변 지뢰 갯수 배열
                    countMap[row][col]=checkCount(col,row);
                }
            }

            que=new ArrayDeque<>();

            int res=0;

            for(int y=0;y<n;y++){
                for(int x=0;x<n;x++){

                    if(map[y][x]=='.' && countMap[y][x]==0){ 
                        // 최소 갯수를 만족하기 위해 주변칸에 지뢰가 없는 것들만 먼저
                        
                        que.offer(new int[]{x,y});
                        res++;
                        bfs();
                        que.clear();
                
                    }
                    

                }
            }

            for(int y=0;y<n;y++){
                for(int x=0;x<n;x++){

                    if(map[y][x]=='.'){
                        res++;
                    }
                }
            }
            sb.append("#"+test_case+" "+res+"\n");
        }
        System.out.print(sb);
    }

    static int checkCount(int x,int y){
        int count=0;
        for(int dir=0;dir<8;dir++){
            int nx=x+dx[dir];
            int ny=y+dy[dir];

            if(!isRange(nx, ny)) continue;

            if(map[ny][nx]=='*'){
                count++;
            }
        }

        return count;
    }


    // 시간 초과가 난다 
    // 계속 반복을 하고있나?
    // 지금 내 로직
    // 맵에서 주변 8방향에 지뢰가 없고 '.' 애들을 큐에 넣고
    // 그 넣어진 좌표 값을 0으로 변경하고
    // 그 좌표 기준 8방향 값이 범위 안이고 '.' 애들을 큐에 넣음
    // 여기가 반복인가? 아니야 여기는 그렇게 많이 반복안됨
    // 그럼 큐에 들어간 애들이 꺼내져서는
    // 그 큐에서 나온 좌표기준 8방향에 지뢰가 없다면 또 8방향 좌표를 큐에 넣고
    // 음 중복이 없는거같은데
    static void bfs(){
        while(!que.isEmpty()){
            
            for(int i=0;i<que.size();i++){
                int[] temp = que.poll();
                //System.out.println(x,y);
                int x=temp[0];
                int y=temp[1];

                // 주변에 지뢰가 없으면 8방향 검사후 8방향 좌표 중
                // '.' 애만 추가
                //if(map[ny][nx]!='.') continue;
                if(countMap[y][x]==0){
                    
                    map[y][x]='0';
                    for(int dir=0;dir<8;dir++){

                        int nx = x+dx[dir];
                        int ny = y+dy[dir];

                        if(isRange(nx, ny) && map[ny][nx]=='.'){
                            // 구현할때 조건을 세부적으로 들어가서 구현하지말고 map[ny][nx]=='.' 이렇게
                            // visited 사용해서 크게크게 엣지 케이스가 발생할수없도록
                            // 크게크게 구현을 하는습관을 가져야하나?
                            // 강사님한테 물어봐야지
                            map[ny][nx]=(char)(countMap[ny][nx]+'0');
                            que.offer(new int[]{nx,ny});
                        }
                    
                    }
                }else{
                    map[y][x]=(char)(countMap[y][x]+'0');
                }
            }
        }

    }

    static boolean isRange(int nx,int ny){
        if(nx>=0 && nx<n && ny>=0 && ny<n)return true;
        return false; 
    }
            
            
    

    

}
