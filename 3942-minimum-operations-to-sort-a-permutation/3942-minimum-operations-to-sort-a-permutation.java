class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        List<int[]> starts = new ArrayList<>();
        boolean inc = true, dec = true;
        for (int i = 0; i < n; ++i) {
            if (nums[(i + 1) % n] != (nums[i] + 1) % n) inc = false;
            if (nums[(i + 1) % n] != (nums[i] - 1 + n) % n) dec = false;
        }
        if (inc) starts.add(new int[]{0, nums[0]});
        if (dec) starts.add(new int[]{1, (n - 1 - nums[0] + n) % n});
        if (starts.isEmpty()) return -1;
        int[][] dist = new int[2][n];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(dist[i], -1);
        }
        Queue<int[]> q = new LinkedList<>();
        for (int[] start : starts) {
            dist[start[0]][start[1]] = 0;
            q.offer(start);
        }
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int t = curr[0], s = curr[1];
            if (t == 0 && s == 0) return dist[t][s];
            
            int d = dist[t][s];
            // Operation 1: Rotate Left by One
            int nt = t, ns = (s + 1) % n;
            if (dist[nt][ns] == -1) {
                dist[nt][ns] = d + 1;
                q.offer(new int[]{nt, ns});
            }
            // Operation 2: Reverse the array
            nt = 1 - t;
            ns = (n - s) % n;
            if (dist[nt][ns] == -1) {
                dist[nt][ns] = d + 1;
                q.offer(new int[]{nt, ns});
            }
        }
        
        return -1;
    }
}