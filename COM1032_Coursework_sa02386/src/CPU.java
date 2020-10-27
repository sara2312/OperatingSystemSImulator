import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



public class CPU {
	
public ProcessScheduler processScheduler = new ProcessScheduler();
public mainMemory memory = new mainMemory();
public virtualMemory OSvirtualMemory = new virtualMemory();


public CPU() {
	super();
	
}
	
	
	public void newJob(File file) {
		 try {
				Scanner sc = new Scanner(file);
				Program programToExecute = new Program();
				OSProcess newProcess = new OSProcess(programToExecute);
				while(sc.hasNextLine()) {	
					programToExecute.instructionsToExecute.add(sc.nextLine());
					}  
				for(String instruction : programToExecute.instructionsToExecute){
					StringTokenizer instructionsToken = new StringTokenizer(instruction);
					ArrayList<String> commandAndParameters = new ArrayList<String>();
					while(instructionsToken.hasMoreElements()) {
						String nextToken = instructionsToken.nextToken();
						
						commandAndParameters.add(nextToken);
					
					}
					
					newProcess.processInstructions.add(commandAndParameters);
					
				}
				newProcess.state = ProcessState.READY;
				this.processScheduler.readyQueue.add(newProcess);
				
				
            }catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
	
	public OSProcess checkReadyQueue() {
		//show whatever is next in line in the ready queue
		return this.processScheduler.readyQueue.getFirst();
	}
	
	public void putNextJobToExecuteInMemory(OSProcess processToExecute) {
		int frameNumber =0; 
		if(processToExecute.processInstructions.size()<= this.memory.numberOfMemoryFrames) {
		for(ArrayList<String> command : processToExecute.processInstructions) {
			frameNumber++;
			this.memory.memoryFrames.put(command, frameNumber);
			
		}}
		
		else {
			System.out.println("process requiers mome memory than the system has available");
			for(ArrayList<String> command : processToExecute.processInstructions) {
				this.OSvirtualMemory.virtualMemory.put(command, frameNumber);
				frameNumber++;
			}
			processToExecute.state = ProcessState.WAITING;
		}
		//for(ArrayList<String> words : this.memory.memoryFrames.keySet()) {}
	}
	
	
	public void executeNextJob() throws InterruptedException, ExecutionException  {
		if(this.processScheduler.readyQueue.isEmpty()) {
			System.out.println("empty queue");
		}
		
		else {
			
				//if(this.processScheduler.readyQueue.getFirst().state == ProcessState.READY) {
					this.putNextJobToExecuteInMemory(processScheduler.readyQueue.getFirst());
					if(this.processScheduler.readyQueue.getFirst().state == ProcessState.READY) {
						this.processScheduler.readyQueue.getFirst().state = ProcessState.RUNNING;
		for(ArrayList<String> instruction : this.memory.memoryFrames.keySet()) {
			this.processScheduler.readyQueue.getFirst().programCounter++;
			CommandRunner runningInstruction = new CommandRunner(instruction);
			Thread t = new Thread(runningInstruction);
			t.start();
			t.join();
				}
		}
				else {
					System.out.println("next job in virtual memory");
				}
				
		}
		
		}
		
	public void executeProcessInVM() {
		
	}
	
	public void terminateJob() {
		this.processScheduler.readyQueue.getFirst().programCounter= 0;
		this.memory.memoryFrames.clear();
		this.processScheduler.readyQueue.getFirst().state = ProcessState.TERMINATED;
		this.processScheduler.readyQueue.pop();
		System.out.println("next job ready to be executed");
	}
	
}