import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Example  {
    public static void main(String[] args) throws Exception {

        // callApiBeforeJava10();
        // callApiInJava11();
        try {
            // Get the local host (your computer)
            InetAddress localHost = InetAddress.getLocalHost();

            // Print IP address and hostname
            System.out.println("IP Address: " + localHost.getHostAddress());
            System.out.println("Host Name: " + localHost.getHostName());
        } catch (UnknownHostException e) {
            System.out.println("Unable to retrieve IP address: " + e.getMessage());
        }


    }

    public static void callApiInJava11(){
        HttpClient httpClient = java.net.http.HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
        .uri(URI.create("https://indian-stock-exchange-api2.p.rapidapi.com/stock?name=tata%20steel"))
        .GET()
        .header("x-rapidapi-host", "indian-stock-exchange-api2.p.rapidapi.com")
        .header("x-rapidapi-key", "b576270b21mshfe21cc7345bfd38p1a1d6ajsn71bb2f6c9db2")
        .build();

        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenAccept(System.out::println)
        .join();

    }

    public static void callApiBeforeJava10()throws Exception {
        URL url  = new URL("https://indian-stock-exchange-api2.p.rapidapi.com/stock?name=tata%20steel");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("x-rapidapi-host", "indian-stock-exchange-api2.p.rapidapi.com");
        httpURLConnection.setRequestProperty("x-rapidapi-key", "b576270b21mshfe21cc7345bfd38p1a1d6ajsn71bb2f6c9db2");

        int responseStatusCode = httpURLConnection.getResponseCode();
        if (responseStatusCode == 200) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine; 
            StringBuffer response = new StringBuffer();

            while((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);   
            }
            bufferedReader.close();

            System.out.println("Response from API: " + response.toString());
        } else {
            System.out.println("Failed to retrive the data from api: status codE: " + responseStatusCode);
        }
    }
}

