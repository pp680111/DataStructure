/**
 * Created by qq578 on 2017/7/7.
 */
public class MorrisBinaryTree {
    public static class Node{
        int element;
        Node left;
        Node right;

        public Node(int element) {
            this.element = element;
            left = right = null;
        }
    }

    private Node root;

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

    public void morrisInOrder(){
        morrisInOrder(root);
    }
    private void morrisInOrder(Node node){
        Node tmpNode = null;
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

    public void morrisPreOrder(){

    }
    private void morrisPreOrder(Node node){
        Node tmpNode = null;
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
                    //这里是将中间节点连接到前驱节点的右子树处的地方，所以输出当前
                    //节点值的话就是输出了一个根或枝节点的值。反正叶子节点的left都是null，也不是在这里输出的
                    System.out.print(node.element + " ");
                    node = node.left;
                }
                else{
                    tmpNode.right = null;
                    node = node.right;
                }
            }
        }
    }
}
