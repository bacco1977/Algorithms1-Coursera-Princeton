import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int size;
    private WeightedQuickUnionUF uf;
    private int topSite;
    private int bottomSite;
    private int openSites;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be a positive integer");
        }
        size = n;
        grid = new boolean[size][size];
        uf = new WeightedQuickUnionUF(size * size + 2);
        topSite = size * size;
        bottomSite = size * size + 1;
        openSites = 0;
    }

    public void open(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IndexOutOfBoundsException("row or column index out of bounds");
        }
        if (!grid[row - 1][col - 1]) {
            grid[row - 1][col - 1] = true;
            openSites++;
            int siteIndex = (row - 1) * size + (col - 1);
            if (row == 1) {
                uf.union(siteIndex, topSite);
            }
            if (row == size) {
                uf.union(siteIndex, bottomSite);
            }
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(siteIndex, siteIndex - size);
            }
            if (row < size && isOpen(row + 1, col)) {
                uf.union(siteIndex, siteIndex + size);
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(siteIndex, siteIndex - 1);
            }
            if (col < size && isOpen(row, col + 1)) {
                uf.union(siteIndex, siteIndex + 1);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IndexOutOfBoundsException("row or column index out of bounds");
        }
        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IndexOutOfBoundsException("row or column index out of bounds");
        }
        int siteIndex = (row - 1) * size + (col - 1);
        return isOpen(row, col) && uf.connected(siteIndex, topSite);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.connected(topSite, bottomSite);
    }
}
