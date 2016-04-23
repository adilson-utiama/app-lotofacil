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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import aplicativo.lotofacil.dao.LoteriasDB;
import aplicativo.lotofacil.jdbc.ConcursosDB;
import aplicativo.lotofacil.model.Jogo;
import aplicativo.lotofacil.util.LotoFacilUtil;

public class PainelConfere extends JPanel {

	private static final long serialVersionUID = -7457982166041143826L;

	private Dimension tamanho;
	
	private JPanel tabelaEspaco;
	private JTable tabela;
	private JScrollPane tabelaBarra;
	private TabelaModeloLoteriaConfer modelo;
	private JPanel painelNumerosEspaco;
	private PainelNumeros painelNumeros;
	private JButton carregar;
	
	private JTextArea areaText = new JTextArea();
	
	private JScrollPane area = new JScrollPane(areaText);
	
	private JLabel acertos11, acertos12, acertos13, acertos14, acertos15;
	private JLabel acertos11R, acertos12R, acertos13R, acertos14R, acertos15R;
	
	private JPanel painelEsquerdo;
	private JPanel painelCentral;
	private JPanel painelDireito;

	private double partes;
	
	Scanner sc = null;
	File file = null;
	
	
	JFileChooser js = new JFileChooser("C:/Users/adilson/Dropbox/_Lotofacil Confere/Jogos");
	LoteriasDB db = new LoteriasDB();
	LotoFacilUtil util = new LotoFacilUtil();
	ConcursosDB database = db.lerObjetoDB();

	public PainelConfere(Dimension tamaho){
		this.tamanho = tamaho;
		this.setSize(tamaho);
		this.setLayout(new BorderLayout());
		
		initlayouts();
		initComponents();
		initSizes();
		initLayoutConfig();
		initListeners();
					
		this.add(painelEsquerdo, BorderLayout.WEST);
		this.add(painelCentral, BorderLayout.CENTER);
		this.add(painelDireito, BorderLayout.EAST);
	}

	private void initlayouts() {
		painelEsquerdo = new JPanel();
		painelCentral = new JPanel();
		painelDireito = new JPanel();
		
		painelCentral.setLayout(null);
		
		tabelaEspaco =  new JPanel();
		tabelaEspaco.setLayout(new BorderLayout());
		
		painelNumerosEspaco = new JPanel();
		painelNumeros = new PainelNumeros(245, 250, 15);
		
	}
	
	private void initComponents(){
		
		modelo = new TabelaModeloLoteriaConfer();
		tabela = new JTable();
		tabela.setModel(modelo);
		tabela.setDefaultRenderer(Object.class, new CelulaTabela());
		tabela.setForeground(Color.BLUE);
		tabela.setRowHeight(30);
		tabela.setFont(new Font(Font.SERIF, Font.BOLD, 15));
		
		tabelaBarra = new JScrollPane(tabela);
		carregar = new JButton("Carregar");
		
		areaText.setFont(new Font(Font.SERIF, Font.BOLD, 15));
		
		acertos11 = new JLabel("11 acertos: "); 
		acertos12 = new JLabel("12 acertos: "); 
		acertos13 = new JLabel("13 acertos: "); 
		acertos14 = new JLabel("14 acertos: "); 
		acertos15 = new JLabel("15 acertos: ");
		
		acertos11R = new JLabel();
		acertos12R = new JLabel();
		acertos13R = new JLabel();
		acertos14R = new JLabel();
		acertos15R = new JLabel();
	}
	
	private void initSizes() {
		partes = tamanho.getWidth() / 5;
		
		painelEsquerdo.setPreferredSize(new Dimension((int)partes, (int)tamanho.getHeight()));
		painelCentral.setPreferredSize(new Dimension((int)(partes * 3), (int)tamanho.getHeight()));
		painelDireito.setPreferredSize(new Dimension((int)partes, (int) tamanho.getHeight()));
		
		painelNumerosEspaco.setPreferredSize(new Dimension(250,280));
		//tabelaBarra.setBounds(10,10,600,250);
		tabelaEspaco.setBounds(10, 15, (int)(partes * 3) - 30, (int)tamanho.getHeight() - 400);
		
		carregar.setBounds(450, 450, 150, 40);
		
		area.setBounds(15, 420, 300, 300);
		
	}
	
	private void initLayoutConfig(){
		painelEsquerdo.setBorder(BorderFactory.createRaisedBevelBorder());
		painelEsquerdo.setBackground(Color.LIGHT_GRAY);
		painelEsquerdo.setLayout(new BorderLayout());
		painelEsquerdo.add(painelNumerosEspaco, BorderLayout.NORTH);
		
		painelCentral.setBorder(BorderFactory.createRaisedBevelBorder());
		painelCentral.setBackground(Color.LIGHT_GRAY);
		painelCentral.add(tabelaEspaco);
		painelCentral.add(carregar);
		
		painelCentral.add(area);
		
		painelNumerosEspaco.setLayout(new BorderLayout());
		painelNumerosEspaco.add(painelNumeros, BorderLayout.CENTER);
		tabelaEspaco.add(tabelaBarra, BorderLayout.CENTER);
		
		painelDireito.setBorder(BorderFactory.createRaisedBevelBorder());
		painelDireito.setBackground(Color.LIGHT_GRAY);
	}
	
	private void preencherPainelNumerico(int jogo){
		String string = database.exibir(jogo);
		painelNumeros.setNumerosSorteados(string);
	}
	
	private void initListeners(){
		carregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				painelNumeros.limparTela();
				painelNumeros.limparSorteados();
				areaText.setText(null);
				modelo.ApagarLista();
				
				int jogo = 0;
				int frequency = 0;
				int acertos11 = 0;
				int acertos12 = 0;
				int acertos13 = 0;
				int acertos14 = 0;
				int acertos15 = 0;
				List<Integer> concursoResult = new LinkedList<>();
				int returnVal = js.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	file = js.getSelectedFile();
			    	
			    }
			    String concurso = null;
			    do{
			    	
			    	concurso = JOptionPane.showInputDialog("Digite o numero do Concurso:");
			    	if(Integer.parseInt(concurso) > database.concursosCadastrados()){
			    		JOptionPane.showMessageDialog(null, "Concurso ainda não ocorreu!");
			    	}
			    }while(Integer.parseInt(concurso) > database.concursosCadastrados());
			    
			    preencherPainelNumerico(Integer.parseInt(concurso));
			    
			    String resultado = database.exibir(Integer.parseInt(concurso));
			    
			    List<Integer> lista = util.stringParaLista(resultado);
			    for (Integer numero : lista) {
					concursoResult.add(numero);
				}
							     
				try {
					sc = new Scanner(new FileInputStream(file));
				} catch (FileNotFoundException error) {
					error.printStackTrace();
				}
				
				while(sc.hasNext()){
					String line = null;
					line = sc.nextLine();
					
					Jogo jogoAdd = new Jogo(line);
					jogoAdd.setId(jogo + 1);
					modelo.adicionaJogo(jogoAdd);
					
					jogo++;
					String[] numeros = line.split(" ");
					for (String string : numeros) {
						int number = Integer.parseInt(string);
						if(concursoResult.contains(number)){
							frequency++;
						}
					}
					
					areaText.append("Jogo " + jogo + " / Frequencia: " + frequency + "\n");
								
					switch(frequency){
					case 11:
						acertos11++;
						break;
					case 12:
						acertos12++;
						break;
					case 13:
						acertos13++;
						break;
					case 14:
						acertos14++;
						break;
					case 15:
						acertos15++;
						break;
					default:
						break;	
					}
					
					frequency = 0;
					//numerosConferir.clear();
				}
				
				double valor = jogo * 2.00;
				String valorTotal = NumberFormat.getCurrencyInstance().format(valor);
				
				String valor11 = NumberFormat.getCurrencyInstance().format(acertos11 * 4.00);
				String valor12 = NumberFormat.getCurrencyInstance().format(acertos12 * 8.00);
				String valor13 = NumberFormat.getCurrencyInstance().format(acertos13 * 20.00);
				String valor14 = NumberFormat.getCurrencyInstance().format(acertos14 * 1500);
				String valor15 = NumberFormat.getCurrencyInstance().format(acertos15 * 1500000);
				
				double somaGanhos = (acertos11 * 4.00) + (acertos12 * 8.00) + (acertos13 * 20.00) + (acertos14 * 1500) + (acertos15 * 1500000);
				String ganhosTotal = NumberFormat.getCurrencyInstance().format(somaGanhos);
				
				String lucro = NumberFormat.getCurrencyInstance().format(somaGanhos - (jogo * 2.00));
				
				String conferido = "Concurso: " + concurso + "\n" +
						"11 acertos: " + acertos11 + "\n" +
						"12 acertos: " + acertos12 + "\n" +
						"13 acertos: " + acertos13 + "\n" +
						"14 acertos: " + acertos14 + "\n" +
						"15 acertos: " + acertos15 + "\n\n" +
						"Valor Total: " + valorTotal + "\n\n" +
						"Ganhos em 11 acertos: " + valor11 + "\n" + 
						"Ganhos em 12 acertos: " + valor12 + "\n" + 
						"Ganhos em 13 acertos: " + valor13 + "\n" + 
						"Ganhos em 14 acertos: " + valor14 + "\n" + 
						"Ganhos em 15 acertos: " + valor15 + "\n\n" +
						"Total de ganhos: " + ganhosTotal + "\n\n" + 
						"Diferença : " + lucro;
				
				//System.out.println(conferido);
				areaText.append(conferido);
				jogo = 0;
				sc.close();
			}
			
		});
		
		tabela.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					int linha = tabela.getSelectedRow();
					Jogo jogo = modelo.getJogo(linha);
					painelNumeros.setNumeros(jogo.getJogo());
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
				painelNumeros.setNumeros(jogo.getJogo());
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
	}
}
