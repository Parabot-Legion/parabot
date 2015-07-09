package parabot.legion.flaxpicknspin.data;

import org.rev317.min.Loader;

import parabot.legion.flaxpicknspin.gui.Gui;

public class Variables {
	public static boolean bankFlax() {
		return Gui.comboBoxOptions.getSelectedIndex() == 1;
	}

	public static boolean spinFlax() {
		return Gui.comboBoxOptions.getSelectedIndex() == 2;
	}

	public static boolean pickFlaxSpinFlax() {
		return Gui.comboBoxOptions.getSelectedIndex() == 3;
	}

	public static int bankSlot(int itemID) {
		int[] bankIDs = Loader.getClient().getInterfaceCache()[5382].getItems();
		for (int i = 0; i < bankIDs.length; i++) {
			if (bankIDs[i] == itemID)
				return i;
		}
		return -1;
	}
}
