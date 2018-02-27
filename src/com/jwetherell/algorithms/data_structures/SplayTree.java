package com.jwetherell.algorithms.data_structures;

/**
 * A splay tree is a self-adjusting binary search tree (BST) with the additional
 * property that recently accessed elements are quick to access again.
 * <p>
 *  @see <a href="https://en.wikipedia.org/wiki/Splay_tree">Splay Tree (Wikipedia)</a>
 * <br>
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class SplayTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Node<T> addValue(T id) {
        Node<T> nodeToReturn = super.addValue(id);
        Node<T> nodeAdded = nodeToReturn;
        if (nodeAdded != null) {
            // Splay the new node to the root position
            while (nodeAdded.parent != null) {
                this.splay(nodeAdded);
            }
        }
        return nodeToReturn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Node<T> removeValue(T value) {
        Node<T> nodeToRemove = super.removeValue(value);
        if (nodeToRemove != null && nodeToRemove.parent != null) {
            Node<T> nodeParent = nodeToRemove.parent;
            // Splay the parent node to the root position
            while (nodeParent.parent != null) {
                this.splay(nodeParent);
            }
        }
        return nodeToRemove;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T value) {
        Node<T> node = getNode(value);
        if (node != null) {
            // Splay the new node to the root position
            while (node.parent != null) {
                this.splay(node);
            }
            return true;
        }
        return false;
    }

    /**
     * Splay the tree at the node.
     * 
     * @param node
     *            to splay at.
     */
    private void splay(Node<T> node) {
        Node<T> parent = node.parent;
        Node<T> grandParent = (parent != null) ? parent.parent : null;

        if(!(parent != null)) {
            checkCond(0);
        }
        if (parent != null && parent == root) {
            checkCond(1);
            grandParent = parent.parent;
            // Zig step
            root = node;
            node.parent = null;

            if (parent!=null) {
                checkCond(3);
                if (node == parent.lesser) {
                    checkCond(5);
                    parent.lesser = node.greater;
                    if (node.greater != null) {
                        checkCond(7);
                        node.greater.parent = parent;
                    }
                    else {
                        checkCond(8);
                    }

                    node.greater = parent;
                    parent.parent = node;
                } else {
                    checkCond(6);
                    parent.greater = node.lesser;
                    if (node.lesser != null) {
                        checkCond(9);
                        node.lesser.parent = parent;
                    }
                    else {
                        checkCond(10);
                    }

                    node.lesser = parent;
                    parent.parent = node;
                }
            }
            else{
                checkCond(4);
            }
            return;
        }
        else{
            checkCond(2);
        }

        if (parent != null && grandParent != null) {
            checkCond(11);
            Node<T> greatGrandParent = grandParent.parent;
            if (greatGrandParent != null && greatGrandParent.lesser == grandParent) {
                checkCond(13);
                greatGrandParent.lesser = node;
                node.parent = greatGrandParent;
            } else if (greatGrandParent != null && greatGrandParent.greater == grandParent) {
                checkCond(14);
                greatGrandParent.greater = node;
                node.parent = greatGrandParent;
            } else {
                checkCond(15);
                // I am now root!
                root = node;
                node.parent = null;
            }

            if ((node == parent.lesser && parent == grandParent.lesser)
                || (node == parent.greater && parent == grandParent.greater)) {
                checkCond(16);
                // Zig-zig step
                if (node == parent.lesser) {
                    checkCond(18);
                    Node<T> nodeGreater = node.greater;
                    node.greater = parent;
                    parent.parent = node;

                    parent.lesser = nodeGreater;
                    if (nodeGreater != null) {
                        checkCond(20);
                        nodeGreater.parent = parent;
                    }
                    else {
                        checkCond(21);
                    }

                    Node<T> parentGreater = parent.greater;
                    parent.greater = grandParent;
                    grandParent.parent = parent;

                    grandParent.lesser = parentGreater;
                    if (parentGreater != null){
                        checkCond(22);
                        parentGreater.parent = grandParent;
                    }
                    else {
                        checkCond(23);
                    }
                } else {
                    checkCond(19);
                    Node<T> nodeLesser = node.lesser;
                    node.lesser = parent;
                    parent.parent = node;

                    parent.greater = nodeLesser;
                    if (nodeLesser != null){
                        checkCond(24);
                        nodeLesser.parent = parent;
                    }
                    else {
                        checkCond(25);
                    }

                    Node<T> parentLesser = parent.lesser;
                    parent.lesser = grandParent;
                    grandParent.parent = parent;

                    grandParent.greater = parentLesser;
                    if (parentLesser != null){
                        checkCond(26);
                        parentLesser.parent = grandParent;
                    }
                    else {
                        checkCond(27);
                    }
                }
                return;
            }
            else {
                checkCond(17);
            }

            // Zig-zag step
            if (node == parent.lesser) {
                checkCond(28);
                Node<T> nodeLesser = node.greater;
                Node<T> nodeGreater = node.lesser;

                node.greater = parent;
                parent.parent = node;

                node.lesser = grandParent;
                grandParent.parent = node;

                parent.lesser = nodeLesser;
                if (nodeLesser != null){
                    checkCond(30);
                    nodeLesser.parent = parent;
                }
                else {
                    checkCond(31);
                }

                grandParent.greater = nodeGreater;
                if (nodeGreater != null){
                    checkCond(32);
                    nodeGreater.parent = grandParent;
                }
                else {
                    checkCond(33);
                }
                return;
            }
            else {
                checkCond(29);
            }

            Node<T> nodeLesser = node.lesser;
            Node<T> nodeGreater = node.greater;

            node.lesser = parent;
            parent.parent = node;

            node.greater = grandParent;
            grandParent.parent = node;

            parent.greater = nodeLesser;
            if (nodeLesser != null){
                checkCond(34);
                nodeLesser.parent = parent;
            }
            else {
                checkCond(35);
            }

            grandParent.lesser = nodeGreater;
            if (nodeGreater != null){
                checkCond(36);
                nodeGreater.parent = grandParent;
            }
            else {
                checkCond(37);
            }
        }
        checkCond(12);
    }

    private static boolean[] combinedConds = new boolean[38];

    private void checkCond (int index) {
        if (!combinedConds[index]) {
            combinedConds[index] = true;
            System.out.printf("[combined()] Branch id %d taken%n", index);
        }
    }

    public void printChecks(){
        System.out.println("These branches were not taken: ");
        for (int i = 0; i < combinedConds.length; i++) {
            if (!combinedConds[i])
                System.out.println(" "+i);
        }
    }
}
