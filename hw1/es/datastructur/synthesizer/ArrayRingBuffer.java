package es.datastructur.synthesizer;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;


    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[])new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (fillCount==rb.length){
            throw new RuntimeException("Ring buffer overflow");
        }

        rb[last] = x;
        last++;
        fillCount++;
        if(last==rb.length){
            last = 0;
        }
    }


    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if(fillCount<=0){
            throw new RuntimeException("Ring buffer underflow");
        }
        T toReturn = rb[first];
        first++;
        fillCount--;
        if (first==rb.length){
            first =0;
        }
        return toReturn;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if(fillCount<=0){
            throw new RuntimeException("Ring buffer underflow");
        }
        T toReturn = rb[first];
        return toReturn;
    }

    @Override
    public boolean equals(Object o) {
       if(o == null){ return false;}
       if (this.getClass() != o.getClass()){return false;}
       ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
       Iterator<T> thisIter = this.iterator();
       Iterator<T> otherIter = other.iterator();
       while(thisIter.hasNext()&& otherIter.hasNext()){
           if (thisIter.next()!=otherIter.next()){
               return false;
           }
       }
       return true;
    }



    @Override
    public Iterator<T> iterator() {
        return new ARIterator();
    }
    private class ARIterator implements Iterator<T>{
        private int wizPos;
        public ARIterator(){
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos<fillCount();
        }

        @Override
        public T next() {
            T returnItem =  rb[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
