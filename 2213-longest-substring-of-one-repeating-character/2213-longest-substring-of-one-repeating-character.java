class Solution {
    class Node {
        char lc, rc;
        int ll, rl, best, len;
        Node() {}
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        this.s = s.toCharArray();
        tree = new Node[4 * n];
        build(0, 0, n - 1);

        int q = queryIndices.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            this.s[idx] = ch;
            update(0, 0, n - 1, idx, ch);
            ans[i] = tree[0].best;
        }
        return ans;
    }

    void build(int node, int l, int r) {
        tree[node] = new Node();
        if (l == r) {
            tree[node].lc = tree[node].rc = s[l];
            tree[node].ll = tree[node].rl = tree[node].best = tree[node].len = 1;
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node + 1, l, mid);
        build(2 * node + 2, mid + 1, r);
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    void update(int node, int l, int r, int idx, char ch) {
        if (l == r) {
            tree[node].lc = tree[node].rc = ch;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(2 * node + 1, l, mid, idx, ch);
        else update(2 * node + 2, mid + 1, r, idx, ch);
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    Node merge(Node left, Node right) {
        Node res = new Node();
        res.lc = left.lc;
        res.rc = right.rc;
        res.len = left.len + right.len;
        
        res.ll = left.ll;
        if (left.ll == left.len && left.rc == right.lc) res.ll += right.ll;
        
        res.rl = right.rl;
        if (right.rl == right.len && left.rc == right.lc) res.rl += left.rl;
        
        res.best = Math.max(left.best, right.best);
        if (left.rc == right.lc) res.best = Math.max(res.best, left.rl + right.ll);

        return res;
    }
}