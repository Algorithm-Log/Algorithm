/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static char[][] map;
	static int[] di = {0,1,1,1,0,-1,-1,-1};
	static int[] dj = {1,1,0,-1,-1,-1,0,1};
	static int n,answer;
	static int[][] count;//지뢰 세기용
	static boolean[][] visited;
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			n = Integer.parseInt(br.readLine());
			map = new char[n][n];
			for(int i = 0; i < n; i++) {
				String str = br.readLine();
				for(int j = 0; j < n; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			count = new int[n][n];
			answer = 0;
			visited = new boolean[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(map[i][j] == '*') continue;
					if(map[i][j] == '.') {
						int cnt = 0; //주변 폭탄 수
						
						for(int d = 0; d < 8; d++) {
							int nx = di[d] + i;
							int ny = dj[d] + j;
							
							if(nx >= 0 && nx < n && ny >= 0 && ny < n) {
								if(map[nx][ny] == '*')	cnt++;
							}
						}
						count[i][j] = cnt;
					}
				}
			}
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(count[i][j] == 0 && !visited[i][j] && map[i][j] == '.') {
						visited[i][j] = true;
						answer++;
						dfs(i,j);
					}
				}
			}
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(!visited[i][j] && map[i][j] == '.') {
						answer++;
					}
				}
			}
			System.out.println("#" + test_case + " " + answer );
		}
	}
	public static void dfs(int row, int col) {
		for(int d = 0; d < 8; d++) {
			int nx = di[d] + row;
			int ny = dj[d] + col;
			
			if(nx >= 0 && nx < n && ny >= 0 && ny < n) {
				if(map[nx][ny] == '*')	continue;
				if(visited[nx][ny])	continue;
				
				visited[nx][ny] = true;
				
				if(count[nx][ny] == 0) {
					dfs(nx,ny);
				}
			}
		}
	}
}