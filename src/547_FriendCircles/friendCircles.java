class Solution {
    
    public int findCircleNum(int[][] M) {
        int n = M.length;
        WQU wqu = new WQU(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (M[i][j] == 1) {
                    wqu.union(i, j);
                }
            }
        }
        return wqu.groups();
    }

    class WQU {
        private final int[] root;
        private final int[] size;
        private int count;

        public WQU(int n) {
            root = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
                size[i] = 1;
            }
            count = n;
        }

        public int find(int p) {
            while (root[p] != p) {
                root[p] = root[root[p]]; // improve
                p = root[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }

            if (size[rootP] < size[rootQ]) {
                root[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                root[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            count--;
        }

        public int groups() {
            return count;
        }
    }
}
