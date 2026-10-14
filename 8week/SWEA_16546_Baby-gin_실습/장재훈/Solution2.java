import java.io.*;

// 그리디 풀이
public class Solution2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String str = br.readLine();
            int[] numbers = new int[10];
            for (int i = 0; i < 6; i++) {
                int num = str.charAt(i)-'0';
                numbers[num]++;
            }

            int babygin = 0;
            // 그리디 풀이 1
            // // 딱 2번 반복.
            // for (int i = 0; i < 2; i++) {
            //     boolean flag = false;
            //     // triple이 되는게 있는지 확인
            //     for (int idx = 0; idx <= 9; idx++) {
            //         if (numbers[idx] >= 3) {
            //             numbers[idx] -= 3; // 3 감소시킴
            //             babygin++;
            //             flag = true;
            //             break;
            //         }
            //     }

            //     if (flag) break;

            //     // run이 되는게 있는지 확인 (3개의 연속된 숫자 있는지 확인)
            //     for (int idx = 0; idx <= 7; idx++) {
            //         if (numbers[idx] != 0 && numbers[idx+1] != 0 && numbers[idx+2] != 0) {
            //             numbers[idx]--;
            //             numbers[idx+1]--;
            //             numbers[idx+2]--;
            //             babygin++;

            //             break;
            //         }
            //     }
            // }


            // 그리디 풀이 2
            int idx = 0;
            // int babygin = 0;
            while (idx <= 9) {
                // triple 인지 확인
                if (numbers[idx] >= 3) {
                    numbers[idx]-=3;
                    babygin++;
                    continue;
                }

                // run 인지 확인
                if (idx <= 7 && numbers[idx] > 0 && numbers[idx+1] > 0 && numbers[idx+2] > 0) {
                    numbers[idx]--;
                    numbers[idx+1]--;
                    numbers[idx+2]--;
                    babygin++;
                    continue;
                }

                idx++; // 위의 둘 다 해당 안된다면, 다음 인덱스 확인
            }

            boolean res = babygin == 2;

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.print(sb.toString());
    }   
}