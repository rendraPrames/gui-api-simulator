package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiHandler {
    public static ApiResponse sendApiRequest(ApiRequest apiRequest) {
        try {
            // Create a URL object with the complete API URL including path and query parameters
            URL url = new URL(apiRequest.getUrl() + apiRequest.getPath());

            // Open a connection to the URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set the request method (GET, POST, PUT, DELETE, etc.)
            conn.setRequestMethod("GET"); // Modify this based on your API's requirements

            // Add any required request headers (e.g., Authorization, Content-Type)
            // conn.setRequestProperty("Authorization", "Bearer " + apiRequest.getAccessToken());
            // conn.setRequestProperty("Content-Type", "application/json");

            // Check the response code to see if the request was successful
            int responseCode = conn.getResponseCode();

            // Read the response data
            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
            }

            // Close the connection
            conn.disconnect();

            // Create an ApiResponse object and set the response data
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setResponse(response.toString());

            return apiResponse;
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any exceptions or errors here
            // You can throw a custom exception or return an error ApiResponse object
            return null;
        }
    }
}
