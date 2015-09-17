
public abstract class Pet implements Nameable{
	private String name;
	
	public void setName(String newName){
		name = newName;
	}
	
	public String getName(){
		return name;
	}
	
	public abstract String speak();
}
