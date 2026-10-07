
import java.util.*;
import java.io.*;
// 16:00 ~ 16:37

//16:37

// 2차원 배열을 순회 하면서 높이가 달라지는 지점을 찾고
// 입력받은 크기 x 만큼의 계단을 찾은 지점에서 방향으로 둔다
// 방향은 if(map[col][i]+1==map[col][i+1]) 로 찾고 +1말고 -1 로도 검사 해줘서 오른쪽값이 하나더 크던가 작던가 하는 경우 찾아주고
// 좌우나 상하 두 방향에서 높이 차이가 나면 계단으로 한번 연결이 되어야한다
// 만약에 높이 차이가 나는 지점을 찾았는데 계단 연결이 불가능 하면 그 방향은 탐색을 중단하고
// 다른 행이나 열 탐색으로 이동
// 탐색을 진행하면서 높이가 차이나는 지점을 찾았을때 계단을 연결하고 쭉 또 탐색하다가
// 마지막 지점까지 왔으면 그방향은 활주로 건설이 가능한것
// 일단 2차원 배열을 행방향 열방향 순회를 2번 해줘야하고
// 순회 하면서 if(map[col][i]+1==map[col][i+1]) 로 높이 차이 나는 지점 찾고 
// 이 경우에는 for(int x=i-1 ~ x>=i-1-x i--)i-1 부터 x크기 만큼 if(map[col][x]+1 == map[col][i])
// 더했을때 i 랑 높이가 같다면 더하고 만약에 아니라면 계단 설치가 불가능 한것이므로 탈출후 다음 행이나 열 탐색
// 음 이렇게 탐색을 완료하면 count++ 할까 탐색이 불가능할때 Count++ 한뒤에 전체에서 뺄까?
// 속도가 불가능 할때 count++ 하고 전체에서 빼는거 좀더 빠를듯
// 이게 끝인거같은데 아 근데
// 내가 지금 계단 설치를 값을 더해서 설치를하는데 계단 설치후 옆지점에서 1칸 낮은 곳이 있으면
// 332211 이렇게일건데 계단 설치하면 333311 이렇게 값이 변경될거고
// 근데 저긴 계단이라 11에 계단 설치하면 활주로가 가능한곳인데
// 음 visited로 계단설치한곳을 표시해주고
// 높이 비교할때 계단이 설치된곳은 2차이나도 가능한것으로?
// 아니면 계단 설치가 가능한곳인지 체크만 하고 값은 더하지 말고 쭉 가자
// 그러면 될듯 ㅇㅋ 구현 ㄱㄱ  

class Solution {
    static StringBuilder sb = new StringBuilder();
    static int n;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T= Integer.parseInt(br.readLine());

        for(int test_case=1;test_case<T+1;test_case++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            int[][] map = new int[n][n];

            for(int col=0;col<n;col++){
                st = new StringTokenizer(br.readLine());
                for(int row=0;row<n;row++){
                    map[col][row]=Integer.parseInt(st.nextToken());
                }
            }
            int count=0;
            boolean stop=false;
            //행 방향 순회
            for(int col=0;col<n;col++){
                stop=false;
                for(int row=0;row<n-1;row++){
                    if(map[col][row]+1==map[col][row+1]){
                        //오른쪽이 더클때
                        //왼쪽으로 계단 설치 가능 여부 확인
                        for(int i=0;i<x;i++){
                            if(isRange(row-i,col) && map[col][row-i]+1 == map[col][row+1])continue;
                            
                            count++;
                            stop=true;
                            break;
                        }
                        if(stop)break;
                    }else if(map[col][row]+1<map[col][row+1]){
                        count++;
                        break;
                    }
                    
                    if(map[col][row]==map[col][row+1]+1){
                        //왼쪽이 더클때
                        //오른쪽으로 계단 설치 가능 여부 확인
                        for(int i=0;i<x;i++){

                            if(isRange(row+1+i,col) && map[col][row+1+i]+1 == map[col][row])continue;
                            
                            count++;
                            stop=true;
                            break;
                        }
                        if(stop)break;
                    }else if(map[col][row]>map[col][row+1]+1){
                        count++;
                        break;
                    }
                }
            }

            //열 방향 순회

            for(int row=0;row<n;row++){
                stop=false;
                for(int col=0;col<n-1;col++){


                    if(map[col][row]+1==map[col+1][row]){
                        //아래쪽이 더클때
                        //위쪽으로 계단 설치 가능 여부 확인
                        for(int i=0;i<x;i++){
                            if(isRange(row,col-i) && map[col-i][row]+1 == map[col+1][row])continue;
                            
                            count++;
                            stop=true;
                            break;
                        }
                        if(stop)break;
                    }else if(map[col][row]+1<map[col+1][row]){
                        count++;
                        break;
                    }

                    if(map[col][row]==map[col+1][row]+1){
                        //위쪽이 더클때
                        //아래쪽으로 계단 설치 가능 여부 확인
                        for(int i=0;i<x;i++){
                            if(isRange(row,col+1+i) && map[col+1+i][row]+1 == map[col][row])continue;
                            
                            count++;
                            stop=true;
                            break;
                        }
                        if(stop)break;
                    }else if(map[col][row]>map[col+1][row]+1){
                        count++;
                        break;
                    }
                }
            }

            sb.append("#"+test_case+" "+((2*n)-count)+"\n");

        }   
        System.out.println(sb);
    }

    static boolean isRange(int nx,int ny){
        if(nx>=0 && nx<n && ny>=0 && ny<n) return true;
        return false;
    }
}
