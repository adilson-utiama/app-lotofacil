package aplicativo.lotofacil.jdbc;

import aplicativo.lotofacil.dao.LoteriasDB;

public class FerramentasDB {

	
	
	public static void main(String[] args) {
		
		LoteriasDB db = new LoteriasDB();
		ConcursosDB database = db.lerObjetoDB();
		//database.removeD(1347);
		//database.removeD(1348);
		
		//database.adiciona("01 03 05 06 07 08 10 12 13 14 17 18 20 23 24");
		//database.remove(1347);
		//database.editar(1347, "01 02 03 04 05 06 07 08 09 10 11 12 13 14 15");
//		database.editar(1348, "01 02 06 07 08 09 10 14 15 18 20 21 22 24 25");
//		database.editar(1349, "02 03 04 05 06 07 08 12 14 16 18 19 21 24 25");
//		
		//db.gravarObjetoDB(database);
		
		System.out.println(database.exibir(1347));
		
		System.out.println(database.concursosCadastrados());
		//System.out.println(database.exibir(1347));
		
	}
	
}
