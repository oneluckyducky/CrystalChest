package pro.geektalk.key.jobs;

import org.powerbot.script.lang.ItemQuery;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Tile;

import pro.geektalk.key.framework.Node;

public class Banking extends Node {

	public Banking(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return BANK_AREA.contains(ctx.players.getLocal())
				&& ctx.inventory.select().name("Crystal key").isEmpty();
	}

	@Override
	public void execute() {
		// -- Calculate $ gained
		//
		if (!ctx.bank.isOpen()) {
			log("Opening bank");
			if (ctx.bank.open()) {
				final Timer timer = new Timer(2500);
				while (!ctx.bank.isOpen()) {
					sleep(50);
					if (!timer.isRunning())
						break;
				}
			}
		} else {
			if (!ctx.inventory.select().isEmpty() && depositInventory()) {
				log("Emptying inventory");
				final Timer timer = new Timer(2500);
				while (!ctx.inventory.select().isEmpty()) {
					sleep(50);
					if (!timer.isRunning())
						break;
				}
			}
			if (ctx.inventory.select().isEmpty()) {
				final ItemQuery<Item> query = ctx.bank.select().name(
						"Crystal key");
				if (query.size() > 0) {
					for (Item itm : ctx.bank.select()) {
						log("Withdrawing key");
						if (itm.getName().equalsIgnoreCase("Crystal key"))
							ctx.bank.withdraw(itm.getId(), 5);
					}
					if (ctx.inventory.select().size() > 0) {
						log("Leaving bank");
						ctx.movement.stepTowards(new Tile(2887, 3417, 0));
						sleep(1200);
					}
				} else {
					loop = 0;
					// ctx.bank.close();
				}
			}
		}
	}
}
