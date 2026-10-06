
import java.util.*;
import java.io.*;

// dfs 안에 bfs 같은데
// 중복이 가능한 순열로 공을 떨어뜨리는 경우의 수를 만들고
// 떨어뜨린 공에따라 bfs 로 맵 정보를 갱신


// dfs의 종료조건을 만족할때 int[] 형식의 공을 떨어뜨릴 x좌표값을 받고 반복문으로
// 좌표를 que에 넣고 bfs 하고 또 넣고 bfs 하고 반복한다음 bfs 를 하면서
// 좌표의 값들을 더하고 bfs 가끝난다음 res 변수와 비교후 큰값으로 갱신
// 맵전체의 숫자들의 합을 배열에 넣을때 더해서 변수에 저장해놓고 res 변수와 빼기한다음 결과출력


class Solution {

    static int n,w,h,total,res;
    static int[][] map,tempMap;
    static int[] cur;
    static Deque<int[]> que;
    static int[] dx={0,0,-1,1}; // 상 하 좌 우
    static int[] dy={-1,1,0,0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T= Integer.parseInt(br.readLine());

        for(int test_case=1;test_case<T+1;test_case++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new int[h][w];
            
            total=0;
            res=0;

            for(int col=0;col<h;col++){
                st= new StringTokenizer(br.readLine());
                for(int row=0;row<w;row++){
                    int val = Integer.parseInt(st.nextToken());
                    map[col][row] =val;
                    if(val!=0)total++;
                }
            }

            cur = new int[n];
            que = new ArrayDeque<>();
            dfs(0);
            System.out.println(total);
            System.out.println(res);
            sb.append("#"+test_case+" "+(total-res)+"\n");
        }
        System.out.println(sb);
    }

    static void dfs(int depth){
        if(depth==n){
            //bfs 하고 결과값 갱신
            int sum=0;
            tempMap= map.clone();
            for(int i=0;i<n;i++){
                int[] temp = findY(cur[i]);
                if(temp != null){
                    sum++;
                    que.offer(temp);
                }

                while(!que.isEmpty()){

                    int[] temp2 = que.poll();
                    int x = temp2[0];
                    int y = temp2[1];
                    int count = tempMap[y][x];
                    tempMap[y][x]=0;
                    for(int j=0;j<count-1;j++){
                        for(int dir=0;dir<4;dir++){

                            int nx = x+dx[dir];
                            int ny = y+dy[dir];

                            if(isRange(nx,ny) && tempMap[ny][nx]!=0){
                                sum++;
                                que.offer(new int[]{nx,ny});
                            }
                        }
                    }
                    
                }
                // 이제 아래로 내려줘야함
                //
                down();
            }
            res = Math.max(res,sum);
            return;
        }

        for(int i=0;i<w;i++){
            cur[depth]=i;
            dfs(depth+1);
        }
    }

    static int[] findY(int x){
        for(int col=0;col<h;col++){
            if(tempMap[col][x]!=0)return new int[]{x,col};
        }
        return null;
    }

    static void down(){

        for(int row=0;row<w;row++){

            int[] temp = findY(row);

            if(temp != null){
                int start = temp[1];
                int end = h-1;

                while(start<end){

                    while(isRange(row,end) && tempMap[end][row]!=0)end--;
                    while(isRange(row, start) && tempMap[start][row]==0)start++;
                    if(start<end){
                        tempMap[end][row]=tempMap[start][row];
                        tempMap[start][row]=0;
                    }else{
                        break;
                    }

                }
            }
        }
    }

    static boolean isRange(int nx,int ny){
        if(nx>=0 && nx<w && ny>=0 &&ny <h)return true;
        return false;
    }
}
