package com.it._06_tree.bst;


import com.it._06_tree.BinaryTree;

import java.util.Comparator;
import java.util.logging.Logger;

/**
 * 前序：根节点->左子树->右子树。可以用于树状结构展示。 * 相对与线性数据结构相比，不能存在索引，二叉树存在以下的几种遍历方式.
 * 中序：左子树->根节点->右子树。对于bst树来说，中序遍历是升序或者降序排列。
 * 后序遍历：左子树->右子树->根节点。适用于一些先对子，然后对父的一些操作的时候。
 * 层序遍历：计算二叉树的高度，判断一棵树是否是完全二叉树，翻转二叉树。
 * <p>
 * 如何来限定传入的泛型参数必须具有可比较性的？
 * 1.定义泛型类的时候要求:E extends Comparable,这样灵活性比较差，对于bean来说比较规则已经去被代码类确定.
 * 2.定义一个成员变量comparator：用来接收外界传过来的比较器.
 *
 * @author : code1997
 * @date : 2021/3/15 10:48
 */
public class BST<E> extends BinaryTree<E> {

    private final Logger logger = Logger.getLogger("logger");

    /**
     * 比较器
     */
    private Comparator<E> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 比较器的设定：
     * 正数：e1>e2
     * 0：e1==e2
     * 负数：e1<e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        //要求实现类必须实现Comparable接口，否则强转会报错
        return ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * 规定：
     * 1.不允许存储null值.
     * 2.遇到值相等的时候如何处理？
     * 2.1.值覆盖. √
     * 2.2直接return.
     */
    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            //add first node
            root = createNode(element, null);
            size++;
            //新添加结点之后的操作
            afterAdd(root);
            return;
        }
        //如果添加的不是根节点，需要先找到需要找到添加节点的父节点.
        Node<E> parent = null;
        Node<E> node = root;
        //初始化比较的值
        int compare = 0;
        while (node != null) {
            compare = compare(element, node.element);
            //注意parent指针赋值的位置.
            parent = node;
            if (compare > 0) {
                node = node.rightChild;
            } else if (compare < 0) {
                node = node.leftChild;
            } else {
                //如果值相等就覆盖
                node.element = element;
                return;
            }
        }
        //当前元素在节点中没有找到，找到了待添加元素的父节点.
        Node<E> newNode = createNode(element, parent);
        //这里不可能出现相等的情况.
        if (compare > 0) {
            parent.rightChild = newNode;
        } else {
            parent.leftChild = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    /**
     * 对于其他的树可能在添加之后需要进行平衡的操作，例如AVL树.
     */
    protected void afterAdd(Node<E> node) {
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }


    public void remove(E element) {
        remove(node(element));
    }

    /**
     * 根据节点，删除节点
     * 1.叶子节点
     * 直接删除即可，特殊的只有根节点也是叶子节点.
     * 2.度为1的节点
     * 如果要被删除的节点是左子节点，那么node.parent.left=child,child.parent=node.parent
     * 如果要被删除的节点是右子节点，那么node.parent.right=child,child.parent=node.parent
     * 如果node是根节点，那么root = child , child.parent = null
     * 3.度为2的节点--前驱或者后继节点覆盖，然后删除前驱或者后继节点.
     * <p>
     * 注：如果一个节点的度为2，那么他的前驱，后继节点的度只可能是0或者1.因此我们对度为2的节点进行处理.
     * <p>
     * 实际上被删除的度为1或者0的前驱或者后继节点.
     */
    private void remove(Node<E> node) {
        if (node == null) {
            //表明没有找到
            logger.info("can`t find this element, so don`t need to remove");
            return;
        }
        if (node.hasTwoChildren()) {
            //该节点的度为2,找后继节点，覆盖度我们要删除的节点
            Node<E> successor = successor(node);
            node.element = successor.element;
            //如果来到了这里，我们需要删除的节点不再是原来的节点，而是后继节点.
            node = successor;
        }
        //待删除节点的度必定为0或者1
        Node<E> replacement = node.leftChild != null ? node.leftChild : node.rightChild;
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
    }

    protected void afterRemove(Node<E> node) {
    }

    /**
     * 根据元素的值找到对应的node节点
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
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


    public boolean contains(E element) {
        return node(element) != null;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must be not null!");
        }
    }


    /**
     * 前序遍历实现树节点的遍历输出
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        toString(root, builder, "");
        return builder.toString();
    }

    /**
     * 实际上是树的前序遍历.
     */
    private void toString(Node<E> node, StringBuilder builder, String prefix) {
        if (node == null) {
            return;
        }
        builder.append(prefix).append(node.element).append("\n");
        toString(node.leftChild, builder, prefix + "[L]");
        toString(node.rightChild, builder, prefix + "[R]");

    }
}
