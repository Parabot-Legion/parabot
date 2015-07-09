package parabot.legion.flaxpicknspin.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.SceneObjects.Option;
import org.rev317.min.api.wrappers.SceneObject;

import parabot.legion.flaxpicknspin.data.AreaInformation;
import parabot.legion.flaxpicknspin.data.Teleport;
import parabot.legion.flaxpicknspin.data.Variables;

public class BankItems implements Strategy {

	@Override
	public boolean activate() {
		return Variables.bankFlax() && Inventory.isFull();
	}

	@Override
	public void execute() {
		SceneObject[] bankBooth = SceneObjects.getNearest(2213);
		if (AreaInformation.AT_FLAX.contains(Players.getMyPlayer().getLocation())) {
			Teleport.SKILLING_SEERS_VILLAGE.Teleport();
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return AreaInformation.AT_BANK.contains(Players.getMyPlayer().getLocation());
				}
			}, 3000);
		}
		if (AreaInformation.AT_BANK.contains(Players.getMyPlayer().getLocation())) {
			if (!Bank.isOpen()) {
				if (bankBooth.length > 0 && bankBooth != null) {
					bankBooth[0].interact(Option.USE_QUICKLY);
					Time.sleep(new SleepCondition() {
						@Override
						public boolean isValid() {
							return Bank.isOpen();
						}
					}, 3000);
				}
			} else {
				if (Bank.isOpen()) {
					// Bank.depositAll(); Doesn't work.
					Bank.depositAllExcept();
					Time.sleep(new SleepCondition() {
						@Override
						public boolean isValid() {
							return Inventory.isEmpty();
						}
					}, 3000);
					Bank.close();
					Time.sleep(new SleepCondition() {
						@Override
						public boolean isValid() {
							return !Bank.isOpen();
						}
					}, 3000);
				}
			}
		}
	}

}
