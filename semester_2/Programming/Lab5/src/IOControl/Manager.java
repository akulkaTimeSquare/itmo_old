package IOControl;

import Collection.HumanBeing;
import Command.Command;
import Exception.IncorrectCommandException;
import Exception.WrongArgsException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Manager {
    /** Manager provides communication between all program elements, controls controller, collectionManager and executor
     * contains all functions from them */
    private Controller cont;
    private CollectionManager colMan;
    private Executor exec;

    public Manager(Executor exec, Controller cont, CollectionManager colMan) {
        this.cont = cont;
        this.colMan = colMan;
        this.exec = exec;
    }

    public String getLine() throws IOException {
        return cont.getLine();
    }

    public void print(String ...inp) {
        try {
            cont.print(inp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void println(String ...inp) {
        try {
            cont.println(inp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addElement(HumanBeing el) {
        colMan.addElement(el);
    }

    public void removeElement(HumanBeing el) {
        colMan.removeElement(el);
    }

    public void removeElementById(long id) {
        colMan.removeElementById(id);
    }

    public HumanBeing getElementById(long id) {
        return colMan.getElementById(id);
    }

    public void updateId(HumanBeing el) {
        colMan.updateId(el);
    }

    public HashSet<HumanBeing> getCollection() { return colMan.getCollection(); }

    public ArrayList<HumanBeing> getSortedCollection() {
        return colMan.getSortedCollection();
    }

    public ArrayList<HumanBeing> getReverseSortedCollection() {
        return colMan.getReverseSortedCollection();
    }

    public int getCollectionSize() {
        return colMan.getCollectionSize();
    }

    public int[] getHasToothpickGroupSize() {
        return colMan.getHasToothpickGroupSize();
    }

    public String getCollectionType() {
        return colMan.getCollectionType();
    }

    public LocalDateTime getInitDate() {
        return colMan.getInitDate();
    }

    public boolean isEmptyCollection(){
        return colMan.isEmptyCollection();
    }

    public void clearCollection() {
        colMan.clearCollection();
    }

    public boolean containsId(long id) {
        return !colMan.containsId(id);
    }

    public void runCommand(String[] inp) throws IncorrectCommandException, WrongArgsException {
        exec.runCommand(this, inp);
    }

    public String[] parseCommand(String inp) {
        return exec.parseCommand(inp);
    }

    public Collection<Command> getCommands() {
        return exec.getCommands();
    }

    public ArrayDeque<Command> getCommandHistory() {
        return exec.getCommandHistory();
    }

    public Document getDocument() {
        try {
            return cont.getDocument();
        } catch (ParserConfigurationException e) {
            return null;
        }
    }
    public Document getDocumentByName(String filename) throws IOException, SAXException {
        try {
            return cont.getDocumentByName(filename);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeXml(Document document, BufferedWriter output) {
        try {
            cont.writeXml(document, output);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public int getHistorySize() {
        return exec.getHistorySize();
    }

    public void setHistorySize(int history_size) {
        exec.setHistorySize(history_size);
    }

    public void setExec(Executor exec) {
        this.exec = exec;
    }

    public void setColMan(CollectionManager colMan) {
        this.colMan = colMan;
    }

    public void setCont(Controller cont) {
        this.cont = cont;
    }

    public void setInput(BufferedReader input) {
        cont.setInput(input);
    }

    public BufferedReader getInput() {
        return cont.getInput();
    }

    public void setOutput(BufferedWriter output) {
        cont.setOutput(output);
    }

    public BufferedWriter getOutput() {
        return cont.getOutput();
    }

    public FileReader getFile(String filename) {
        return cont.getFile(filename);
    }

    public void pushToStack(BufferedReader input) {
        cont.pushToStack(input);
    }

    public BufferedReader removeFromStack() {
        return cont.removeFromStack();
    }

    public boolean isInStack(BufferedReader input) {
        return cont.isInStack(input);
    }

}