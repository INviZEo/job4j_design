package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> parVal = findBy(parent);
            if (findBy(parent).isPresent()) {
                if (findBy(child).isEmpty()) {
                    result = parVal.get().children.add(new Node<>(child));
                }
            }
        return result;
    }

    public boolean isBinary() {
        return findByPredicate(val -> val.children.size() > 2).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(val -> val.value.equals(value));
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (condition.test(element)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return result;
    }
}
