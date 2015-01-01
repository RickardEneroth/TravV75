package se.eneroth.travv75;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Rickard on 2014-12-23.
 */
public class TravV75 {

    public static void main(String args[]) throws Exception{
        TravV75 travV75 = new TravV75();
        travV75.generateRandomV75();
    }

    public void generateRandomV75() throws Exception {
        System.out.println("Skapar V75-system!");
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("travv75.properties");
        prop.load(stream);

        List horses1 = putHorsesInList(prop.getProperty("Lopp1"));
        List horses2 = putHorsesInList(prop.getProperty("Lopp2"));
        List horses3 = putHorsesInList(prop.getProperty("Lopp3"));
        List horses4 = putHorsesInList(prop.getProperty("Lopp4"));
        List horses5 = putHorsesInList(prop.getProperty("Lopp5"));
        List horses6 = putHorsesInList(prop.getProperty("Lopp6"));
        List horses7 = putHorsesInList(prop.getProperty("Lopp7"));

        List<Coupon> finalList = new ArrayList<Coupon>();
        boolean done = false;
        int count = 0;
        while(done == false) {
            Coupon coupon = new Coupon();
            coupon.legNo1 = getMarks(horses1);
            coupon.legNo2 = getMarks(horses2);
            coupon.legNo3 = getMarks(horses3);
            coupon.legNo4 = getMarks(horses4);
            coupon.legNo5 = getMarks(horses5);
            coupon.legNo6 = getMarks(horses6);
            coupon.legNo7 = getMarks(horses7);
            if (notAlreadyInList(finalList, coupon)) {
                finalList.add(coupon);
                count++;
            }

            if (count == Integer.parseInt(prop.getProperty("AntalRader"))) done = true;
        }

        System.out.println("Antal skapade rader = " + finalList.size() + "\nFörsta raden:\n" + finalList.get(0).toString());

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("issuer");
        doc.appendChild(rootElement);
        rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttribute("xsi:noNamespaceSchemaLocation", "http://webservice.atg.se/schemas/atg_filebetting.xsd");
        rootElement.setAttribute("schemaversion","ATG File Betting XSD ver 1.8");

        Element betcoupons = doc.createElement("betcoupons");
        rootElement.appendChild(betcoupons);
        int antalRader = Integer.parseInt(prop.getProperty("AntalRader"));
        for (int i = 1; i <= antalRader; i++) {

            Element coupon = doc.createElement("v75Coupon");
            betcoupons.appendChild(coupon);
            coupon.setAttribute("couponid", "" + i);
            coupon.setAttribute("date", prop.getProperty("Datum"));
            coupon.setAttribute("betmultiplier", "1");

            Element leg1 = doc.createElement("leg");
            coupon.appendChild(leg1);
            leg1.setAttribute("legno", "1");
            leg1.setAttribute("marks", finalList.get(i-1).legNo1);

            Element leg2 = doc.createElement("leg");
            coupon.appendChild(leg2);
            leg2.setAttribute("legno", "2");
            leg2.setAttribute("marks", finalList.get(i-1).legNo2);

            Element leg3 = doc.createElement("leg");
            coupon.appendChild(leg3);
            leg3.setAttribute("legno", "3");
            leg3.setAttribute("marks", finalList.get(i-1).legNo3);

            Element leg4 = doc.createElement("leg");
            coupon.appendChild(leg4);
            leg4.setAttribute("legno", "4");
            leg4.setAttribute("marks", finalList.get(i-1).legNo4);

            Element leg5 = doc.createElement("leg");
            coupon.appendChild(leg5);
            leg5.setAttribute("legno", "5");
            leg5.setAttribute("marks", finalList.get(i-1).legNo5);

            Element leg6 = doc.createElement("leg");
            coupon.appendChild(leg6);
            leg6.setAttribute("legno", "6");
            leg6.setAttribute("marks", finalList.get(i-1).legNo6);

            Element leg7 = doc.createElement("leg");
            coupon.appendChild(leg7);
            leg7.setAttribute("legno", "7");
            leg7.setAttribute("marks", finalList.get(i-1).legNo7);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("C:\\projekt\\TravV75\\out\\v75_" + prop.getProperty("Datum") + ".xml"));

        // Output to console for testing
        // StreamResult result = new StreamResult(System.out);

        transformer.transform(source, result);

        System.out.println("Filen klar!");
    }

    public int getRandomIndex() {
        Random random = new Random();
        int randomNum = random.nextInt(100) + 1;
        if (randomNum >= 1  && randomNum <= 50) return 0;
        if (randomNum >= 51 && randomNum <= 75) return 1;
        if (randomNum >= 76 && randomNum <= 88) return 2;
        if (randomNum >= 89 && randomNum <= 95) return 3;
        if (randomNum >= 96 && randomNum <= 100) return 4;

        return -1;
    }

    public List putHorsesInList(String horses) {
        List<String> items = Arrays.asList(horses.split(","));
        return items;
    }

    public String getMarks(List list) {
        int horse = Integer.parseInt((String) list.get(getRandomIndex()));
        String ret;
        if (horse == 1)  return "100000000000000";
        if (horse == 2)  return "010000000000000";
        if (horse == 3)  return "001000000000000";
        if (horse == 4)  return "000100000000000";
        if (horse == 5)  return "000010000000000";
        if (horse == 6)  return "000001000000000";
        if (horse == 7)  return "000000100000000";
        if (horse == 8)  return "000000010000000";
        if (horse == 9)  return "000000001000000";
        if (horse == 10) return "000000000100000";
        if (horse == 11) return "000000000010000";
        if (horse == 12) return "000000000001000";
        if (horse == 13) return "000000000000100";
        if (horse == 14) return "000000000000010";
        if (horse == 15) return "000000000000001";

        return "";
    }

    public boolean notAlreadyInList(List finalList, Coupon coupon) {
        for (int i = 0; i < finalList.size(); i++) {
            if (finalList.get(i).toString().equalsIgnoreCase(coupon.toString())) {
                return false;
            }
        }

        return true;
    }
}
