//23:37

//음 배열에서 요소를 하나씩 꺼내서 새로운 배열을 만드는데
// 모든 요소를 한개이상 포함해야하고 (아마 이게 종료조건일듯)
// 순서가 있나? 근데 어차피 내가 순서를 변경해줄수가 없네
// 그냥 꺼내기만 해야하닌까 하나씩 꺼내서 요소를 한번씩 꺼냈으면
// 요소를 한번이상 꺼낸걸 어떻게 검사할까?
// gems 배열을 반복문으로 전부돌면서 일단
// 보석의 가짓수를 체크하면서 SET 을 만들어놓고
// gems 에서 보석을 하나 꺼낼때 만들어둔 set에서
// 요소를 삭제시키고 set에 값이 아무것도 없으면 모든 요소를 1개이상 꺼낸걸로
// dfs 까지 사용해야할까 2중반복문으로 될거같은데
// 시작점 끝점 두개 값만 필요한거닌까
// i=0 j=i+1 로 반복문돌리고
// 하나 꺼낼때 마다 set 에서 같은 값제거 조건문으로 set에 요소갯수가 0이면 종료
// 하고 i가 시작 j가끝 진열대 번호로 거리 계산 result min으로 갱신
// set을 클래스 변수로? 지역변수로?
// 테케마다 초기화 해줘야하닌까 지역변수로
// i=0 j=i 해야할듯 제일처음 꺼 하나만 뽑고도 종료 될수도있으니까
// 근데 결과값을 길이가 아니라 인덱스 배열로 리턴해야하네
// 그럼 결과값 갱신할떄 int 배열도 같이갱신 하자
 
import java.util.HashSet;
import java.util.Set;

class Solution {

    public int[] solution(String[] gems) {

        int[] result = {0, 0};
        int minLen = Integer.MAX_VALUE;

        Set<String> categories = new HashSet<>();

        for (String gem : gems) {
            categories.add(gem);
        }

        for (int start = 0; start < gems.length; start++) {

            Set<String> remain = new HashSet<>(categories);

            for (int end = start; end < gems.length; end++) {

                remain.remove(gems[end]);

                if (remain.isEmpty()) {

                    if (minLen > end - start) {
                        minLen = end - start;

                        result[0] = start + 1;
                        result[1] = end + 1;
                    }

                    break;
                }
            }
        }

        int[] answer = result;
        return answer;
    }
}