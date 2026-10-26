class Solution {
    public int countIslands(int[][] grid, int k) {
        int n=grid.length;
        int m=grid[0].length;
        int ans=0;
        boolean[][]visited=new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]!=0 && !visited[i][j] ){
                    long sum=check(grid,i,j,visited);
                    if(sum%k==0) ans++;
                }
            }
        }
        return ans;
    }
    public long check(int[][]grid,int r,int c,boolean[][]visited){
        int n=grid.length;
        int m=grid[0].length;
        if(r<0||c<0||r>=n||c>=m){
            return 0;
        }
        if(grid[r][c]==0||visited[r][c]){
            return 0;
        }
        visited[r][c]=true;
        long sum=grid[r][c];
        sum+=check(grid,r+1,c,visited);
        sum+=check(grid,r-1,c,visited);
        sum+=check(grid,r,c+1,visited);
        sum+=check(grid,r,c-1,visited);
        return sum;
    }
}