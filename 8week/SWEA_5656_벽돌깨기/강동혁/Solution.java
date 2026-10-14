
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
    static int[] boundary = {0,0,0,0}; // 상 하 좌 우
    static boolean[][] visited;
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
                    // 전체 벽돌 숫자 저장
                }
            }
            // for(int col=0;col<h;col++){
            //     for(int row=0;row<w;row++){
            //         System.out.print(map[col][row]);
            //     }
            //     System.out.println();
                    
            // }
            cur = new int[n];
            que = new ArrayDeque<>();
            dfs(0);
            //System.out.println(total);
            //System.out.println(res);
            sb.append("#"+test_case+" "+(total-res)+"\n");

        }
        System.out.println(sb);
    }

    static void dfs(int depth){
        if(depth==n){
            //bfs 하고 결과값 갱신
            int sum=0;
            // 순열로 만들어지는 구슬의 경우의수마다 새로운 맵에서 던져야하기 때문에
            // 맵을 초기화
            tempMap = new int[h][w];

            for (int i = 0; i < h; i++) {
                tempMap[i] = map[i].clone();
            }
            // for(int col=0;col<h;col++){
            //     for(int row=0;row<w;row++){
            //         System.out.print(tempMap[col][row]);
            //     }
            //     System.out.println();
                    
            // }
            //cur=new int[]{2,2,6};////////////
            for(int i=0;i<n;i++){
                //구슬 갯수만큼 던지기
                visited=new boolean[h][w];
                // 구슬을 한번 던질때마다 중복을 방지 하는 배열을 초기화

                int[] temp = findY(cur[i]); //구슬과 충돌할 제일위의 벽돌 좌표찾기

                if(temp != null){
                    // 벽돌이 존재한다면
                    sum++;
                    que.offer(temp.clone()); //충돌하는 벽돌에서 bfs 시작하기위해 큐에 삽입
                }else{
                    //벽돌이 없으면 다음 x좌표로이동
                    continue;
                }

                while(!que.isEmpty()){

                    int[] temp2 = que.poll();
                    int x = temp2[0];
                    int y = temp2[1];
                    int count = tempMap[y][x];
                    tempMap[y][x]=0; //값을 꺼낼때 좌표값을 0으로 바꿔줌
                    

                    for(int dir=0;dir<4;dir++){

                        int nx = x+dx[dir];
                        int ny = y+dy[dir];
                        int len=0;

                        while(len < count - 1){ //현재 방향으로 좌표 값보다 -1 만큼 탐색

                            if(!isRange(nx, ny)) break;

                            if(!visited[ny][nx]){ // 중복으로 큐에 들어가는거 방지

                                visited[ny][nx] = true;

                                if(tempMap[ny][nx] != 0){ //벽돌이 맞으면 큐에 삽입
                                                            // 큐에 넣을때 부서졌다고 값을 더해줌 이유는 없음 그냥 그렇게했음 큐에서 꺼낼때 해줘도 될거같음 아마
                                    sum++;
                                    que.offer(new int[]{nx, ny});
                                }
                            }

                            len++;

                            nx += dx[dir];
                            ny += dy[dir];
                        }
                    }
                    
                    
                }
                //이제 아래로 내려줘야함
                
                // for(int col=0;col<h;col++){
                //     for(int row=0;row<w;row++){
                //         System.out.print(tempMap[col][row]);
                //     }
                //     System.out.println();
                    
                // }
                // System.out.println();
                // System.out.println();
                // System.out.println();


                //구슬과 충돌한 벽돌에서 bfs 탐색을 끝낸후 벽돌을 아래로 내림
                down();


                // for(int col=0;col<h;col++){
                //     for(int row=0;row<w;row++){
                //         System.out.print(tempMap[col][row]);
                //     }
                //     System.out.println();
                    
                // }
                // System.out.println();
                // System.out.println();
                // System.out.println();
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
            //제일 위의 벽돌 찾기
            if(tempMap[col][x]!=0)return new int[]{x,col};
        }
        return null;
    }

    static void down(){

        // x좌표 순회 하면서
        for(int row=0;row<w;row++){

            // 제일 위 벽돌 찾고
            int[] temp = findY(row);

            if(temp!=null){
                //벽돌이 있다면 list에 넣고 map 값을 0으로 변경
                int start =temp[1];
                int end=h-1;
                List<Integer> list = new ArrayList<>();
                while(start<=end){
                    if(tempMap[start][row]!=0){
                        list.add(tempMap[start][row]);
                        tempMap[start][row]=0;
                    }
                    start++;
                }
                
                //제일 아래에서 부터 list 에서 꺼내서 나열
                for(int i=0;i<list.size();i++){
                    tempMap[end-i][row]=list.get(list.size()-i-1);
                }
            }

        }
    }

    static boolean isRange(int nx,int ny){
        if(nx>=0 && nx<w && ny>=0 &&ny <h)return true;
        return false;
    }
}
