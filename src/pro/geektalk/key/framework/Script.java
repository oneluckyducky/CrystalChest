package pro.geektalk.key.framework;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;

public abstract class Script extends PollingScript implements PaintListener {

	private final Set<Node> container = new HashSet<Node>();

	private Iterator<Node> iterator = null;

	private Node task = null;

	public abstract void onStart();

	public abstract void onStop();

	private boolean run = false;

	public Set<Node> getContainer() {
		return container;
	}

	public void setRun(boolean t) {
		log.info(t ? "Running" : "Pausing");
		run = t;
	}

	public enum Task {
		ADD, REMOVE;
	}

	public void log(final Object object, final boolean formatted) {
		System.out.println(formatted ? String.format("[%s] %s",
				"Crystal Chest Looter", object) : object);

	}

	public void log(final Object object) {
		log(object, true);
	}

	public void modify(Task task, Node... nodes) {
		switch (task) {
		case ADD:
			for (Node n : nodes) {
				if (n != null && !container.contains(n)) {
					container.add(n);
					log.info(n.getClass().getSimpleName()
							+ " added to container new size: "
							+ container.size());
				}
			}
			break;
		case REMOVE:
			for (Node n : nodes) {
				if (n != null && container.contains(n)) {
					container.remove(n);
					log.info(n.getClass().getSimpleName()
							+ " removed from container new size: "
							+ container.size());
				}
			}
			break;
		}
	}

	public Script() {
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				onStart();
			}
		});
		getExecQueue(State.STOP).add(new Runnable() {
			@Override
			public void run() {
				onStop();
			}
		});
		getExecQueue(State.SUSPEND).add(new Runnable() {
			@Override
			public void run() {
				setRun(false);
			}
		});
		getExecQueue(State.RESUME).add(new Runnable() {
			@Override
			public void run() {
				setRun(true);
			}
		});
	}

	@Override
	public int poll() {
		if (GlobalProvider.loop == 0) {
			super.stop();
		}
		if (run && container.size() > 0) {
			if (iterator == null || !iterator.hasNext()) {
				iterator = container.iterator();
			} else {
				task = iterator.next();
				if (task != null && task.activate()) {
					task.execute();
				}
			}
		}
		return GlobalProvider.loop;
	}

}
