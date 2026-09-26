class Solution {
    public String evaluate(String s, List<List<String>> knowledge){
        Map<String,String>mp=new HashMap<>();
        for(List<String>k:knowledge)mp.put(k.get(0),k.get(1));
        StringBuilder ans=new StringBuilder();
        StringBuilder key=new StringBuilder();
        boolean in=false;
        for(char c:s.toCharArray()){
            if(c=='('){
                in=true;
                key.setLength(0);
            }
            else if(c==')'){
                in=false;
                String k=key.toString();
                ans.append(mp.getOrDefault(k,"?"));
            }
            else{
                if(in)key.append(c);
                else ans.append(c);
            }
        }
        return ans.toString();
    }
}