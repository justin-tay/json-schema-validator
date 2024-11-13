package com.networknt.schema.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * View of a list of lists.
 * <p>
 * This is used for performance to reduce copies .
 * 
 * @param <E> the type contains in the list
 */
public class ListView<E> implements List<E> {
    private final List<List<E>> lists = new ArrayList<>();

    /**
     * Adds a list to the view.
     * 
     * @param list to add to the view
     * @return the view
     */
    public ListView<E> union(List<E> list) {
        if (list != null && !list.isEmpty()) {
            this.lists.add(list);
        }
        return this;
    }

    @Override
    public int size() {
        int size = 0;
        for (List<E> list : lists) {
            size += list.size();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return lists.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        for (List<E> list : lists) {
            if (list.contains(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListViewIterator<E>(this);
    }

    @Override
    public Object[] toArray() {
        int size = size();
        Object[] result = new Object[size];
        Iterator<?> iterator = iterator();
        for (int x = 0; x < size; x++) {
            result[x] = iterator.hasNext() ? iterator.next() : null;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        int size = size();
        T[] result = size <= a.length ? a : (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        Iterator<?> iterator = iterator();
        for (int x = 0; x < size; x++) {
            result[x] = iterator.hasNext() ? (T) iterator.next() : null;
        }
        return result;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /**
     * Iterator.
     * 
     * @param <E> the type contains in the set
     */
    public static class ListViewIterator<E> implements ListIterator<E> {
        private final ListView<E> view;
        private int index = 0;

        public ListViewIterator(ListView<E> view) {
            this.view = view;
        }

        public ListViewIterator(ListView<E> view, int index) {
            this.view = view;
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            int size = this.view.size();
            if (size > this.index) {
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            int nextIndex = nextIndex();
            try {
                return this.view.get(nextIndex);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            } finally {
                this.index = nextIndex + 1;
            }
        }

        @Override
        public boolean hasPrevious() {
            return previousIndex() >= 0;
        }

        @Override
        public E previous() {
            int previousIndex = previousIndex();
            this.index = previousIndex;
            return this.view.get(previousIndex);
        }

        @Override
        public int nextIndex() {
            return this.index;
        }

        @Override
        public int previousIndex() {
            return this.index - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(lists);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        Collection<?> collection = (Collection<?>) obj;
        if (collection.size() != size()) {
            return false;
        }
        try {
            return containsAll(collection);
        } catch (ClassCastException ignore) {
            return false;
        } catch (NullPointerException ignore) {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        Iterator<E> iterator = iterator();
        if (iterator.hasNext()) {
            builder.append(iterator.next().toString());
        }
        while (iterator.hasNext()) {
            builder.append(", ");
            builder.append(iterator.next().toString());
        }
        builder.append(']');
        return builder.toString();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(int index) {
        int current = index;
        for (List<E> list : lists) {
            int size = list.size();
            if (size > current) {
                return list.get(current);
            } else {
                current = current - size;
            }
        }
        throw new IndexOutOfBoundsException("Index: "+index);
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListViewIterator<E>(this);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListViewIterator<E>(this, index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List<E> result = new ArrayList<>(toIndex - fromIndex);
        for (int x = fromIndex; x < toIndex; x++) {
            result.add(this.get(x));
        }
        return result;
    }
}
