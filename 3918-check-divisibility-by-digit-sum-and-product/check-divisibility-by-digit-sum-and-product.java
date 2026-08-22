class Solution {
    public boolean checkDivisibility(int n) {
        int temp=n;
        int sum = 0;
        int pro = 1;
        while(n>0){
            int ld= n%10;
            sum+= ld;
            pro*=ld;
            n/= 10;

        }
        return temp%(sum+pro) == 0;

    }
}