package pro.geektalk.key.jobs;

import java.util.EnumSet;

import org.powerbot.script.methods.Game;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Path.TraversalOption;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.TilePath;

import pro.geektalk.key.framework.Node;

public class Walking extends Node {

	@SuppressWarnings("unused")
	private final Tile bankCenterTile = new Tile(2876, 3417, 0);

	@SuppressWarnings("unused")
	private final Tile chestCenterTile = new Tile(2917, 3449, 0);

	private final Tile[] walkingPath = new Tile[] { new Tile(2875, 3416, 0),
			new Tile(2877, 3416, 0), new Tile(2882, 3417, 0),
			new Tile(2887, 3416, 0), new Tile(2893, 3416, 0),
			new Tile(2899, 3415, 0), new Tile(2905, 3415, 0),
			new Tile(2911, 3420, 0), new Tile(2956, 3431, 0),
			new Tile(2920, 3429, 0), new Tile(2921, 3436, 0),
			new Tile(2921, 3442, 0), new Tile(2922, 3448, 0),
			new Tile(2918, 3450, 0), };

	private Item key = null;

	public Walking(MethodContext ctx) {
		super(ctx);
		if (key == null)
			for (Item item : ctx.inventory.select().name("Crystal key")
					.limit(1))
				if (item != null)
					key = item;

	}

	private boolean toChest() {
		return !CHEST_AREA.contains(ctx.players.getLocal())
				&& ctx.inventory.select().name("Crystal key").size() > 0;
	}

	private boolean toBank() {
		return !BANK_AREA.contains(ctx.players.getLocal())
				&& (ctx.inventory.count() == 28 || ctx.inventory.select()
						.name("Crystal key").isEmpty());

	}

	@Override
	public boolean activate() {
		return toChest() || toBank();
	}

	@Override
	public void execute() {
		final TilePath path = ctx.movement.newTilePath(walkingPath);
		if (ctx.game.getClientState() == Game.INDEX_MAP_LOADED) {
			if (toChest()) {
				log("Walking to chest");
				path.traverse(EnumSet.of(TraversalOption.HANDLE_RUN));
				sleep(1800); // 3 game ticks
			} else {
				log("Walking to bank");
				path.reverse().traverse(EnumSet.of(TraversalOption.HANDLE_RUN));
				sleep(1800); // 3 game ticks
			}
		}
	}

}
