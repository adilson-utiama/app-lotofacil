package aplicativo.lotofacil.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import sun.net.www.content.image.jpeg;
import jdk.nashorn.internal.runtime.regexp.joni.ast.QuantifierNode;
import aplicativo.lotofacil.model.Jogo;

public class PainelJogos extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JPanel valores;
	private JLabel quantJogos;
	private JLabel valorJogos;
	private JLabel teimosinha;
	
	private JPanel painelViewEstatisticas; 
	private JPanel painelViewJogos;
	private JPanel painelViewBotoes;
	private JPanel tecladoEspaco;
	private Dimension tamanho;
	
	private PainelNumeros teclado;
	private PainelInfo info;
	
	private JTable tabela = new JTable();
	private TabelaModeloLoteria modelo;
	private JScrollPane js;
	private double parte;
	
	private JPopupMenu menu;
	private JMenuItem item;

	private Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 15);
		
	public PainelJogos(Dimension tamanho){
		this.tamanho = tamanho;
		
		setLayout(new BorderLayout());
		
		initLayout();
		initListeners();
		
		add(painelViewEstatisticas, BorderLayout.WEST);
		add(painelViewJogos, BorderLayout.CENTER);
		add(painelViewBotoes, BorderLayout.EAST);
		
	}

	private void initLayout() {
		tecladoEspaco = new JPanel();
		tecladoEspaco.setLayout(new BorderLayout());
		tecladoEspaco.setPreferredSize(new Dimension(300,300));
		info = new PainelInfo();
		//info.setPreferredSize(new Dimension(200,50));
		parte = tamanho.getWidth() / 5;
		initComponents();
	
		painelViewEstatisticas = new JPanel();
		painelViewEstatisticas.setLayout(new BorderLayout());
		painelViewEstatisticas.setPreferredSize(new Dimension((int)parte, (int)tamanho.getHeight()));
		tecladoEspaco.add(teclado, BorderLayout.CENTER);
		painelViewEstatisticas.add(tecladoEspaco, BorderLayout.NORTH);
		painelViewEstatisticas.add(info, BorderLayout.CENTER);
		painelViewEstatisticas.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
		
		painelViewJogos = new JPanel(null);
		//painelViewJogos.setPreferredSize(new Dimension((int)(parte * 3), (int)tamanho.getHeight()- 200));
		//js.setPreferredSize(new Dimension((int)(parte * 3), (int)tamanho.getHeight() - 400));
		js.setBounds(10, 15, (int)(parte * 3) - 30, (int)tamanho.getHeight() - 300);
		painelViewJogos.add(js, BorderLayout.CENTER);
		valores = new JPanel();
		valores.setLayout(null);
		valores.setBounds(10, 520,(int)(parte * 3) - 30, 50);
		valores.add(quantJogos);
		valores.add(valorJogos);
		valores.add(teimosinha);
		painelViewJogos.add(valores);
		painelViewJogos.setBackground(Color.LIGHT_GRAY);
		painelViewJogos.setBorder(BorderFactory.createRaisedBevelBorder());
		
		painelViewBotoes = new JPanel(new BorderLayout());
		painelViewBotoes.setPreferredSize(new Dimension((int)parte, (int) tamanho.getHeight()));
		painelViewBotoes.add(new PainelBotoesJogos(modelo), BorderLayout.CENTER);
		painelViewBotoes.setBackground(Color.LIGHT_GRAY);
		painelViewBotoes.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
	}

	private void initComponents() {
		menu = new JPopupMenu("Otions");
		item = new JMenuItem("Deletar");
		menu.add(item);
		
		quantJogos = new JLabel("Quantidade de Jogos: ");
		quantJogos.setFont(fonte );
		quantJogos.setBounds(10, 10, 260, 30);
		valorJogos = new JLabel("Valor Total: R$");
		valorJogos.setFont(fonte);
		valorJogos.setBounds(220, 10, 250, 30);
		teimosinha = new JLabel("Valor com teimosinha de 3: ");
		teimosinha.setFont(fonte);
		teimosinha.setBounds(400, 10, 300, 30);
				
		teclado = new PainelNumeros((int) parte - 40, 250, 15);
		
		modelo = new TabelaModeloLoteria();
		
		tabela.setFont(new Font(Font.SERIF, Font.BOLD, 15));
		tabela.setDefaultRenderer(Object.class, new CelulaTabela());
		tabela.setModel(modelo);
		tabela.setForeground(Color.BLUE);
		tabela.setRowHeight(30);
		tabela.setToolTipText("Para deletar linha clique em DELETE.");
		tabela.add(menu);
		js = new JScrollPane(tabela);
		
	}
	
	private void initListeners(){
		tabela.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					int linha = tabela.getSelectedRow();
					Jogo jogo = modelo.getJogo(linha);
					teclado.setNumeros(jogo.getJogo());
					info.setRepetidos(jogo);
				}
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3){
					menu.show(tabela, e.getX(), e.getY());
					item.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							int linha = tabela.getSelectedRow();
							if(tabela.isRowSelected(linha)){
								modelo.deletarJogo(linha);
							}
								
						}
					});
				}
				
			}
		});
	
		tabela.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				int linha = tabela.getSelectedRow();
				Jogo jogo = modelo.getJogo(linha);
				teclado.setNumeros(jogo.getJogo());
				info.setRepetidos(jogo);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE){
					
					if(modelo.getRowCount() > 0){
						int linha = tabela.getSelectedRow();
						int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja apagar Jogo?");
						if(confirmacao == 0){
							modelo.deletarJogo(linha);
						}
						
					}
					
				}
			}
		});
		
		modelo.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				int count = modelo.getRowCount();
				quantJogos.setText("Quantidade de Jogos: " + count);
				String valores2 = getValores(count, 1);
				String valores3 = getValores(count, 3);
				valorJogos.setText("Valor Total: " + valores2);
				teimosinha.setText("Valor com teimosinha de 3: " + valores3);
			}
		});
	}
	
	private String getValores(int jogos, int q){
		double value = (jogos *= 2.00) * q;
		String format = NumberFormat.getCurrencyInstance().format(value);
		
		return format;
	}
}
