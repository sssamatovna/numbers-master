package commands.logic;

import com.vk.api.sdk.objects.messages.Message;
import commands.Command;
import commands.UnknownCommand;
import stock.StockList;

import java.util.Collection;

/*
 * производит выборку команды из коллекции по первому слову в сообщении
 * */
public class CommandDeterminant {
	public static Command getCommand(Collection<Command> commands, Message message) {
		String body = message.getBody().toLowerCase();

		if (StockList.getStockList() != null) {
			for (int i = 0; i < 30; i++) {
				if (StockList.getStockList()[i][0].toLowerCase().equals(body)) {

					StockList.setStock(i);
					body = "GetStock";
				}
			}
		}

		for (Command command : commands) {
			if (command.name.equals(body.split(" ")[0])) {
				return command;
			}
		}
		return new UnknownCommand("Unknown");
	}
}