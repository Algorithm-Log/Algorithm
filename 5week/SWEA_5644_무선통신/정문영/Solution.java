package SWEA_5644_무선통신.정문영;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
  public static void main(String[] args) throws Exception{
    //1. 입력
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int tc = Integer.parseInt(br.readLine());
    for (int testcase = 1; testcase <= tc; testcase++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int m = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());

      int[][] move_arr = new int[2][m];
      int[][] bc = new int[a][4];
      int result = 0;

      st = new StringTokenizer(br.readLine());
      for(int i=0; i<m; i++) {
        move_arr[0][i] = Integer.parseInt(st.nextToken());
      }

      st = new StringTokenizer(br.readLine());
      for(int i=0; i<m; i++) {
        move_arr[1][i] = Integer.parseInt(st.nextToken());
      }

      for(int i=0; i<a; i++) {
        st = new StringTokenizer(br.readLine());

        for(int j=0; j<4; j++) {
          bc[i][j] = Integer.parseInt(st.nextToken());
        }

        //좌표 변환 후 저장
        int temp = bc[i][0] -1;
        bc[i][0] = bc[i][1] -1;
        bc[i][1] = temp;
      }

      //2. 좌표 계산 *1,1부터 시작이라 -1씩 해줘야 함
      int[][] location = {{0,0}, {9,9}};

      for (int i=0; i<=m; i++) {
        
        int max = 0;
        for (int j=0; j<a; j++) {
        
          for (int k=0; k<a; k++) {
            if (!check_bc(location[0][0], location[0][1], bc, j)  //둘 다 포함되는 bc가 없을 경우
              && !check_bc(location[1][0], location[1][1], bc, k)) continue;

            int sum = 0;
            if (!check_bc(location[0][0], location[0][1], bc, j)) {
              sum = bc[k][3];
              max = Math.max(max, sum);
              continue;
            } else if (!check_bc(location[1][0], location[1][1], bc, k)) {
              sum = bc[j][3];
              max = Math.max(max, sum);
              continue;
            }

            if (j == k) {
              sum = bc[j][3];
            } else {
              sum = bc[j][3] + bc[k][3];
            } 

            max = Math.max(max, sum);
          }
        }

        result += max;

        if (i == m) {
          break;
        }

        // 좌표 계산 (t=0일 때부터 계산해야 하기 때문에 좌표 계산을 후에 진행)
        location = calculate_location(move_arr, location, i);
      }
      
      System.out.println("#" + testcase + " " + result);
    }
  }

  static boolean check_bc(int x, int y, int[][] bc, int idx) {
    int cal_x = Math.abs(x - bc[idx][0]);
    int cal_y = Math.abs(y - bc[idx][1]);
      
    if ( cal_x + cal_y <= bc[idx][2]) { 
      return true;
    }

    return false;
  }

  static int[][] calculate_location(int[][] move_arr, int[][] location, int idx) {
    for (int j=0; j<2; j++) {
      if (move_arr[j][idx] == 1) { //상
        location[j][0] -= 1;
      }

      if (move_arr[j][idx] == 2) { //우
        location[j][1] += 1;
      }

      if (move_arr[j][idx] == 3) { //하
        location[j][0] += 1;
      }

      if (move_arr[j][idx] == 4) { //좌
        location[j][1] -= 1;
      }
    }

    return location;
  }
}