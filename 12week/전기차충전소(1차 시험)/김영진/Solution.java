import java.util.*;
import java.io.*;

class Solution
{
    static int n,answer;
    static int[][] home, selected;
    static String[][] map;
    static List<int[]> position;
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
            n = Integer.parseInt(br.readLine());
            home = new int[n][3];
            answer = Integer.MAX_VALUE;
            map = new String[31][31];
            selected = new int[2][2]; //충전소 2개의 좌표

            for(int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                home[i][0] = Integer.parseInt(st.nextToken());
                home[i][1] = Integer.parseInt(st.nextToken());
                home[i][2] = Integer.parseInt(st.nextToken());
                map[home[i][0] + 15][home[i][1] + 15] = "h";
            }

            position = new ArrayList<>();

            //집 위치가 아닌 좌표를 저장한다. 충전소를 설치할 수 있기 때문에
            for(int i = 0; i < 31; i++){
                for (int j = 0; j < 31; j++) {
                    if(!"h".equals(map[i][j])){
                        position.add(new int[] {(i-15),(j-15)});
                    }
                }
            }

            //충전소 1개만 설치 할 수 있는 경우
            for(int i = 0; i < 31; i++){
                for(int j = 0; j < 31; j++){
                    if("h".equals(map[i][j]))   continue;
                    int sum = 0;
                    boolean flag = true;
                    for(int k = 0; k < n; k++){
                        int dis = Math.abs(home[k][0]-(i-15)) + Math.abs(home[k][1] - (j-15));

                        if(dis > home[k][2]){
                            flag = false;
                            break;
                        }
                        sum += dis;
                    }
                    if(flag){
                        answer = Math.min(answer, sum);
                    }
                }
            }

            //1개로 안되면 2개인 경우를 봐야함. 이때 dfs를 통해 탐색한다.
            if(answer == Integer.MAX_VALUE){
                dfs(0,0);
            }
            if(answer == Integer.MAX_VALUE){
                answer = 1;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("#")
            .append(test_case)
            .append(" " + answer);
            System.out.println(sb);
		}
	}
    //충전소가 2개인 경우
    public static void dfs(int index, int depth){
        if(depth == 2){
            int sum = 0;
            for(int i = 0; i < n; i++){
                int dis_1 = Math.abs(home[i][0] - selected[0][0]) + Math.abs(home[i][1] - selected[0][1]);
                int dis_2 = Math.abs(home[i][0] - selected[1][0]) + Math.abs(home[i][1] - selected[1][1]);
                int dis = Math.min(dis_1, dis_2);
                if(dis > home[i][2]){
                    return;
                }
                sum += dis;
            }
            answer = Math.min(answer, sum);
            return ;
        }
        for(int i = index; i < position.size(); i++){
            selected[depth][0] = position.get(i)[0];
            selected[depth][1] = position.get(i)[1];
            dfs(i+1, depth + 1);
        }
    }
}