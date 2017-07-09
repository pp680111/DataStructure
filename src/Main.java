/**
 * Created by qq578 on 2017/7/6.
 */
public class Main {
    public static void main(String[] args){
//        MorrisBinaryTree tree = new MorrisBinaryTree();
//        int[] data = {4,2,6,1,3,5,7};
//        tree.buildTree(data);
//        tree.copyDelete(2);
//        tree.morrisInOrder();

        RebuildTree tree = new RebuildTree();
        int[] pre = {1,2,4,5,3,6};
        int[] in = {4,2,5,1,6,3};
        RebuildTree.TreeNode node = tree.rebuild(pre,in);
        tree.setRoot(node);
        tree.morrisInOrder();
    }
}
