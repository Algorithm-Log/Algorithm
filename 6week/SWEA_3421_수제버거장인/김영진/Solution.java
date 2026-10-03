// import java.io.*;
// import java.util.*;
// class Solution
// {
// 	static int n,m,cnt;
// 	static int[] a,b;
// 	static boolean[] visited;
// 	public static void main(String args[]) throws Exception
// 	{
//     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		int T = Integer.parseInt(br.readLine());

// 		for(int test_case = 1; test_case <= T; test_case++)
// 		{
// 			StringTokenizer st = new StringTokenizer(br.readLine());
// 			n = Integer.parseInt(st.nextToken());
// 			m = Integer.parseInt(st.nextToken());

// 			a = new int[m];
// 			b = new int[m];

// 			visited = new boolean[n+1];

// 			for(int i = 0; i < m; i++){
// 				st = new StringTokenizer(br.readLine());
// 				a[i] = Integer.parseInt(st.nextToken());
// 				b[i] = Integer.parseInt(st.nextToken());
// 			}
// 			cnt = 0;
// 			dfs(1);
// 			System.out.println("#" + test_case + " " + cnt);
// 		}
// 	}
// 	public static void dfs(int index){
// 		if(index == n + 1){
// 			for(int i = 0; i < m; i++){
// 				if(visited[a[i]] && visited[b[i]]){
// 					return;
// 				}
// 			}
// 			cnt++;
// 			return ;
// 		}
// 		visited[index] = true;
// 		dfs(index + 1);

// 		visited[index] = false;
// 		dfs(index + 1);
// 	}
// }


import java.util.*;
import java.io.*;

class Solution
{
	static int n, min;
	static int[][] arr;
	static int[] di = {0,1,0,-1};
	static int[] dj = {1,0,-1,0};
	static List<int[]> cores;
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			cores = new ArrayList<>();

			for(int i = 0; i < n; i++){
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++){
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j] == 1 && i != 0 && j != 0 && i != n-1 && j != n-1){ //선을 놓을 수 있는 좌표는 저장해둔다.
						cores.add(new int[]{i,j});
					}
				}
			}
			min = Integer.MAX_VALUE;

			dfs(0,0,0);
		}
	}
	public static void dfs(int index, int contect, int sum){
		if (index == n) {

			return;
		}
		int x = cores.get(index)[0];
		int y = cores.get(index)[1];

		for(int d = 0; d < 4; d++){
			int nx = x + di[d];
			int ny = y + dj[d];

			if (nx >= 0 && nx < n&& ny >= 0 && ny < n) {

			}
		}
	}
}