package mx.uaemex.fi.ing_software_ii.alejandria.persistencia;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.EstadoPrestamo;
//import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.EstadoPrestamo;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.LibroAbstracto;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.MaterialBibliografico;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Nombre;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Prestamo;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.TipoUsuario;
import mx.uaemex.fi.ing_software_ii.alejandria.persistencia.data.Usuario;

public class Ejecucion {

	public static void main(String[] args) {
		
		UsuarioDAOImp usuarioDaoImp = new UsuarioDAOImp();
		MaterialDaoImp materialDaoImp = new MaterialDaoImp();
		PrestamoDaoImp prestamoDaoImp = new PrestamoDaoImp();
		
		Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nPROGRAMACION DAO");
            System.out.println("1. Trabajar con Usuario");
            System.out.println("2. Trabajar con Material Bibliográfico");
            //System.out.println("3. Trabajar con Préstamos");
            System.out.println("3. Salir");
            System.out.print("\nSelecciona una opcion: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    menuUsuario(scanner, usuarioDaoImp);
                    break;
                case 2:
                    menuMaterial(scanner, materialDaoImp);
                    break;
                case 3:
                    //menuPrestamo(scanner, prestamoDaoImp);
                    exit = true;
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        System.out.println("Programa finalizado.");
        scanner.close();
    }

    private static void menuUsuario(Scanner scanner, UsuarioDAOImp usuarioDaoImp) {
    	
    	System.out.println("\nOpciones de Usuario");
        System.out.println("1. Crear usuario");
        System.out.println("2. Consultar usuario");
        System.out.println("3. Consultar todos los usuarios");
        System.out.println("4. Modificar usuario");
        System.out.println("5. Eliminar usuario");
        System.out.println("6. Volver al menú principal");
        System.out.print("\nSelecciona una opcion: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
            	
                System.out.print("Ingrese el nombre de pila: ");
                String nombrePila = scanner.nextLine();
                
                System.out.print("Ingrese el primer apellido: ");
                String primerApellido = scanner.nextLine();
                
                System.out.print("Ingrese el segundo apellido: ");
                String segundoApellido = scanner.nextLine();
                Nombre nombre = new Nombre(nombrePila, primerApellido, segundoApellido);

                System.out.print("Ingrese el login: ");
                String login = scanner.nextLine();
                
                System.out.print("Ingrese el password: ");
                String password = scanner.nextLine();
                
                System.out.print("Ingrese el tipo de usuario (Alumno, Investigador, Profesor): ");
                String tipoUsuarioStr = scanner.nextLine();
                TipoUsuario tipoUsuario = TipoUsuario.valueOf(tipoUsuarioStr);

                Usuario usuario = new Usuario(0, login, password, tipoUsuario, nombre);
                usuarioDaoImp.crearUsuario(usuario);
                break;
                
            case 2:
                System.out.print("Ingrese el ID del usuario a consultar: ");
                int usuarioId = scanner.nextInt();
                scanner.nextLine();
                
                Usuario usuarioConsultado = usuarioDaoImp.consultarUsuario(usuarioId);
                if (usuarioConsultado != null) {
                    System.out.println(usuarioConsultado);
                }
                break;
                
            case 3:
                ArrayList<Usuario> usuarios = usuarioDaoImp.consultarUsuario();
                for (Usuario u : usuarios) {
                    System.out.println(u);
                }
                break;
                
            case 4:
                System.out.print("Ingrese el ID del usuario a modificar: ");
                int modificarId = scanner.nextInt();
                scanner.nextLine();

                Usuario usuarioExistente = usuarioDaoImp.consultarUsuario(modificarId);
                if (usuarioExistente == null) {
                    System.out.println("Usuario no encontrado.");
                    break;
                }

                System.out.print("Ingrese el nuevo nombre de pila (actual: " + usuarioExistente.getNombre().getNombreDePila() + "): ");
                String nuevoNombrePila = scanner.nextLine();
                if (nuevoNombrePila.isEmpty()) {
                    nuevoNombrePila = usuarioExistente.getNombre().getNombreDePila();
                }

                System.out.print("Ingrese el nuevo primer apellido (actual: " + usuarioExistente.getNombre().getPrimerApellido() + "): ");
                String nuevoPrimerApellido = scanner.nextLine();
                if (nuevoPrimerApellido.isEmpty()) {
                    nuevoPrimerApellido = usuarioExistente.getNombre().getPrimerApellido();
                }

                System.out.print("Ingrese el nuevo segundo apellido (actual: " + usuarioExistente.getNombre().getSegundoApellido() + "): ");
                String nuevoSegundoApellido = scanner.nextLine();
                if (nuevoSegundoApellido.isEmpty()) {
                    nuevoSegundoApellido = usuarioExistente.getNombre().getSegundoApellido();
                }

                Nombre nuevoNombre = new Nombre(nuevoNombrePila, nuevoPrimerApellido, nuevoSegundoApellido);

                System.out.print("Ingrese el nuevo login (actual: " + usuarioExistente.getLogin() + "): ");
                String nuevoLogin = scanner.nextLine();
                if (nuevoLogin.isEmpty()) {
                    nuevoLogin = usuarioExistente.getLogin();
                }

                System.out.print("Ingrese el nuevo password (actual: " + usuarioExistente.getPassword() + "): ");
                String nuevoPassword = scanner.nextLine();
                if (nuevoPassword.isEmpty()) {
                    nuevoPassword = usuarioExistente.getPassword();
                }

                System.out.print("Ingrese el nuevo tipo de usuario (Alumno, Investigador, Profesor) (actual: " + usuarioExistente.getTipo() + "): ");
                String nuevoTipoUsuarioStr = scanner.nextLine();
                TipoUsuario nuevoTipoUsuario = usuarioExistente.getTipo();
                
                if (!nuevoTipoUsuarioStr.isEmpty()) {
                    try {
                        nuevoTipoUsuario = TipoUsuario.valueOf(nuevoTipoUsuarioStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tipo de usuario inválido. Se mantendrá el tipo actual.");
                    }
                }

                Usuario usuarioModificado = new Usuario(modificarId, nuevoLogin, nuevoPassword, nuevoTipoUsuario, nuevoNombre);
                usuarioDaoImp.modificarUsuario(modificarId, usuarioModificado);
                break;

            case 5:
                System.out.print("Ingrese el ID del usuario a eliminar: ");
                int eliminarId = scanner.nextInt();
                scanner.nextLine();
                
                usuarioDaoImp.eliminarUsuario(eliminarId);
                break;
            default:
                System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                break;
        }
    }
        
	private static void menuMaterial(Scanner scanner, MaterialDaoImp materialDaoImp) {

		System.out.println("\nOpciones de Material Bibliográfico");
		System.out.println("1. Crear material");
		System.out.println("2. Consultar material por ID");
		System.out.println("3. Consultar todos los materiales");
		System.out.println("4. Modificar material");
		System.out.println("5. Eliminar material");
		System.out.println("6. Volver al menú principal");
		System.out.print("\nSelecciona una opción: ");

		int choice = scanner.nextInt();
		scanner.nextLine(); // Limpiar el buffer

		switch (choice) {
		case 1: // Crear material
			System.out.print("Ingrese el título del material: ");
			String titulo = scanner.nextLine();

			System.out.print("Ingrese el nombre del autor: ");
			String nombreAutor = scanner.nextLine();
			System.out.print("Ingrese el primer apellido del autor: ");
			String primerApellidoAutor = scanner.nextLine();
			System.out.print("Ingrese el segundo apellido del autor: ");
			String segundoApellidoAutor = scanner.nextLine();

			Nombre autor = new Nombre(nombreAutor, primerApellidoAutor, segundoApellidoAutor);
			LibroAbstracto nuevoMaterial = new LibroAbstracto(0, titulo, autor) {
			};

			materialDaoImp.crearMaterial(nuevoMaterial);
			System.out.println("Material creado exitosamente.");
			break;

		case 2: // Consultar material por ID
			System.out.print("Ingrese el ID del material a consultar: ");
			int materialId = scanner.nextInt();
			scanner.nextLine();

			LibroAbstracto materialConsultado = materialDaoImp.consultarMaterial(materialId);
			if (materialConsultado != null) {
				System.out.println(materialConsultado);
			} else {
				System.out.println("No se encontró ningún material con ese ID.");
			}
			break;

		case 3: // Consultar todos los materiales
			ArrayList<MaterialBibliografico> materiales = materialDaoImp.consultarMaterial();
			if (materiales.isEmpty()) {
				System.out.println("No hay materiales disponibles.");
			} else {
				for (MaterialBibliografico material : materiales) {
					System.out.println(material);
				}
			}
			break;

		case 4: // Modificar material
			System.out.print("Ingrese el ID del material a modificar: ");
			int modificarId = scanner.nextInt();
			scanner.nextLine();

			LibroAbstracto materialExistente = materialDaoImp.consultarMaterial(modificarId);
			if (materialExistente == null) {
				System.out.println("Material no encontrado.");
				break;
			}

			System.out.print("Ingrese el nuevo título del material (actual: " + materialExistente.getTitulo() + "): ");
			String nuevoTitulo = scanner.nextLine();
			if (nuevoTitulo.isEmpty()) {
				nuevoTitulo = materialExistente.getTitulo();
			}

			System.out.print("Ingrese el nuevo nombre del autor (actual: "
					+ materialExistente.getAutor().getNombreDePila() + "): ");
			String nuevoNombreAutor = scanner.nextLine();
			if (nuevoNombreAutor.isEmpty()) {
				nuevoNombreAutor = materialExistente.getAutor().getNombreDePila();
			}

			System.out.print("Ingrese el nuevo primer apellido del autor (actual: "
					+ materialExistente.getAutor().getPrimerApellido() + "): ");
			String nuevoPrimerApellidoAutor = scanner.nextLine();
			if (nuevoPrimerApellidoAutor.isEmpty()) {
				nuevoPrimerApellidoAutor = materialExistente.getAutor().getPrimerApellido();
			}

			System.out.print("Ingrese el nuevo segundo apellido del autor (actual: "
					+ materialExistente.getAutor().getSegundoApellido() + "): ");
			String nuevoSegundoApellidoAutor = scanner.nextLine();
			if (nuevoSegundoApellidoAutor.isEmpty()) {
				nuevoSegundoApellidoAutor = materialExistente.getAutor().getSegundoApellido();
			}

			Nombre nuevoAutor = new Nombre(nuevoNombreAutor, nuevoPrimerApellidoAutor, nuevoSegundoApellidoAutor);
			LibroAbstracto materialModificado = new LibroAbstracto(modificarId, nuevoTitulo, nuevoAutor) {
			};

			materialDaoImp.modificarMaterial(modificarId, materialModificado);
			System.out.println("Material modificado exitosamente.");
			break;

		case 5: // Eliminar material
			System.out.print("Ingrese el ID del material a eliminar: ");
			int eliminarId = scanner.nextInt();
			scanner.nextLine();

			materialDaoImp.eliminarMaterial(eliminarId);
			System.out.println("Material eliminado exitosamente.");
			break;

		case 6: // Volver al menú principal
			System.out.println("Volviendo al menú principal...");
			break;

		default:
			System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
			break;
		}
		
		
		
		// PRUEBA DE DAO PRESTAMO
		//Prestamo prestamo = new Prestamo(choice, null, null, null, null, null, null, choice);
		
		//Para crear un usuario
				// ES NECESARIO MODIFICAR LA TABLA DE USUARIO PARA QUE NO TENGA ID incrementable
				
				//Nombre nombre = new Nombre("Jose", "Angel", "Venegas");
				//Usuario usuario = new Usuario(3,"Annngel", "june22",TipoUsuario.Investigador,nombre);
				//usuarioDaoImp.crearUsuario(usuario);
				
				
				
				
				/**
				Para consultar a un usuario
				Usuario usuario = usuarioDaoImp.consultarUsuario(1);  //NO FUNCIONA :c
				System.out.println("ID: " + usuario.getId() + ", Login: " + usuario.getLogin() + ", Password: " + usuario.getPassword() + 
						", Tipo de Usuario: " + usuario.getTipo() + ", Nombre: " + usuario.getNombre().getNombreDePila() +" "+ 
						usuario.getNombre().getPrimerApellido() +" "+ usuario.getNombre().getSegundoApellido());
				**/
				
				 //Para consultar todos los usuarios
				/*
				ArrayList<Usuario> usuarios = new ArrayList<>();
				usuarios = usuarioDaoImp.consultarUsuario();
				
				for (int i = 0; i < usuarios.size(); i++) {
				    System.out.println(usuarios.get(i).toString());
				}
				*/
				
				
				
				//Test UPDATE to table user
				//Nombre nombre = new Nombre("Renata", "Pichardo", "Rodea");
				//Usuario usuario = new Usuario(2,"rennpich", "julio24",TipoUsuario.Alumno,nombre);
				
				//usuarioDaoImp.modificarUsuario(2, usuario);
				
				//Test DELETE to table user
				//usuarioDaoImp.eliminarUsuario(101);
				
				
				//############################################################################
				//TEST DE MATERIAL BIBLIOGRAFICO
				
				Nombre nombre = new Nombre("Esmeralda", "Lopez", "Estrada");
				LibroAbstracto libro = new LibroAbstracto(2, "El fantasma de canterville", nombre) {
				};
				
				Nombre nombre1 = new Nombre("Jose", "Angel", "Venegas");
				Usuario usuario = new Usuario(2,"Annngel", "june22",TipoUsuario.Investigador,nombre1);
				//usuarioDaoImp.crearUsuario(usuario);
				
				//materialDaoImp.crearMaterial(libro);

				/*
				ArrayList<MaterialBibliografico> materiales = new ArrayList<>();
				materiales = materialDaoImp.consultarMaterial();
				
				for (int i = 0; i < materiales.size(); i++) {
				    System.out.println(materiales.get(i).toString());
				}
				*/
				
				//Consultar individualmente
				//LibroAbstracto libro = materialDaoImp.consultarMaterial(1);  //NO FUNCIONA :c
				//System.out.println("ID: " + libro.getId() + " Titulo: " + libro.getTitulo() + " Autor: " + libro.getAutor());
				
				//materialDaoImp.modificarMaterial(1, libro);
				
				//materialDaoImp.eliminarMaterial(3);
				
				
				
				//System.out.println(p.getFechaInicio());
				
				//prestamoDaoImp.crearPrestamo(p);
				
				
				/*
				//Date d = Date.valueOf("2078-12-12");
				ArrayList<Prestamo> estados = new ArrayList<>();
				estados = prestamoDaoImp.consultarEstado();
				
				System.out.println("Estados");
				for (int i = 0; i < estados.size(); i++) {
				    System.out.println(estados.get(i).getEstado());
				}
				
				//prestamoDaoImp.modificarPrestamo(2, p);*/

	}

}
