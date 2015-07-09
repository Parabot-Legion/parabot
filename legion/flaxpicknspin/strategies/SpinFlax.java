package parabot.legion.flaxpicknspin.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.SceneObjects.Option;
import org.rev317.min.api.wrappers.SceneObject;

import parabot.legion.flaxpicknspin.data.AreaInformation;
import parabot.legion.flaxpicknspin.data.Constants;
import parabot.legion.flaxpicknspin.data.Teleport;
import parabot.legion.flaxpicknspin.data.Variables;

public class SpinFlax implements Strategy {

	@Override
	public boolean activate() {
		return (Variables.spinFlax() || Variables.pickFlaxSpinFlax()) && Inventory.getCount(1780) == 28;
	}

	@Override
	public void execute() {

		SceneObject[] lowerLadder = SceneObjects.getNearest(1747);
		SceneObject[] spinningWheel = SceneObjects.getNearest(2644);
		if (AreaInformation.AT_BANK.contains(Players.getMyPlayer().getLocation())) {
			Constants.TO_SPIN_FLAX.walkTo();
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return AreaInformation.LOWER_SPINNER.contains(Players.getMyPlayer().getLocation());
				}
			}, 3000);
		} else {
			if (AreaInformation.AT_FLAX.contains(Players.getMyPlayer().getLocation())) {
				Teleport.SKILLING_SEERS_VILLAGE.Teleport();
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return AreaInformation.AT_BANK.contains(Players.getMyPlayer().getLocation());
					}
				}, 3000);
			}
		}
		if (AreaInformation.LOWER_SPINNER.contains(Players.getMyPlayer().getLocation())) {
			if (Inventory.getCount(1780) == 28) {
				if (Game.getPlane() == 0) {
					if (lowerLadder != null && lowerLadder.length > 0) {
						lowerLadder[0].interact(Option.FIRST);
						Time.sleep(new SleepCondition() {
							@Override
							public boolean isValid() {
								return spinningWheel.length > 0 && Game.getPlane() == 1;
							}
						}, 3000);
					}
				}
			}
		}
		if (Players.getMyPlayer().getAnimation() == -1) {
			if (Inventory.getCount(1780) == 28) {
				if (Game.getPlane() == 1) {
					if (spinningWheel != null && spinningWheel.length > 0) {
						spinningWheel[0].interact(SceneObjects.Option.SECOND);
						Time.sleep(new SleepCondition() {
							@Override
							public boolean isValid() {
								return Inventory.getCount(1780) <= 27;
							}
						}, 3000);
					}
				}
			}
		}
	}

}
