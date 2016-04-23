package aplicativo.lotofacil.testes;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GeradorTeste extends JFrame{

	private JLabel label = new JLabel();
	private JButton button = new JButton("Iniciar");
	
	public GeradorTeste(){
		setSize(300, 300);
		setLayout(new GridLayout(2,1));
		setLocationRelativeTo(null);
		
		add(label);
		add(button);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Thread(new Gerador(label)).start();
				
			}
		});
	}
	
	public static void main(String[] args) {
		
		new GeradorTeste().setVisible(true);
		
	}
}
