package hu.pallas.stopper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.*;

public class Stopper implements ActionListener {
	
	private JFrame frame;
	private JButton startButton;
	private JButton stopButton;
	private JButton resetButton;
	private JPanel felso;
	private JLabel ora;
	private Timer timer;
	private int elapsedSeconds;
	
	private int frameWidth, frameHeight;
	private boolean visible;
	
	public int getFrameWidth() {
		return frameWidth;
	}
	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}
	public int getFrameHeight() {
		return frameHeight;
	}
	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Stopper(){
		createFrame();
	}
	
	private void updateTimer() {
	      
		int minutes = elapsedSeconds / 60;
     	int seconds = elapsedSeconds % 60;
     	DecimalFormat df = new DecimalFormat("00");
     	ora.setText(df.format(minutes) + ":" + df.format(seconds));
	}
	
	public void createFrame() {
		frame = new JFrame();
		frame.setTitle("Pallas StopWatch");
		frame.getContentPane().setBackground(new Color(100, 149, 237));
		frame.setLocationRelativeTo(null);
		frame.setSize(getFrameWidth(), getFrameHeight());
		frame.setLayout(null);
		frame.setVisible(isVisible());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
		startButton = new JButton("START");
        startButton.addActionListener(this);
        startButton.setFont(new Font("Sans-serif", Font.BOLD, 15));
        startButton.setBackground(new Color(95, 158, 160));
        startButton.setBounds(50, 190, 90, 50);
        frame.add(startButton);

        stopButton = new JButton("STOP");
        stopButton.addActionListener(this);
        stopButton.setFont(new Font("Sans-serif", Font.BOLD, 15));
        stopButton.setBackground(new Color(119, 136, 153));
        stopButton.setBounds(150, 190, 90, 50);
        frame.add(stopButton);

        resetButton = new JButton("RESET");
        resetButton.addActionListener(this);
        resetButton.setFont(new Font("Sans-serif", Font.BOLD, 15));
        resetButton.setBackground(new Color(188, 143, 143));
        resetButton.setBounds(250, 190, 90, 50);
        frame.add(resetButton);
        
        felsoPanel();
        frame.add(felso);
        
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                elapsedSeconds++;
                updateTimer();
            }
        });
	}
	
	public void felsoPanel() {
		felso = new JPanel();
		felso.setBounds(50, 10, 290, 170);
		felso.setBackground(new Color(173, 216, 230));
		
		GridLayout gridlayout = new GridLayout(2,1);
		felso.setLayout(gridlayout);
		
	    JPanel minSec = new JPanel(); 
	    minSec.setBackground(new Color(173, 216, 230));
	    JLabel minLabel = new JLabel("Min  ");
	    JLabel secLabel = new JLabel("Sec");
	    
	    minLabel.setFont(new Font("Sans-serif", Font.BOLD, 60));
	    secLabel.setFont(new Font("Sans-serif", Font.BOLD, 60));
	    
	    minSec.add(minLabel);
	    minSec.add(secLabel);
	    felso.add(minSec);
	    
	    ora = new JLabel("00:00");
	    ora.setFont(new Font("Sans-serif", Font.BOLD, 100));
	    ora.setHorizontalAlignment(JLabel.CENTER);
	    felso.add(ora);
				
	}
	
	public void actionPerformed(ActionEvent e) {
       try {
		String button = e.getActionCommand();

        if (button.equals("START")) {
            timer.start();
        } else if (button.equals("STOP")) {
            timer.stop();
        } else if (button.equals("RESET")) {
            timer.stop();
            elapsedSeconds = 0;
            updateTimer();
        }
       } catch(Exception hiba) {
    	   frame.setTitle("nemjau" + hiba);
       }
	}
}
