package aplicativo.lotofacil.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PainelNumeros extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JButton n[] = new JButton[26];
	private List<Integer> numeros = new ArrayList<>();
	private int selecionavel;

	public PainelNumeros(int largura ,int altura ,int selecionavel){
		this.selecionavel = selecionavel;
		this.setBackground(Color.LIGHT_GRAY);
		this.setSize(new Dimension(largura, altura));
		this.setLayout(new FlowLayout());
		
		int botaoLargura = this.getWidth() / 5;
		int botaoAltura = this.getHeight() / 5;
		
		for(int i = 1; i <= 25; i++){
			String num;
			if(i < 10){
				num = "0" + i;
				this.add(n[i] = new JButton(num));
				n[i].setPreferredSize(new Dimension(botaoLargura, botaoAltura));
				//n[i].addActionListener(new ListenerBotoes(n[i]));
				n[i].setBackground(Color.WHITE);
				n[i].setBorder(BorderFactory.createRaisedSoftBevelBorder());
			}else{
				num = String.valueOf(i);
				this.add(n[i] = new JButton(num));
				n[i].setPreferredSize(new Dimension(botaoLargura, botaoAltura));
				//n[i].addActionListener(new ListenerBotoes(n[i]));
				n[i].setBackground(Color.WHITE);
				n[i].setBorder(BorderFactory.createRaisedSoftBevelBorder());
			}
			
		}
		
	}
	
	public List<Integer> getNumerosSelecionados(){
		Collections.sort(numeros);
		return numeros;
	}
	
	public void limparTela(){
		for(int i = 1; i <= 25; i++){
			this.n[i].setBorder(BorderFactory.createRaisedSoftBevelBorder());
			this.n[i].setBackground(Color.WHITE);
			//this.n[i].setForeground(Color.BLACK);
			this.n[i].setEnabled(true);
		}
		numeros.clear();
	}
	
	public void setNumeros(String jogo) {
		limparTela();
		String[] numeros = jogo.split(" ");
		for (String numero : numeros) {
			int num = Integer.parseInt(numero);
			this.n[num].setBackground(Color.ORANGE);
			//this.n[num].setForeground(Color.WHITE);
		}
		
	}
	
	public void setNumerosSorteados(String jogo){
		String[] numeros = jogo.split(" ");
		for (String numero : numeros) {
			int num = Integer.parseInt(numero);
			//this.n[num].setBackground(Color.ORANGE);
			this.n[num].setForeground(Color.RED);
		}
	}
	
	public void limparSorteados(){
		for(int i = 1; i <= 25; i++){
			this.n[i].setForeground(Color.BLACK);
		}
	}
	
	class ListenerBotoes implements ActionListener{
		
		JButton botao;
		
		public ListenerBotoes(JButton botao){
			this.botao = botao;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(numeros.size() >= selecionavel){
				JOptionPane.showMessageDialog(null, "Quantidade maxima numeros selecionados: " + numeros.size());
			}else{
				botao.setBackground(Color.GRAY);
				botao.setBorder(BorderFactory.createLoweredSoftBevelBorder());
				botao.setEnabled(false);
				numeros.add(Integer.parseInt(botao.getText()));
			}
			
		}
		
	}

	
}


