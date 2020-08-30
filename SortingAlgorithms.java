package queuepackage;


public class SortingAlgorithms {	
	
private static final int MIN_SIZE = 5;	

	/** generic static recursive QuickSort array into natural order. 
	Uses median-of-three pivot selection for arrays of at least 
    MIN_SIZE entries, and uses insertion sort for other arrays. */
    public static <T extends Comparable<? super T>>
    
        void quickSort(T[] a, int first, int last){
    	
    if (last - first + 1 < MIN_SIZE){ // array too small to partition
        insertionSort(a, first, last, true);
    }
    else{
        // Create the partition: Smaller | Pivot | Larger
        int pivotIndex = partition(a, first, last);
        // recursive call to sort sub-arrays Smaller and Larger
        quickSort(a, first, pivotIndex - 1);
        quickSort(a, pivotIndex + 1, last);
    } // end if
} // end quickSort    
    
    // partition method does most of the work
	private static <T extends Comparable<? super T>> 
	    int partition(T[] a, int first, int last) {
		//choose midpoint between first and last index by taking average
		int mid = (last + first)/2;
		//put first and last in place
		sortFirstMiddleLast(a, first, mid, last);
		//put pivot beside sorted last element
		doSwap(a, mid, last - 1);
		int pivotIndex = last - 1;
		T pivotValue = a[pivotIndex];
		//indexes must avoid already sorted elements and pivot
		int leftIndex  = first +1;
		int rightIndex  = last - 2;
		boolean done = false;
		while(!done) {
			while(a[leftIndex].compareTo(pivotValue) < 0 )
				leftIndex++;
			while(pivotValue.compareTo( a[rightIndex] ) < 0 )
				rightIndex--;
			if(leftIndex <= rightIndex) {
				doSwap(a, leftIndex, rightIndex);
				leftIndex++;
				rightIndex--;
			}
			else
				done = true;	
		}
		//put pivot in place and move to next pivot value
		doSwap(a, pivotIndex, leftIndex);
		pivotIndex = leftIndex;
		return pivotIndex;
	}// end partition
	
	
	// helper method for swapping two elements
	public static void doSwap(Object[] a, int index1, int index2){
		Object tmp = a[index1];
		a[index1] =  a[index2];
		a[index2] = tmp;
	}
	
	
   // Sorts the first, middle, and last entries of an array into ascending order.
    private static <T extends Comparable<? super T>>
    void sortFirstMiddleLast(T[] a, int first, int mid, int last) {
	// more exhaustive than concise, performance will not be affected due to
	// compiler optimization and sorting no more than three elements this way
	T max; T med; T min;
	if( a[first].compareTo(a[mid]) > 0 ){
		 if( a[first].compareTo(a[last]) > 0 ){
		  max = a[first];
		  if( a[mid].compareTo(a[last]) > 0 ){
		   med = a[mid];
		   min = a[last];
		  }else{
		   med = a[last];
		   min = a[mid];
		  }
		 }else{
		  med = a[first];
		  max = a[last];
		  min = a[mid];
		 }
		}else{
		 if( a[mid].compareTo(a[last]) > 0 ){
		  max = a[mid];
		  if( a[first].compareTo(a[last]) > 0 ){
		   med = a[first];
		   min = a[last];
		  }else{
		   med = a[last];
		   min = a[first];
		  }
		 }else{
		  med = a[mid];
		  max = a[last];
		  min = a[first];
		 }		 
		}
    a[first] = min;
    a[mid] = med;
    a[last] = max;
    }
    
    
	// insertion sort for arrays of length < MIN_SIZE
	public static <T extends Comparable<? super T>>
	void insertionSort(T[] a, int first, int last, boolean direction){
	
		if (first < last) {
	       // Sort all but the last entry
	       insertionSort(a, first, last -1, direction);
	       
	   /*    ***DEBUGGING CODE***
	       // Print current order & recursion count
		   System.out.println("\n\n" + (last) +"th iteration: ");
		   for (int i = 0; i < a.length; i++) {
		     System.out.print(a[i] + ", ");
	        }
	   */
	
		   // Insert the last entry in sorted order
	       insertInOrder(a[last], a, first, last -1, direction);
	    } // end if
	} // end insertionSort
	
	// Insertion sort helper method
	public static <T extends Comparable<? super T>>
	void insertInOrder(T anEntry,T[] a,int begin,int end, boolean dir) {
		//if direction is true sort ascending
		if (dir){
	    // Inserts anEntry into the sorted array entries a[begin] through a[end].
	       if (anEntry.compareTo(a[end]) >= 0) {
	          a[end + 1] = anEntry;
	       }
	       else if (begin < end) {
	
	       a[end + 1] = a[end];
	       insertInOrder(anEntry, a, begin, end -1, dir); }
		
	       else{ 
	        	// begin == end and anEntry < a[end] 	
	          a[end + 1] = a[end];
	          a[end] = anEntry; }
           }
		else{ //if direction is false sort descending
			// Inserts anEntry into the sorted array entries a[begin] through a[end].
			if (anEntry.compareTo(a[end]) <= 0) {
			   a[end + 1] = anEntry; }
			else if (begin < end) {			
			   a[end + 1] = a[end];
			   insertInOrder(anEntry, a, begin, end -1, dir); }				
			else{ 
				// begin == end and anEntry < a[end] 	
			   a[end + 1] = a[end];
			   a[end] = anEntry; }
		}
	}
}