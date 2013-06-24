package pro.geektalk.key.jobs;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.GameObject;

import pro.geektalk.key.framework.Node;

public class Opening extends Node {

	public Opening(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return CHEST_AREA.contains(ctx.players.getLocal())
				&& ctx.inventory.select().name("Crystal key").size() > 0
				&& ctx.objects.select().id(CHEST_ID).size() == 1;
	}

	@Override
	public void execute() {
		GameObject chest = null;
		for (GameObject objs : ctx.objects.select().id(CHEST_ID).first()) {
			log("Assigning chest");
			if (objs != null)
				chest = objs;
		}
		if (chest != null) {
			log("found chest -- opening");
			if (chest.interact("Open", "Closed chest")) {
				sleep(600); // sleep one game tick
				final Component cont = ctx.widgets.get(1186, 7);
				final Component use = ctx.widgets.get(1188, 20);
				if (cont.isValid() && cont.isVisible()) {
					ctx.keyboard.send(" ", false);
					sleep(600);
				}
				if (use.isValid() && use.isVisible()) {
					ctx.keyboard.send("1", false);
					sleep(600);
				}

			}
		}
	}

}
