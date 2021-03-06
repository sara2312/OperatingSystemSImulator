

	
	/*
	 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
	 *
	 * Redistribution and use in source and binary forms, with or without
	 * modification, are permitted provided that the following conditions
	 * are met:
	 *
	 *   - Redistributions of source code must retain the above copyright
	 *     notice, this list of conditions and the following disclaimer.
	 *
	 *   - Redistributions in binary form must reproduce the above copyright
	 *     notice, this list of conditions and the following disclaimer in the
	 *     documentation and/or other materials provided with the distribution.
	 *
	 *   - Neither the name of Oracle or the names of its
	 *     contributors may be used to endorse or promote products derived
	 *     from this software without specific prior written permission.
	 *
	 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
	 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
	 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
	 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
	 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
	 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
	 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
	 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
	 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
	 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
	 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
	 */ 

	

	import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;
import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;

	/*
	 * FileChooserDemo.java uses these files:
	 *   images/Open16.gif
	 *   images/Save16.gif
	 */
	public class Booting extends JPanel
	                            implements ActionListener {
	    static private final String newline = "\n";
	    JButton openButton, executeButton;
	    JTextArea log;
	    JFileChooser fc;
	    public CPU processor = new CPU();
	    
	   
	    public Booting() {
	        super(new BorderLayout());

	        //Create the log first, because the action listeners
	        //need to refer to it.
	        log = new JTextArea(5,20);
	        log.setMargin(new Insets(5,5,5,5));
	        log.setEditable(false);
	        JScrollPane logScrollPane = new JScrollPane(log);

	        //Create a file chooser
	        fc = new JFileChooser();

	        openButton = new JButton("Enqueue new process",
	                                 createImageIcon("Open16.gif"));
	        openButton.addActionListener(this);

	        executeButton = new JButton("executeProcess", createImageIcon("Save16.gif"));
	        executeButton.addActionListener(this);
	       
	        JPanel buttonPanel = new JPanel(); //use FlowLayout
	        buttonPanel.add(openButton);
	        buttonPanel.add(executeButton);
	       
	        add(buttonPanel, BorderLayout.PAGE_START);
	        add(logScrollPane, BorderLayout.CENTER);
	        
	        
	    }

	    @Override
		public void actionPerformed(ActionEvent e) {

	        File selectedFile = null;
	        if (e.getSource() == openButton) {
	            int returnVal = fc.showOpenDialog(Booting.this);

	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                
	                //This is where a real application would open the file.
	                selectedFile = fc.getSelectedFile();
	                this.processor.newJob(selectedFile);
	                
	                log.append("Opening: " + selectedFile.getName() + "." + newline);
	                
	                  } //else {
	                //log.append("Open command cancelled by user." + newline);
	           // }
	            log.setCaretPosition(log.getDocument().getLength());
} 
	        
	        else if(e.getSource() == executeButton) {
	        	
	        	//if(!this.processor.processScheduler.readyQueue.isEmpty()) {
	        		log.append(" \n executing next job in the queue");
	        		try {
						processor.executeNextJob();
					} catch (InterruptedException | ExecutionException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		processor.terminateJob();
	        		log.append("\n job executed");
	        	//}
	        	//else {log.append("ready queue empty");}
	        
	        log.setCaretPosition(log.getDocument().getLength());
	        }
	            
	    }

	    /** Returns an ImageIcon, or null if the path was invalid. */
	    protected static ImageIcon createImageIcon(String path) {
	        java.net.URL imgURL = Booting.class.getResource(path);
	        if (imgURL != null) {
	            return new ImageIcon(imgURL);
	        } else {
	            System.err.println("Couldn't find file: " + path);
	            return null;
	        }
	    }

	    /**
	     * Create the GUI and show it.  For thread safety,
	     * this method should be invoked from the
	     * event dispatch thread.
	     */
	    public static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Operating System Simulator");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Add content to the window.
	        frame.add(new Booting());

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	        
	    }

	    public static void main(String[] args) {
	       // Schedule a job for the event dispatch thread:
	        //creating and showing this application's GUI.
	       //try {
			//SwingUtilities.invokeAndWait(new Runnable() {
			       // @Override
					//public void run() {
			           // //Turn off metal's use of bold fonts
			            //UIManager.put("swing.boldMetal", Boolean.FALSE); 
			          // createAndShowGUI();
			       // }
			   // });
		//} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
	   // }
	    	Booting boot = new Booting();
	    	 boot.createAndShowGUI();
	}
	    
	}



