package commands;

import com.vk.api.sdk.objects.messages.Message;
import vkcore.VKManager;

public class UnknownCommand extends Command {

	public UnknownCommand(String name) {
		super(name);
	}

	@Override
	public void exec(Message message) {
		new VKManager().sendMessage("Неизвестная команда.", message.getUserId());
	}
}