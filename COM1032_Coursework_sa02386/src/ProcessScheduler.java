import java.util.LinkedList;

public class ProcessScheduler {
	
	public LinkedList<OSProcess> readyQueue;
	
	public ProcessScheduler() {
		super();
		this.readyQueue= new LinkedList<OSProcess>();
	}

}
