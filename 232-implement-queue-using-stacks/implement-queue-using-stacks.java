class MyQueue {
    private final Stack<Integer> in;
    private final Stack<Integer> ot;
    public MyQueue() {
        in = new Stack<>();
        ot = new Stack<>();
    }
    
    public void push(int x) {
        in.push(x);
    }
    
    public int pop() {
        peek();
        return ot.pop();
    }
    
    public int peek() {
        if(ot.empty())
        while(!in.empty())
        ot.push(in.pop());
        return ot.peek();
    }
    
    public boolean empty() {
        return in.empty() && ot.empty();

    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */