package org.ventanas

import java.nio.file.Path

/**
 * 1º Lectura de empleados desde archivo de texto:
 * • Debes leer un archivo de texto con los datos de los empleados utilizando flujos de caracteres.
 * • El archivo debe contener una lista de empleados, donde cada línea representa un empleado y los campos están separados por comas.
 * • El fichero a leer lo puedes encontrar en el siguiente enlace
 * • Los campos que contiene cada línea son:
 *      - ID: Identificador único del empleado.
 *      - Apellido: Apellido del empleado.
 *      - Departamento: Departamento al que pertenece el empleado.
 *      - Salario: Salario actual del empleado.

 * 2. Generación de un archivo XML:
 * • A partir de los datos leídos del archivo de texto, deberás generar un archivo XML que contenga la información de los empleados. Utiliza el modelo DOM para crear este archivo XML.

 * <empleados>
 *  <empleado id="101">
 *  <apellido>García</apellido>
 *  <departamento>Recursos Humanos</departamento>
 *  <salario>3000</salario>
 *  </empleado>
 *  <empleado id="102">
 *  <apellido>López</apellido>
 *  <departamento>Desarrollo</departamento>
 *  <salario>4000</salario>
 *  </empleado>
 *  <!-- Otros empleados -->
 * </empleados>

 * 3. Modificación de un nodo en el archivo XML:
 * • Implementa una función que permita modificar el salario de un empleado en base a su ID. Por ejemplo, si se le pasa la ID 102 y el nuevo salario de 4500, la función debe modificar el nodo correspondiente en el archivo XML:

 * <empleado id="102">
 *  <apellido>López</apellido>
 *  <departamento>Desarrollo</departamento>
 *  <salario>4500</salario>
 * </empleado>

 * 4. Lectura del archivo XML modificado y salida en consola:
 * • Finalmente, debes leer el archivo XML resultante y mostrar la información de todos los empleados en la consola de la siguiente forma:

 * ID: 101, Apellido: García, Departamento: Recursos Humanos, Salario:
 * 3000
 * ID: 102, Apellido: López, Departamento: Desarrollo, Salario: 4500
 * ...

 */


fun main() {
    val path: Path = Path.of("C:\\2ºDAM\\ADA\\Act03-ADA\\src\\main\\resources\\empleados.xml")
    val lista = ObtenerInfoEmple().leerArchivo()
    val generadorXML = GeneratorXML(lista)
    generadorXML.createDocument()
    val modificatorXML = ModificatorXML(path)
    modificatorXML.modXML("2","2777.0")
    modificatorXML.saveChanges(path)
    val salidaConsola = SalidaConsola(path)
    val listaEmpleados = salidaConsola.infoGetter()

    println(listaEmpleados.joinToString(separator = "\n") { empleado ->
        " ID: ${empleado.id}, Apellido: ${empleado.apellido}, Departamento: ${empleado.departamento}, Salario: ${empleado.salario}"
    })

}