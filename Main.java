import java.util.Scanner;

public class Main {
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
            System.out.println(":");


            int opcion = scanner.nextInt();
            System.out.println("Ingrese la cantidad: ");
            float cantidad = scanner.nextFloat();

            Main app = new Main();
            float resultado = app.ConverterDivisa(cantidad, opcion);
            String DivsaName = app.GetNameDivisa(opcion);
            System.out.println("El resultado de la conversión es: " + resultado +" ["+DivsaName+"]");

            System.out.println("===========================================");
            System.out.println("¿Desea realizar otra conversión? (1: Sí, 0: No)");
            flag = scanner.nextByte();
        } while (flag == 1);

        System.out.println("¡Gracias por usar el conversor de monedas!");
    }

    public float ConverterDivisa(float cantidad, int opcion) {
        float resultado = 0.0f;
        switch (opcion) {
            case 1:
                resultado = cantidad / GetDivisa(1);
                break;
            case 2:
                resultado = cantidad * GetDivisa(1);
                break;
            case 3:
                resultado = cantidad / GetDivisa(2);
                break;
            case 4:
                resultado = cantidad * GetDivisa(2);
                break;
            case 5:
                resultado = cantidad / GetDivisa(3);
                break;
            case 6:
                resultado = cantidad * GetDivisa(3);
                break;
            case 7:
                resultado = cantidad / GetDivisa(4);
                break;
            case 8:
                resultado = cantidad * GetDivisa(4);
                break;
            case 9:
                resultado = cantidad / GetDivisa(5);
                break;
            case 10:
                resultado = cantidad * GetDivisa(5);
                break;
            default:
                System.out.println("Opción no válida.");
        }
        return resultado;
    }

    public float GetDivisa(int divisaFind) {
        float divisaBack = 0f;
        float[] arrayDivisas = {17.23f, 18.45f, 21.50f, 0.02f, 3.33f};
        int[] numeros = {1, 2, 3, 4, 5};

        for (int i = 0; i < numeros.length; i++) {
            if (divisaFind == numeros[i]) {
                divisaBack = arrayDivisas[i];
                break;
            }
        }
        return divisaBack;
    }

    public String GetNameDivisa(int divisaFind) {
        int[] numeros = {1, 2, 3, 4, 5};
        String Name;
        switch (divisaFind){
            case 1:
                Name ="USD";
                break;
            case 2:
                Name ="MXN";
                break;
            case 3:
                Name ="€";
                break;
            case 4:
                Name ="MXN";
                break;
            case 5:
                Name ="£";
                break;
            case 6:
                Name ="MXN";
                break;
            case 7:
                Name ="ARS";
                break;
            case 8:
                Name ="MXN";
                break;
            case 9:
                Name ="R$";
                break;
            case 10:
                Name ="MXN";
                break;
            default:
                System.out.println("Opcion no valida");
                Name="";

        }
        return Name;
    }
}
