package commands;

import com.vk.api.sdk.objects.messages.Message;
import stock.StockParsing;
import vkcore.VKManager;

import java.io.IOException;

public class HelloCommand extends Command {
	public HelloCommand(String name) {
		super(name);
	}

	@Override
	public void exec(Message message) {
		new VKManager().sendMessage(getStock(), message.getUserId());
	}

	private String getStock() {
		String hello = "Привет! 👾\n" +
				"Скажи мне название компании из списка, чтобы посмотреть ее котировки 📊\n" +
				"Напиши \"следующие\", что бы я показал остальные 👉\n\n" ;

		try {
			String[][] stock = new StockParsing().getStock(1);

			for (int i = 0; i < 30; i++) {
					hello += "\"" + stock[i][0] + "\" " + getEmoji(stock[i][2]) + "\n";
			}

			return hello;
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