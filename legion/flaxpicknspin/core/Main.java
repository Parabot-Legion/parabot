package parabot.legion.flaxpicknspin.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;

import parabot.legion.flaxpicknspin.data.Constants;
import parabot.legion.flaxpicknspin.gui.Gui;
import parabot.legion.flaxpicknspin.strategies.BankItems;
import parabot.legion.flaxpicknspin.strategies.BankStrings;
import parabot.legion.flaxpicknspin.strategies.PickFlax;
import parabot.legion.flaxpicknspin.strategies.SpinFlax;
import parabot.legion.flaxpicknspin.strategies.WalkToFlax;

@ScriptManifest(author = "Legion", category = Category.OTHER, description = "Picks flax an banks or picks flaxs an spins or spins flax from bank. Start at flax fields and have flaxs in first bank slot ", name = "Flax Spin & Bank", servers = {
		"Ikov" }, version = 2)
public class Main extends Script implements MessageListener, Paintable {

	private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

	public boolean onExecute() {
		Gui GUI = new Gui();
		GUI.setVisible(true);
		while (GUI.isVisible()) {
			sleep(50);
		}
		strategies.add(new WalkToFlax());
		strategies.add(new PickFlax());
		strategies.add(new SpinFlax());
		strategies.add(new BankItems());
		strategies.add(new BankStrings());
		provide(strategies);
		return true;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.ITALIC, 14));

		g.drawString("Flax Count - " + Constants.FLAX_PICKS, 37, 58);
		g.drawString("Strings Made - " + Constants.STRINGS_MADE, 37, 75);
	}

	public void messageReceived(MessageEvent i) {
		if (i.getMessage().contains("into a bowstring")) {
			Constants.STRINGS_MADE += 1;
		} else if (i.getMessage().contains("pick some flax")) {
			Constants.FLAX_PICKS += 1;
		}
	}
}
