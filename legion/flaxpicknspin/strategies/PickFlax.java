package parabot.legion.flaxpicknspin.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.SceneObjects.Option;
import org.rev317.min.api.wrappers.SceneObject;

import parabot.legion.flaxpicknspin.data.AreaInformation;
import parabot.legion.flaxpicknspin.data.Variables;

public class PickFlax implements Strategy {
	@Override
	public boolean activate() {
		return (Variables.bankFlax() || Variables.pickFlaxSpinFlax()) && !Inventory.isFull()
				&& AreaInformation.AT_FLAX.contains(Players.getMyPlayer().getLocation());
	}

	@Override
	public void execute() {
		SceneObject[] flax = SceneObjects.getNearest(2646, 2647);
		if (Players.getMyPlayer().getAnimation() == -1) {
			if (flax.length > 0 && flax != null) {
				flax[0].interact(Option.FIRST);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Players.getMyPlayer().getAnimation() != -1;
					}
				}, 3000);
			}

		}

	}

}
