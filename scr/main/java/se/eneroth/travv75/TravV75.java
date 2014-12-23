package se.eneroth.travv75;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by Rickard on 2014-12-23.
 */
public class TravV75 {
    public static void main(String args[]) throws Exception{
        System.out.println("Hello V75!");
        TravV75 t = new TravV75();
        t.generateRandomV75();
    }

    public void generateRandomV75() throws Exception {
        System.out.println("Generating V75!");
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("travv75.properties");
        prop.load(stream);
        System.out.println("Lopp1=" + prop.getProperty("Lopp1"));
        System.out.println("Lopp2=" + prop.getProperty("Lopp2"));
        System.out.println("Lopp3=" + prop.getProperty("Lopp3"));
        System.out.println("Lopp4=" + prop.getProperty("Lopp4"));
        System.out.println("Lopp5=" + prop.getProperty("Lopp5"));
        System.out.println("Lopp6=" + prop.getProperty("Lopp6"));
        System.out.println("Lopp7=" + prop.getProperty("Lopp7"));

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("issuer");
        doc.appendChild(rootElement);
        rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttribute("xsi:noNamespaceSchemaLocation", "http://webservice.atg.se/schemas/atg_filebetting.xsd");
        rootElement.setAttribute("company","Eneroth Trav");
        rootElement.setAttribute("product","V75-generator");
        rootElement.setAttribute("version","0.9");
        rootElement.setAttribute("createddate", new Date().toString());
        rootElement.setAttribute("createdtime", new Date().toString());
        rootElement.setAttribute("schemaversion","ATG File Betting XSD ver 1.8");

        Element coupon = doc.createElement("v75Coupon");
        rootElement.appendChild(coupon);
        coupon.setAttribute("couponid", "1");
        coupon.setAttribute("date", prop.getProperty("Datum"));
        coupon.setAttribute("betmultiplier", "1");
        coupon.setAttribute("v7", "1");

        Element leg1 = doc.createElement("leg");
        coupon.appendChild(leg1);
        leg1.setAttribute("legno", "1");
        leg1.setAttribute("marks", "100000000000000");

        Element leg2 = doc.createElement("leg");
        coupon.appendChild(leg2);
        leg2.setAttribute("legno", "2");
        leg2.setAttribute("marks", "010000000000000");

        Element leg3 = doc.createElement("leg");
        coupon.appendChild(leg3);
        leg3.setAttribute("legno", "3");
        leg3.setAttribute("marks", "001000000000000");

        Element leg4 = doc.createElement("leg");
        coupon.appendChild(leg4);
        leg4.setAttribute("legno", "4");
        leg4.setAttribute("marks", "000100000000000");

        Element leg5 = doc.createElement("leg");
        coupon.appendChild(leg5);
        leg5.setAttribute("legno", "5");
        leg5.setAttribute("marks", "000010000000000");

        Element leg6 = doc.createElement("leg");
        coupon.appendChild(leg6);
        leg6.setAttribute("legno", "6");
        leg6.setAttribute("marks", "000001000000000");

        Element leg7 = doc.createElement("leg");
        coupon.appendChild(leg7);
        leg7.setAttribute("legno", "7");
        leg7.setAttribute("marks", "000000100000000");

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("C:\\projekt\\TravV75\\out\\v75.xml"));

        // Output to console for testing
        // StreamResult result = new StreamResult(System.out);

        transformer.transform(source, result);

        System.out.println("File saved!");
    }
}
