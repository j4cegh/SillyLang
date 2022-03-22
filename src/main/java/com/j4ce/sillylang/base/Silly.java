package com.j4ce.sillylang.base;

import com.j4ce.sillylang.Shared;
import com.j4ce.sillylang.declfields.SetGlobalVarsDeclField;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import static com.j4ce.sillylang.Keywords.*;

public class Silly implements Runnable {
    @Override
    public void run() {
        try {
            //creating a constructor of file class and parsing an XML file
            File file = new File("XMLFile.xml");
            //an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList docChildNodes = doc.getChildNodes();

            // top-level loop
            for (int itr = 0; itr < docChildNodes.getLength(); itr++) {
                Element element = (Element) (docChildNodes.item(itr));
                if (element.getTagName() == Program) {
                    NodeList programNodes = element.getChildNodes();
                    for (int itr2 = 0; itr2 < programNodes.getLength(); itr2++) {
                        if (programNodes.item(itr2).getNodeType() == Node.ELEMENT_NODE) {
                            Element programElement = (Element) (programNodes.item(itr2));
                            switch (programElement.getTagName()) {
                                case DF_SetGlobalVars: {
                                    SetGlobalVarsDeclField setGlobalVarsDeclField = new SetGlobalVarsDeclField(programElement);
                                    setGlobalVarsDeclField.run();
                                    break;
                                }

                                case M_Main: {
                                    Shared.langFile = new SillyInstance(programElement);
                                    Shared.langFile.Run();
                                    break;
                                }
                            }
                        }

                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}


