import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int maxEnergy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        int[][] lid = new int[m][n];
        for (int[] row : lid) Arrays.fill(row, -1);
        int lCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') { sr = i; sc = j; }
                if (ch == 'L') lid[i][j] = lCount++;
            }
        }

        if (lCount == 0) return 0;
        int fullMask = (1 << lCount) - 1;
        int[][][] bestEnergy = new int[m][n][1 << lCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc, maxEnergy, 0, 0});
        bestEnergy[sr][sc][0] = maxEnergy;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], e = cur[2], mask = cur[3], steps = cur[4];

            if (mask == fullMask) return steps;
            if (e < bestEnergy[r][c][mask]) continue;

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                char ch = classroom[nr].charAt(nc);
                if (ch == 'X') continue;

                int ne = e - 1;
                if (ne < 0) continue;

                int nmask = mask;
                if (ch == 'L') {
                    nmask = mask | (1 << lid[nr][nc]);
                }

                if (ch == 'R') ne = maxEnergy;
                if (ne == 0 && ch!= 'R' && nmask!= fullMask) {
                    if (nmask == fullMask) return steps + 1;
                    continue;
                }

                if (ne <= bestEnergy[nr][nc][nmask]) continue;

                bestEnergy[nr][nc][nmask] = ne;
                q.offer(new int[]{nr, nc, ne, nmask, steps + 1});
            }
        }

        return -1;
    }
}