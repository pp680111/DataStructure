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

    public void delete(int data){
        Node current = root;
        Node prev = null;
        Node toBeDeleteNode = null;

        while(current != null && current.element != data){
            prev = current;
            if(current.element > data)
            {
                current = current.left;
            }
            else if(current.element < data)
            {
                current = current.right;
            }
        }

        if(current == null)
        {

            System.out.println("找不到该节点,或者树是空的");
            return;
        }
        toBeDeleteNode = current;

        if(current.left != null && current.right != null)
        {
            Node tmpNode = current.right;
            //在右子树里面寻找最左节点
            while(tmpNode.left != null)
                tmpNode = tmpNode.left;
            //把整个被删除节点的左子树都给它
            tmpNode.left = current.left;
            //current指向替代被删除节点的节点
            current = current.right;

        }
        else if(current.left == null)
            current = current.right;
        else
            current = current.left;

        if(toBeDeleteNode == root)
            root = current;
        else if(prev.left == toBeDeleteNode)
            prev.left = current;
        else prev.right = current;
    }

    public void copyDelete(int data)
    {
        Node node = root;
        Node previous = null;
        Node toBeDelete = null;

        while(node != null && node.element != data){
            if(node.element > data)
            {
                previous = node;
                node = node.left;
            }
            else
            {
                previous = node;
                node = node.right;
            }
        }

        if(node == null)
        {
            System.out.println("树为空或树中不存在此节点");
            return;
        }

        toBeDelete = node;

        if(node.left != null && node.right != null){
            Node tmp = node.left;
            Node prev = node;

            while(tmp.right != null)
            {
                prev = tmp;
                tmp = tmp.right;
            }

            node.element = tmp.element;
            //这里如果左子树中最右节点就是被删除节点的左子树的话，那么就把最右节点的左子树赋给被删除节点，如果不是的话就把最右子树的左子树赋给它的父节点的右子树
            if(prev == node)
                node.left = tmp.left;
            else prev.right = tmp.left;
        }
        else if(node.left == null){
            node = node.right;
        }
        else node = node.left;

        if(node == root)
            root = node;
        else if(previous.left == toBeDelete)
            previous.left = node;
        else previous.right = node;
    }
}
