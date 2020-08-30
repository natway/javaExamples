package queuepackage;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
 *  Driver test class for Queue and QuickSort.
 *  
 */

public class PersonQueue {
	
	private static final int DEFAULT_SIZE = 5;
	
	ArrayQueue<Person> myQ;
	
	
	public PersonQueue() {
		myQ = new ArrayQueue<Person>(DEFAULT_SIZE);
	}
	
	
	public void createPerson() {
		Person subject = new Person();
		Scanner uIn = new Scanner(System.in);		
		System.out.println("\nEnter person's first name: ");
		    subject.setFirstName(uIn.nextLine());
		System.out.println("\nEnter person's last name: ");
		    subject.setLastName(uIn.nextLine());		    
		System.out.println("\nEnter person's age: ");
		    subject.setAge(uIn.nextInt());
		this.myQ.enqueue(subject);			
	}
	
	public Person[] toArray() {
		Person[] pList = new Person[DEFAULT_SIZE];
		for(int i = 0; i < pList.length; ++i) {
			pList[i] = myQ.dequeue();
		   // System.out.print(pList[i].getFirstName() + ", ");
		}
		myQ.enqueue(pList);
		return pList;
	}
	
	public static void main(String[] args) {
		
		PersonQueue myQ = new PersonQueue();
		
		// prompt for person information until DEFAULT_SIZE is reached
		// createPerson() calls myQ.enqueue()
		for(int i = 0; i < DEFAULT_SIZE; ++i) {
			System.out.print("\n" + (i+1) + ".  ");
			myQ.createPerson();
		}
		
		// make and display sortable array from queue
		Person[] pArray= myQ.toArray();
		System.out.print("\nUnsorted Queue: ");
		for (int i = 0; i < pArray.length; ++i)
		    System.out.print("\n" +  pArray[i]);
		
		// sort array by last name using my implementation of QuickSort
		SortingAlgorithms.quickSort(pArray, 0, DEFAULT_SIZE - 1);
		// Display sorted array
		System.out.print("\n\nSorted descending by last name: ");
		for (int i = 0; i < pArray.length; ++i)
		    System.out.print("\n" +  pArray[i]);
		
		
		//   sort by age using Arrays.sort (SortingAlgorithms.quickSort only
                                          // works with natural ordering)
		Arrays.sort(pArray, new Comparator<Person>() { 
			public int compare(Person p1, Person p2) {
				return (int)(p2.getAge() - p1.getAge()); 
			}
		});
		System.out.print("\n\nSorted descending by age: ");
		for (int i = 0; i < pArray.length; ++i)
		    System.out.print("\n" +  pArray[i]);		
	}
}
