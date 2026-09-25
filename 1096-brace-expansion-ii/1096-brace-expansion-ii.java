class Solution {
    public List<String> braceExpansionII(String expression) {
        Deque<Set<String>>st=new ArrayDeque<>();
        Deque<Character>op=new ArrayDeque<>();
        Set<String>cur=new HashSet<>();
        cur.add("");
        for(int i=0;i<expression.length();i++) {
            char c=expression.charAt(i);
            if(c>='a'&&c<='z'){
                Set<String> nxt = new HashSet<>();
                for (String s : cur) nxt.add(s + c);
                cur = nxt;
            } else if (c == '{') {
                st.push(cur);
                op.push('{');
                cur = new HashSet<>();
                cur.add("");
            } else if (c == ',') {
                st.push(cur);
                op.push(',');
                cur = new HashSet<>();
                cur.add("");
            } else {
                Set<String> inner = cur;
                while (!op.isEmpty() && op.peek() == ',') {
                    op.pop();
                    Set<String> p = st.pop();
                    Set<String> u = new HashSet<>();
                    u.addAll(p);
                    u.addAll(inner);
                    inner = u;
                }
                op.pop();
                Set<String> p = st.pop();
                Set<String> prod = new HashSet<>();
                for (String a : p) for (String b : inner) prod.add(a + b);
                cur = prod;
            }
        }
        List<String> ans = new ArrayList<>(cur);
        Collections.sort(ans);
        return ans;
    }
}