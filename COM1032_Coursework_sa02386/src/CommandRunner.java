import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class CommandRunner implements Runnable{
	
public ArrayList<String> command = null;
	public CommandRunner( ArrayList<String> command) {
		super();
		this.command = command;
	}
	@Override
	public void run(){
		ProcessBuilder pb = new ProcessBuilder(this.command);
		pb.redirectErrorStream(true);
		try {
		Process p5 = pb.start();
		p5.waitFor();
		BufferedReader br = new BufferedReader(new InputStreamReader(p5.getInputStream()));
		String line;
		while((line = br.readLine()) !=null) {
			System.out.println(line);
		}} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
