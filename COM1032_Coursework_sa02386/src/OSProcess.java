import java.util.ArrayList;
import java.util.LinkedList;
public class OSProcess {
public ProcessState state = null;
//public int ProcessID = 0;
public LinkedList<ArrayList<String>> processInstructions;
int programCounter =0;

public OSProcess(Program program) {
	super();
	this.processInstructions  = new LinkedList<ArrayList<String>>();
	this.state =ProcessState.NEW;
	
}


}
