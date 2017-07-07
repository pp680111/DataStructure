import java.util.Stack;

/**
 * Created by qq578 on 2017/6/30.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    //作为二叉搜索树节点的嵌套类
    private static class BinaryNode<T>{
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        BinaryNode(T element){
            this(element,null,null);
        }

        BinaryNode(T element,BinaryNode<T> left,BinaryNode<T> right){
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    private BinaryNode<T> root;

    public BinarySearchTree(){
        root = null;
    }

    public void makeEmpty(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(T x){
        return contains(x,root);
    }
    private boolean contains(T x,BinaryNode<T> t){
//        if(t == null)
//            return false;
//
//        int compareResult = x.compareTo(t.element);
//
//        if(compareResult > 0)
//            return contains(x,t.right);
//        else if(compareResult == 0)
//            return true;
//        else return contains(x,t.left);
        /*
            MyVersion
         */
        int compareResult = 0;

        while(t != null){
            compareResult = x.compareTo(t.element);
            if(compareResult > 0)
                t = t.right;
            else if(compareResult == 0)
             return true;
            else t = t.left;
        }

        return false;
    }

    public T findMin(){
        if(isEmpty())
            return null;
        return findMin(root).element;
    }
    private BinaryNode<T> findMin(BinaryNode<T> node){
        if(node != null)
            while(node.left != null)
                node = node.left;

        return node;
    }

    public T findMax(){
        if(isEmpty())
            return null;
        return findMax(root).element;
    }
    private BinaryNode<T> findMax(BinaryNode<T> node){
        if(node != null)
            while(node.right != null)
                node = node.right;

        return node;
    }

    public void insert(T x){
        root = insert(x,root);
    }
    private BinaryNode<T> insert(T x,BinaryNode<T> t){
        if(t == null)
            return new BinaryNode<T>(x,null,null);

        int compareResult = 0;
        BinaryNode<T> root = t;

        while(t != null)
        {
            compareResult = x.compareTo(t.element);
            if(compareResult > 0)
                t = t.right;
            else if(compareResult < 0)
                t = t.left;
            else
                ;//处理重复节点
        }

        //通过上面的while循环现在的t已经是需要插入的节点了
        t = new BinaryNode<T>(x,null,null);

        return root;
    }

    public void remove(T x){
        root = remove(x,root);
    }
    private BinaryNode<T> remove(T x,BinaryNode<T> t){
        //这个是书里的实现，使用了递归，这样写起来比较简便，虽然牺牲了效率但是在代码上比较容易理解

        if(t == null)
            return null;

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0)
            t.left = remove(x,t.left);
        else if(compareResult > 0)
            t.right = remove(x,t.right);
        else if(t.left != null && t.right != null) //有两个子树
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element,t.right);
            //上面这里直接替换了被删除节点的值，这样就可以避免多次操作被删除节点在树结构中有关的引用
        }
        else
            t = (t.left != null) ? t.left : t.right;

        return t;


//        if(t == null)
//            return t;
//
//        int compareResult = 0;
//        BinaryNode<T> root = t;
//        BinaryNode<T> pNode = t;
//
//        while(t != null){
//
//            compareResult = x.compareTo(t.element);
//            if(compareResult > 0)
//            {
//                pNode = t;
//                t = t.right;
//            }
//            else if(compareResult < 0)
//            {
//                pNode = t;
//                t = t.left;
//            }
//            else //这个点代表找到了应该被删除的节点
//                break;
//        }
//
//        //这里还要考虑到这个点就是根节点的情况
//        if(t.left != null && t.right != null)
//        {
//            t.element = findMin(t.right).element;
//            t.right = remove(t.element,t.right);
//        }
//        else if(t.left != null)
//        {
//            //这里还要考虑到让父节点指向t节点的指向t的left,也就是还是两次查找,而且还比递归调用代码复杂
//            t.left.right = t.right;
//            t = t.left;
//        }
//        else{
//            t.right.left = t.left;
//            t = t.right;
//        }
//        return t;
    }

    public void printTree(){

    }

    private void printTree(BinaryNode<T> t){

    }

    public void iterativePreOrder(BinaryNode<T> node){
        Stack<BinaryNode<T>> stack = new Stack<BinaryNode<T>>();
        BinaryNode<T> tmpNode = null;
        stack.push(node);
        while(!stack.isEmpty()){
            tmpNode = stack.pop();
            System.out.println(tmpNode.element);
            if(tmpNode.right != null)
                stack.push(tmpNode.right);
            if(tmpNode.left != null)
                stack.push(tmpNode.left);
        }
    }

    public void iterativeInOrder(BinaryNode<T> node){
        /*
        思路：如果当前节点非空的话就一路往左子树前进，将自己压入栈中。如果当前节点是空的话就说明访问到左子树的头了，然后访问根节点，再然后访问右子树
         */
        BinaryNode<T> currentNode = node;
        Stack<BinaryNode<T>> stack = new Stack<BinaryNode<T>>();

        //这里循环条件的个人理解是如果node为null的话那么代表它访问到一个子树的尽头了，如果栈中有节点的话那么借拿出来访问，没有的话就说明整棵树都访问完了
        while(currentNode != null || !stack.isEmpty()){
            //访问当前节点之前先一路往左子树前进，将自己压入栈中稍后再访问
            while(currentNode != null){
                stack.push(currentNode);
                currentNode = currentNode.left;
            }


            if(!stack.isEmpty()){
                currentNode = stack.pop();
                System.out.println(currentNode.element + " ");
                currentNode = currentNode.right;
            }
        }
    }

    public void iterativePostOrder(BinaryNode<T> node){
        BinaryNode<T> currentNode = node;
        BinaryNode<T> lastVisit = node;
        Stack<BinaryNode<T>> stack = new Stack<BinaryNode<T>>();
        while(currentNode != null || !stack.isEmpty())
        {
            while(currentNode != null)
            {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
        }

        currentNode = stack.peek();
        if(currentNode.right == null || currentNode.right == lastVisit)
        {
            System.out.print(currentNode.element + " ");
            stack.pop();
            lastVisit = currentNode;
            currentNode = null;
        }else{
            currentNode = currentNode.right;
        }
    }
}
