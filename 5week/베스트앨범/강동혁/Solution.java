//10:17

// genres 배열의 장르갯수를 카운팅 해야함
// set에 넣어서 장르를 관리하자
// set에 넣을 필요 없을듯 그냥 genres 로 map을 만들면 
// genres 배열의 길이만큼 plays 배열을 순회하면서
// genres[i],plays[i]  map 을 사용해서
// map에  genres[i] 가 키로 있으면 value 값을 plays[i] 만큼 더해주고
// 없으면 genres[i],plays[i] 를 각각 키와 벨류로 해서 추가하기
// 아 노래의 고유번호도 저장을 해야하네 음 map의 벨류에 [재생수,고유번호]
// 이렇게 저장이 가능한가? MAP <String,int[]>


// map의 put() 함수는 같은 키의 값은 덮어쓴다
// map 의 get() 함수는 없는 키의 값은 null 로 가져온다 -> getOrDefault()
// map.getOrDefault(s, 0) -> map에 s 값인 키가 존재하면 map에 저장되어있는 기존값 반환
// s인 키가 존재하지 않으면 0 두번째 자리에 쓴값 
// containsKey() 키가 존재하는지 안하는지
// map 출력하는 법 

// entrySet 으로 하나씩 순회 하면서 출력 
// 키와 벨류 두가지 값에 모두 접근가능
// map 은 반복자 iterable 객체가 아니라 바로 반복문에 넣을수 없음
// entrySet 으로 변환해서 사용하거나 함수를 사용해서 변환해줘야함

// key 와 value 값만 뽑아서 접근도 가능함 
// keySet() ,values()

// remove() 해당하는 키값의 요소 삭제

// 이제 저장된 map에서 베스트 앨범을 만들면되는데
// map 을 int[1] 재생수 기준으로 내림차순으로 정렬을 시키고
// 정렬된 map 을 순회 하면서 첫번째 노래의 장르 를 terget 변수에 저장후
// result 변수에 int[0] 저장
// 순회 하면서 장르가 target 인 map에서 위와 같이 반복
// 다음 장르를 넣어야하는데 이미 순회 하면서 지나왔을수 있음
// 그냥 위 순회를 break로 빠져 나오고
// 반복문을 한번더 돌자
// 그리고 위와같이 반복

//map 정렬 map 은 Collection 이 아니기때문에 
// stream 으로 변환이 안돼서 entrySet 으로 변환후 정렬
// map 을 변환하는 방법
// keySet() -> key 값으로 이루어진  set Set<Key>
// values() -> value 값으로 이루어진 컬렉션     Collection<Value>
// entrySet() -> 키와 벨류 값 이 저장된 Entry 값으로 이루어진 set   Set<Entry<Key, Value>>

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String[] genres, int[] plays) {

        Map<String,int[]> map = new HashMap<>();
        String[] target = new String[2];
            for(int i=0;i<genres.length;i++){
                if(map.containsKey(genres[i])){
                    map.put(genres[i], new int[]{i,map.get(genres[i])[1]+plays[i]});
                }else{
                    map.put(genres[i],new int[]{i,plays[i]});
                }
            }

        List<Map.Entry<String, Integer>> sortedList = map.entrySet().stream().sorted( a ,b -> Integer.compare(a.getKey()[1],b.getKey()[1])).collect(Collectors.toList());

            
        

        for (String key : map.keySet()) {
        System.out.println(key + " " + map.get(key)[0] +" "+ map.get(key)[1]);
        }

        int[] answer = {};
        return answer;
    }
}