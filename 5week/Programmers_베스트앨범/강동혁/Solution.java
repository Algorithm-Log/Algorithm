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

// map 정렬 map 은 Collection 이 아니기때문에 
// stream 으로 변환이 안돼서 entrySet 으로 변환후 정렬
// map 을 변환하는 방법
// keySet() -> key 값으로 이루어진  set Set<Key>
// values() -> value 값으로 이루어진 컬렉션     Collection<Value>
// entrySet() -> 키와 벨류 값 이 저장된 Entry 값으로 이루어진 set   Set<Entry<Key, Value>>

//computeIfAbsent(genres[i], k -> new ArrayList<>()) -> 키가 map 에 없으면 새로운
// ArrayList 를 만들어서 넣고 이미 있으면 기존 List를 가져와라



// genres 와 plays 를 map에 넣은다음 먼저 장르 별로 정렬을 먼저한다
// 장르 별로 한쪽으로 값을 모을때는 plays 의 값중 가장 큰값의 장르가 제일 앞쪽에 오도록 정렬한다
// 그후 값을 기준으로 정렬을 하는데 모든 map요소 를 전부 던져주어서
// 정렬을 하면 장르별로 모아둔게 흩어질수있기 때문에
// 같은 장르 끼리만 정렬이 되도록하고싶은데 어떻게 해야하지
// 그리고 같은 장르 끼리 정렬시에 같은 재생횟수 일시 고유번호를 기준으로 정렬을 해야함으로
// map의 value에 고유번호를 같이 저장해준다 int[] 형태로 값을 넣으면 될거같음
// 근데 map의 키값을 장르로 하면 값이 덮어쓰여지니
// map의 value값을 List<int[]> 형식으로 저장하여 같은 장르의 키값을 추가할시에
// 저 리스트에 add 하는걸로 
// 정렬을 그럼 먼저 같은 장르 끼리 모아줘야하닌까
// 아니지 map요소는 같은 장르끼리 무조건 뭉쳐있네
// 그럼 map을 plays 값을 기준으로 정렬후 정렬할때 값이 같으면 고유번호 기준으로 정렬이되도록
// 각각의 장르 마다 최대의 plays 를 꺼낸후
// 순위를 메기고 장르수 * 2 만큼의 반복문을 돌면서(바깥이 장르수 안쪽이 2번 인 2중반복문)
// 순위에 맞게 map 요소의 고유번호 값을 가져오도록 
import java.util.*;
import java.util.stream.Collectors;

class Solution {

        

    public int[] solution(String[] genres, int[] plays) {

        Map<String,List<int[]>> map = new HashMap<>();

        
        for(int i=0;i<genres.length;i++){
            map.computeIfAbsent(genres[i],k->new ArrayList<>()).add(new int[]{i,plays[i]});
        }

        for(String key : map.keySet()){
            map.get(key).sort((a,b)->{if(Integer.compare(a[1] ,b[1])==0){
                return Integer.compare(a[0],b[0]);
            }
            return b[1]-a[1];
        } );
        }
        
        Map<String,Integer> temp= new HashMap<>();

        for(String key : map.keySet()){
            for(int i=0;i<map.get(key).size();i++){
             temp.put(key,temp.computeIfAbsent(key, k-> 0)+map.get(key).get(i)[1]);
            }
        }
        
        List<String> genreOrder = new ArrayList<>(temp.keySet());
        genreOrder.sort((a,b) -> Integer.compare(temp.get(b),temp.get(a)));

        List<Integer> result = new ArrayList<>();
        
        for(String key : genreOrder){
            int count = Math.min(2, map.get(key).size());
            for(int j=0;j<count;j++){
                result.add(map.get(key).get(j)[0]);
            }
        }
        int[] answer = new int[result.size()];

        for(int i=0;i<result.size();i++){
            answer[i] = result.get(i);
        }


        return answer;  
    }
}