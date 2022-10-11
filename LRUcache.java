import java.util.*;
class LRUCache {

    Set<UserInfo> cache;
    int capacity;

    public LRUCache(int capacity)
    {
        this.cache = new LinkedHashSet<UserInfo>(capacity);
        this.capacity = capacity;
    }

    // This function returns false if key is not
    // present in cache. Else it moves the key to
    // front by first removing it and then adding
    // it, and returns true.
    public boolean get(UserInfo key)
    {
        if (!cache.contains(key))
            return false;
        cache.remove(key);
        cache.add(key);
        return true;
    }

    /* Refers key x with in the LRU cache */
    public void refer(UserInfo key)
    {
        if (get(key) == false)
            put(key);
    }

    // displays contents of cache in Reverse Order
    public void display()
    {
        LinkedList<UserInfo> list = new LinkedList<>(cache);

        // The descendingIterator() method of java.util.LinkedList
        // class is used to return an iterator over the elements
        // in this LinkedList in reverse sequential order
        Iterator<UserInfo> itr = list.descendingIterator();

        while (itr.hasNext())
            System.out.print(itr.next().userName + " ");
    }

    public void put(UserInfo key)
    {

        if (cache.size() == capacity) {
            UserInfo firstKey = cache.iterator().next();
            cache.remove(firstKey);
        }

        cache.add(key);
    }

    public static void main(String[] args)
    {
        UserInfo u1 = new UserInfo("Hello1");
        UserInfo u2 = new UserInfo("Hello2");
        UserInfo u3 = new UserInfo("Hello3");
        UserInfo u4 = new UserInfo("Hello4");
        UserInfo u5 = new UserInfo("Hello5");

        LRUCache ca = new LRUCache(10);


        ca.refer(u3);
        ca.refer(u2);





        ca.display();
    }
}