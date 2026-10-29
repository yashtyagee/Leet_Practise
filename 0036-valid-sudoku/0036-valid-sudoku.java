class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] box = new boolean[9][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int d = c - '0';
                int b = (i / 3) * 3 + j / 3;
                if (row[i][d] || col[j][d] || box[b][d]) return false;
                row[i][d] = col[j][d] = box[b][d] = true;
            }
        }
        return true;
    }
}