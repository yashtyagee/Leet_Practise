class Solution {
    public int smallestNumber(int n, int t) {
        for(int i=n;i<=n+10;i++){
            if(getDigitProduct(i)%t==0){
                return i;
            }
        }
        return n;
    }
    private int getDigitProduct(int num){
        int p=1;
        while(num>0){
            p*=(num%10);
            num/=10;
        }
        return p;
    }
}