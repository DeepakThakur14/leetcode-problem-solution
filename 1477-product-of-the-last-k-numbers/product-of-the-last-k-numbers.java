class ProductOfNumbers {

    List<Integer> prefix;
    int product;

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
        product = 1;
    }

    public void add(int num) {
        if (num == 0) {
            prefix.clear();
            product = 1;
        } else {
            product *= num;
            prefix.add(product);
        }
    }

    public int getProduct(int k) {
        if (k > prefix.size()) {
            return 0;
        }

        if (k == prefix.size()) {
            return prefix.get(prefix.size() - 1);
        }

        return prefix.get(prefix.size() - 1) /
               prefix.get(prefix.size() - k - 1);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */