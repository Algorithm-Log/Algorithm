
import java.util.*;
import java.io.*;

// 전차가 발사를 하면 바라보는 방향으로 포탄을 발사하고
// 포탄과 충돌 한벽이 강철이 아니라면 그 좌표는 평지가 된다
// 입력으로 주어진 2차원 배열의 맵을
// 전차가 움직일때 바로바로 변경해주는게 나을까?
// 그래야할듯
// 전차의 이동은 dx dy 로 움직이고
// 
class Solution {

    public static void main(String[] args)throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T=Integer.parseInt(br.readLine());
        
        for(int test_case=1;test_case<T+1;test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            
            char[][] map = new char[H][W];
            
            int dir =-1; //상 하 좌 우
            int currentX=-1;
            int currentY=-1;
            
            int[] dx = {0,0,-1,1}; // 상 하 좌 우 
            int[] dy = {-1,1,0,0};
            
            for(int col=0;col<H;col++) {
                // 맵의 상태를 받아서 2차원배열에 넣고
                // 탱크의 현재 좌표와 바라보는 방향을 저장
                char[] temp = br.readLine().toCharArray();
                for(int row=0;row<W;row++) {
                    
                    if(temp[row]=='<') {
                        dir=2;
                        currentX=row;
                        currentY=col;
                        }
                    if(temp[row]=='>') {
                        dir=3;
                        currentX=row;
                        currentY=col;
                        }
                    if(temp[row]=='^') {
                        dir=0;
                        currentX=row;
                        currentY=col;
                        }
                    if(temp[row]=='v') {
                        dir=1;
                        currentX=row;
                        currentY=col;
                        }
                    
                        map[col][row]=temp[row];
                    }
                
                    
                }

            // 탱크의 수행명령의 길이
            int inputLen = Integer.parseInt(br.readLine());
            
            // 탱크의 명령들이 들어가있는 배열
            char[] order = br.readLine().toCharArray();
            
            
            
            for(int i=0;i<order.length;i++) {
                
                // 명령에 해당하는 바라보는 방향을 변경하고
                // 그 방향으로 한칸 앞으로 갈수있는지 확인
                // 갈수있다면 map의 현재 좌표를 평지(".") 로변경
                // 다음 좌표를 내상태("<") 로 변경
                // 근데 갈수없어도 탱크가 바라보는 방향은 변경시켜줘야함
                switch (order[i]) {
                
                case 'U': {
                    dir=0; //방향을 위로
                    int nx = currentX+dx[0]; //현재좌표에서 바라보는 방향으로 + 한칸
                    int ny = currentY+dy[0]; 
                    if(nx >=0 && nx<W && ny>=0 && ny<H && map[ny][nx]=='.') { // 한칸이동 하였을때 갈수있는 곳인지 확인

                        map[currentY][currentX]='.'; // 한칸이동전 좌표의 값을 평지로 변경 (">" 였다면 "." 으로)
                        currentX=nx; //현재 좌표를 한칸 이동한 좌표로 변경
                        currentY=ny;
                        map[ny][nx]='^'; //한칸 이동한 좌표의 값을 탱크로 변경 ("." 였다면 "^" 으로)
                        }
                    else {
                        // 이동 할수없다고 해도 탱크의 바라보는 방향을 변경시키고
                        // dir 만 변경시키는게 아니라 map에 표시된 탱크상태도 변경해줘야함
                        map[currentY][currentX]='^';
                    }
                    //case 문 break
                    break;
                    }
                case 'D': {
                    dir=1;
                    int nx = currentX+dx[1];
                    int ny = currentY+dy[1];
                    if(nx >=0 && nx<W && ny>=0 && ny<H &&map[ny][nx]=='.') {
                        map[currentY][currentX]='.';
                        currentX=nx;
                        currentY=ny;
                        map[ny][nx]='v';
                        }else {
                            map[currentY][currentX]='v';
                        }
                    break;
                    }
                case 'L': {
                    dir=2;
                    int nx = currentX+dx[2];
                    int ny = currentY+dy[2];
                    if(nx >=0 && nx<W && ny>=0 && ny<H &&map[ny][nx]=='.') {
                        map[currentY][currentX]='.';
                        currentX=nx;
                        currentY=ny;
                        map[ny][nx]='<';
                        }
                    else {
                        map[currentY][currentX]='<';
                    }
                    break;
                    }
                case 'R': {

                    dir=3;

                    int nx = currentX+dx[3];
                    int ny = currentY+dy[3];

                    if(nx >=0 && nx<W && ny>=0 && ny<H &&map[ny][nx]=='.') {

                        map[currentY][currentX]='.';
                        currentX=nx;
                        currentY=ny;
                        map[ny][nx]='>';

                        }
                    else {
                        map[currentY][currentX]='>';
                    }

                    break;
                    }
                case 'S': {
                    // 총알의 시작점을 현재 내좌표 + 바라보는 방향으로 한칸더이동한 좌표에서 시작
                    int nbulletX=currentX+dx[dir];
                    int nbulletY=currentY+dy[dir];
                    while(true) {
                        
                        // 총알이 맵의 크기를 벗어나면 반복문 종료
                        if(!(nbulletX >= 0 && nbulletX < W && nbulletY >= 0 && nbulletY < H)){
                            break;
                        }
                        
                        // 총알이 한칸 이동하였을때 장애물 확인후
                        // 장애물이 강철벽이면 반복문 종료
                        // 장애물이 그냥벽이면 평지로 변경
                        
                        if(map[nbulletY][nbulletX]=='*') {
                            map[nbulletY][nbulletX]='.';
                            break;
                            }
                        if(map[nbulletY][nbulletX]=='#') {
                            break;
                        }
                        // 위 2가지 조건문에 걸리지 않으면
                        // 한칸더 이동
                        nbulletX=nbulletX+dx[dir];
                        nbulletY=nbulletY+dy[dir];
                        }
                        
                        break;
                    }
                
                }
            
            }
            
            System.out.printf("#%d ",test_case);
            for(int col=0;col<H;col++) {
                for(int row=0;row<W;row++) {
                    System.out.printf("%c",map[col][row]);
                }
                System.out.println();
            }
            
            }
            
            
    }

}
