class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if(sum>9*num) return "";
        if(num>1&&sum<1) return ""; 
        if(num==1&&sum==0) return "";
        StringBuilder sb = new StringBuilder();
        int rem = sum;
        for(int i=0;i<num;i++){
            int put = Math.min(9, rem);
            int remainingPos = num - i - 1;
            if(rem - put > 9 * remainingPos){
                put = rem - 9 * remainingPos;
            }
            if(i==0 && num>1 && put==0) return "";
            sb.append(put);
            rem -= put;
        }
        if(rem != 0) return "";
        return sb.toString();
    }
}