package co.edu.uptc.structures;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryTreeEquilibrate<T> {
    private NodeDouble<T> root;
    private Comparator<T> comparator;


    public BinaryTreeEquilibrate(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    /*public void insert(T value) {
        NodeDouble<T> node = new NodeDouble<T>(value);
        if (isEmpty()) {
            root = node;
        } else {
            NodeDouble<T> aux = root;
            NodeDouble<T> father = null;
            while (aux != null) {

                father = aux;
                if (comparator.compare(value, aux.getData()) > 0) {
                    aux = aux.getRight();
                } else {
                    aux = aux.getLeft();
                }
            }
            if (comparator.compare(value, father.getData()) > 0) {
                father.setRight(node);
            } else {
                father.setLeft(node);
            }
        }
    }*/

    public T searchData(T value) {
        return findData(root, value);
    }

    public T findData(NodeDouble<T> current, T value) {
        NodeDouble<T> nodeFound = new NodeDouble<T>(null);
        T dataFound = nodeFound.getData();
        if (current == null) {
            dataFound = null;
        }

        if (comparator.compare(value, current.getData()) == 0) {
            dataFound = current.getData();
        } else {
            if (comparator.compare(value, current.getData()) > 0) {
                dataFound = findData(current.getRight(), value);
            } else {
                dataFound = findData(current.getLeft(), value);
            }
        }
        return dataFound;
    }

    public void remove(T value) throws Exception {
        this.root = remove(this.root, value);
    }

    public NodeDouble<T> remove(NodeDouble<T> current, T value) {

        if (current == null) {
            throw new RuntimeException("Element not found");
        }

        if (comparator.compare(value, current.getData()) > 0) {
            current.setRight(remove(current.getRight(), value));
        } else if (comparator.compare(value, current.getData()) < 0) {
            current.setLeft(remove(current.getLeft(), value));
        } else {
            if (current.getLeft() == null) {
                return current.getRight();
            } else if (current.getRight() == null) {
                return current.getLeft();
            } else {
                NodeDouble<T> aux = remplace(current);
                if (current.equals(root)) {
                    root = aux;
                }
            }
        }
        return current;
    }

    private NodeDouble<T> remplace(NodeDouble<T> current) {
        NodeDouble<T> father = current;
        NodeDouble<T> aux = current.getLeft();
        while (aux.getRight() != null) {
            father = aux;
            aux = aux.getRight();
        }
        current.setData(aux.getData());
        if (father.equals(current)) {
            father.setLeft(aux.getLeft());
        } else {
            father.setRight(aux.getLeft());
        }
        return aux;
    }

    public List<T> inOrder() {
        List<T> list = new ArrayList<T>();
        return inOrder(this.root, list);
    }

    private List<T> inOrder(NodeDouble<T> node, List<T> list) {
        if (node != null) {
            inOrder(node.getLeft(), list);
            list.add(node.getData());
            inOrder(node.getRight(), list);
        }
        return list;
    }


    public List<T> preOrder() {
        List<T> list = new ArrayList<T>();
        return preOrder(this.root, list);
    }

    public List<T> preOrder(NodeDouble<T> node, List<T> list) {

        if (node != null) {
            list.add(node.getData());
            preOrder(node.getLeft(), list);
            preOrder(node.getRight(), list);
        }
        return list;
    }

    public List<T> postOrder() {
        List<T> list = new ArrayList<T>();
        return postOrder(this.root, list);
    }

    public List<T> postOrder(NodeDouble<T> node, List<T> list) {
        if (node != null) {
            postOrder(node.getLeft(), list);
            postOrder(node.getRight(), list);
            list.add(node.getData());
        }
        return list;
    }

    public void insert(T value) {
        insert(this.root, value);
    }

    public void insert(NodeDouble<T> node, T value) {
        NodeDouble<T> nodeNew = new NodeDouble<T>(value);
        if (isEmpty()) {
            this.root = nodeNew;
        } else {
            if (comparator.compare(value, node.getData()) > 0) {
                if (node.getRight()==null){
                    node.setRight(nodeNew);
                    node.setFactorEquilibrium(node.getFactorEquilibrium()+1);
                    searchFather(node).setFactorEquilibrium(searchFather(node).getFactorEquilibrium()+1);
                    searchFather(node).setRight(equilibrate(node));//linker
                    System.out.printf(searchFather(node).getData()+"\n");
                    if (this.root.getFactorEquilibrium()==2) {
                        System.out.printf("heree23" + searchFather(this.root).getData() + "\n");
                        this.root=equilibrate(this.root);
                    }
                }else {
                    insert(node.getRight(), value);
                }
            } else {
                if (comparator.compare(value, node.getData()) < 0) {
                    if (node.getLeft() == null) {
                        node.setLeft(nodeNew);
                        node.setFactorEquilibrium(node.getFactorEquilibrium() - 1);
                        searchFather(node).setFactorEquilibrium(searchFather(node).getFactorEquilibrium()-1);
                        searchFather(node).setLeft(equilibrate(node));
                        System.out.printf("heree" + searchFather(node).getData() + "\n");
                        if (this.root.getFactorEquilibrium() == -2) {
                            System.out.printf("heree23" + searchFather(this.root).getData() + "\n");
                            this.root = equilibrate(this.root);
                        }
                    } else {
                        insert(node.getLeft(), value);
                    }
                } else {
                    throw new RuntimeException("Element already exists");
                }
            }
        }

       // return node;//recuperar la hoja, antes de la inserci�n.
    }
    public NodeDouble<T> searchFather(NodeDouble<T> nodeChild){
        return searchFather(this.root, nodeChild);
    }

    public NodeDouble<T> searchFather(NodeDouble<T> node, NodeDouble<T> nodeChild) {
        NodeDouble<T> nodeFather = new NodeDouble<T>(null);
        if (node != null) {
            if (node.getLeft() != null && node.getLeft().getData().equals(nodeChild.getData())) {
                nodeFather = node;
                nodeFather.setLeft(null);
            } else if (node.getRight() != null && node.getRight().getData().equals(nodeChild.getData())) {
                nodeFather = node;
                nodeFather.setRight(null);
            } else {
                if (comparator.compare(nodeChild.getData(), node.getData()) > 0) {
                    nodeFather = searchFather(node.getRight(), nodeChild);
                } else {
                    nodeFather = searchFather(node.getLeft(), nodeChild);
                }
            }
        }
        return nodeFather;
    }

    private NodeDouble<T> equilibrate(NodeDouble<T> node) {
        NodeDouble<T> rootSubTree = node;
        if(node != null){
            if(node.getFactorEquilibrium() == 2){
                if(node.getRight().getFactorEquilibrium() == 1){
                   rootSubTree=rotationSimpleDD(node, node.getRight());
                }else{
                    rootSubTree=rotationDoubleID(node, node.getRight());
                }
            }else if(node.getFactorEquilibrium() == -2){
                if(node.getLeft().getFactorEquilibrium() == -1){
                    rootSubTree=rotationSimpleII(node, node.getLeft());
                }else{
                    rootSubTree=rotationDoubleDI(node, node.getLeft());
                }
            }
        }
        return rootSubTree;
    }

    public NodeDouble<T> rotationSimpleII(NodeDouble<T> nodeProblem, NodeDouble<T> nodeReference) {
        nodeProblem.setLeft(nodeReference.getRight());
        nodeReference.setRight(nodeProblem);
        nodeProblem = nodeReference;
        nodeProblem.setFactorEquilibrium(nodeProblem.getFactorEquilibrium()+2);
        nodeReference.setFactorEquilibrium(nodeReference.getFactorEquilibrium()+1);
        return nodeProblem;
    }

    public NodeDouble<T> rotationSimpleDD(NodeDouble<T> nodeProblem, NodeDouble<T> nodeReference) {
        nodeProblem.setRight(nodeReference.getLeft());
        nodeReference.setLeft(nodeProblem);
        nodeProblem = nodeReference;
        nodeProblem.setFactorEquilibrium(nodeProblem.getFactorEquilibrium()-2);
        nodeReference.setFactorEquilibrium(nodeReference.getFactorEquilibrium()-1);
        return nodeProblem;
    }

    public NodeDouble<T> rotationDoubleID(NodeDouble<T> nodeProblem, NodeDouble<T> nodeReference) {
        NodeDouble<T> nodeDescendant = nodeReference.getLeft();
        nodeProblem.setRight(nodeDescendant.getLeft());
        nodeDescendant.setLeft(nodeProblem);
        nodeReference.setLeft(nodeDescendant.getRight());
        nodeDescendant.setRight(nodeReference);
        nodeProblem = nodeDescendant;
        nodeProblem.setFactorEquilibrium(nodeProblem.getFactorEquilibrium()-2);
        nodeReference.setFactorEquilibrium(nodeReference.getFactorEquilibrium()+1);
        return nodeProblem;
    }

    public NodeDouble<T> rotationDoubleDI(NodeDouble<T> nodeProblem, NodeDouble<T> nodeReference) {
        NodeDouble<T> nodeDescendant = nodeReference.getRight();
        System.out.printf(nodeProblem.getData() +"____\n" +nodeProblem.getFactorEquilibrium());
        System.out.printf("\n"+nodeReference.getData() +"sdfdsf\n");
        nodeProblem.setLeft(nodeDescendant.getRight());

        nodeDescendant.setRight(nodeProblem);
        nodeReference.setRight(nodeDescendant.getLeft());
        nodeDescendant.setLeft(nodeReference);
        nodeProblem = nodeDescendant;
        nodeProblem.setFactorEquilibrium(nodeProblem.getFactorEquilibrium()+2);
        nodeReference.setFactorEquilibrium(nodeReference.getFactorEquilibrium()-1);
        return nodeProblem;
    }


}