/**
 * Created by qq578 on 2017/7/8.
 */
public class RebuildTree {
    public static class TreeNode{
        int element;
        TreeNode left,right;

        public TreeNode(int data){
            element = data;
        }
    }

    TreeNode root;

    public TreeNode rebuild(int [] pre, int [] in){
        if(pre == null || in == null || pre.length == 0 || in.length == 0 || pre.length != in.length)
            return null;
        if(pre.length == 1)
            return new TreeNode(pre[0]);
        return rebuild(pre,0,pre.length - 1,in,0,in.length - 1);
    }

    public TreeNode rebuild(int[] pre,int preStart,int preEnd,int[] in,int inStart,int inEnd){
//        TreeNode node = new TreeNode(pre[preStart]);
//        int index = inStart;
//        for(;index <= inEnd;index++){
//            if(in[index] == pre[preStart])
//                break;
//        }
//
//        if(inEnd - index == 0)
//            node.right = null;
//        else if(inEnd - index == 1)
//            node.right = new TreeNode(in[inEnd]);
//        else node.right = rebuild(pre,preEnd - (inEnd - index - 1),preEnd,in,index + 1,inEnd);
//
//        if(index - inStart == 0)
//            node.left = null;
//        else if(index - inStart == 1)
//            node.left = new TreeNode(in[inStart]);
//        else node.left = rebuild(pre,preStart + 1,index - inStart + preStart - 1,in,inStart,index - 1);
//
//        return node;
        TreeNode node = new TreeNode(pre[preStart]);
        int index = inStart;

        for(;index <= inEnd;index++){
            if(in[index] == pre[preStart])
                break;
        }

        if(inEnd - index == 0)
            node.right = null;
        else if(inEnd - index == 1)
            node.right = new TreeNode(in[inEnd]);
        else node.right = rebuild(pre,preEnd - inEnd + index + 1,preEnd,in,index + 1,inEnd);

        if(index - inStart == 0)
            node.left = null;
        else if(index - inStart == 1)
            node.left = new TreeNode(in[inStart]);
        else node.left = rebuild(pre,preStart + 1,index - inStart + preStart - 1,in,inStart,index - 1);

        return node;
    }

    public void setRoot(TreeNode node){
        root = node;
    }

    public void morrisInOrder(){
        morrisInOrder(root);
    }
    private void morrisInOrder(TreeNode node){
        TreeNode tmpNode = null;
        while(node != null){
            if(node.left == null){
                System.out.print(node.element + " ");
                node = node.right;
            }
            else{
                tmpNode = node.left;

                while(tmpNode.right != null && tmpNode.right != node)
                    tmpNode = tmpNode.right;

                if(tmpNode.right == null)
                {
                    tmpNode.right = node;
                    node = node.left;
                }
                else{
                    tmpNode.right = null;
                    System.out.print(node.element + " ");
                    node = node.right;
                }
            }
        }
    }
}
