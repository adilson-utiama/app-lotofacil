package aplicativo.lotofacil.util;

import aplicativo.lotofacil.dao.LoteriasDB;
import aplicativo.lotofacil.jdbc.ConcursosDB;

public class BancoFactory {

	private static LoteriasDB loteriasDB = new LoteriasDB();
	private static ConcursosDB concursosDB = new ConcursosDB();
	
	public static ConcursosDB getBanco(){
		concursosDB = loteriasDB.lerObjetoDB();
		return concursosDB;
	}
}
