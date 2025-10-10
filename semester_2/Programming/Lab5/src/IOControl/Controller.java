package IOControl;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Stack;

public class Controller {
    /** Controller controls IO (responsible for IO)
     * getLIne: reading from output a line
     * print: printing
     * getDocument: gives you xml document
     * getDocumentByName: gives you xml document from a filename
     * writeXml: writing data to XML
     * private input and output allows to change and get current output and input fields
     * field buffered is false = not waiting line from output
     * Controller also contains a stack that changes when execute_script runs a new execute_script
     * */
    private final Stack<BufferedReader> stack;
    private boolean buffered = false;

    private BufferedReader input;
    private BufferedWriter output;

    public Controller (InputStreamReader input, OutputStreamWriter output) {
        this.input = new BufferedReader(input);
        this.output = new BufferedWriter(output);
        this.stack = new Stack<>();
    }

    public String getLine() throws IOException {
        return input.readLine();
    }

    public void print(String ...inp) throws IOException {
        for (String data: inp) {
            output.write(data);
            if (!buffered) output.flush();
        }
    }

    public void println(String ...inp) throws IOException {
        for (String data: inp) {
            print(data, "\n");
        }
    }

    public Document getDocument() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = docFactory.newDocumentBuilder();
        return builder.newDocument();
    }
    public Document getDocumentByName(String filename) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = docFactory.newDocumentBuilder();
        return (filename != null) ?
                builder.parse(filename + (filename.matches(".*\\.xml") ? "" : ".xml")) :
                builder.newDocument();
    }

    public void writeXml(Document doc, BufferedWriter output) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }

    public void setInput(BufferedReader input) {
        this.input = input;
    }

    public BufferedReader getInput() {
        return input;
    }

    public void setOutput(BufferedWriter output) {
        this.output = output;
    }

    public BufferedWriter getOutput() {
        return output;
    }

    public void setBuffered(boolean buffered) {
        this.buffered = buffered;
    }

    public boolean isBuffered() {
        return buffered;
    }

    public FileReader getFile(String filename) {
        try {
            return new FileReader(filename);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public void pushToStack(BufferedReader input) {
        stack.push(input);
    }

    public BufferedReader removeFromStack() {
        return stack.pop();
    }
    public boolean isInStack(BufferedReader input) {
        return stack.search(input) != -1;
    }
}
