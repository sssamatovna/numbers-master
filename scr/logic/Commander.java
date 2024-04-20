package commands.logic;

import com.vk.api.sdk.objects.messages.Message;
import commands.Commands;

/*
 * первые узел в обработке команды
 * */
public class Commander {
	public static void execute(Message message) {
		CommandDeterminant.getCommand(Commands.getCommands(), message).exec(message);
	}
}
