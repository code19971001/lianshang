package com.it._08_map;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 使用红黑树实现treeMap
 * 1.封装一个entry类来封装k,v，持有RBT
 * 2.直接将TreeMap当成一个红黑树来实现
 * <p>
 * 如果将value值固定下来，实际上就是set的实现.
 *
 * @author : code1997
 * @date : 2021/11/22 20:57
 */
@SuppressWarnings({"unchecked", "unused"})
public class TreeMap<K, V> implements IMap<K, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private Node<K, V> root;
    private int size;

    private Comparator<K> comparator;

    public TreeMap() {
        this(null);
    }

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    private void elementNotNullCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must be not null!");
        }
    }

    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        return new Node<>(key, value, parent);
    }

    /**
     * 要求key必须具备可比较性
     */
    private int compare(K e1, K e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        //要求实现类必须实现Comparable接口，否则强转会报错
        return ((Comparable<K>) e1).compareTo(e2);
    }

    private void afterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;
        //如果添加的节点是根节点，直接染成黑色.
        if (parent == null) {
            black(node);
            return;
        }
        if (isBlack(parent)) {
            return;
        }
        Node<K, V> uncle = parent.sibling();
        Node<K, V> grand = parent.parent;
        if (isRed(uncle)) {
            //父节点和叔父节点需要染色
            black(parent);
            black(uncle);
            //祖父节点染色并向上合并
            afterPut(red(grand));
            return;
        }
        //叔父节点不是红色，可以使用avl树的旋转
        red(grand);
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                black(parent);
            } else {
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            if (node.isLeftChild()) {
                black(node);
                rotateRight(parent);
            } else {
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    public V put(K key, V value) {
        elementNotNullCheck(key);
        if (root == null) {
            //add first node
            root = createNode(key, value, null);
            size++;
            //新添加结点之后的操作
            afterPut(root);
            return value;
        }
        //如果添加的不是根节点，需要先找到需要找到添加节点的父节点.
        Node<K, V> parent = null;
        Node<K, V> node = root;
        //初始化比较的值
        int compare = 0;
        while (node != null) {
            compare = compare(key, node.key);
            //注意parent指针赋值的位置.
            parent = node;
            if (compare > 0) {
                node = node.rightChild;
            } else if (compare < 0) {
                node = node.leftChild;
            } else {
                //如果key相等就值覆盖
                node.value = value;
                return value;
            }
        }
        //当前元素在节点中没有找到，找到了待添加元素的父节点.
        Node<K, V> newNode = createNode(key, value, parent);
        //这里不可能出现相等的情况.
        if (compare > 0) {
            parent.rightChild = newNode;
        } else {
            parent.leftChild = newNode;
        }
        size++;
        afterPut(newNode);
        return value;
    }

    private Node<K, V> node(K key) {
        Node<K, V> node = root;
        while (node != null) {
            int cmp = compare(key, node.key);
            if (cmp == 0) {
                return node;
            } else if (cmp > 0) {
                node = node.rightChild;
            } else {
                node = node.leftChild;
            }
        }
        //没有找到
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node == null ? null : node.value;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }


    protected Node<K, V> successor(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        if (node.rightChild != null) {
            //如果存在右子节点，那么后继节点肯定是当前节点的右子节点的最左子节点
            Node<K, V> tempNode = node.rightChild;
            while (tempNode.leftChild != null) {
                tempNode = tempNode.leftChild;
            }
            return tempNode;
        } else {
            while (node.parent != null && node == node.parent.rightChild) {
                node = node.parent;
            }
            //node.parent==null.
            //node节点==node.parent.leftChild
            return node.parent;
        }
    }

    private V remove(Node<K, V> node) {
        if (node == null) {
            //表明没有找到
            System.out.println("can`t find this element, so don`t need to remove");
            return null;
        }
        V oldValue = node.value;
        if (node.hasTwoChildren()) {
            //该节点的度为2,找后继节点，覆盖度我们要删除的节点
            Node<K, V> successor = successor(node);
            node.key = successor.key;
            node.value = successor.value;
            //如果来到了这里，我们需要删除的节点不再是原来的节点，而是后继节点.
            node = successor;
        }
        //待删除节点的度必定为0或者1
        Node<K, V> replacement = node.leftChild != null ? node.leftChild : node.rightChild;
        if (replacement != null) {
            //删除节点度为1
            //更改父节点
            replacement.parent = node.parent;
            //对根节点的判断
            if (node.parent == null) {
                //删除了根节点.
                root = replacement;
            } else if (node == node.parent.leftChild) {
                node.parent.leftChild = replacement;
            } else {
                node.parent.rightChild = replacement;
            }
            //恢复平衡需要等待节点确定已经被删除.如果删除是度为1的节点，传递参数为:replacement,对avl树没有任何影响
            afterRemove(replacement);
        } else if (node.parent == null) {
            //只有一个根节点
            root = null;
            afterRemove(node);
        } else {
            //是叶子节点但不是根节点
            if (node.parent.leftChild == node) {
                node.parent.leftChild = null;
            } else {
                node.parent.rightChild = null;
            }
            afterRemove(node);
        }
        size--;
        return oldValue;
    }


    /**
     * 1.如果删除的节点是红色的：直接删除。
     * 2.删除的节点是黑色节点：
     * 2.1.删除节点存在两个red节点：不会存在
     * 2.1.拥有一个red节点的black节点：将替代节点染黑
     * 2.2.删除黑色叶子节点.
     *
     * @node : 被删除的子节点或者替代被删除的叶子节点.
     */
    protected void afterRemove(Node<K, V> node) {
        if (isRed(node)) {
            //如果是红色叶子节点，多一步染色也无所谓；如果替代节点是红色，那么必须需要染成黑色
            black(node);
            return;
        }
        //删除是根节点
        Node<K, V> parent = node.parent;
        if (parent == null) {
            return;
        }
        //执行到这里的时候：sibling()实际上已经失效了.实际上我们可以反向思考,一旦执行到这里，父节点清空了哪一个，就代表是另外一边的节点，来获取兄弟节点.
        //存在不严谨的地方，因为还有可能是父节点下溢，因此需要添加一层判断node.isLeftChild
        boolean left = parent.leftChild == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.rightChild : parent.leftChild;
        //左右是对称的.
        if (left) {
            //删除节点在右边，兄弟节点在左边
            if (isRed(sibling)) {
                //兄弟节点是红色，将兄弟节点转化为黑色进行处理
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //更换兄弟
                sibling = parent.rightChild;
            }
            //来到这里说明：兄弟节点必定为黑色
            if (isBlack(sibling.leftChild) && isBlack(sibling.rightChild)) {
                //兄弟节点无法借节点给我，父节点向下合并:实际上将父节点染黑，兄弟节点染红.
                boolean parentIsBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentIsBlack) {
                    //递归调用
                    afterRemove(parent);
                }
            } else {
                //兄弟节点至少有一个红节点可以借给我
                if (isBlack(sibling.rightChild)) {
                    //如果左孩子为黑色，对兄弟节点进行左旋转，将其转换为ll的情况
                    rotateRight(sibling);
                    //如果进行了左旋转，需要更新sibling
                    sibling = parent.rightChild;
                }
                //旋转之后的中心节点(兄弟节点)的颜色应该跟随之前parent的节点，防止parent指针转换，先染色再旋转
                color(sibling, colorOf(parent));
                black(sibling.rightChild);
                black(parent);
                //ll的情况下对parent进行右旋转即可.
                rotateLeft(parent);
            }
        } else {
            //删除节点在右边，兄弟节点在左边
            if (isRed(sibling)) {
                //兄弟节点是红色，将兄弟节点转化为黑色进行处理
                black(sibling);
                red(parent);
                rotateRight(parent);
                //更换兄弟
                sibling = parent.leftChild;
            }
            //来到这里说明：兄弟节点必定为黑色
            if (isBlack(sibling.leftChild) && isBlack(sibling.rightChild)) {
                //兄弟节点无法借节点给我，父节点向下合并:实际上将父节点染黑，兄弟节点染红.
                boolean parentIsBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentIsBlack) {
                    //递归调用
                    afterRemove(parent);
                }
            } else {
                //兄弟节点至少有一个红节点可以借给我
                if (isBlack(sibling.leftChild)) {
                    //如果左孩子为黑色，对兄弟节点进行左旋转，将其转换为ll的情况
                    rotateLeft(sibling);
                    //如果进行了左旋转，需要更新sibling
                    sibling = parent.leftChild;
                }
                //旋转之后的中心节点(兄弟节点)的颜色应该跟随之前parent的节点，防止parent指针转换，先染色再旋转
                color(sibling, colorOf(parent));
                black(sibling.leftChild);
                black(parent);
                //ll的情况下对parent进行右旋转即可.
                rotateRight(parent);
            }

        }

    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    /**
     * 只能逐个遍历，然后进行判断,这里采用层序遍历的方式
     */
    @Override
    public boolean containsValue(V value) {
        if (root == null) {
            return false;
        }
        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<K, V> tmpNode = queue.poll();
            //实际上这个方式相对于null单独处理来说，代码量偏少，效率会差一些(多了一次判断)，java官方是分开来写的。
            if (valEquals(tmpNode.value, value)) {
                return true;
            }
            if (tmpNode.leftChild != null) {
                queue.offer(tmpNode.leftChild);
            }
            if (tmpNode.rightChild != null) {
                queue.offer(tmpNode.rightChild);
            }
        }
        return false;
    }

    private boolean valEquals(V v1, V v2) {
        return Objects.equals(v1, v2);
    }

    /**
     * 中序遍历
     */

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) {
            return;
        }
        traversal(root, visitor);
    }

    private void traversal(Node<K, V> node, Visitor<K, V> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        traversal(node.leftChild, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.visit(node.key, node.value);
        traversal(node.rightChild, visitor);
    }


    /**
     * 旋转，更新节点的parent以及高度节点高度.
     */
    protected void rotateLeft(Node<K, V> grand) {
        //注意根据图进行理解，更新节点的指向
        Node<K, V> parent = grand.rightChild;
        Node<K, V> child = parent.leftChild;
        grand.rightChild = parent.leftChild;
        parent.leftChild = grand;
        //旋转之后一定要更新节点之间关系.
        afterRotate(grand, parent, child);
    }

    protected void rotateRight(Node<K, V> grand) {
        //获取需要更改的节点
        Node<K, V> parent = grand.leftChild;
        Node<K, V> child = parent.rightChild;
        //更改grand和parent的指针
        grand.leftChild = parent.rightChild;
        parent.rightChild = grand;
        afterRotate(grand, parent, child);

    }

    protected void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
        //使得parent成为根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.leftChild = parent;
        } else if (grand.isRightChild()) {
            grand.parent.rightChild = parent;
        } else {
            //grand是根节点
            root = parent;
        }
        //更新child的父节点
        if (child != null) {
            child.parent = grand;
        }
        //更新grand的父节点
        grand.parent = parent;
        //更新grand以及parent节点的高度，因为我们已经进行了旋转，所以需要先更新grand，然后更新parent.
    }

    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) {
            return null;
        }
        node.color = color;
        return node;
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    /**
     * 判断某个节点的颜色
     *
     * @return : default is black,
     */
    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    private static class Node<K, V> {
        K key;
        V value;
        boolean color = RED;
        public Node<K, V> leftChild;
        public Node<K, V> rightChild;
        public Node<K, V> parent;

        public boolean isLeftChild() {
            return parent != null && this == parent.leftChild;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.rightChild;
        }

        /**
         * @return : sibling node
         */
        public Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.rightChild;
            }
            if (isRightChild()) {
                return parent.leftChild;
            }
            //如果不是左/右节点，是叶子节点，所以肯定没有兄弟节点。
            return null;
        }


        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = "[ key = " + parent.key + ", value = " + parent.value.toString() + " ]";
            }
            return "[ key = " + key.toString() + ", value = " + value.toString() + " ]" + "_P(" + parentString + ")";
        }

        public boolean isLeaf() {
            return this.leftChild == null && this.rightChild == null;
        }

        public boolean hasTwoChildren() {
            return this.leftChild != null && this.rightChild != null;
        }

    }
}
