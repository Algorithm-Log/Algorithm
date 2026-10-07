import java.io.*;

public class Solution {
    static int[] cards;
    static boolean res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            cards = new int[6];
            res = false;

            String str = br.readLine();

            for (int i = 0; i < 6; i++) {
                cards[i] = str.charAt(i) - '0';
            }

            permutation(0, new boolean[6], new int[6]);
            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void permutation(int cnt, boolean[] isSelected, int[] numbers) {
        if (cnt == 6) {
            // 0~2가 run? triple?
            boolean n1 = run(numbers[0], numbers[1], numbers[2]) || triple(numbers[0], numbers[1], numbers[2]);
            // 3~5가 run? triple?
            boolean n2 = run(numbers[3], numbers[4], numbers[5]) || triple(numbers[3], numbers[4], numbers[5]);

            if (n1 && n2) {
                res = true;
            }
            return;
        }

        for (int i = 0; i < 6; i++) {
            if (isSelected[i]) continue;
            isSelected[i] = true;
            numbers[cnt] = cards[i];
            permutation(cnt+1, isSelected, numbers);
            isSelected[i] = false;
            // permutation(cnt, isSelected, numbers); // 이건 굳이 다시 안해줘도 된다!
        }
    }

    static boolean run(int a, int b, int c) {
        return (b - a == 1) && (c - b == 1);
    }

    static boolean triple(int a, int b, int c) {
        return (a == b) && (b == c);
    }
}

/*

0~9까지 카드 중 임의 6장
3개가 연속 -> run
3개가 같은 번호 -> triple

6개가 run이랑 triple로 구성 -> baby gin

순열을 만들어서 확인해야한다!

// static
int[] cards = new int[6]
boolean res;

for(i = 0 ~ 6) {
    cards[i] = String.charAt(i)-'0'
}
res = false;
순열 코드

int cnt = 0;
isSelected[] = new boolean[6];
while(true) {
    if (cnt == 6);
    for (int i = 0; i < 6; i++) {
        isSelected[i] = true;

    }
}

void permutation(int cnt, boolean[] isSelected, int[] numbers) {
    if (cnt == 6) {
        // 0~2가 run? triple?
        boolean n1 = run(numbers[0], numbers[1], numbers[2]) || triple(numbers[0], numbers[1], numbers[2]);
        // 3~5가 run? triple?
        boolean n2 = run(numbers[3], numbers[4], numbers[5]) || triple(numbers[3], numbers[4], numbers[5]);

        if (n1 && n2) {
            res = true;
            return;
        }
    }

    for (int i = 0; i < 6; i++) {
        if (isSelected[i]) continue;
        isSelected[i] = true;
        numbers[cnt] = cards[i];
        permutation(cnt+1, isSelected, numbers);
        isSelected[i] = false;
        permutation(cnt, isSelected, numbers);
    }
}

boolean run(int a, int b, int c) {
    return (b - a == 1) && (c - b == 1);
}

boolean triple(int a, int b, int c) {
    return (a == b) && (b == c);
}

*/

// 여기까지 설계하는데 30분 사용! (16:29 ~ 17:02)
// 구현하는데 10분 사용! (17:08~17:19)