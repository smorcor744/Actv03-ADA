package org.ventanas

import org.w3c.dom.DOMImplementation
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Text
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class GeneratorXML(private val listEmple:MutableList<Empleado>) {

    private val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
    private val builder: DocumentBuilder = factory.newDocumentBuilder()
    private val imp: DOMImplementation = builder.domImplementation

    fun createDocument() {
        val document: Document = imp.createDocument(null, "empleados", null)

        listEmple.forEach {
            val empleado: Element = document.createElement("empleado")
            empleado.setAttribute("id", it.id)
            document.documentElement.appendChild(empleado)

            val apellido: Element = document.createElement("apellido")
            val departamento: Element = document.createElement("departamento")
            val salario: Element = document.createElement("salario")

            val textoApellido: Text = document.createTextNode(it.apellido)
            val textoDepartamento: Text = document.createTextNode(it.departamento)
            val textoSalario: Text = document.createTextNode(it.salario)

            apellido.appendChild(textoApellido)
            departamento.appendChild(textoDepartamento)
            salario.appendChild(textoSalario)

            empleado.appendChild(apellido)
            empleado.appendChild(departamento)
            empleado.appendChild(salario)

        }

        val source: Source = DOMSource(document)
        val result: StreamResult =
            StreamResult(Path.of("C:\\2ºDAM\\ADA\\Act03-ADA\\src\\main\\resources\\empleados.xml").toFile())
        val transformer: Transformer = TransformerFactory.newInstance().newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.transform(source, result)

    }
}