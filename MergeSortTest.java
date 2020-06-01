import java.util.*;
import java.io.*;

public class MergeSortTest{

	public static void main(String[] args){

		int n = Integer.parseInt(args[0]);
		int[] arr = new int[n]; 

		for(int i = 0; i < n; i++){
			arr[i] = 100000 * (i+1);
		}

		try
        {
        	ArrayList<Float> st = new ArrayList<Float>();
		    ArrayList<Float> mt = new ArrayList<Float>();

           	for(int c : arr){
           		System.out.println(c + ":");
           		Process gen = Runtime.getRuntime().exec("java RandomNumGenerator " + c);

           		BufferedReader make = new BufferedReader(new InputStreamReader(gen.getInputStream()));
		        PrintWriter print = new PrintWriter(new File("nums.txt"));
		        String line = "";
		        while ((line = make.readLine()) != null)
		        {
		           print.println(line);
		        }
		        make.close();
		        print.close();

		        Process process = Runtime.getRuntime().exec("java MMergeSort " + c);
				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		        line = null;

		        while ((line = input.readLine()) != null)
		        {
		        	System.out.println("   " + line);
		        	Scanner word = new Scanner(line);
		        	if(line.charAt(0)=='S'){
		        		word.next();
		        		word.next();
		        		st.add(Float.parseFloat(word.next()));
		        	}
		        	else{
		        		word.next();
		        		word.next();
		        		word.next();
		        		mt.add(Float.parseFloat(word.next()));
		        	}
		        	word.close();
		        }
		        input.close();
			}

			//Printouts
				System.out.println();
				System.out.println("Size:	");
		        for(int c : arr){
		        	System.out.print(c + ", ");
				}
				System.out.println();

				System.out.println();
				System.out.println("Single-Threaded:	");
		        for(Float e : st){
		        	System.out.print(e + ", ");
		        }
		        System.out.println();

		        System.out.println();
		      	System.out.println("Multi-Threaded:	");
		        for(Float e : mt){
		        	System.out.print(e + ", ");
		        }
		        System.out.println();
		        System.out.println();

        } 
        catch (IOException e){
        	e.printStackTrace();
        }

	}


}