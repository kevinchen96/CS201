import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;


public class BusBoyFactory extends Thread{
	private int numBoys;
	private int numBoysPerTable;
	private List<Lock> tableLock;
	private List<Integer> tableNum;
	private Semaphore busBoys;
	private int busBoysInCurrTable;
	
	
	public BusBoyFactory(int numBoys, int numBoysPerTable, int numTables){
		busBoysInCurrTable = 0;
		tableLock =  Collections.synchronizedList(new ArrayList<Lock>());
		tableNum =  Collections.synchronizedList(new ArrayList<Integer>());
		this.numBoys = numBoys;
		this.numBoysPerTable = numBoysPerTable;
		busBoys = new Semaphore(numBoys);
		start();
	}
	
	
	public synchronized boolean requestClean(Lock t, int num){
		tableLock.add(t);	
		tableNum.add(num);
		
		try {
			wait();
		} catch (InterruptedException e) {
			
		}
		return true;
		
	}
	
	
	//clean()
	public void run(){
		while(true){			
			if(!tableNum.isEmpty()){					
				try {
					busBoys.acquire();
					sendToTable();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		}		
	}
	
	private void sendToTable(){
		busBoysInCurrTable++;
		if(busBoysInCurrTable == numBoysPerTable){
			clean();
		}
	}
	
	private synchronized void doneCleaning(){
		notify();
	}
	
	private void clean(){
		BusBoyThread c = new BusBoyThread(numBoys, tableLock.get(0), tableNum.get(0));
		tableLock.remove(0);
		tableNum.remove(0);
		busBoysInCurrTable = 0;
	}
	
	
	class BusBoyThread extends Thread{
		private int numBoy;
		private Lock tableLock;
		private int tableNum;
		
		public BusBoyThread(int numBoy, Lock tableLock, int tableNum){	
			this.numBoy = numBoy;
			this.tableLock = tableLock;
			this.tableNum = tableNum;
			run();
		}

		public void run() {
			Restaurant.addMessage("Table " + tableNum + " is being cleaned by " + numBoy + " bus boy(s).");
			try {
				Thread.sleep((long)1000.0/numBoy);
			} catch (InterruptedException e) {
				
			}
			Restaurant.addMessage("Table " + tableNum + " was cleaned in " + (1.0 /numBoy) + " second(s)");
			for(int i = 0; i < numBoys; i++){
				busBoys.release();
			}
			doneCleaning();
		}
	}
}
