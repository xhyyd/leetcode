class Solution {
    private int[] root;
    private int[] size;
    int count;

    private int find(int p) {
        while (root[p] != p) {
            root[p] = root[root[p]];
            p = root[p];

        }
        return p;
    }

    private void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI == rootJ) {
            return;
        }
        if (size[rootI] > size[rootJ]) {
            root[rootJ] = rootI;
            size[rootI] += size[rootJ];
        } else {
            root[rootI] = rootJ;
            size[rootJ] += size[rootI];
        }
        count--;
    }

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        count = n * n * 2;
        root = new int[count];
        size = new int[count];
        for (int i = 0; i < count; i++) {
            root[i] = i;
            size[i] = 1;
        }

        String lineUp = null;
        for (int i = 0; i < n; i++) {
            String line = grid[i];
            for (int j = 0; j < n; j++) {
                int index = i * n * 2 + j * 2;

                // union self
                char cur = line.charAt(j);
                if (cur == ' ') {
                    union(index, index + 1);
                }

                // union up
                if (lineUp != null) {
                    int from = index;
                    if (cur == '\\') {
                        from = index + 1;
                    }
                    int to = index - 2 * n;
                    char up = lineUp.charAt(j);
                    if (up == '/') {
                        to++;
                    }
                    union(from, to);
                }

                // left
                if (j > 0) {
                    union(index, index - 1);
                }
            }
            lineUp = line;
        }
        return count;
    }
}
