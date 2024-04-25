import java.util.Scanner;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static final String API_KEY = "tu_clave_de_api_aqui";
    private static final String API_BASE_URL = "https://v6.exchangeratesapi.io/latest?base=";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================================");
        System.out.println("Bienvenido al conversor de monedas!");
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
    }

    public float converterDivisa(float cantidad, int opcion) {
        float tasaCambio = getTasaCambio(opcion);
        return cantidad * tasaCambio;
    }

    public float getTasaCambio(int opcion) {
        String baseCurrency = "";
        switch (opcion) {
            case 1:
                baseCurrency = "MXN";
                break;
            case 2:
                baseCurrency = "USD";
                break;
            case 3:
                baseCurrency = "MXN";
                break;
            case 4:
                baseCurrency = "EUR";
                break;
            case 5:
                baseCurrency = "MXN";
                break;
            case 6:
                baseCurrency = "GBP";
                break;
            case 7:
                baseCurrency = "MXN";
                break;
            case 8:
                baseCurrency = "ARS";
                break;
            case 9:
                baseCurrency = "MXN";
                break;
            case 10:
                baseCurrency = "BRL";
                break;
            default:
                System.out.println("Opción no válida.");
                return 0.0f;
        }

        try {
            URL url = new URL(API_BASE_URL + baseCurrency);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject rates = jsonResponse.getJSONObject("rates");

            float tasaCambio = 1.0f; // Por defecto, la tasa de cambio es 1.0 si la moneda base y la objetivo son iguales
            switch (opcion) {
                case 1:
                    tasaCambio = rates.getFloat("USD");
                    break;
                case 2:
                    tasaCambio = 1 / rates.getFloat("USD");
                    break;
                case 3:
                    tasaCambio = rates.getFloat("EUR");
                    break;
                case 4:
                    tasaCambio = 1 / rates.getFloat("EUR");
                    break;
                case 5:
                    tasaCambio = rates.getFloat("GBP");
                    break;
                case 6:
                    tasaCambio = 1 / rates.getFloat("GBP");
                    break;
                case 7:
                    tasaCambio = rates.getFloat("ARS");
                    break;
                case 8:
                    tasaCambio = 1 / rates.getFloat("ARS");
                    break;
                case 9:
                    tasaCambio = rates.getFloat("BRL");
                    break;
                case 10:
                    tasaCambio = 1 / rates.getFloat("BRL");
                    break;
            }
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
