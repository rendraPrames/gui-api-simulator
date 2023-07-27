package gui;

import api.ApiRequest;
import api.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApiSimulatorGUI extends JFrame {

    private JButton executeButton;
    private JTextArea requestTextArea;
    private JTextArea responseTextArea;
    private JComboBox<String> apiTypeDropdown;
    private JTextField apiUrlField;

    public ApiSimulatorGUI() {
        // Initialize the frame and other components
        setTitle("API Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new GridBagLayout()); // Use GridBagLayout for more control

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding between components

        // Initialize text areas for request and response
        requestTextArea = new JTextArea(10, 40); // Set initial rows and columns
        responseTextArea = new JTextArea(10, 40);
        responseTextArea.setEditable(false); // Make response text area read-only

        // Initialize the dropdown list for API types
        String[] apiTypes = {"Payment", "User", "Product"}; // Add other API types as needed
        apiTypeDropdown = new JComboBox<>(apiTypes);
        apiTypeDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateApiUrlField();
            }
        });

        // Initialize the API URL input field
        apiUrlField = new JTextField(30); // Set initial columns
        apiUrlField.setEditable(false); // URL is updated based on selected API type

        // Initialize the button and add an action listener
        executeButton = new JButton("Execute");
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action to be performed when the button is clicked
                // For example, call the sendApiRequest method here
                sendApiRequest();
            }
        });

        // Add the components to the frame using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("API Type:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(apiTypeDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("API URL:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(apiUrlField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Request Body:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(requestTextArea), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(executeButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Response:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(responseTextArea), gbc);

        // Make the frame visible
        pack(); // Resize the frame to fit the components properly
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void updateApiUrlField() {
        // Update the API URL field based on the selected API type
        String apiUrl = "https://api.example.com"; // Replace with the base URL of your API
        String selectedApiType = apiTypeDropdown.getSelectedItem().toString().toLowerCase();
        apiUrlField.setText(apiUrl + "/" + selectedApiType);
    }

    private void sendApiRequest() {
        // Implement the API request handling here
        // You can get the request body from the requestTextArea
        String requestBodyText = requestTextArea.getText();

        // Parse the JSON data and create the ApiRequest object
        Person requestBody = parseRequestBody(requestBodyText);
        String apiUrl = apiUrlField.getText();

        // Create the ApiRequest instance
        ApiRequest apiRequest = new ApiRequest(apiUrl, requestBody);

        // For this example, we will simply display a sample response in the responseTextArea
        String successResponse = "{\"status\": \"success\", \"message\": \"Request processed successfully\", \"data\": " + requestBodyText + "}";
        responseTextArea.setText(successResponse);
    }

    private Person parseRequestBody(String requestBodyText) {
        // Implement the JSON parsing logic to create the Person object
        // For simplicity, we'll create a Person object manually
        Person person = new Person();
        person.setName("John Doe");
        person.setEmail("johndoe@example.com");
        person.setAge(30);
        person.setGender("Male");
        return person;
    }

    public static void main(String[] args) {
        // Create and display the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ApiSimulatorGUI();
            }
        });
    }
}