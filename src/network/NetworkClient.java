package network;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.net.Socket;

/**
 * NetworkClient handles network communications for a client application.
 * It provides methods to connect to a server, send requests (like login, get drawings, add, update, and delete drawings),
 * and receive responses.
 */
public class NetworkClient {
    // Fields to store responses from the server
    public String loginResponse;
    public String drawingsResponse;
    public String addDrawingResponse;
    private JsonArray drawingsJsonArray;
    private JsonArray myDrawingsJsonArray;

    // Getters and setters for JSON arrays
    public JsonArray getDrawingsJsonArray() {
        return this.drawingsJsonArray;
    }

    public JsonArray getMyDrawingsJsonArray() {
        return this.myDrawingsJsonArray;
    }

    public void setMyDrawingsJsonArray(JsonArray jsonArray) {
        this.myDrawingsJsonArray = jsonArray;
    }

    public void setDrawingsJsonArray(String drawingsJsonArray) {
        try (JsonReader reader = Json.createReader(new StringReader(drawingsJsonArray))) {
            this.drawingsJsonArray = reader.readArray();
        }
    }

    // Socket for network communication
    private Socket socket;
    // Writer for sending data to server
    private PrintWriter out;
    // Reader for receiving data from server
    private BufferedReader in;

    /**
     * Establishes a connection to the server.
     * Initializes the socket and sets up input and output streams.
     *
     * @throws IOException If an I/O error occurs while setting up the socket and streams.
     */
    public void connect() throws IOException {
        socket = new Socket("cs5001-p3.dynv6.net", 8080);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * Sends a login request to the server using a token.
     *
     * @param token The authentication token for login.
     * @throws IOException If an I/O error occurs during communication.
     */
    public void login(String token) throws IOException {
        JsonObject request = Json.createObjectBuilder()
                .add("action", "login")
                .add("data", Json.createObjectBuilder()
                        .add("token", token))
                .build();

        out.println(request.toString());
        String response = in.readLine();
        loginResponse = response;
        System.out.println("Response from server: " + response);
    }

    /**
     * Requests a list of drawings from the server.
     *
     * @throws IOException If an I/O error occurs during communication.
     */
    public void getDrawings() throws IOException {
        JsonObject request = Json.createObjectBuilder()
                .add("action", "getDrawings")
                .build();

        out.println(request.toString());
        String response = in.readLine();
        drawingsResponse = response;
        setDrawingsJsonArray(drawingsResponse);
//        System.out.println("Response from server: " + this.drawingsJsonArray);
    }

    /**
     * Sends a request to add a new drawing.
     *
     * @param jsonString The JSON string representing the drawing to be added.
     * @throws IOException If an I/O error occurs during communication.
     */
    public void addDrawing(String jsonString) throws IOException {

        String request = jsonString;
        out.println(request);

        String response = in.readLine();
        addDrawingResponse = response;
        System.out.println("Response from server: " + response);
    }

    /**
     * Sends a request to update an existing drawing.
     *
     * @param jsonObject The JSON object representing the updated drawing.
     * @throws IOException If an I/O error occurs during communication.
     */
    public void updateDrawing(JsonObject jsonObject) throws IOException {
        JsonObject request = jsonObject;
        if (request != null){
            out.println(request.toString());
            String response = in.readLine();
            System.out.println("Response from server: " + response);
        }
    }

    /**
     * Sends a request to delete a drawing.
     *
     * @param id The ID of the drawing to be deleted.
     * @throws IOException If an I/O error occurs during communication.
     */
    public void deleteDrawing(String id) throws IOException {
        JsonObject request = Json.createObjectBuilder()
                .add("action", "deleteDrawing")
                .add("data", Json.createObjectBuilder()
                        .add("id", id))
                .build();

        out.println(request.toString());
        String response = in.readLine();
        System.out.println("Response from server: " + response);
    }
}
