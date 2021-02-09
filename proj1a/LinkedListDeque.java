public class LinkedListDeque<T>  {
    public  class staffNode{
        public T item;
        public staffNode pre;
        public staffNode next;
        public staffNode(T i, staffNode p,staffNode n){
            item = i;
            pre = p;
            next = n;
        }
    }
    private staffNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new staffNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        size = 0;
    }
    public LinkedListDeque(LinkedListDeque other){
        sentinel = new staffNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        size = other.size;
//        staffNode p = other.sentinel;
//        while(p.next!= p){
//            sentinel.pre = new staffNode(p.next.item,sentinel.pre,sentinel);
//            sentinel.pre.pre.next = sentinel.pre;
//        }
        for(int i = 0; i< other.size(); i+=1){
            addLast((T) other.get(i));
        }
    }

    public void addFirst(T item){
        sentinel.next = new staffNode(item, sentinel, sentinel.next);
        sentinel.next.next.pre = sentinel.next;
        size++;
    }
    public void addLast(T item){
        sentinel.pre = new staffNode(item, sentinel.pre, sentinel);
        sentinel.pre.pre.next = sentinel.pre;
        size++;
    }
    public boolean isEmpty(){
        if(sentinel.pre == sentinel&& sentinel.next ==sentinel&&size == 0){
            return true;
        }
        return false;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        staffNode p = sentinel.next;
        while(p !=sentinel){
            System.out.print(p.item+" ");
            p = p.next;
        }
        System.out.println();
    }
    public T removeFirst(){
        if(sentinel.next == sentinel){
            return null;
        }
        T removed = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.pre = sentinel;
        size--;
        return removed;
    }
    public T removeLast(){
        if(sentinel.pre == sentinel){
            return null;
        }
        T removed = sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.pre.next = sentinel;
        size--;
        return removed;
    }
    public T get(int index){
        if(sentinel.next == sentinel){
            return null;
        }
        if(index>=size){
            return null;
        }
        staffNode p = sentinel.next;
        while(index>0){
            p = p.next;
            index--;
        }
        return p.item;
    }
    public T getRecursiveHelper(staffNode n, int index){
        if(index==0){
            return n.item;
        }
        return getRecursiveHelper(n.next,index-1);
    }
    public T getRecursive(int index){
        if(sentinel.next == sentinel){
            return null;
        }
        if(index>=size){
            return null;
        }
        return getRecursiveHelper(sentinel.next,index);
    }
}
