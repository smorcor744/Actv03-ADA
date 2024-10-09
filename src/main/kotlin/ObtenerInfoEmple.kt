package org.ventanas

import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Path


class ObtenerInfoEmple {
    private val ficheroPrueba: Path = Path.of("C:\\2ºDAM\\ADA\\Act03-ADA\\src\\main\\resources\\empleados.txt")
    private val listaEmpleados = mutableListOf<Empleado>()

    fun leerArchivo(): MutableList<Empleado> {
        val br : BufferedReader = Files.newBufferedReader(ficheroPrueba)

        br.use { flujo ->
            flujo.readLine().split(",")

            flujo.forEachLine { line ->
                val info = line.split(",")
                if (info.size == 4) {
                    listaEmpleados.add(Empleado(info[0], info[1], info[2], info[3]))
                }
            }
        }
        return listaEmpleados
    }


}