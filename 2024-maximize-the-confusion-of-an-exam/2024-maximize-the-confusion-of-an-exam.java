class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(
            longest(answerKey,k,'T'),
            longest(answerKey,k,'F')
        );
    }
    private int longest(String s,int k,char target){
        int l=0,max=0;
        int flips=0;
        for (int r=0;r<s.length();r++){
            if(s.charAt(r)!=target) flips++;
            while(flips>k){
                if(s.charAt(l)!=target) flips--;
                l++;
            }
            max=Math.max(max,r-l+1);
        }
        return max;
    }
}