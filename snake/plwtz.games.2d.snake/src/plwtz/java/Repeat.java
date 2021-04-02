package plwtz.java;

import java.util.function.Consumer;

public class Repeat {

	public static <T> void repeat(int timesToRepeat, Consumer<T> command, T parameter) {
		for (int i = 0; i < timesToRepeat; i++)
			command.accept(parameter);
	}

	public static void repeat(int timesToRepeat, Runnable voidCommand) {
		for (int i = 0; i < timesToRepeat; i++)
			voidCommand.run();
	}

}
