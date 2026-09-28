class Solution {
    public int maxDepth(String s) {
        int curr=0;
        int ans=0;
        for(char ch:s.toCharArray()){
            if(ch=='('){
                curr++;
                ans=Math.max(ans,curr);
            }
            else if(ch==')'){
                curr--;
            }
        }
        return ans;
        
    }
}