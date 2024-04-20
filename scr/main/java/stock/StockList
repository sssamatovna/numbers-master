package stock;

/*
 * хранилище страницы, чтобы можно было получить данные по компании в GetStockCommand
 * */
public class StockList {
	private static String[][] stockList;
	private static String stock = "";

	public static String[][] getStockList() {
		return stockList;
	}

	public static void setStockList(String[][] stocks) {
		stockList = stocks;
	}

	public static String getStock() {
		return stock;
	}

	public static void setStock(int line) {
		stock += "\"" + stockList[line][0] + "\"\n\n";

		stock += "Последняя сделка:\n" + stockList[line][1] + " 💸\n\n";

		String emoji = "📈";
		for (String sign : stockList[line][2].split("")) {
			if (sign.equals("-")) {
				emoji = "📉";
			}
		}
		stock += "Изменение цены:\n" + stockList[line][2] + emoji + "\n\n";

		stock += "Открытие:\n" + stockList[line][3] + " 🔓\n\n";
		stock += "Максимальная:\n" + stockList[line][4] + " 🐘\n\n";
		stock += "Минимальная:\n" + stockList[line][5] + " 🐁\n\n";
		stock += "Закрытие:\n" + stockList[line][6] + " 🔒\n\n";
		stock += "Объем:\n" + stockList[line][7] + " 📊\n\n";
		stock += "Время обновления:\n" + stockList[line][8] + " ⌚\n\n";
	}
}
