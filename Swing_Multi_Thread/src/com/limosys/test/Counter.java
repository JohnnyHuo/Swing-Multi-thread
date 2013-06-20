package com.limosys.test;

import javax.swing.JLabel;
import javax.swing.JProgressBar;


public class Counter extends Thread{

	private volatile boolean isRunning = false;
	private JProgressBar progressBar;
	private JLabel label;
	private JLabel totalLabel;
	private int interval;
	
	public Counter(int interval, JProgressBar progressBar, JLabel label, JLabel totalLabel) {
		this.interval = interval;
		this.progressBar = progressBar;
		this.label = label;
		this.totalLabel = totalLabel;
	}

	@Override
	public void run() {
		
		isRunning = true;
		for(int i=1;i<=100;i++){
			
			while(! isRunning){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
					continue;
				}
			}
			
			progressBar.setValue(i);
			label.setText(i+"");
			synchronized (totalLabel) {
				String text = totalLabel.getText();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
		//		Integer total = Integer.parseInt(text.substring(text.indexOf("Grand Total: ")+"Grand Total: ".length()));
		//		System.out.println(total);
				
				Integer total = Integer.parseInt(text)+1;
				totalLabel.setText(total+"");
			}
			
			
			
			try {
				Thread.sleep(interval*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				continue;
				
			}
		}
		
	}

	
	
	public void pauseCounter(){
		this.isRunning = false;
	}

    public void resumeCounter(){
    	this.isRunning = true;
    }

}
