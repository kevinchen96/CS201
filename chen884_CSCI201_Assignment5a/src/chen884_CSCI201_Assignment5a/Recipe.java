package chen884_CSCI201_Assignment5a;

import java.util.ArrayList;

public class Recipe {
	ArrayList<ArrayList<Integer>> number;
	ArrayList<ArrayList<Integer>> tool;
	ArrayList<Integer> location;
	ArrayList<Integer> time;
	ArrayList<Integer> numMaterials;
	ArrayList<Integer> materials;
	public Recipe(ArrayList<ArrayList<Integer>> number,
			ArrayList<ArrayList<Integer>> tool,
			ArrayList<Integer> location,
			ArrayList<Integer> time, ArrayList<Integer> hi4, ArrayList<Integer> hi5) {
		this.number = number;
		this.tool = tool;
		this.location = location;
		this.time = time;
		this.numMaterials = hi4;
		this.materials = hi5;
		// TODO Auto-generated constructor stub
	}
	public void print(){
		for(int i = 0; i < number.size(); i++){
			System.out.print("Number: ");
			for(int j = 0; j < number.get(i).size(); j++){
				System.out.print(number.get(i).get(j) + " ");
			}
			System.out.print("Tool: ");
			for(int j = 0; j < tool.get(i).size(); j++){
				System.out.print(tool.get(i).get(j) + " ");
			}
			System.out.print("location: " + location.get(i));
			System.out.print(" time: " + time.get(i));
			System.out.println();
		}
	}
	public ArrayList<ArrayList<Integer>> getNumber() {
		return number;
	}
	public ArrayList<ArrayList<Integer>> getTool() {
		return tool;
	}
	public ArrayList<Integer> getLocation() {
		return location;
	}
	public ArrayList<Integer> getTime() {
		return time;
	}
	public ArrayList<Integer> getNumMaterials() {
		return numMaterials;
	}
	public ArrayList<Integer> getMaterials() {
		return materials;
	}

}
