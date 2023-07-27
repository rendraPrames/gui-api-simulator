package api;

import gui.ApiSimulatorGUI;

import javax.swing.*;

public class ApiSimulatorApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ApiSimulatorGUI gui = new ApiSimulatorGUI();
            gui.setVisible(true);
        });
    }
}
