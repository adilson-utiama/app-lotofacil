package aplicativo.lotofacil.view;

import javax.swing.JLabel;

public class ImplementacaoRunnable extends Thread{

	private JLabel label;
	
	
	public ImplementacaoRunnable(JLabel label){
		this.label = label;
		
	}
	
	@Override
	public void run(){
		for (int i = 0; i < 15000; i++) {
	
			label.setText(String.valueOf(i));
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}
