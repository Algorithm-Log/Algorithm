
import java.util.*;
import java.io.*;

// 얼마전에 푼 문제라 문제유형이 기억이 난다
// 마름모 모양으로 탐색을 진행하면되고 마름모 모양으로 탐색을 진행할때
// dx dy 로 다음 좌표로 이동을 시키는데 이때 상 하 좌 우 이렇게 방향을 마음대로 정해놓고
// dfs로 완전탐색을 시키면 마름모 모양이 만들어지지않는다
// 시계 방향이든 반시계방향이든 한붓 그리기 처럼 출발한 방향에 맞게 마름모 모양으로 방향을 회전 시켜줘야한다
// 우하 좌하 좌상 우상 이렇게 이동하고
// 문제 풀때 힘들었던게 dfs로 분기점을 방향을 꺽을지 앞으로 갈지 2갈래로 나눠서
// dfs 를 2개 호출했었는데 현재 바라보는 방향으로 한칸 이동후 바라보는 방향을 변경할지 안할지
// 로 상태를 정하고 종료조건으로는 모든 방향을 다돌았을때 크기를 갱신 해주는것으로
// 그리고 시작 좌표를 줄여주면 시간이 좀 줄어들거같긴한데 크게 줄어들지는 않을듯
// 해보고 시간초과뜨면 최적화 해주는걸로
// dfs 매개변수로 현재방향0~3 ,깊이0~, visited 는 필요없음 방향이 고정이라 왔던길을 갈수가 없음
// 아 맞다 이동하면서 다음좌표에 해당하는 번호를 set에 저장해서 중복검사하고 중복이면 안가는걸로
class Solution {
    static int[][] arr;
    static int result;
    static int[] dx={1,-1,-1,1}; //우하 좌하 좌상 우상
    static int[] dy={1,1,-1,-1};
    static Set<Integer> set;
    static int N,startX,startY;
    public static void main(String[] args)throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T=Integer.parseInt(br.readLine());
        
        for(int test_case=1;test_case<T+1;test_case++) {

            N = Integer.parseInt(br.readLine());

            arr = new int[N][N];

            for(int row=0;row<N;row++){

                StringTokenizer st = new StringTokenizer(br.readLine());

                for(int col=0;col<N;col++){

                    arr[row][col]=Integer.parseInt(st.nextToken());

                }
            }
            result=-1;
            // 시작을 어디서 할건지
            for(int y=0;y<N;y++){
                for(int x=0;x<N;x++){
                    set=new HashSet<>();
                    
                    startX=x;
                    startY=y;
                    dfs(x,y,0,0);
                    set.clear();
                }
            }

            System.out.printf("#%d %d\n",test_case,result);
        }
            
            
    }

    static void dfs(int x,int y,int depth,int dir){

        // 현재 좌표 x,y 에서 카페수는 depth 개 이고 현재 방향은 dir 이다
        if(dir==4)return;
        //System.out.printf("x:%d y:%d depth :%d dir :%d\n",x,y,depth,dir);
        if(dir==3 && x==startX && y==startY){
            result=Math.max(result,depth);
            return;
        }

        int nx = x+dx[dir];
        int ny = y+dy[dir];
        if(isRange(nx,ny) && ! set.contains(arr[ny][nx])){
            
            set.add(arr[ny][nx]);
            //방향변경
            dfs(nx,ny,depth+1,dir+1);
            //그대로
            dfs(nx,ny,depth+1,dir);
            set.remove(arr[ny][nx]);
        }
    }

    static boolean isRange(int x,int y){
        if(x>=0 && x<N && y>=0 && y<N)return true;
        return false;
    }

}
