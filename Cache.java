import java.util.Iterator;
import java.util.LinkedList;
/**
 * A simple cache program that uses the java LinkedList. Works for any type.
 * toString provides the tracked number of hits (data is found in cache) and references, and also calculated hit/reference ratio
 * Maintains itself at a maximum size by deleting the last item in the cache
 * 
 * @author Joshua Pfeil
 */
public class Cache<T> {
    
    //instance vars
    LinkedList<T> cache;
    int size;
    int numReferences;
    int numHits;

    /**
     * Default constructor, only takes the size of the cache and sets up the variables to defaults
     * @param size
     */
    public Cache (int size){
        cache = new LinkedList <T> ();
        this.size = size;
        numReferences = 0;
        numHits = 0;
    }

    /**
     * The most important aspect of the cache. Runs through the cache and checks if the object is in the cache.
     * If the object is in the cache, numHits is incremented and the object is moved to the front of the list.
     * If the object is not in the cache, it is added.
     * 
     * @param object
     * @return The object that was searched for
     */
    public T getObject(T object){
        numReferences++;
        Iterator<T> it = cache.iterator(); //uses the LinkeList iterator due to its increased efficency in locating items and removal.
        if(cache.size() > 0){

            T current;

            while(it.hasNext()){ // runs through the list and checks if the object is inside.
                current = it.next();
                if(current.equals(object)){
                    it.remove();
                    cache.addFirst(current);
                    numHits++;
                    return current;
                }                
            }
        }
        //adds using addObject
        addObject(object);
        return object;

    }

    /**
     * Uses the addFirst method of the LinkedList to add it to the beginning of the cache.
     * If the cache is full before this, the last element is removed to make space.
     * 
     * @param object
     */
    public void addObject(T object){
        if(size == cache.size()){
            cache.removeLast();
        }
        cache.addFirst(object);
    }

    /**
     * Removes the object form the list
     * 
     * @param object
     */
    public void removeObject(T object){
        cache.remove(object);
    }

    /**
     * Runs through the list and removes each element, making the cache empty
     */
    public void clearCache(){
        Iterator<T> it = cache.iterator();
        for(int i = 0; i<size; i++){
            it.next();
            it.remove();
        }
    }

    /**
     * The toString method. Calculates the hit to reference ratio and then prints numReferences, numHits, and the caculated ratio.
     */
    public String toString(){
        double hitRatio = (double) numHits / numReferences;
        String str = "Total number of references:        " + numReferences;
        str += "\nTotal number of cache hits:        " + numHits;
        str += "\n1st-level cache hit ratio:         " + hitRatio;
        return str;
    }
}


