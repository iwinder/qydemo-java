package Others.Algorithm.search;

import Utills.PrintUtill;

public class BST <Key extends Comparable<Key>, Value>{
    private Node root;

    private class Node {
        private Key key;   // 键
        private Value val; // 值
        private Node left, rgiht; // 指向子书的链接
        private int N; //以该节点为根的子树中的节点总数。

        public Node(Key key, Value value, int N){
            this.key = key;
            this.val = value;
            this.N = N;
        }
    }

    public int size(){
        return size(root);
    }
    private int size(Node x){
         return x==null ? 0 : x.N;
    }

    /**
     *  查找-递归
     * @param key
     * @return
     */
    public Value get(Key key){
        return get(root, key);
    }
    private Value get(Node x, Key key){
        if (x==null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            return get(x.left, key);
        }else if (cmp >0){
            return get(x.rgiht, key);
        }else {
            return x.val;
        }
    }

    /**
     * 查找--非递归
     * @param x
     * @param key
     * @return
     */
    private Value iterativeGet(Node x, Key key){
        int cmp;
        while (x !=null){
            cmp = key.compareTo(x.key);
            if (cmp < 0 ) {
                x = x.left;
            }else if (cmp > 0){
                x = x.rgiht;
            } else {
                return x.val;
            }
        }
        return null;
    }
    public Value iterativeGet(Key key){
        return iterativeGet(root, key);
    }

    /**
     * 插入
     * @param key
     * @param val
     */
    public void put(Key key, Value val){
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value value){
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp<0){
            x.left = put(x.left, key, value);
        }else if (cmp >0){
            x.rgiht = put(x.rgiht, key, value);
        }else {
            x.val = value;
        }
        x.N = size(x.left) + size(x.rgiht) + 1;
        return x;
    }

    /**
     * 获取最小key
     * @return
     */
    public Key min(){
        return min(root).key;
    }

    private Node min(Node x){
        if (x.left == null) return x;
        return min(x.left);
    }

    /**
     * 向下取整
     *
     * 若给定的键key小于二叉查找树的根节点的键，那么小于等于key的最大键floor(key)d一定在根节点的左子树中；
     * 若给定的键key大于二叉查找树的根节点的键，那么只有当根节点右子树中存在小于等于键key的节点时，小于等于key的最大值才会出现在右子树中，否则根节点就是小于等于key的最大键。
     * @return
     */
    public Key floor(Key key){
        return floor(root, key).key;
    }

    private Node floor(Node x, Key key){
        if (x ==null ) return null;
        int  cmp = key.compareTo(x.key);
        if (cmp == 0){
            return x;
        }else if (cmp <0){
            return floor(x.left, key);
        }
        Node t = floor(x.rgiht, key);
        return t != null? t : x;

    }


    /**
     * 获取最大key
     * @return
     */
    public Key max(){
        return max(root).key;
    }

    private Node max(Node x){
        if (x.rgiht == null) return x;
        return max(x.rgiht);
    }

    /**
     * 获取排名为key的Key值
     * @param key
     * @return
     */
    public Key select(int key){
        return select(root, key).key;
    }

    private Node select(Node x, int k){
        if (x == null) return null;
        int t = size(x.left);
        if (t >k){
            return select(x.left, k);
        }else if (t < k){
            return select(x.rgiht, k-t-1);
        }else {
            return x;
        }
    }

    /**
     * 获取key的排名
     * @param key
     * @return
     */
    public int rank(Key key){
        return rank(root, key);
    }

    private int rank(Node x, Key key){
        if (x == null)return 0;
        int cmp = key.compareTo(x.key);
        if (cmp <0) {
            return rank(x.left, key);
        }else if (cmp > 0 ){
            return 1+size(x.left) + rank(x.rgiht, key);
        }else {
            return size(x.left);
        }
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node x){
        if (x == null) return x.rgiht;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.rgiht) + 1;
        return x;
    }

    /**
     * 删除-根据key
     * @param key
     */
    public void delete(Key key){
        root = delete(root, key);
    }

    private Node delete(Node x, Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            x.left = delete(x.left, key);
        } else if (cmp >0){
            x.rgiht = delete(x.rgiht, key);
        }else {
            if (x.rgiht == null) return x.left;
            if (x.left == null) return x.rgiht;
            Node t = x;
            x = min(t.rgiht);
            x.rgiht = deleteMin(t.rgiht);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.rgiht) + 1;
        return x;
    }

    /**
     * 前序遍历
     * @param x
     */
    public void preOrder(Node x){
        if (x == null) return;
        PrintUtill.println(x.key);
        preOrder(x.left);
        preOrder(x.rgiht);
    }

    /**
     * 中序遍历
     * @param x
     */
    public void inOrder(Node x){
        if (x == null) return;
        inOrder(x.left);
        PrintUtill.println(x.key);
        inOrder(x.rgiht);
    }

    public void postOrder(Node x){
        if (x == null) return;
        preOrder(x.left);
        preOrder(x.rgiht);
        PrintUtill.println(x.key);
    }




}
