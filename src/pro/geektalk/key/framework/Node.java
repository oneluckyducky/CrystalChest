package pro.geektalk.key.framework;

import java.util.logging.Logger;

import org.powerbot.script.methods.MethodContext;

public abstract class Node extends GlobalProvider {

	public Node(MethodContext ctx) {
		super(ctx);
	}

	public final Logger log = Logger.getLogger(getClass().getName());

	public abstract boolean activate();

	public abstract void execute();

	public void log(final Object object, final boolean formatted) {
		System.out.println(formatted ? String.format("[%s] %s",
				"Crystal Chest Looter", object) : object);
	}

	public void log(final Object object) {
		log(object, true);
	}

	@Override
	public String toString() {
		return "[".concat(this.getClass().getName().concat("]"));
	}

}
