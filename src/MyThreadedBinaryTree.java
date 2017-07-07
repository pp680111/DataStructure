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
}
