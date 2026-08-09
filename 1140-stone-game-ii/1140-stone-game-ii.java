class Solution {
    private int[][] memo;
    private int[] suffixSum;
    public int stoneGameII(int[] piles){
        int n=piles.length;
        memo=new int[n][n+1];
        for(int[] row:memo){
            Arrays.fill(row,-1);
        }
        suffixSum=new int[n];
        suffixSum[n-1]=piles[n-1];
        for(int i=n-2;i>=0;i--){
            suffixSum[i]=suffixSum[i+1]+piles[i];
        }
        return solve(0,1,piles);
    }
    private int solve(int i,int m,int[] piles){
        int n=piles.length;
        if(i+2*m>=n){
            return suffixSum[i];
        }
        if(memo[i][m]!=-1){
            return memo[i][m];
        }
        int maxStones=0;
        for(int x=1;x<=2*m;x++){
            int opponentStones=solve(i+x,Math.max(m,x),piles);
            maxStones=Math.max(maxStones,suffixSum[i]-opponentStones);
        }
        return memo[i][m]=maxStones;
    }
}