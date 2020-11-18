package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel messageLabel;
    private String message;

    /**
     * MODIFIES: this
     * EFFECTS : Instantiates the Info dialog and shows the message
     */
    public InfoDialog(String message) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.message = message;
        this.messageLabel.setText(this.message);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    /**
     * EFFECTS: Closes the dialog when the okay button is pressed
     */
    private void onOK() {
        // add your code here
        dispose();
    }
}
