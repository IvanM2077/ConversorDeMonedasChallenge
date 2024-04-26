import java.util.Scanner;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static final String API_KEY = "APIKEY";
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================================");
        System.out.println("¡Bienvenido al conversor de monedas!");
        byte flag = 1;

        do {
            System.out.println("Seleccione una de las siguientes opciones: ");
            System.out.println("1) Convertir de Peso a Dólar");
            System.out.println("2) Convertir de Dólar a Peso");
            System.out.println("3) Convertir de Peso a Euro");
            System.out.println("4) Convertir de Euro a Peso");
            System.out.println("5) Convertir de Peso a Libra");
            System.out.println("6) Convertir de Libra a Peso");
            System.out.println("7) Convertir de Peso a Peso Argentino");
            System.out.println("8) Convertir de Peso Argentino a Peso");
            System.out.println("9) Convertir de Peso a Real");
            System.out.println("10) Convertir de Real a Peso");
            System.out.print(": ");

            int opcion = scanner.nextInt();
            System.out.print("Ingrese la cantidad: ");
            float cantidad = scanner.nextFloat();

            Main app = new Main();
            float resultado = app.converterDivisa(cantidad, opcion);
            String divisaName = app.getNameDivisa(opcion);
            System.out.println("El resultado de la conversión es: " + resultado + " [" + divisaName + "]");

            System.out.println("===========================================");
            System.out.println("¿Desea realizar otra conversión? (1: Sí, 0: No)");
            flag = scanner.nextByte();
        } while (flag == 1);

        System.out.println("¡Gracias por usar el conversor de monedas!");
        scanner.close();
    }

    public float converterDivisa(float cantidad, int opcion) {
        float tasaCambio = getTasaCambio(opcion);
        return cantidad * tasaCambio;
    }

    public float getTasaCambio(int opcion) {
        String baseCurrency = "";
        String targetCurrency = "";

        // Definir monedas base y objetivo según la opción seleccionada
        switch (opcion) {
            case 1:
                baseCurrency = "MXN";
                targetCurrency = "USD";
                break;
            case 2:
                baseCurrency = "USD";
                targetCurrency = "MXN";
                break;
            case 3:
                baseCurrency = "MXN";
                targetCurrency = "EUR";
                break;
            case 4:
                baseCurrency = "EUR";
                targetCurrency = "MXN";
                break;
            case 5:
                baseCurrency = "MXN";
                targetCurrency = "GBP";
                break;
            case 6:
                baseCurrency = "GBP";
                targetCurrency = "MXN";
                break;
            case 7:
                baseCurrency = "MXN";
                targetCurrency = "ARS";
                break;
            case 8:
                baseCurrency = "ARS";
                targetCurrency = "MXN";
                break;
            case 9:
                baseCurrency = "MXN";
                targetCurrency = "BRL";
                break;
            case 10:
                baseCurrency = "BRL";
                targetCurrency = "MXN";
                break;
            default:
                System.out.println("Opción no válida.");
                return 0.0f;
        }

        try {
            // Crear la URL de la solicitud
            String urlStr = API_BASE_URL + baseCurrency;

            // Conexión HTTP
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Leer la respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Procesar la respuesta JSON
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject conversionRates = jsonResponse.getJSONObject("conversion_rates");

            // Obtener la tasa de cambio
            float tasaCambio = conversionRates.getFloat(targetCurrency);
            return tasaCambio;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public String getNameDivisa(int divisaFind) {
        String name;
        switch (divisaFind) {
            case 1:
                name = "USD";
                break;
            case 2:
                name = "MXN";
                break;
            case 3:
                name = "EUR";
                break;
            case 4:
                name = "MXN";
                break;
            case 5:
                name = "GBP";
                break;
            case 6:
                name = "MXN";
                break;
            case 7:
                name = "ARS";
                break;
            case 8:
                name = "MXN";
                break;
            case 9:
                name = "BRL";
                break;
            case 10:
                name = "MXN";
                break;
            default:
                System.out.println("Opción no válida");
                name = "";
        }
        return name;
    }
}
