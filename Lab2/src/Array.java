import java.util.Scanner;


public class Array {
	static int [] data;
	public Array(int x){
		data = new int[x];
		for(int i = 0; i < x; i++){
			data[i] = i+1;
		}
	}
	public static void main(String []args){
		System.out.println("Enter the size of the array: ");
		Scanner s = new Scanner(System.in);
		Array test = new Array(s.nextInt());
		System.out.println("Enter the value to search for: ");
		int search = s.nextInt();
		System.out.println("Brute Search result: " + BruteSearch(search));
		System.out.println("Binary Search result: " + BinSearch(search));		
	}
	private static int BruteSearch(int search){
		for(int i = 0; i < data.length; i++){
			if(data[i] == search){
				return (i+1);
			}
		}
		return -1;
	}
	private static int BinSearch(int search){
		int pivot = data.length/2-1;
		int start = 0;
		int end = data.length;
		int count = 1;
		while(start != pivot || end != pivot){
			if(data[pivot] == search){
				return count;
			}
			else if(data[pivot] < search){
				start = pivot;
				pivot = (end + start)/2;
				
			}
			else if(data[pivot] > search){
				end = pivot;
				pivot = (end + start)/2;
			}
			count ++;
		}
		return -1;
	}
}
