class Solution {
    static final int MOD = 1_000_000_007;
    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int R = 2 * k;
        if (R > N) return 0;
        return nCr(N, R);
    }
    private int nCr(int n, int r) {
        if (r > n - r) r = n - r;
        long[] fact = new long[n+1];
        fact[0]=1;
        for(int i=1;i<=n;i++) fact[i]=fact[i-1]*i%MOD;

        long num = fact[n];
        long den = fact[r]*fact[n-r]%MOD;
        return (int)(num * modPow(den, MOD-2) % MOD);
    }
    private long modPow(long a, long b) {
        long res=1;
        while(b>0){
            if((b&1)==1) res=res*a%MOD;
            a=a*a%MOD;
            b>>=1;
        }
        return res;
    }
}