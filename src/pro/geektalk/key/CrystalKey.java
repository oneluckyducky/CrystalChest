package pro.geektalk.key;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javafx.application.Application;

import org.powerbot.event.MessageEvent;
import org.powerbot.event.MessageListener;
import org.powerbot.script.Manifest;

import pro.geektalk.key.framework.GlobalProvider;
import pro.geektalk.key.framework.Script;
import pro.geektalk.key.jobs.AssembleKeys;
import pro.geektalk.key.jobs.Banking;
import pro.geektalk.key.jobs.Opening;
import pro.geektalk.key.jobs.Walking;

@Manifest(authors = { "OneLuckyDuck" }, description = "Open the crystal chest", name = "Crystal Chest Opener")
public class CrystalKey extends Script implements MessageListener {

	@Override
	public void onStart() {
		modify(Task.ADD, new Opening(ctx), new Walking(ctx), new AssembleKeys(
				ctx), new Banking(ctx));
		setRun(true);
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unused")
	private void showGui() {
		Application.launch(Gui.class);
	}

	@Override
	public void repaint(Graphics g1) {
		final Graphics2D g = (Graphics2D) g1;

		g.drawString(GlobalProvider.status, 10, 50);

	}

	@Override
	public void messaged(MessageEvent m) {
		final String msg = m.getMessage();
		if (msg.contains("unlock the chest")) {
			GlobalProvider.chestsOpened++;
		} else if (msg.contains("could not bank all")) {
			log.severe("Out of bank space!");
			super.stop();
		}

	}
}
