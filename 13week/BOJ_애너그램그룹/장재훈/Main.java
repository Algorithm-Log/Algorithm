import java.util.*;
import java.io.*;

public class Main {
    static Map<String, List<String>> group = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 1. 애너그램 그룹 분류
        while (true) {
            String str = br.readLine();
            if (str == null) break;

            // 동일한 문자 구성의 단어를 같은 Key로 관리 (tea, eat, ate -> aet)
            char[] cArr = str.toCharArray();
            Arrays.sort(cArr);
            String target = new String(cArr);

            List<String> list = group.getOrDefault(target, new ArrayList<>());
            list.add(str);

            // list.sort((a, b) -> a.compareTo(b));
            // 입력마다 반복 정렬하는 것은 비효율적이므로 입력 완료 후 정렬

            group.put(target, list);
        }

        // 2. 그룹 내부 사전순 정렬
        /*
        List<String> keys = new ArrayList<>(group.keySet());
        for (String key : keys) {
            List<String> sortValues = group.get(key); // map.get(key)가 참조하는건 얕은 복사가 아니라 깊은 복사! -> 정렬만 수행해도 map에 바로 반영됨!
            sortValues.sort((a, b) -> a.compareTo(b));
            group.put(key, sortValues); // -> 위의 이유로 put할 필요가 없다!
        }
        */
        // Key가 필요 없으므로 values()로 직접 접근하고, 동일한 객체를 참조하므로 put() 생략
        for (List<String> list : group.values()) {
            list.sort((a, b) -> a.compareTo(b));
            // list.sort(String::compareTo); // 메서드 참조를 이용한 동일한 표현
        }

        // 3. 그룹 간 정렬 (크기 내림차순, 동일 크기일 경우 사전순)
        List<List<String>> values = new ArrayList<>(group.values());

        values.sort((a, b) -> {
            if (a.size() == b.size()) {
                // get(0)이 사전순으로 가장 앞선 단어임을 보장하기 위해 그룹 내부를 먼저 정렬
                return a.get(0).compareTo(b.get(0));
            }
            return b.size() - a.size();
        });

        // 4. 상위 5개 그룹 출력
        /*
        for (int i = 0; i < values.size(); i++) {
            if (i >= 5) break;
        */
        // 그룹이 5개 미만인 경우까지 고려하여 Math.min()으로 반복 범위를 직접 지정
        for (int i = 0; i < Math.min(values.size(), 5); i++) {
            List<String> current = values.get(i);

            // current.sort((a, b) -> a.compareTo(b));
            // 그룹 내부 정렬을 이미 수행했으므로 중복 정렬 제거

            sb.append("Group of size ").append(current.size()).append(": ");

            // 그룹 크기에는 중복을 포함하되, 출력 시에는 인접한 단어를 비교하여 중복 제외
            for (int j = 0; j < current.size(); j++) {
                if (j != 0 && current.get(j - 1).equals(current.get(j))) continue;
                sb.append(current.get(j)).append(" ");
            }

            sb.append(".\n");
        }

        System.out.print(sb.toString());
    }
}

/*
그룹 크기가 같을 때 사전 순 정렬 - 오류
그룹이 5개 미만인 경우 처리	- 오류
*/