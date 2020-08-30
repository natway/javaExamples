package queuepackage;

public class ArrayQueue<T> implements QueueInterface<T>{
	private T[] queue; // Circular array implementation of Queue with one unused location
	   private int frontIndex;
	   private int backIndex;
	   private int numOfEntries = 0;
	   private boolean initialized = false;
	   private static final int DEFAULT_CAPACITY = 50;
	   private static final int MAX_CAPACITY = 10000;	   
	  
	   
	   public ArrayQueue(){
	      this(DEFAULT_CAPACITY);
	   } // end default constructor  
	   
	   //constructor with capacity
	   public ArrayQueue(int initialCapacity){
	      checkCapacity(initialCapacity);	      
	      // The cast is safe because the new array contains null entries
	      @SuppressWarnings("unchecked")
	      T[] tempQueue = (T[]) new Object[initialCapacity + 1];
	      queue = tempQueue;
	      frontIndex = 0;
	      backIndex = initialCapacity;
	      initialized = true;
	   } // end constructor
	   
	   // add element to tail of queue
	   public void enqueue(T newEntry){
	      checkInitialization();
	      ensureCapacity();
	      backIndex = (backIndex + 1) % queue.length;
	      queue[backIndex] = newEntry;
	      numOfEntries++;
	   } // end enqueue	   
	   
	   // add array of elements to queue
	   public void enqueue(T[] entryArray){
	      for (int i =0; i <= (entryArray.length - 1); ++i) {
	    	  this.enqueue(entryArray[i]);
	      }
	   } // end enqueue	   
	   
	   // get reference to element at head of queue
	   public T getFront(){
	      checkInitialization();
	      if (isEmpty())
	         throw new EmptyQueueException();
	      else
	         return queue[frontIndex];
	   } // end getFront
	   
	   // get reference and remove element from queue
	   public T dequeue() {
	      checkInitialization();
	      if (isEmpty())
	         throw new EmptyQueueException();
	      else{
	         T front = queue[frontIndex];
	         queue[frontIndex] = null;
	         frontIndex = (frontIndex + 1) % queue.length;
	         numOfEntries--;
	         return front;
	      } // end if
	   } // end dequeue	 
	   
	   
	// Doubles the size of the array queue if it is full
	// Precondition: checkInitialization has been called.
	private void ensureCapacity(){
	   if (frontIndex == ((backIndex + 2) % queue.length)) { // if array is full,
		                                                   //double size of array
	      T[] oldQueue = queue;
	      int oldSize = oldQueue.length;
	      int newSize = 2 * oldSize;
	      checkCapacity(newSize);
	      // The cast is safe because the new array contains null entries
	      @SuppressWarnings("unchecked")
	      T[] tempQueue = (T[]) new Object[2 * oldSize];
	      queue = tempQueue;
	      for (int index = 0; index < oldSize - 1; index++){
	         queue[index] = oldQueue[frontIndex];
	         frontIndex = (frontIndex + 1) % oldSize;
	      } // end for	      
	      frontIndex = 0;
	      backIndex = oldSize - 2;
	   } // end if
	} // end ensureCapacity	
	
	// removes all elements
	public void clear() {
		while (!isEmpty()){
		dequeue();
		}
	}	
	
	// check if queue is empty
	public boolean isEmpty(){
	  return frontIndex == ((backIndex + 1) % queue.length);
	} // end isEmpty	
	private void checkCapacity(int capacity){
	      if (capacity > MAX_CAPACITY)
	         throw new IllegalStateException("Attempt to create a queue whose " +
	                                         "capacity exeeds allowed " +
	                                         "maximum of " + MAX_CAPACITY);
	   } // end checkCapacity	
	
	// Throws an exception if this object is not initialized.
	   private void checkInitialization(){
	      if (!initialized)
	      throw new SecurityException("ArrayQueue object is not initialized " +
	                                  "properly.");
	   } // end checkInitialization
	} // end ArrayQueue
