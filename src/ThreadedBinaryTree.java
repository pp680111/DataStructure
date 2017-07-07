/**
 * Created by qq578 on 2017/7/5.
 */
public class ThreadedBinaryTree {
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
        /*
        为了仿照线性表的存储结构，在二叉树的线索链表上添加一个头结点，令其lchild域指向二叉树的根节点，其rchild域的指针指向中序遍历访问的最后一个节点。
        反之，令二叉树中序序列中的第一个节点的lchild域指针和最后一个节点的rchild域的指针均指向头结点
         */
        Node head;
        Node root;

        public void initTree(){
            head = new Node(-1);
        }

        /*
        这个函数不难理解，就是循环数组，然后对一个数据一直寻找它能插入的地方，如果已经有节点的数据和它一样的话就跳过
         */
        public void buildTree(int[] data){
            head = null;
            root = new Node(data[0]);
            for(int i=1;i< data.length;i++){
                Node tmpNode = root;
                while(true){
                    if(tmpNode.element == data[i])
                        break;

                    if(tmpNode.element > data[i])
                    {
                        if(tmpNode.left == null){
                            tmpNode.left = new Node(data[i]);
                            break;
                        }
                        tmpNode = tmpNode.left;
                    } else
                    {
                      if(tmpNode.right == null)
                      {
                          tmpNode.right = new Node(data[i]);
                          break;
                      }
                      tmpNode = tmpNode.right;
                    }
                }
            }
        }

         public void inOrderThreading(){
            Node current;
            Node previous;

            initTree();

            head.lTag = false;
            head.rTag = true;
             // 二叉树为空的时候，头结点指向其本身
            if(root == null){
                head.left = head.right = head;
            }
            else{
                current = root;

                head.left = current;
                previous = head;
                previous = inThreading(current,previous);
                System.out.println("建立线索二叉树后，previous指针的值为：" + previous.element);
                previous.rTag = true;
                previous.right = head;
                head.right = previous;
                System.out.println("建立线索二叉树后，最后一个节点为：" + previous.element + ",对应的后记节点为：" + previous.right.element);
            }
         }

         public Node inThreading(Node current,Node previous){
            if(current != null)
            {
                Node tmpNode = inThreading(current.left,previous);
                //前驱线索
                if(current.left == null && current.lTag == false)
                {
                    current.lTag = true;
                    current.left = previous;
                }
                previous = tmpNode;
                if(previous.right == null && previous.rTag == false){
                    previous.rTag = true;
                    previous.right = current;
                }

                previous = current;
                previous = inThreading(current.right,previous);

                return previous;
            }

            return previous;
         }

         //查找二叉查找树的最小节点：线索化二叉树前后的区别
        public Node getFirstNode(Node node){
             if(head != null)
             {
                 while(node.left != head)
                     node = node.left;
             }
             else{
                 while(node.left != null){
                     node = node.left;
                 }
             }

             return node;
        }

        //查找二叉查找树的最大节点
        public Node getLastNode(Node node){
            if(head == null){
                while(node.right != null)
                    node = node.right;
            }
            else{
                while(node.right != head)
                    node = node.right;
            }

            return node;
        }

        //查找节点的前驱节点
        public Node getPredecessor(Node node){
            if(node.left != null)
            {
                return getLastNode(node.left);
            }
            Node parent = getParent(node);
            while(parent != null && node == parent.left)
            {
                node = parent;
                parent = getParent(parent);
            }

            return parent;
        }

        //查找后继节点
        public Node getSuccessor(Node node){
           if(node.right != null){
               return getFirstNode(node.right);
           }

           Node parent = getParent(node);

           if(parent == null)
               return null;
           while(parent != null){
               if(parent.left == node)
                   return parent;
               else{
                   node = parent;
                   parent = getParent(parent);
               }
           }
           return parent;
        }

        //求出父亲节点
        public Node getParent(Node node){
            Node p = root;
            Node tmp = null;
            while(p != null && p.element != node.element)
            {
                if(p.element > node.element)
                {
                    tmp = p;
                    p = p.left;
                }
                else{
                    tmp = p;
                    p = p.right;
                }
            }

            return tmp;
        }

        //线索化的递归遍历二叉树
        public void inOrderReaversal(){
            Node node;
            if(head != null){
                //node表示head头指针指向的root节点
                node = head.left;
                //空疏或者遍历结束head==node
                while(node != head){
                    while(node.lTag == false)
                        node = node.left;
                    System.out.print(node.element + " ");
                    while(node.rTag && node.right != head){
                        node = node.right;
                        System.out.print(node.element + " ");
                    }
                    //访问玩叶节点的后记之后，访问右子树
                    node = node.right;
                }
            }
        }

        public void normalInOrderTraversal(){
            normalInOrderTraversal(root);
            System.out.println();
        }
        public void normalInOrderTraversal(Node node){
            if(node != null){
                normalInOrderTraversal(node.left);
                System.out.print(node.element + " ");
                normalInOrderTraversal(node.right);
            }
        }
    }




