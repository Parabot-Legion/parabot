package parabot.legion.flaxpicknspin.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

import parabot.legion.flaxpicknspin.data.AreaInformation;
import parabot.legion.flaxpicknspin.data.Constants;
import parabot.legion.flaxpicknspin.data.Variables;

public class WalkToFlax implements Strategy {
	TilePath path = new TilePath(Constants.TO_FLAX);

	@Override
	public boolean activate() {
		return (Variables.bankFlax() || Variables.pickFlaxSpinFlax()) && Inventory.isEmpty()
				&& !AreaInformation.AT_FLAX.contains(Players.getMyPlayer().getLocation()) && Game.getPlane() == 0;
	}

	@Override
	public void execute() {
		// Credits to JKetelaar partially helping with walking
		for (final Tile tile : path.getTiles()) {
			if (tile.isReachable() && tile.isWalkable()) {
				tile.walkTo();
			}
			while (tile.distanceTo() > 5) {
				tile.walkTo();
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return tile.distanceTo() <= 3;
					}
				}, 2500);
			}
		}

	}

}
