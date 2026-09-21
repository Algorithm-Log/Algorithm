class Solution {
    int min = 51;
    boolean[] visited;
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        dfs(begin, target, words, 0);
        
        return min == 51 ? 0 : min;
    }
    
    public void dfs (String begin, String target, String[] words, int depth){
        if(begin.equals(target)){
            min = Math.min(depth, min);
            return;
        }
        
        for(int i = 0; i < words.length; i++){
            if(visited[i]) continue;
            if(diffOne(begin, words[i])){
                visited[i] = true;
                dfs(words[i], target, words, depth + 1);
                visited[i] = false;
            }
        }
    }
    
    public boolean diffOne(String begin, String str){
        char[] beginC = begin.toCharArray();
        char[] strC = str.toCharArray();
        
        int cnt = 0;
        for(int i = 0; i < begin.length(); i++){
            if(beginC[i] == strC[i]) cnt++;
        }
        if(begin.length()-1 == cnt){
            return true;
        }
        return false;
    }
}