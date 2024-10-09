package org.ventanas

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class SalidaConsola(path: Path) {
    private val dbf: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
    private val db: DocumentBuilder = dbf.newDocumentBuilder()
    private val document: Document = db.parse(path.toFile())
    private val root: Element = document.documentElement

    fun infoGetter(): MutableList<Empleado> {
        root.normalize()

        val empleados = root.getElementsByTagName("empleado")
        val listaEmpleados = mutableListOf<Empleado>()

        for (i in 0 until empleados.length) {
            val nodo = empleados.item(i)

            if (nodo.nodeType == Node.ELEMENT_NODE) {
                val nodoElemento = nodo as Element

                val elementoId = nodoElemento.getAttribute("id")
                val elementoApellido = nodoElemento.getElementsByTagName("apellido")
                val elementoDepartamento = nodoElemento.getElementsByTagName("departamento")
                val elementoSalario = nodoElemento.getElementsByTagName("salario")

                val textContentApellido = elementoApellido.item(0).textContent
                val textContentDepartamento = elementoDepartamento.item(0).textContent
                val textContentSalario = elementoSalario.item(0).textContent


                listaEmpleados.add(Empleado(elementoId,textContentApellido, textContentDepartamento,textContentSalario))
            }
        }
        return listaEmpleados
    }



}