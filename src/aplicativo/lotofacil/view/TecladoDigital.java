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

public class TecladoDigital extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JButton botoes[] = new JButton[26];
	private List<Integer> numeros = new ArrayList<>();
	private int selecionavel;

	public TecladoDigital(int largura ,int altura ,int selecionavel){
		this.selecionavel = selecionavel;
		
		this.setSize(new Dimension(largura, altura));
		this.setLayout(new FlowLayout());
		
		int botaoLargura = this.getWidth() / 5;
		int botaoAltura = this.getHeight() / 5;
		
		for(int i = 1; i <= 25; i++){
			String num;
			if(i < 10){
				num = "0" + i;
				this.add(botoes[i] = new JButton(num));
				setarBotoes(botaoLargura, botaoAltura, i);
			}else{
				num = String.valueOf(i);
				this.add(botoes[i] = new JButton(num));
				setarBotoes(botaoLargura, botaoAltura, i);
			}
			
		}
		
	}

	private void setarBotoes(int botaoLargura, int botaoAltura, int i) {
		botoes[i].setPreferredSize(new Dimension(botaoLargura, botaoAltura));
		botoes[i].addActionListener(new ListenerBotoes(botoes[i]));
		botoes[i].setBackground(Color.LIGHT_GRAY);
		botoes[i].setBorder(BorderFactory.createRaisedSoftBevelBorder());
	}
	
	public List<Integer> getNumerosSelecionados(){
		Collections.sort(numeros);
		return numeros;
	}
	
	public void limparTela(){
		for(int i = 1; i <= 25; i++){
			this.botoes[i].setBorder(BorderFactory.createRaisedSoftBevelBorder());
			this.botoes[i].setBackground(Color.LIGHT_GRAY);
			this.botoes[i].setEnabled(true);
		}
		numeros.clear();
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


