package pro.geektalk.key.framework;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.Tile;

import pro.geektalk.key.framework.api.RSArea;

public class GlobalProvider extends MethodProvider {

	public RSArea area = null;

	public static final RSArea BANK_AREA = new RSArea(new Tile(2871, 3421, 0),
			new Tile(2880, 3413, 0));

	public static final RSArea CHEST_AREA = new RSArea(new Tile(2913, 3454, 0),
			new Tile(2922, 3445, 0));

	public static final int CHEST_ID = 172;

	public static int loop = 100;

	public static int chestsOpened = 0;

	public static String status = "";

	public long money = 0;

	public GlobalProvider(MethodContext ctx) {
		super(ctx);
		area = new RSArea(ctx);
	}

	public boolean depositInventory() {
		final Component btn = ctx.widgets.get(762, 34);
		return btn.isValid() ? btn.click() : false;
	}
}
