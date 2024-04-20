package commands;

import com.vk.api.sdk.objects.messages.Message;
import stock.StockList;
import vkcore.VKManager;

public class GetStockCommand extends Command {
	public GetStockCommand(String name) {
		super(name);
	}

	@Override
	public void exec(Message message) {
		new VKManager().sendMessage(getStock(), message.getUserId());
	}

	private String getStock() {
		String stock = "";

		for (int i = 0; i < 9; i++) {
			stock = StockList.getStock();
		}
		return stock;
	}
}