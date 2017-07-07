/**
 * Created by qq578 on 2017/7/6.
 */
public class Main {
    public static void main(String[] args){
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        int[] data = {2,8,7,4,9,3,1,6,7,5};
        tree.buildTree(data);
        tree.normalInOrderTraversal();
//        tree.inOrderThreading();
//        tree.inOrderReaversal();
    }
}
