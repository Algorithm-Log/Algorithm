//12:51

// records 를 key String : 차량번호 value : 시간
// 시간을 분 으로 변경해서 계산을 해야하는데 입차와 출차가 쌍으로 들어온다는 보장이 없음
// 입력을 일단 int 로 변경해서 map에 담고 담을때 List에 담아야 할지
// List 에 담는데 담았다가 계산을 할때 List value 값의 갯수를 먼저 새고
// 만약에 7개 이면 그걸 2로 나눠서 3번 시간 계산을 해서 새로운 map에
// 넣어주고 계산한 값은 remove 해주고 남은 시간은 밖에서 따로 한번더 계산해서
// 
//  
import java.util.*;
import java.util.stream.Collectors;

class Solution {


    public int[] solution(int[] fees, String[] records) {

        Map<String,List<Integer>> map = new HashMap<>();

        for(String record : records){

            String[] temp = record.split(" ");

            String[] temp1 = temp[0].split(":");

            int hour = Integer.parseInt(temp1[0]);
            int minute = Integer.parseInt(temp1[1]);

            map.computeIfAbsent(temp[1],k -> new ArrayList<Integer>()).add( (hour*60)+minute );
            
        }

        // 지금 map에 출입차 시간이 분으로 들어가 있음
        // 주어진 입력이 시간 순서대로 주어져 있어서 정렬 없이 순서대로 2개씩 끊어서 계산하면 될거같음
        
        Map<String,Integer> calMap = new HashMap<>();
        

        for(String key : map.keySet()){
            for(int i=0;i<map.get(key).size()/2;i++){

                int calTime = map.get(key).get(2*i+1) - map.get(key).get(2*i);
                calMap.put(key , calMap.getOrDefault(key, 0) + calTime);

            }

            
            map.get(key).subList(0, (map.get(key).size()/2)*2).clear();

            
        }

        // 만약에 입차 를 했는데 출차가 없던 차들을 계산

        for(String key : map.keySet()){

            if(!map.get(key).isEmpty()){
                 int calTime = 1439 - map.get(key).get(0);
                calMap.put(key,calMap.getOrDefault(key,0) + calTime);
            }

        }

        //이제 차량 번호 순으로 계산해서 출력

        List<String> sortedKeys= calMap.keySet().stream().sorted( (a,b) -> Integer.compare(Integer.parseInt(a), Integer.parseInt(b))).collect(Collectors.toList());
        int index=0;
        int[] answer = new int[calMap.keySet().size()];

        for(String key : sortedKeys){

            int extraTime = calMap.get(key) - fees[0];

            if(calMap.get(key)-fees[0] >0){
                answer[index] = ( ( ( (extraTime+fees[2]-1 ) / fees[2] ) ) * fees[3]) +fees[1];
                index++;
            }else{
                answer[index]=fees[1];
                index++;
            }

        }
        
        return answer;

    }


}