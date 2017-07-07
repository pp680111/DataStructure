/**
 * Created by qq578 on 2017/7/7.
 */
public class MyThreadedBinaryTree {
    public static class Node {
        int element;
        Node left;
        Node right;
        boolean lTag, rTag;

        public Node(int data) {
            element = data;
            left = right = null;
            lTag = rTag = false;
        }

        public Node(int element, Node left, Node right, boolean lTag, boolean rTag) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.lTag = lTag;
            this.rTag = rTag;
        }
    }

    private Node root;
    private Node pre;

    public MyThreadedBinaryTree(){
        root = pre = null;
    }

    public void buildTree(int[] nums){
        root = new Node(nums[0]);
        Node tmpNode = null;
        for(int i = 1;i < nums.length;i++){
            tmpNode = root;
            while(true){
                if(tmpNode.element == nums[i])
                    break;
                if(tmpNode.element > nums[i])
                {
                    if(tmpNode.left == null)
                    {
                        tmpNode.left = new Node(nums[i]);
                        break;
                    }
                    tmpNode = tmpNode.left;
                }
                else{
                    if (tmpNode.right == null){
                        tmpNode.right = new Node(nums[i]);
                        break;
                    }
                    tmpNode = tmpNode.right;
                }
            }
        }
    }

    public void inOrderThreading(){
        inOrderThreading(root);
    }
    private void inOrderThreading(Node node){
        if(node != null){
            //这里还是按照递归式中序遍历的思想来操作
            inOrderThreading(node.left);

            if(!node.lTag && node.left == null){
                node.left = pre;
                node.lTag = true;
            }
            if(pre != null && pre.right == null && !pre.rTag)
            {
                pre.right = node;
                pre.rTag = true;
            }
            pre = node;

            inOrderThreading(node.right);
        }
    }

    public void preOrderThreading(){
        preOrderThreading(root);
    }
    private void preOrderThreading(Node node){
        if(node != null){
            if(!node.lTag && node.left == null)
            {
                node.lTag = true;
                node.left = pre;
            }
            if(pre != null && pre.right == null && !pre.rTag)
            {
                pre.right = node;
                pre.rTag = true;
            }
            pre = node;

            //因为前序遍历的话是先处理自身的然后在处理左子树和右子树的，如果设置了节点的前驱之后再去处理左子树的话就会导致无限循环，所以先判断左右子树是否指向子树，如果指向子树就对子树进行线索化
            if(!node.lTag)
                preOrderThreading(node.left);
            if(!node.rTag)
                preOrderThreading(node.right);
        }
    }

    public void inOrderTraversal(){
        Node node = root;
        while(node != null && !node.lTag){
            node = node.left;
        }

        do{
            System.out.print(node.element + " ");
            if(node.rTag)
                node = node.right;
            else{
                node = node.right;
                while(node != null && !node.lTag){
                    node = node.left;
                }
            }
        }while(node != null);
    }

    public void preOrderTraversal(){
        Node node = root;
        while(node != null && !node.lTag)
        {
            System.out.print(node.element + " ");
            node = node.left;
        }
        //这里是当树一路往左走到叶子节点的时候，输出最左叶子节点的
        System.out.print(node.element + " ");

        do{
            if(node.rTag)
            {
                node = node.right;
                System.out.print(node.element + " ");
            }
            else if(node.right != null )
            {
                while(node != null && !node.lTag)
                {
                    node = node.left;
                    System.out.print(node.element + " ");
                }
            }
            else node = null;
        }while(node != null);
    }
}
