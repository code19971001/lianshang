package com.it._06_tree.bst;

import java.util.Comparator;

/**
 * 红黑树 : 心中要联想到B树
 *
 * @author : code1997
 * @date : 2021/3/25 20:57
 */
public class RBT<E> extends BBST<E> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBT() {
        this(null);
    }

    public RBT(Comparator<E> comparator) {
        super(comparator);

    }

    /**
     * 1.如果是根节点，染成黑色并返回
     * 2.如果父节点是黑色，不做任何处理.
     * 3.如果uncle节点是红色，将父节点和叔父节点染成黑色，祖父节点染成红色并上溢.
     * 4.如果uncle节点不是红色,根据位置进行进行合适的旋转.
     */
    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        //如果添加的节点是根节点，直接染成黑色.
        if (parent == null) {
            black(node);
            return;
        }
        if (isBlack(parent)) {
            return;
        }
        Node<E> uncle = parent.sibling();
        Node<E> grand = parent.parent;
        if (isRed(uncle)) {
            //父节点和叔父节点需要染色
            black(parent);
            black(uncle);
            //祖父节点染色并向上合并
            afterAdd(red(grand));
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

    /**
     * 1.如果删除的节点是红色的：直接删除。
     * 2.删除的节点是黑色节点：
     * 2.1.删除节点存在两个red节点：不会存在
     * 2.1.拥有一个red节点的black节点：将替代节点染黑
     * 2.2.删除黑色叶子节点.
     *
     * @node : 被删除的子节点或者替代被删除的叶子节点.
     */
    @Override
    protected void afterRemove(Node<E> node) {
        if (isRed(node)) {
            //如果是红色叶子节点，多一步染色也无所谓；如果替代节点是红色，那么必须需要染成黑色
            black(node);
            return;
        }
//        如果取代者是红色，染黑返回即可.
//        if (isRed(replacement)) {
//            black(replacement);
//            return;
//        }

        //删除是根节点
        Node<E> parent = node.parent;
        if (parent == null) {
            return;
        }
        //执行到这里的时候：sibling()实际上已经失效了.实际上我们可以反向思考,一旦执行到这里，父节点清空了哪一个，就代表是另外一边的节点，来获取兄弟节点.
        //存在不严谨的地方，因为还有可能是父节点下溢，因此需要添加一层判断node.isLeftChild
        boolean left = parent.leftChild == null || node.isLeftChild();
        Node<E> sibling = left ? parent.rightChild : parent.leftChild;
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


    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return null;
        }
        ((RedBlackNode<E>) node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }


    /**
     * 判断某个节点的颜色
     *
     * @return : default is black,
     */
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RedBlackNode<E>) node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RedBlackNode<>(element, parent);
    }

    /**
     * 对于红黑树来说存在颜色，节点的颜色只可能是BLACK或者RED
     */
    private static class RedBlackNode<E> extends Node<E> {

        /**
         * 默认为红色会是的红黑树的性质尽快满足
         */
        boolean color = RED;

        public RedBlackNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String colorStr = color == RED ? "R_" : "B_";
            return colorStr + element.toString();
        }
    }


}
