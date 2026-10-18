class Solution {
    long MOD = 1_000_000_007L;
    long[][] comb;

    void buildComb(int n){
        comb = new long[n+1][n+1];
        for(int i=0;i<=n;i++){
            comb[i][0]=comb[i][i]=1;
            for(int j=1;j<i;j++){
                comb[i][j]=(comb[i-1][j-1]+comb[i-1][j])%MOD;
            }
        }
    }

    long solve(List<Integer> nums){
        int n = nums.size();
        if(n<=2) return 1;
        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for(int i=1;i<n;i++){
            if(nums.get(i)<root) left.add(nums.get(i));
            else right.add(nums.get(i));
        }
        long c = comb[n-1][left.size()];
        long wl = solve(left);
        long wr = solve(right);
        return c * wl % MOD * wr % MOD;
    }

    public int numOfWays(int[] nums) {
        buildComb(nums.length);
        List<Integer> list = new ArrayList<>();
        for(int x: nums) list.add(x);
        long ans = solve(list);
        return (int)((ans - 1 + MOD)%MOD); // original ko minus karna hai
    }
}