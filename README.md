"# tienda-backend" 

-- Pasos para ejecutar aplicación de manera local --

1. Tener previamente instalado el JDK en su versión 17(la versión JDK debe ser exactamente esta)
y Apache Maven en su versión 3.8.4 de preferencia, dejar ambos en las variables de entorno del sistema.

2. Ir al directorio src/main/resources y abrir el archivo application.properties y dejar habilitado el apartado que dice LOCAL
   para realizar pruebas en una base de datos local. Nota: debe existir la base previo a ejecutar comandos de compilación ya
   que de no ser así, la aplicación mostrará excepción de conexión a base de datos, el modelo se encuentra en esta ruta tienda-backend\src\main\java\com\tesis\tiendavirtualbackend\utils\modelo.mwb.


3. Ejecutar comando mvn clean install en terminal para compilar y verificar que no exista ninguna excepción.

4. Ejecutar el comando mvn spring-boot:run para poder ejecutar la aplicación. Nota: previamente verificar que el puerto 8082

se encuetre desocupado porque es en el que correrá la aplicación.


