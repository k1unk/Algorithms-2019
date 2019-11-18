package lesson3;

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
     *
     * Трудоемкость - O(height()), если дерево сбалансировано - то O(log(n)), если нет - то O(n)
     * Ресурсоемкость - О(1)
     */
    @Override
    public boolean remove(Object o) {
        if (height() == 0) return false;
        @SuppressWarnings("unchecked")
        T t = (T) o;
        root = remove(t, root);
        size--;
        return true;
    }

    private Node<T> remove(T number, Node<T> node) {
        if (number.compareTo(node.value) < 0) {
            node.left = remove(number, node.left);
        }
        else {
            if (number.compareTo(node.value) > 0) {
                node.right = remove(number, node.right);
            }
            else {
                if (node.left != null && node.right != null) {
                    Node<T> minimum = new Node<>(minimum(node.right).value);
                    minimum.left = node.left;
                    minimum.right = node.right;
                    node = minimum;
                    node.right = remove(node.value, node.right);
                }
                else {
                    if (node.left != null) {
                        return node.left;
                    }
                    else {
                        return node.right;
                    }
                }
            }
        }
        return node;
    }

    private Node<T> minimum(Node<T> node) {
        if (node.left == null) return node;
        else return minimum(node.left);
    }

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
        private Node<T> current = null;

        private BinaryTreeIterator() {
            // Добавьте сюда инициализацию, если она необходима
        }

        /**
         * Проверка наличия следующего элемента
         * Средняя
         *
         * Трудоемкость - O(height()), если дерево сбалансировано - то O(log(n)), если нет - то O(n)
         * Ресурсоемкость - О(1)
         */
        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        /**
         * Поиск следующего элемента
         * Средняя
         *
         * Трудоемкость - O(height()), если дерево сбалансировано - то O(log(n)), если нет - то O(n)
         * Ресурсоемкость - О(1)
         */
        @Override
        public T next() {
            current = findNext();
            if (current != null) return current.value;
            return null;
        }

        Node<T> findNext() {
            if (root == null) return null;

            Node<T> res = null;

            if (current != null) {

                Node<T> now = root;
                while (now != null) {
                    if (now.value.compareTo(current.value) > 0) {
                        res = now;
                        now = now.left;
                    } else {
                        now = now.right;
                    }
                }

            }
            else res = findFirst();

            return res;
        }

        private Node<T> findFirst() {
            Node<T> cur = root;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         *
         * Трудоемкость - O(height()), если дерево сбалансировано - то O(log(n)), если нет - то O(n)
         * Ресурсоемкость - О(1)
         */
        @Override
        public void remove() {
            root = BinaryTree.this.remove(current.value, root);
            size--;
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
     *
     * Трудоемкость - O(n)
     * Ресурсоемкость - О(n)
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return subSet2(fromElement, toElement, false);
    }

    private SortedSet<T> subSet2(T fromElement, T toElement, boolean lessOrEqual) {
        SortedSet<Comparable> set;
        SortedSet<T> set2 = new TreeSet<>();

        if (root == null) return set2;

        set = allElements(root);

        for (int i = 0; i < set.size(); i++) {
            @SuppressWarnings("unchecked")
            Comparable o = (T) set.toArray()[i];
            if (lessOrEqual) {
                if (o.compareTo(fromElement) >= 0 && o.compareTo(toElement) <= 0) set2.add((T) o);
            }
            else {
                if (o.compareTo(fromElement) >= 0 && o.compareTo(toElement) < 0) set2.add((T) o);
            }
        }

        return set2;
    }

    private SortedSet<Comparable> allElements(Node<T> node) {
        SortedSet<Comparable> set = new TreeSet<>();
        if (node.left != null) {
            SortedSet<Comparable> temp = allElements(node.left);
            for (int i = 0; i < temp.size(); i++) {
                set.add((Comparable) temp.toArray()[i]);
            }
        }
        set.add(node.value);
        if (node.right != null) {
            SortedSet<Comparable> temp = allElements(node.right);
            for (int i = 0; i < temp.size(); i++) {
                set.add((Comparable) temp.toArray()[i]);
            }
        }
        return set;
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     *
     * Трудоемкость - O(n)
     * Ресурсоемкость - О(n)
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        return subSet2(first(), toElement,false);
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     *
     * Трудоемкость - O(n)
     * Ресурсоемкость - О(n)
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return subSet2(fromElement, last(),true);
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
