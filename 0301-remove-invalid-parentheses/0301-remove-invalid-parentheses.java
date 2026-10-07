class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        remove(s, res, 0, 0, new char[]{'(', ')'});
        return res;
    }

    void remove(String s, List<String> res, int last_i, int last_j, char[] par) {
        int cnt = 0;
        for (int i = last_i; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) cnt++;
            if (s.charAt(i) == par[1]) cnt--;
            if (cnt < 0) {
                for (int j = last_j; j <= i; j++) {
                    if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1)!= par[1])) {
                        remove(s.substring(0, j) + s.substring(j + 1), res, i, j, par);
                    }
                }
                return;
            }
        }
        String rev = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            remove(rev, res, 0, 0, new char[]{')', '('});
        } else {
            res.add(rev);
        }
    }
}