import java.util.*;

class Solution {
    int n, LOG = 18;
    List<Integer>[] g;
    int[] tin, tout, depth, base;
    int[][] up;
    int timer = 0;
    int[] bit;
    char[] cur;

    // BIT xor
    void bitXor(int idx, int val) {
        for (; idx <= n; idx += idx & -idx) bit[idx] ^= val;
    }
    void rangeXor(int l, int r, int val) {
        bitXor(l, val);
        bitXor(r + 1, val);
    }
    int pointQuery(int idx) {
        int res = 0;
        for (; idx > 0; idx -= idx & -idx) res ^= bit[idx];
        return res;
    }

    int lca(int a, int b) {
        if (depth[a] < depth[b]) { int t = a; a = b; b = t; }
        int diff = depth[a] - depth[b];
        for (int k = 0; k < LOG; k++) if (((diff >> k) & 1) == 1) a = up[k][a];
        if (a == b) return a;
        for (int k = LOG - 1; k >= 0; k--) if (up[k][a] != up[k][b]) { a = up[k][a]; b = up[k][b]; }
        return up[0][a];
    }

    // iterative dfs for tin/tout and base
    void build(int root) {
        Deque<int[]> st = new ArrayDeque<>(); // node, parent, state 0=enter 1=exit
        st.push(new int[]{root, root, 0});
        // we need stack for base building
        while (!st.isEmpty()) {
            int[] cur = st.pop();
            int u = cur[0], p = cur[1], state = cur[2];
            if (state == 0) {
                tin[u] = ++timer;
                up[0][u] = p;
                for (int k = 1; k < LOG; k++) up[k][u] = up[k-1][up[k-1][u]];
                st.push(new int[]{u, p, 1});
                for (int v : g[u]) if (v != p) {
                    depth[v] = depth[u] + 1;
                    base[v] = base[u] ^ (1 << (this.cur[v] - 'a'));
                    st.push(new int[]{v, u, 0});
                }
            } else {
                tout[u] = timer;
            }
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        g = new List[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) { g[e[0]].add(e[1]); g[e[1]].add(e[0]); }

        tin = new int[n]; tout = new int[n]; depth = new int[n];
        up = new int[LOG][n]; base = new int[n]; bit = new int[n + 2];
        cur = s.toCharArray();

        base[0] = 1 << (cur[0] - 'a');
        build(0);

        List<Boolean> ansList = new ArrayList<>();
        for (String qq : queries) {
            String[] p = qq.split(" ");
            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char nc = p[2].charAt(0);
                char oc = cur[u];
                if (oc == nc) continue;
                int delta = (1 << (oc - 'a')) ^ (1 << (nc - 'a'));
                rangeXor(tin[u], tout[u], delta);
                cur[u] = nc;
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);
                int L = lca(u, v);
                int pu = base[u] ^ pointQuery(tin[u]);
                int pv = base[v] ^ pointQuery(tin[v]);
                int maskL = 1 << (cur[L] - 'a');
                int pathMask = pu ^ pv ^ maskL;
                ansList.add((pathMask & (pathMask - 1)) == 0);
            }
        }
        boolean[] res = new boolean[ansList.size()];
        for (int i = 0; i < res.length; i++) res[i] = ansList.get(i);
        return ansList;
    }
}