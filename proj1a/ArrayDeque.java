public class ArrayDeque<T> {
    T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int capacity;
    private static int eFactor = 2;
    private static int cFactor = 2;
    private static double ratio = 0.25;

    public ArrayDeque(){
        size = 0;
        nextFirst = 7;
        nextLast = 0;
        capacity = 8;
        items = (T[])new Object[capacity];
    }

    public int onePlus(int index){
        if(index == capacity-1){
            return 0;
        }
        return index+1;
    }

    public int oneMinus(int index){
        if(index == 0){
            return capacity-1;
        }
        return index-1;
    }
    public int size(){
        return size;
    }

    public T get(int index){
        if(index>=size){
            return null;
        }
        int indexAfterFirst = nextFirst+1+index;
        if(indexAfterFirst>=capacity){
            indexAfterFirst = indexAfterFirst-capacity;
        }
        return items[indexAfterFirst];
    }

    public void addFirst(T item){
        items[nextFirst] = item;
        nextFirst = oneMinus(nextFirst);
        size+=1;
        expand();
    }
    public void addLast(T item){
        items[nextLast] = item;
        nextLast = onePlus(nextLast);
        size+=1;
        expand();
    }
    public T removeFirst(T item){
        if(isEmpty()){
            return null;
        }
        int firstItemIndex = onePlus(nextFirst);
        T removed = items[firstItemIndex];
        nextFirst = firstItemIndex;
        items[firstItemIndex] = null;
        size-=1;
        contract();
        return removed;
    }
    public T removeLast(T item){
        if(isEmpty()){
            return null;
        }
        int lastItemIndex = oneMinus(nextLast);
        T removed = items[lastItemIndex];
        nextLast = lastItemIndex;
        items[lastItemIndex] = null;
        size-=1;
        contract();
        return removed;
    }
    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }

    public void printDeque(){
        int firstIndex = onePlus(nextFirst);
        while(firstIndex!=nextLast){
            System.out.println(items[firstIndex]+" ");
            firstIndex = onePlus(firstIndex);

        }
        System.out.println();
    }

    public void resize(int newCapacity){
        T[] newItems = (T[]) new Object[newCapacity];
        int currentFirst = onePlus(nextFirst);
        int currentLast = oneMinus(nextLast);

        if(currentFirst<currentLast){
            int length = currentLast-currentFirst+1;
            System.arraycopy(items,currentFirst,newItems,0,length);
            nextLast = length;
            nextFirst = newCapacity-1;
        }
        else{
            int lengthFirsts = capacity - currentFirst;
            int newCurrentFirst = newCapacity - lengthFirsts;
            //length should be currentLast+1
            int lengthLasts = nextLast;
            System.arraycopy(items,currentFirst,newItems,newCurrentFirst,lengthFirsts);
            System.arraycopy(items,0,newItems,0,lengthLasts);
            nextFirst = newCurrentFirst-1;
        }
        capacity = newCapacity;
        items = newItems;
    }

    private void expand(){
        if(size == capacity){
            int newCapacity = capacity*eFactor;
            resize(newCapacity);
        }
    }
    private void contract(){
        if(size/capacity<0.25 && capacity>=16){
            int newCapacity = capacity/cFactor;
            resize(newCapacity);
        }
    }



}
