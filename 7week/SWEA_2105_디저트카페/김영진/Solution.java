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
	  static int[] di = {1,1,-1,-1};
	  static int[] dj = {1,-1,-1,1};
	  static int n, max;
	  static int[] desert;
	  static int[][] arr;
		public static void main(String args[]) throws Exception
		{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int T = Integer.parseInt(br.readLine());
			for(int test_case = 1; test_case <= T; test_case++)
			{
	      n =Integer.parseInt(br.readLine());
	      arr = new int[n][n];
	      for (int i = 0; i < n; i++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        for (int j = 0; j < n; j++) {
	          arr[i][j] = Integer.parseInt(st.nextToken());
	        }        
	      }
	      max = -1;
	      for(int i = 0; i < n; i++){
	        for(int j = 0; j < n; j++){
	          desert = new int[101];
	          desert[arr[i][j]] = 1;
	          dfs(i,j, 1, i,j,0);
	        }
	      }
	      System.out.println("#" + test_case + " " + max);
	    }
		}
	  public static void dfs(int i, int j, int count, int startX, int startY, int dir){
	    int nx = di[dir] + i;
	    int ny = dj[dir] + j;
	    
	    if(nx >= 0 && nx < n && ny >= 0 && ny < n){
	      if(startX == nx && startY == ny) {
	    	  if(count >= 4) {
	    		  max = Math.max(max, count);
	    	  }
	      }
	      else if(desert[arr[nx][ny]] == 0) {
	    	  desert[arr[nx][ny]] = 1;
	    	  dfs(nx,ny,count + 1, startX, startY, dir);
	    	  desert[arr[nx][ny]] = 0;
	      }
	    }
	    if(dir < 3) {
	    	int nd = dir + 1;
	    	nx = i + di[nd];
	    	ny = j + dj[nd];
	    	if(nx >= 0 && nx < n && ny >= 0 && ny < n){
	    	      if(startX == nx && startY == ny) {
	    	    	  if(count >= 4) {
	    	    		  max = Math.max(max, count);
	    	    	  }
	    	      }
	    	      else if(desert[arr[nx][ny]] == 0) {
	    	    	  desert[arr[nx][ny]] = 1;
	    	    	  dfs(nx,ny,count + 1, startX, startY, nd);
	    	    	  desert[arr[nx][ny]] = 0;
	    	      }
	    	    }
	    }
	  } 
	}