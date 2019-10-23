package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    public int height() {
        return height(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        @SuppressWarnings("unchecked")
        T oInT = (T) o;
        System.out.println(oInT);
        System.out.println(height());
        if (height() == 0) return false;
        size--;

        try {
            System.out.print(root.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.println();

        try {
            System.out.print(root.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.println();

        try {
            System.out.print(root.left.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.left.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.println();

        try {
            System.out.print(root.left.left.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.left.left.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.left.right.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.left.right.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.left.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.left.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.right.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.right.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.println();

        try {
            System.out.print(root.left.left.left.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.left.left.left.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.left.left.right.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.left.left.right.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.left.right.left.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.left.right.left.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.left.right.right.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.left.right.right.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.left.left.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.left.left.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.left.right.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.left.right.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.right.left.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.right.left.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.right.right.left.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.print(" ");
        try {
            System.out.print(root.right.right.right.right.value);
        } catch (Exception e) {
            System.out.print("_");
        }

        System.out.println();

        find23(root,oInT);
        return true;
    }

    private Node<T> find23(Node<T> start,T valueOfNodeThatWillBeRemoved) {
        if (start==null) return null;
        int comparison = valueOfNodeThatWillBeRemoved.compareTo(start.value);
        if (comparison < 0) {
            start.left = find23(start.left, valueOfNodeThatWillBeRemoved);
        } else {
            if (comparison>0) {
                start.right = find23(start.right, valueOfNodeThatWillBeRemoved);
            } else {
                System.out.println("NUUU");
                if (start.left != null && start.right != null) {
                    start = minimum(start.right);
                    start.right = find23(start.right,start.value) ;
                } else {
                    if (start.left != null) {
                        start = start.left;
                    } else {
                        start = start.right;
                    }
                }
            }
        }
        return start;
    }
    private Node<T> minimum(Node<T> start) {
        if (start.left==null) return start;
        return minimum(start.left);
    }
//    private boolean find22(Node<T> start, T valueOfNodeThatWillBeRemoved) {
//        int  comparison = valueOfNodeThatWillBeRemoved.compareTo(start.value);
//        if (comparison == 0) {
//            System.out.println(start.value);
//            System.out.println(valueOfNodeThatWillBeRemoved);
//            System.out.println("ROOT IS sTRATS");
//            start = null;
//            return false;
//        } else if (comparison < 0) {
//            if (start.left.value == valueOfNodeThatWillBeRemoved) return startToRemove(start,valueOfNodeThatWillBeRemoved);
//            return find22(start.left, valueOfNodeThatWillBeRemoved);
//        } else {
//            if (start.right.value == valueOfNodeThatWillBeRemoved) return startToRemove(start,valueOfNodeThatWillBeRemoved);
//            return find22(start.right, valueOfNodeThatWillBeRemoved);
//        }
//    }
//    private boolean startToRemove(Node<T> parent, T valueOfNodeThatWillBeRemoved) {
//        if (parent.left!=null) if (parent.left.value == valueOfNodeThatWillBeRemoved) {
//            System.out.println("LEF");
//            if (parent.left.left==null && parent.left.right==null) {
//                parent.left=null;
//                System.out.println("leftDeleted,00");
//
//                return true;
//            }
//            if (parent.left.left==null) {
//               // parent.left=null;
//                parent.left=parent.left.right;
//
//                System.out.println("leftDeleted,01");
//
//                return true;
//            }
//            if (parent.left.right==null) {
//              //  parent.left=null;
//                parent.left=parent.left.left;
//
//                System.out.println("leftDeleted,10");
//
//                return true;
//            }
//            return deleteRecurse(parent,valueOfNodeThatWillBeRemoved,0);
//        }
//
//        if (parent.right!=null)if (parent.right.value == valueOfNodeThatWillBeRemoved) {
//            System.out.println("RIG");
//            if (parent.right.left==null && parent.right.right==null) {
//                parent.right=null;
//                System.out.println("rightDeleted,00");
//
//                return true;
//            }
//            if (parent.right.left==null) {
//                parent.right=parent.right.right;
//               // parent.right=null;
//                System.out.println("rightDeleted,01");
//
//                return true;
//            }
//            if (parent.right.right==null) {
//                parent.right=parent.right.left;
//               // parent.right=null;
//                System.out.println("rightDeleted,10");
//
//                return true;
//            }
//            return deleteRecurse(parent,valueOfNodeThatWillBeRemoved,1);
//        }
//
//        return true;
//    }
//    private boolean deleteRecurse(Node<T> parent, T valueOfNodeThatWillBeRemoved, int right) {
//        if (right==0) {
//            int  comparison = parent.value.compareTo(valueOfNodeThatWillBeRemoved);
//            if (comparison>0) return deleteRecurse(parent.left,valueOfNodeThatWillBeRemoved,0 );
//        }
//        if (right==1) {
//            int  comparison = parent.value.compareTo(valueOfNodeThatWillBeRemoved);
//            if (comparison<0) return deleteRecurse(parent.right,valueOfNodeThatWillBeRemoved,0 );
//        }
//        System.out.println("DDDOUBLE");
//       return true;
//    }
    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private BinaryTreeIterator() {
            // Добавьте сюда инициализацию, если она необходима
        }

        /**
         * Проверка наличия следующего элемента
         * Средняя
         */
        @Override
        public boolean hasNext() {
            // TODO
            throw new NotImplementedError();
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
        @Override
        public T next() {
            // TODO
            throw new NotImplementedError();
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
            // TODO
            throw new NotImplementedError();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        // TODO
        throw new NotImplementedError();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
