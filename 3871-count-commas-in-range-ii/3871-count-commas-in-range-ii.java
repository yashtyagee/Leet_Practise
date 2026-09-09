class Solution {
    public long countCommas(long n) {
        long res=1000;
        long r=0;
        while(res<=n){
            r+=n-res+1;
            res*=1000;


        }
        return r;
        
    }
}