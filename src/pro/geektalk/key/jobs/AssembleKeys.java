package pro.geektalk.key.jobs;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Item;

import pro.geektalk.key.framework.Node;

public class AssembleKeys extends Node {

	public AssembleKeys(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return ctx.inventory.select().name("Tooth half of a key").size() > 0
				&& ctx.inventory.select().name("Loop half of a key").size() > 0;
	}

	@Override
	public void execute() {
		Item half = null;
		for (Item itm : ctx.inventory.select().name("Tooth half of a key")
				.first()) {
			log("Assigning Tooth half");
			if (itm != null)
				half = itm;
		}
		if (half != null) {
			log("Found Tooth half");
			half.click();
		}

	}

}
