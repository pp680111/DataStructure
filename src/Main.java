/**
 * Created by qq578 on 2017/7/6.
 */
public class Main {
    public static void main(String[] args){
        MorrisBinaryTree tree = new MorrisBinaryTree();
        int[] data = {4,2,6,1,3,5,7};
        tree.buildTree(data);
        tree.morrisInOrder();
    }
}
