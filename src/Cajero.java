import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Cajero {

	public static void main(String[] args) {
		System.out.println("Hola, gracias por insertar su tarjeta. ¿Qué operación desea realizar?");

		double saldo = 10000.00;

		Scanner operaciones = new Scanner(System.in);
		int opciones;
		int intentosInvalidos = 1;
		ArrayList<String> movimientos = new ArrayList<>();

		do {
			System.out.println("1- Retirar dinero");
			System.out.println("2- Hacer depósito");
			System.out.println("3- Consultar saldo");
			System.out.println("4- Quejas");
			System.out.println("5- Ver últimos 5 movimientos");
			System.out.println("9- Salir del cajero");

			opciones = operaciones.nextInt();

			switch (opciones) {
			case 1:
				System.out.println("Su saldo es de: $" + saldo);
				System.out.print("Ingrese la cantidad a retirar (múltiplo de $50 y no más de $6,000): ");
				double cantidadRetiro = operaciones.nextDouble();
				if (cantidadRetiro % 50 == 0 && cantidadRetiro <= 6000 && cantidadRetiro <= saldo) {
					saldo -= cantidadRetiro;
					System.out.println("Retire su tarjeta y cuente su dinero. Saldo restante: " + saldo);

					operaciones.nextLine();

					System.out.print("¿Desea donar $200 para la graduación de ch30? (Si/No): ");
					String respuestaDonacion = operaciones.nextLine();

					if (respuestaDonacion.equalsIgnoreCase("Si")) {
						saldo -= 200;
						System.out
								.println("Gracias por su donación. La CH30 está muy agradecida con usted. Nuevo saldo: "
										+ saldo);
					} else if (respuestaDonacion.equalsIgnoreCase("No")) {
						System.out.println("No se realizó ninguna donación. Saldo sin cambios: " + saldo);
					} else {
						System.out.println("Respuesta inválida, no se realizará ninguna donación.");
					}

					String movimiento = obtenerFechaHora() + " Retiro de $" + cantidadRetiro;
					movimientos.add(movimiento);
				} else {
					System.out.println("Cantidad inválida o saldo insuficiente. Asegúrese de seguir las indicaciones.");
				}
				break;

			 case 2:
                 System.out.println("Seleccione una opción para realizar su depósito:");
                 System.out.println("1) Cuenta de cheques");
                 System.out.println("2) Tarjeta de crédito");
                 int opcionDeposito = operaciones.nextInt();
                 if (opcionDeposito == 1 || opcionDeposito == 2) {
                     System.out.print("Ingrese la cantidad a depositar: ");
                     String cantidadDepositarStr = operaciones.next();
                     
                     try {
                    	 double cantidadDepositar = Double.parseDouble(cantidadDepositarStr);
                         
                         if (cantidadDepositar >= 0) {
                             if (opcionDeposito == 1) {
                                 saldo += cantidadDepositar;
                                 System.out.println("Depósito en cuenta de cheques exitoso. Nuevo saldo: " + saldo);
                             } else {
                                 saldo -= cantidadDepositar;
                                 System.out.println("Depósito en Tarjeta de Crédito exitoso. Nuevo saldo: " + saldo);
                             }
                             String movimiento = obtenerFechaHora() + (opcionDeposito == 1 ? " Depósito a cuenta de cheques de $" : " Retiro de Tarjeta de Crédito de $") + cantidadDepositar;
                             movimientos.add(movimiento);
                         } else {
                             System.out.println("Cantidad inválida. Debe ingresar un valor positivo.");
                         }
                     } catch (NumberFormatException e) {
                         System.out.println("Entrada no válida. Debe ingresar un número válido.");
                     }
                 } else {
                     System.out.println("Opción de depósito inválida, inténtelo de nuevo.");
                 }
                 break;


			case 3:
				System.out.println("Su saldo actual es de: $" + saldo);
				break;

			case 4:
				System.out.println("No disponible por el momento, intente más tarde.");
				break;

			case 5:
				if (movimientos.isEmpty()) {
					System.out.println("No ha realizado movimientos.");
				} else {
					System.out.println("Sus últimos cinco movimientos fueron: ");
					int count = 0;
					for (int i = movimientos.size() - 1; i >= 0 && count < 5; i--) {
						System.out.println(movimientos.get(i));
						count++;
					}
				}
				break;

			case 9:
				System.out.println("Gracias por confiar en nosotros.");
				System.out.println("Saliendo del cajero CH30.");
				operaciones.close();
				System.exit(0);

			default:
				System.out.println("La opción que eligió es inválida, por favor elija un número válido.");
				intentosInvalidos++;
				if (intentosInvalidos == 3) {
					System.out.println("Demasiados intentos incorrectos, saliendo del cajero.");
					operaciones.close();
					System.exit(2);
				}
			}

			System.out.print("¿Desea regresar al menú principal? (Si/No): ");
			String respuestaMenu = operaciones.next();
			if (!respuestaMenu.equalsIgnoreCase("Si")) {
				System.out.println("Gracias por utilizar el Cajero Automático. ¡Hasta luego!");
				operaciones.close();
				System.exit(0);
			}
		} while (true);
	}

	private static String obtenerFechaHora() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return dateFormat.format(new Date());
	}
}
