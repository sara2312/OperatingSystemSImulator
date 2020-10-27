import java.util.ArrayList;
import java.util.HashMap;
public class mainMemory {

	HashMap<ArrayList<String>,  Integer> memoryFrames;
    ArrayList<OSProcess> processesInMemory ;
    public  final int numberOfMemoryFrames= 15;

public mainMemory() {
	super();
	this.memoryFrames =  new HashMap<ArrayList<String> , Integer >();
	this.processesInMemory = new ArrayList<OSProcess>();
	
}

}
