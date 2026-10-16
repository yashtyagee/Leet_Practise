class Solution {
    long MOD = 1_000_000_007L;
    long[] sum, sumSq, lazy;
    int n;

    void apply(int node, int l, int r, long v){
        long len = r - l + 1;
        sumSq[node] = (sumSq[node] + 2*v%MOD*sum[node]%MOD + v*v%MOD*len%MOD)%MOD;
        sum[node] = (sum[node] + v*len)%MOD;
        lazy[node] = (lazy[node] + v)%MOD;
    }
    void push(int node, int l, int r){
        if(lazy[node]!=0 && l!=r){
            int mid=(l+r)/2;
            apply(node*2,l,mid,lazy[node]);
            apply(node*2+1,mid+1,r,lazy[node]);
        }
        lazy[node]=0;
    }
    void update(int node,int l,int r,int ql,int qr,long v){
        if(ql>r || qr<l) return;
        if(ql<=l && r<=qr){ apply(node,l,r,v); return; }
        push(node,l,r);
        int mid=(l+r)/2;
        update(node*2,l,mid,ql,qr,v);
        update(node*2+1,mid+1,r,ql,qr,v);
        sum[node]=(sum[node*2]+sum[node*2+1])%MOD;
        sumSq[node]=(sumSq[node*2]+sumSq[node*2+1])%MOD;
    }
    long querySq(int node,int l,int r,int ql,int qr){
        if(ql>r || qr<l) return 0;
        if(ql<=l && r<=qr) return sumSq[node];
        push(node,l,r);
        int mid=(l+r)/2;
        return (querySq(node*2,l,mid,ql,qr)+querySq(node*2+1,mid+1,r,ql,qr))%MOD;
    }

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4*n+5];
        sumSq = new long[4*n+5];
        lazy = new long[4*n+5];
        java.util.Map<Integer,Integer> last = new java.util.HashMap<>();
        long ans = 0;
        for(int i=1;i<=n;i++){
            int x = nums[i-1];
            int p = last.getOrDefault(x,0);
            if(p+1 <= i-1) update(1,1,n,p+1,i-1,1);
            update(1,1,n,i,i,1);
            last.put(x,i);
            long cur = querySq(1,1,n,1,i);
            ans = (ans + cur)%MOD;
        }
        return (int)ans;
    }
}