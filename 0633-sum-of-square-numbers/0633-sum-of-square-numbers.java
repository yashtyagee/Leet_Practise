class Solution {
    public boolean judgeSquareSum(int c) {
        long left=0;
        long right=(long)Math.sqrt(c);
        long sum=0;
        while(left<=right){
            sum=left*left+right*right;
            if(sum==c){
                return true;
            }
            else if(sum<c){
                left++;
            }
            else{
                right--;
            }

        }
        return false;
        
    }
}