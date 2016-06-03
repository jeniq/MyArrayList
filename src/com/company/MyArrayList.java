package com.company;

import java.util.*;

/**
 * Created by Женя on 02.06.2016.
 */
public class MyArrayList<T> implements List<T>{
    private static final double CAPACITY_KOEF = 1.5;
    private static final int CAPACITY_START = 10;
    private Object[] array;
    private int capacity;
    private int size;
    private int modCount = 0;

    public MyArrayList(){
        array = new Object[CAPACITY_START];
    }

    public MyArrayList(int initialCapacity){
        if (initialCapacity >= CAPACITY_START){
            this.array = new Object[initialCapacity];
        }else if (initialCapacity > 0){
            this.array = new Object[CAPACITY_START];
        }else{
            throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
        }
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o){
        for (Object c : array){
            if (c.equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator(){
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(Object value){
        ensureCapacity(size + 1);
        array[size++] = value;
        modCount++;
        return true;
    }

    @Override
    public void add(int index, T o){
        if (index < 0 || index >=size){
            throw new IllegalArgumentException("No such index in this list. Size: " + size + ".");
        }
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = o;
        size++;
        modCount++;
    }

    @Override
    public boolean remove(Object value){
        for (int i = 0; i < array.length; i++){
            if (array[i].equals(value)){
                System.arraycopy(array, i + 1, array, i, array.length - i - 1);
                array[--size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T remove(int index){
        if (index < 0 || index >=size){
            throw new IllegalArgumentException("No such index in this list. Size: " + size + ".");
        }
        int toRemove = size - index - 1;
        T t = (T) array[index];
        if (toRemove > 0){
            System.arraycopy(array, index + 1, array, index, toRemove);
        }
        array[--size] = null;
        modCount++;
        return t;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public T get(int index){
        if (index < 0 || index >=size){
            throw new IllegalArgumentException("No such index in this list. Size: " + size + ".");
        }
        return (T) array[index];
    }

    @Override
    public T set(int index, T o){
        if (index <= 0 || index >= size){
            throw new IllegalArgumentException("No such index in this list. Size: " + size + ".");
        }
        array[index] = o;
        modCount++;
        return (T)array[index];
    }
    @Override
    public ListIterator listIterator() {
        return new ListIterator<T>() {
            int index = 0;
            int modCount = MyArrayList.this.modCount;
            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public T next() {
                return (T) array[index++];
            }

            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            @Override
            public T previous() {
                return (T) array[--index];
            }

            @Override
            public int nextIndex() {
                return index + 1;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(--index);
                modCount++;
            }

            @Override
            public void set(T o) {
                MyArrayList.this.set(index, o);
                modCount++;
            }

            @Override
            public void add(T o) {
                MyArrayList.this.add(index, o);
                modCount++;

            }

            private void checkMods(){
                if (modCount != MyArrayList.this.modCount){
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        for (Object c : array){
            buffer.append(c);
        }
        return buffer.toString();
    }

    private void ensureCapacity(int minCapacity){
        if (minCapacity - array.length > 0){
            growArray(minCapacity);
        }
    }

    private void growArray(int minCapacity){
        int oldCapacity = array.length;
        int newCapacity = (int)(oldCapacity*CAPACITY_KOEF);
        if (newCapacity - minCapacity < 0){
            newCapacity = minCapacity;
        }
        array = Arrays.copyOf(array, newCapacity);
    }

}
