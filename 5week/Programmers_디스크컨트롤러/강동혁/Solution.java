import java.util.*;
import java.io.*;

//14:19

// 음 시간을 어떻게 표현할까
// 배열로 포현할까
// 길이가 2000인 배열을 사용해도되나?
// 그러지 말고 저장할때 시간이 저장이 되니
// 현재시각을 0으로 두고 하나의 작업이 종료됬을때
// 그 작업의 소요 시간을 더해서 현재 시간을 측정하는걸로
// jobs 를 요청시간을 기준으로 정렬을 시키고
// 요청시간이 같다면 소요시간을 기준으로 졍렬을 시켜서
// map에 키값을 요청시간 벨류를 list<Integer> 로 만들어서
// map에서 요소를 하나씩 꺼내서 근데 map에는 순서가 없으니
// 그냥 배열에 넣을까 배열에 인덱스 가 요청시간이고
// 배열안에 list<Integer> 가있고 그 리스트에 값을 추가하는 식으로
// jobs 를 다 배열에 다 넣고 난뒤에 배열의 모든 요소를 순회하면서
// list 를 정렬 해주고 
// 다시 배열을 순회 하면서 작업이 있으면 꺼내서
// 작업의 갯수 만큼 반복문을 돌면서 작업의 소요 시간을 
// 현재 시간에 더해주고 
// 다음 요청 시간으로 넘어가고 아 근데
// 이러면 나중에 요청이 들어왔더라도 소요시간이 짧으면
// 그거 먼저 해야하는데 정렬이 안되네
// 그럼 우선순위 큐 써보자
// 어캐쓰지 큐에 jobs 를 넣는다 
// 우선순위 큐를 만들때 정렬기준을 요청시간으로 잡고 요청시간이 같다면
// 소요시간을 기준으로 해서 쭉 다 정렬하고
// 값을 꺼내고 근데 값을 꺼냈을때 지나간 시간을 기준으로 
// 정렬을 다시해줘야하는데 값을 한번 꺼 낼때마다
// 정렬을 해준다? 이건 말도안되는데
// 그럼 값을 전부다 넣지말고
// jobs 를 정렬 한뒤에
// 큐에 jobs[0] 값을 넣고 꺼대면서
// 현재시간을 소요시간 만큼 더해주고
// jobs[] 에서 현재시간 만큼의 요청시간에 해당하는 애들을 전부
// 모아서 큐에 넣어주고 반복


class Solution {
    public int solution(int[][] jobs) {
        Map<Integer,List<Integer>> jobMap = new TreeMap<>();
        int count=jobs.length;
        for(int i=0;i<jobs.length;i++){

            jobMap.computeIfAbsent(jobs[i][0],k -> new ArrayList<Integer>()).add(jobs[i][1]);
            
        }

        
        
        int currentTime=0;
        
        PriorityQueue<int[]> que = new PriorityQueue<>((a,b) -> {if (a[1] == b[1]) {
        return Integer.compare(a[0], b[0]);
    }
    return Integer.compare(a[1], b[1]);
        }); 
        int preCurrentTime=0;
        int result=0;
        //제일 처음 작업만 큐에 넣어줌
        for(int key : jobMap.keySet()){
            for(int i=0 ; i<jobMap.get(key).size();i++){
                que.add(new int[]{key,jobMap.get(key).get(i)});
            }
            
            
            break;
        }
        
        while(!que.isEmpty()){
            int[] temp = que.poll();
            if(temp[0]>currentTime){
                currentTime=temp[0];
            }
            preCurrentTime = currentTime;
            currentTime=preCurrentTime+temp[1];
            
            result += currentTime-temp[0];
            System.out.println("요청: "+temp[0]+"소요: "+temp[1]+"작업이 끝난 현재시간: "+currentTime);
            int i=preCurrentTime+1;
                for(int key : jobMap.keySet()){
                    
                    if(key>=i){
                        for(int j=0;j<jobMap.get(key).size();j++){
                            que.add(new int[]{key,jobMap.get(key).get(j)});
                            
                        }
                        
                    }
                }
                
            
        }

        
        int answer = result/count;
        System.out.println(result);
        return answer;

    }
}