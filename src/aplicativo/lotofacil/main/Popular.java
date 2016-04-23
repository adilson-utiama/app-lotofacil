package aplicativo.lotofacil.main;

import aplicativo.lotofacil.dao.LoteriasDB;
import aplicativo.lotofacil.jdbc.ConcursosDB;

public class Popular {

	public static void main(String[] args) {
		
		LoteriasDB loteriasDB = new LoteriasDB();
		
		loteriasDB.importarDadosParaObjeto();
		loteriasDB.gravarObjetoDB();
	}
}
