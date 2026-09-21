//14:09

// (y1,x1,y2,x2) 를 보고 범위 만 구해내면 되는문제
// 현재위치를 (x1,y1)으로 잡고 
// x1 에서 오른쪽으로 x2 까지
// dx dy 로 좌표를 이동하면서  최소값갱신
// dx={1,0,-1,0} 우 하 좌 상
// dy={0,1,0,-1}

// 일단 2중 반복문으로 2차원 배열을 만들고
// 반복문으로 4방향을 돌게 하고
// 한방향으로 쭉 가다가 규칙에 맞게 도달하면 방향전환
// 그래서 가면서 현재값이랑 최소값이랑 비교후 갱신


class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] arr = new int[rows][columns];

        for(int y=1;y<=rows;y++){
            for(int x=1;x<=columns;x++){
                arr[y-1][x-1]=((y-1)*columns)+x;
            }
        }

       


        int[] dx={1,0,-1,0}; //우 하 좌 상
        int[] dy={0,1,0,-1};

        

        int[] answer = new int[queries.length];
        int index=0;
        for(int i=0;i<queries.length;i++){
            int x=queries[i][1]-1;
            int y=queries[i][0]-1;
          
            int temp=arr[y][x];
            int result=Integer.MAX_VALUE;

            for(int direction=0;direction<4;direction++){
                
                int nx=x+dx[direction];
                int ny=y+dy[direction];
                
                while(nx>=queries[i][1]-1 && nx <=queries[i][3]-1 &&
                     ny>=queries[i][0]-1 && ny<=queries[i][2]-1){

                        int current = arr[ny][nx];
                        arr[ny][nx]=temp;
                        temp=current;
                        
                        result = Math.min(result,temp);
                        x=nx;
                        y=ny;
                        nx=x+dx[direction];
                        ny=y+dy[direction];

                }

            }
            answer[index]=result;
            index++;
        }
        
        
        return answer;
    }
}