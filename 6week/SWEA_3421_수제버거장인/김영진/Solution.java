class Solution
{
	static int n,m,cnt;
	static int[] a,b;
	static boolean[] visited;
	public static void main(String args[]) throws Exception
	{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			a = new int[m];
			b = new int[m];

			visited = new boolean[n+1];

			for(int i = 0; i < m; i++){
				st = new StringTokenizer(br.readLine());
				a[i] = Integer.parseInt(st.nextToken());
				b[i] = Integer.parseInt(st.nextToken());
			}
			cnt = 0;
			dfs(1);
			System.out.println("#" + test_case + " " + cnt);
		}
	}
	public static void dfs(int index){
		if(index == n + 1){
			for(int i = 0; i < m; i++){
				if(visited[a[i]] && visited[b[i]]){
					return;
				}
			}
			cnt++;
			return ;
		}
		visited[index] = true;
		dfs(index + 1);

		visited[index] = false;
		dfs(index + 1);
	}
}