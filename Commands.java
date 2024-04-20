package commands;

import java.util.HashSet;

public class Commands {
	private static HashSet<Command> commands = new HashSet<Command>();

	static {
		commands.add(new UnknownCommand("unknown"));
		commands.add(new HelloCommand("привет"));
		commands.add(new NextCommand("следующие"));
		commands.add(new GetStockCommand("GetStock"));
	}

	public static HashSet<Command> getCommands() {
		return commands;
	}

	public static void addCommand(Command command) {
		commands.add(command);
	}
}