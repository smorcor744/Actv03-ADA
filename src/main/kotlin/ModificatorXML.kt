package org.ventanas

import org.w3c.dom.Document
import org.w3c.dom.Element
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult


class ModificatorXML(path: Path) {
    private val dbf: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
    private val db: DocumentBuilder = dbf.newDocumentBuilder()
    private val document: Document = db.parse(path.toFile())
    private val root: Element = document.documentElement

    fun modXML(empleadoId: String, nuevoSalario: String){
        root.normalize()

        val empleados = root.getElementsByTagName("empleado")

        for (i in 0 until empleados.length){
            val empleado = empleados.item(i) as Element

            if (empleado.getAttribute("id") == empleadoId) {
                val salarioNode = empleado.getElementsByTagName("salario").item(0)
                salarioNode.textContent = nuevoSalario
            }
        }
    }
    fun saveChanges(outputPath: Path) {
        val transformerFactory = TransformerFactory.newInstance()
        val transformer = transformerFactory.newTransformer()
        val source = DOMSource(document)
        val result = StreamResult(outputPath.toFile())
        transformer.transform(source, result)
    }


}