package parabot.legion.flaxpicknspin.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import parabot.legion.flaxpicknspin.data.Variables;

public class Gui extends JFrame {

	private static final long serialVersionUID = 1L;

	public Gui() {
		initComponents();
	}

	private void buttonRunActionPerformed(ActionEvent e) {
		if (comboBoxOptions.getSelectedIndex() == 1) {
			Variables.bankFlax();
		} else {
			if (comboBoxOptions.getSelectedIndex() == 2) {
				Variables.spinFlax();
			} else {
				if (comboBoxOptions.getSelectedIndex() == 3) {
					Variables.pickFlaxSpinFlax();
				}
			}
		}
		setVisible(false);
	}

	private void initComponents() {
		title = new JLabel();
		label2 = new JLabel();
		tabbedPane1 = new JTabbedPane();
		panel2 = new JPanel();
		label4 = new JLabel();
		comboBoxOptions = new JComboBox<>();
		buttonRun = new JButton();
		label5 = new JLabel();

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		// ---- title ----
		title.setText("Flax Picker & Spinner");
		title.setFont(new Font("Trajan Pro", Font.BOLD, 14));
		contentPane.add(title);
		title.setBounds(5, 5, 210, title.getPreferredSize().height);

		// ---- label2 ----
		label2.setText("V1");
		contentPane.add(label2);
		label2.setBounds(220, 0, 35, label2.getPreferredSize().height);

		// ======== tabbedPane1 ========
		{

			// ======== panel2 ========
			{
				panel2.setLayout(null);

				// ---- label4 ----
				label4.setText("Choose Option");
				panel2.add(label4);
				label4.setBounds(0, 5, 95, label4.getPreferredSize().height);

				// ---- comboBoxOptions ----
				comboBoxOptions.setModel(new DefaultComboBoxModel<>(
						new String[] { " ", "Bank Flax", "Spin Flax From Bank", "Pick Flax Then Spin" }));
				panel2.add(comboBoxOptions);
				comboBoxOptions.setBounds(0, 25, 120, comboBoxOptions.getPreferredSize().height);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for (int i = 0; i < panel2.getComponentCount(); i++) {
						Rectangle bounds = panel2.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel2.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel2.setMinimumSize(preferredSize);
					panel2.setPreferredSize(preferredSize);
				}
			}
			tabbedPane1.addTab("Settings", panel2);
		}
		contentPane.add(tabbedPane1);
		tabbedPane1.setBounds(0, 40, 240, 115);

		// ---- buttonRun ----
		buttonRun.setText("Start");
		buttonRun.setFont(new Font("Tekton Pro", Font.BOLD, 12));
		buttonRun.addActionListener(e -> {
			buttonRunActionPerformed(e);
		});
		contentPane.add(buttonRun);
		buttonRun.setBounds(0, 185, 100, buttonRun.getPreferredSize().height);

		// ---- label5 ----
		label5.setText("   ");
		contentPane.add(label5);
		label5.setBounds(0, 230, 55, label5.getPreferredSize().height);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for (int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
	}

	private JLabel title;
	private JLabel label2;
	private JTabbedPane tabbedPane1;
	private JPanel panel2;
	private JLabel label4;
	public static JComboBox<String> comboBoxOptions;
	private JButton buttonRun;
	private JLabel label5;
}
