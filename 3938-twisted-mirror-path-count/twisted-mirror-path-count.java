class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int mod = 1000000007;

        long[][] right = new long[m][n];
        long[][] down = new long[m][n];
        right[0][0] = 1;

        int[][] vornadexil = grid;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(vornadexil[i][j] == 1){
                    if(i+1<m){
                        down[i+1][j] = (down[i+1][j] + right[i][j])%mod;
                    }
                    if(j+1<n){
                        right[i][j+1] = (right[i][j+1] + down[i][j])%mod;
                    }
                }
                else{
                    long ways = (right[i][j]+ down[i][j])%mod;
                    if(j+1<n){
                        right[i][j+1] = (right[i][j+1]+ways)%mod;
                    }

                    if(i+1<m){
                        down[i+1][j] = (down[i+1][j]+ ways)%mod;
                    }
                }
            }
        }
        return (int)((right[m-1][n-1]+ down[m-1][n-1])%mod);
    }
}