/** ----------------------------------- 
	MMergeSort.java

	@author William Clift
			Data Structures
			8 April 2020

	Compile and Run Instructions:
			
		Compile:	javac MMergeSort.java RandomNumGenerator.java 			

		Run:		java MMergeSort [# of Items to Sort]

		Output: 	Sorting took [] seconds.
   					Multi-Threaded Sorting took [] seconds.

    ----------------------------------- **/

import java.util.Scanner;
import java.util.concurrent.*;
import java.util.Arrays;
import java.util.*;
import java.io.*;

@SuppressWarnings("unchecked")
public class MMergeSort<T extends Comparable<? super T>> extends RecursiveAction{

	private final T[] a;
	private final int n;
	private static final int processors = Runtime.getRuntime().availableProcessors();

  

    public static void main(String[] args) throws FileNotFoundException{

    	try {

	    	Scanner s = new Scanner(System.in);
			System.err.println("Loading file.");

			Integer[] v = new Integer[Integer.parseInt(args[0])];
			int i = 0;
			while(s.hasNextInt()){
				v[i++] = s.nextInt();
			}
			s.close();

			MMergeSort<Integer> st = new MMergeSort<>(v, -1);
			MMergeSort<Integer> mt = new MMergeSort<>(v, 0);

		/* ------------------- *
		 *  Classic MergeSort  * 
		 * ------------------- */
			long start = System.currentTimeMillis();
				st.compute();
			long end = System.currentTimeMillis();
			System.out.println("Sorting took " + ((float)(end - start) / 1000F) + " seconds.");


		/* -------------------- *
		 *  ForkJoin Framework  * 
		 * -------------------- */
			long startmt = System.currentTimeMillis();
				mt.compute();
			long endmt = System.currentTimeMillis();
			System.out.println("Multi-Threaded Sorting took " + ((float)(endmt - startmt) / 1000F) + " seconds.");

		}catch (Exception e) {
  			e.printStackTrace();
		}
    }

    /**
     * Returns the array a
     *	@return a
     */
    public T[] getA(){
    	return this.a;
    }

   	/**
    *	Constructor MMergeSort
    * @param a
    * @param l
    * @param r
    * @param left
    * @param right
    */
    public MMergeSort(final T[] a, final int n){
    	this.a = a;
    	this.n = n;
    }

    /**
     *  Overrides the compute method in RecursiveAction
     */
	@Override
	protected void compute(){
		mergeSort(a, n);
	}

    /**
    * Merge Sorting Recursive Algorithm
    * @param a
    * @param n
    */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void mergeSort(T[] a, int n) {
		int m = a.length;
		if (m < 2)
		    return;

		int mid = m / 2;
		
		T[] l = (T[]) new Comparable[mid];
		T[] r = (T[]) new Comparable[m-mid];

		for (int i = 0; i < l.length; i++) {
		    l[i] = a[i];				//Left array
		}
		for (int i = mid; i < m; i++) {
		    r[i - mid] = a[i];			//Right Array
		}

		if(n >= 0 && Math.pow(2, n) <= processors){			//If processes > # of cores
			invokeAll(new MMergeSort(l, n++), new MMergeSort(r, n++));

		}else{
			MMergeSort<T> left = new MMergeSort<>(l, n);
			MMergeSort<T> right = new MMergeSort<>(r, n);
			left.compute();
			right.compute();
		}

		merge(a, l, r, l.length, r.length);	//Merge the arrays
    }


    /**
    *  Merge Sort - Merge Algorithm
    * @param a
    * @param l
    * @param r
    * @param left
    * @param right
    */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void merge(T[] a,
			     										T[] l,
														T[] r,
														int left,
														int right) {
	
		int i = 0, j = 0, k = 0;
		while (i < left && j < right) {
		    if (l[i].compareTo(r[j]) <= 0){
				a[k++] = l[i++];
			}
		    else{
		    	a[k++]= r[j++];
		    }
		}
		while (i < left) {
		    a[k++] = l[i++];
		}
		while (j < right) {
		    a[k++] = r[j++];
		}
    }

}