class Solution {
    public int gcdOfOddEvenSums(int n) {
        int sume=0;
        int sumo=0;
        for(int i=1;i<=n;i++){
            sume+=(2*i);
            sumo+=(2*i-1);
        }
        return gcd(sumo,sume);
        
    }
    public int gcd(int a,int b){
        if(b==0){
            return a;
        }
        return gcd(b,a%b);
    }
}