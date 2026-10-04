import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static int n,x,m, max;
	static List<int[]> recodList;
	static int[] cage;
	static int[] answer;
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      x = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());

			recodList = new ArrayList<>();
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				recodList.add(new int[]{a,b,c}); //a번 부터 b번까지 c개이다.
			}
			answer = new int[n + 1];
			//n개 있고, 1번 부터 N번까지
			cage = new int[n + 1]; // 1번부터 n까지

			//각 우리에 0~X마리 이하가 있음
			//m개 기록이 있고,l번부터 r번 까지 수를 세었더니 s마리였다.

			//전략:
			//1~2번까지 5마리 ... 경우의 수를 따져라
			//그리고 그걸 보았을때 사전 순이다? 그럼 cage[1] = 0, cage[2] = 5가 된다
			//cage[3]에 대한 정보는 없으므로, 그리고 문제에서는 햄스터 수가 가장 큰 것을 원하기 때문에 cage[3] = 5가 된다.
			//dfs로 풀면 될거같은데
			max = -1; //전체 총합을 저장.
			dfs(1,0);
			sb.append("#").append(test_case).append(" ");

			if (max == -1) {
				sb.append(-1);
			}
			else{
				for(int i = 1; i <= n; i++){
					sb.append(answer[i]).append(" ");
				}
			}
			sb.append('\n');	
		}
		System.out.println(sb);
	}
	public static void dfs(int index, int sum){
		if(index == n+1){
			//list에서 하나씩 꺼내면서 l번부터 r번까지 s인 경우를 다 봐야함
			boolean check = true;
			for (int i = 0; i < recodList.size(); i++) {
				int st = recodList.get(i)[0];
				int end = recodList.get(i)[1];
				int per = recodList.get(i)[2];

				int perSum = 0;
				for (int j = st; j <= end; j++) {
					perSum += cage[j];
				}
				if(perSum != per){
					check = false;
					break;
				}
			}
			if(check){
				if (sum > max) {
					max = sum;
					answer = cage.clone();
				}
				else if(sum == max){
					for (int i = 1; i <= n; i++) {
						if (cage[i] < answer[i]) {
							answer = cage.clone();
							break;
						}
						else if(cage[i] > answer[i]){
							break;
						}
					}
				}
			}
				return ;
		}
		for (int i = 0; i <= x; i++) {
			cage[index] = i; // 모든 경우를 다 넣음
			dfs(index+1, sum + i);
		}
	}
}