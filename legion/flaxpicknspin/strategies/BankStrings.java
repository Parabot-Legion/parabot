package parabot.legion.flaxpicknspin.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.SceneObjects.Option;
import org.rev317.min.api.wrappers.SceneObject;

import parabot.legion.flaxpicknspin.data.AreaInformation;
import parabot.legion.flaxpicknspin.data.Teleport;
import parabot.legion.flaxpicknspin.data.Variables;

public class BankStrings implements Strategy {

	@Override
	public boolean activate() {
		return (Variables.pickFlaxSpinFlax() || Variables.spinFlax()) && Inventory.getCount(1778) == 28
				|| Bank.isOpen();
	}

	@Override
	public void execute() {
		SceneObject[] bankBooth = SceneObjects.getNearest(2213);
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
					Bank.depositAllExcept();
					Time.sleep(new SleepCondition() {
						@Override
						public boolean isValid() {
							return Inventory.isEmpty();
						}
					}, 3000);
					// Check to see if it has to withdraw flax
					if (Variables.spinFlax()) {
						Menu.sendAction(431, 1779, Variables.bankSlot(1780), 5382);
						Time.sleep(1000);
					}
					Bank.close();
					Time.sleep(new SleepCondition() {
						@Override
						public boolean isValid() {
							return !Bank.isOpen();
						}
					}, 3000);
				}
			}
		} else {
			if (Inventory.getCount(1778) == 28) {
				if (!AreaInformation.AT_BANK.contains(Players.getMyPlayer().getLocation())) {
					Teleport.SKILLING_SEERS_VILLAGE.Teleport();
					Time.sleep(new SleepCondition() {
						@Override
						public boolean isValid() {
							return AreaInformation.AT_BANK.contains(Players.getMyPlayer().getLocation());
						}
					}, 3000);
				}
			}
		}
	}

}
