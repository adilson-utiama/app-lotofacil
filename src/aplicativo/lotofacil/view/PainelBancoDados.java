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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import aplicativo.lotofacil.dao.LoteriasDB;
import aplicativo.lotofacil.jdbc.ConcursosDB;
import aplicativo.lotofacil.model.Jogo;
import aplicativo.lotofacil.util.LotoFacilUtil;

public class PainelBancoDados extends JPanel {

	private static final long serialVersionUID = -8987990903397933803L;
	
	private LoteriasDB db;
	private ConcursosDB database;
	
	private JPanel painelTeclado;
	private JButton adicionar;
	private JButton limpar;
	private TecladoDigital tecladoInclusao;
	private LotoFacilUtil util;
	
	private JPanel painelEsquerdo; 
	private JPanel painelCentral;
	private JPanel painelDireito;
	private Dimension tamanho;
	private PainelNumeros teclado;
	private JTable tabela = new JTable();
	private TabelaModeloLoteriaBanco modelo;
	private JScrollPane js;
	private double parte;
	
		
	public PainelBancoDados(Dimension tamanho){
		this.tamanho = tamanho;
		
		setLayout(new BorderLayout());
		
		initLayout();
		initListeners();
		
		popularTabela();
		
		add(painelEsquerdo, BorderLayout.WEST);
		add(painelCentral, BorderLayout.CENTER);
		add(painelDireito, BorderLayout.EAST);
		
	}

	private void popularTabela() {
		modelo.ApagarLista();
		int cadastrados = database.concursosCadastrados();
		
		for (int i = cadastrados; i > 0; i--) {
			Jogo jogo = new Jogo(i, database.exibir(i));
			modelo.adicionaJogo(jogo);
		}
		
	}

	private void initLayout() {
		parte = tamanho.getWidth() / 5;
		initComponents();
	
		painelEsquerdo = new JPanel();
		painelEsquerdo.setLayout(new BorderLayout());
		painelEsquerdo.setPreferredSize(new Dimension((int)parte, (int)tamanho.getHeight()));
		painelEsquerdo.add(teclado, BorderLayout.CENTER);
		painelEsquerdo.setBorder(BorderFactory.createRaisedBevelBorder());
				
		painelTeclado = new JPanel();
		painelTeclado.setLayout(new BorderLayout());
		painelTeclado.setBounds(50, 420, 350, 310);
		tecladoInclusao = new TecladoDigital(300, 280, 15);
		painelTeclado.add(tecladoInclusao, BorderLayout.CENTER);
		
		adicionar = new JButton("Adicionar");
		adicionar.setBounds(410, 450, 150, 40);
		
		limpar = new JButton("Limpar");
		limpar.setBounds(410, 500, 150, 40);
		
		painelCentral = new JPanel(null);
		//painelViewJogos.setPreferredSize(new Dimension((int)(parte * 3), (int)tamanho.getHeight()));
		js.setBounds(10, 15, (int)(parte * 3) - 30, (int)tamanho.getHeight() - 400);
		painelCentral.add(js, BorderLayout.CENTER);
		painelCentral.add(painelTeclado);
		painelCentral.add(adicionar);
		painelCentral.add(limpar);
		painelCentral.setBackground(Color.LIGHT_GRAY);
		painelCentral.setBorder(BorderFactory.createRaisedBevelBorder());
		
		painelDireito = new JPanel(new BorderLayout());
		painelDireito.setPreferredSize(new Dimension((int)parte, (int) tamanho.getHeight()));
		painelDireito.setBackground(Color.LIGHT_GRAY);
		painelDireito.setBorder(BorderFactory.createRaisedBevelBorder());
		
	}

	private void initComponents() {
		db = new LoteriasDB();
		database = db.lerObjetoDB();
				
		teclado = new PainelNumeros((int) parte - 40, 250, 15);
		util = new LotoFacilUtil();
		modelo = new TabelaModeloLoteriaBanco();
		
		tabela.setFont(new Font(Font.SERIF, Font.BOLD, 15));
		tabela.setDefaultRenderer(Object.class, new CelulaTabela());
		tabela.setModel(modelo);
		tabela.setForeground(Color.BLUE);
		tabela.setRowHeight(30);
			
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
				}
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
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
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		adicionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				List<Integer> selecionados = tecladoInclusao.getNumerosSelecionados();
				if(selecionados.size() == 15){
					String numeros = util.listaParaString(selecionados);
					try{
						db.executarBackup(database);
						database.adiciona(numeros);
						db.gravarObjetoDB(database);
					}catch(Exception erro){
						JOptionPane.showMessageDialog(null, erro);
					}
					popularTabela();
				}else{
					JOptionPane.showMessageDialog(null, "Nao incuido!");
				}
				
			}
		});
		
		limpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tecladoInclusao.limparTela();
				
			}
		});
	}
}
