class Solution {
    public int minInsertions(String s) {
        int insertion=0;
        int need=0;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('){
                if(need%2==1){
                    insertion++;
                    need--;
                }
                need+=2;

            }
            else{
                need--;
                if(need<0){
                    insertion++;
                    need=1;
                }
            }
        }
        return insertion+need;
    }
}