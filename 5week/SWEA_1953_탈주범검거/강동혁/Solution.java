import java.io.*;
import java.util.*;

//17:08

// bfs 로 탐색을 하는데 주어지는 시간 만큼 깊이? 큐에서 값을 꺼냈을때
// 탐색가능한 곳의 갯수를 카운트하면 될거같고
// 근데 입력으로 파이프 맵 구조가 주어지는데
// 이걸 어떻게 표현을하지
// switch case 문으로 현재 파이프의 타입에 따라
// que에 값을 넣을때 상 하 좌 우 중 해당하는 좌표만 넣도록
// 이동은 dx dy 로 이동
// 입력받은 파이프 맵을 2차원 int 배열에 넣고
// que에써 뽑은 좌표의 arr 값에 따라 que에 값을 추가

public class Solution {
	
	static int[][] arr;

	public static boolean check(int dir,int x, int y){
		if(dir==0){
			//상
			if(arr[y][x]==1 ||arr[y][x]==2 ||arr[y][x]==5 ||arr[y][x]==6){
				return true;
			}
		}else if(dir==1){
			//하
			if(arr[y][x]==1 ||arr[y][x]==2 ||arr[y][x]==4 ||arr[y][x]==7){
				return true;
			}
		}else if(dir==2){
			//좌
			if(arr[y][x]==1 ||arr[y][x]==3 ||arr[y][x]==4 ||arr[y][x]==5){
				return true;
			}

		}else if(dir==3){
			//우
			if(arr[y][x]==1 ||arr[y][x]==3 ||arr[y][x]==6 ||arr[y][x]==7){
				return true;
			}
		}
		return false;
	}


	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T=Integer.parseInt(br.readLine());

		for(int test_case=1;test_case<T+1;test_case++){
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			int startY=Integer.parseInt(st.nextToken());
			int startX=Integer.parseInt(st.nextToken());

			int time=Integer.parseInt(st.nextToken());

			arr= new int[N][M];

			for(int row=0;row<N;row++){
				st = new StringTokenizer(br.readLine());
				for(int col=0;col<M;col++){

					arr[row][col]=Integer.parseInt(st.nextToken());

				}
			}

			int[] dx = {0,0,-1,1}; //상 하 좌 우
			int[] dy = {-1,1,0,0}; //상 하 좌 우

			Queue<int[]> que = new ArrayDeque<>();

			boolean[][] visitied=new boolean[N][M];
			visitied[startY][startX] = true;
			que.add(new int[]{startX,startY});

			int result=0;
			int depth=0;
			while(!que.isEmpty()){

				if(depth==time){
					break;
				}

				int size = que.size();

				
				for(int j=0;j<size;j++){
					int[] temp  = que.poll();
					result++;
					int x=temp[0];
					int y=temp[1];
					
					switch (arr[y][x]) {
						case 1:
							for(int i=0;i<4;i++){
								int nx= x+dx[i];
								int ny=y+dy[i];
								if(nx>=0 && nx<M && ny>=0 && ny<N && !visitied[ny][nx] && arr[ny][nx]!=0){
									if(check(i,nx,ny)){
										que.add(new int[]{nx,ny});
										visitied[ny][nx]=true;
									}
									
								}
							}
							break;
						case 2:
							for(int i=0;i<2;i++){
								int nx= x+dx[i];
								int ny=y+dy[i];
								if(nx>=0 && nx<M && ny>=0 && ny<N &&!visitied[ny][nx] && arr[ny][nx]!=0){
									if(check(i,nx,ny)){
										que.add(new int[]{nx,ny});
										visitied[ny][nx]=true;
									}
								}
							}
							break;
					
						case 3:
							for(int i=2;i<4;i++){
								int nx= x+dx[i];
								int ny=y+dy[i];
								if(nx>=0 && nx<M && ny>=0 && ny<N && !visitied[ny][nx] && arr[ny][nx]!=0){
									if(check(i,nx,ny)){
										que.add(new int[]{nx,ny});
										visitied[ny][nx]=true;
									}
								}
							}
							break;
						case 4:
							for(int i=0;i<4;i++){
								if(i==1 || i==2){
									continue;
								}
								int nx= x+dx[i];
								int ny=y+dy[i];
								if(nx>=0 && nx<M && ny>=0 && ny<N && !visitied[ny][nx] && arr[ny][nx]!=0){
									if(check(i,nx,ny)){
										que.add(new int[]{nx,ny});
										visitied[ny][nx]=true;
									}
								}
							}
							break;
						case 5:
							for(int i=0;i<4;i++){
								if(i==0 || i==2){
									continue;
								}
								int nx= x+dx[i];
								int ny=y+dy[i];
								if(nx>=0 && nx<M && ny>=0 && ny<N && !visitied[ny][nx] && arr[ny][nx]!=0){
									if(check(i,nx,ny)){
										que.add(new int[]{nx,ny});
										visitied[ny][nx]=true;
									}
								}
							}
							break;
						case 6:
							for(int i=0;i<4;i++){
								if(i==0 || i==3){
									continue;
								}
								int nx= x+dx[i];
								int ny=y+dy[i];
								if(nx>=0 && nx<M && ny>=0 && ny<N && !visitied[ny][nx] && arr[ny][nx]!=0){
									if(check(i,nx,ny)){
										que.add(new int[]{nx,ny});
										visitied[ny][nx]=true;
									}
								}
							}
							break;
						case 7:
							for(int i=0;i<4;i++){
								if(i==1 || i==3){
									continue;
								}
								int nx= x+dx[i];
								int ny=y+dy[i];
								if(nx>=0 && nx<M && ny>=0 && ny<N && !visitied[ny][nx] && arr[ny][nx]!=0){
									if(check(i,nx,ny)){
										que.add(new int[]{nx,ny});
										visitied[ny][nx]=true;
									}
								}
							}
							break;
					
						default:
							break;
					}



				}
				depth++;
				
			}

			System.out.printf("#%d %d\n",test_case,result);
		}
	}
}
