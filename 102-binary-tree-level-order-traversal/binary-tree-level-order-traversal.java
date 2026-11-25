/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root== null) return list;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(queue.size()>0){
            List<Integer> l1 = new ArrayList<>();
            int count = queue.size();
            for(int i=0; i<count; i++){
                TreeNode curr = queue.remove();
                l1.add(curr.val);
                if(curr.left!= null) queue.add(curr.left);
                if(curr.right != null) queue.add(curr.right);
            }
            list.add(l1);
        }
        return list;
    }
}