package model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;

public class product {

    private static final String API_URL = "https://dummyjson.com/products";
    private static final String X_CONS_ID = "1234567";
    private static final String USER_KEY = "faY738sH";

    public static void main(String[] args) {
        try {

            String jsonData = fetchDataFromUrl(API_URL);


            JSONArray products = new JSONArray(jsonData);

            int[] ratings = new int[products.length()];


            for (int i = 0; i < products.length(); i++) {
                JSONObject product = products.getJSONObject(i);
                int rating = product.getInt("rating");
                ratings[i] = rating;
            }

            selectionSort(ratings);


            System.out.println("Data Produk yang Diurutkan Berdasarkan Rating:");
            for (int rating : ratings) {
                for (int i = 0; i < products.length(); i++) {
                    JSONObject product = products.getJSONObject(i);
                    if (product.getInt("rating") == rating) {
                        System.out.println("Nama Produk: " + product.getString("name"));
                        System.out.println("Rating: " + rating);
                        System.out.println("------------------------");
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String fetchDataFromUrl(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();


        connection.setRequestProperty("X-cons_ID", X_CONS_ID);
        connection.setRequestProperty("user_key", USER_KEY);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }

    private static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
