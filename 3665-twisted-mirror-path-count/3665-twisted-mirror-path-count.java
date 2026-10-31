class Solution {
    int MOD = 1_000_000_007;
    int m, n;
    int[][] grid;
    int[][][] memoX; // final landing row
    int[][][] memoY; // final landing col
    boolean[][][] vis;
    // 0 = right, 1 = down
    // memo for mirror cell (x,y) with incoming dir d
    
    public int uniquePaths(int[][] g) { // wrapper for some signatures
        return twistedMirrorPathCount(g);
    }
    
    public int twistedMirrorPathCount(int[][] g) {
        grid = g;
        m = grid.length;
        n = grid[0].length;
        memoX = new int[2][m][n];
        memoY = new int[2][m][n];
        vis = new boolean[2][m][n];
        for(int d=0;d<2;d++) for(int i=0;i<m;i++) Arrays.fill(memoX[d][i], -2);
        
        long[][] dp = new long[m][n];
        dp[0][0]=1;
        
        // iterate by sum = i+j
        for(int sum=0; sum<=m+n-2; sum++){
            for(int i=0;i<m;i++){
                int j = sum - i;
                if(j<0 || j>=n) continue;
                if(dp[i][j]==0) continue;
                if(i==m-1 && j==n-1) continue;
                if(grid[i][j]==1) continue; // mirror pe kabhi khade nahi hote
                // try right
                int[] landR = getLandingFrom(i, j, 0);
                if(landR!=null){
                    dp[landR[0]][landR[1]] = (dp[landR[0]][landR[1]] + dp[i][j]) % MOD;
                }
                // try down
                int[] landD = getLandingFrom(i, j, 1);
                if(landD!=null){
                    dp[landD[0]][landD[1]] = (dp[landD[0]][landD[1]] + dp[i][j]) % MOD;
                }
            }
        }
        return (int)dp[m-1][n-1];
    }
    
    // from (i,j) try to move dir -> final landing cell or null
    int[] getLandingFrom(int i, int j, int dir){
        int ni = i + (dir==1 ? 1 : 0);
        int nj = j + (dir==0 ? 1 : 0);
        if(ni>=m || nj>=n) return null;
        if(grid[ni][nj]==0) return new int[]{ni,nj};
        // ni,nj is mirror, we entered it with dir
        return resolveMirror(ni, nj, dir);
    }
    
    int[] resolveMirror(int x, int y, int inDir){
        // inDir is direction used to enter (x,y)
        if(memoX[inDir][x][y]!=-2){
            if(memoX[inDir][x][y]==-1) return null;
            return new int[]{memoX[inDir][x][y], memoY[inDir][x][y]};
        }
        vis[inDir][x][y]=true;
        int outDir = 1 - inDir; // right->down, down->right
        int nx = x + (outDir==1 ? 1 : 0);
        int ny = y + (outDir==0 ? 1 : 0);
        if(nx>=m || ny>=n){
            memoX[inDir][x][y]=-1;
            return null;
        }
        int[] res;
        if(grid[nx][ny]==0){
            res = new int[]{nx,ny};
        }else{
            res = resolveMirror(nx, ny, outDir);
        }
        if(res==null){
            memoX[inDir][x][y]=-1;
        }else{
            memoX[inDir][x][y]=res[0];
            memoY[inDir][x][y]=res[1];
        }
        return res;
    }
    
    // LeetCode exact signature:
    public int countPaths(int[][] grid) {
        return twistedMirrorPathCount(grid);
    }
}