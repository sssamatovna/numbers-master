package commands;

import com.vk.api.sdk.objects.messages.Message;
import stock.StockParsing;
import vkcore.VKManager;

import java.io.IOException;

public class NextCommand extends Command {
	private static int page = 1;

	public NextCommand(String name) {
		super(name);
	}

	@Override
	public void exec(Message message) {
		new VKManager().sendMessage(getStock(), message.getUserId());
	}

	private String getStock() {
		page++;

		String stockList = "";

		try {
			String[][] stock = new StockParsing().getStock(page);

			for (int i = 0; i < 30; i++) {
				stockList += "\"" + stock[i][0] + "\" " + getEmoji(stock[i][2]) + "\n";
			}

			return stockList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getEmoji(String percentage) {
		String emoji = "📈";

		for(String sign : percentage.split("")) {
			if (sign.equals("-")) {
				emoji = "📉";
			}
		}
		return emoji;
	}
}