/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import rouletteexcercise.RouletteSettings.RouletteType;

/**
 *
 * @author Shay
 */
public class RouletteXMLManager {
    private Document document;
    
    public RouletteXMLManager(String filename) throws ParserConfigurationException, SAXException, IOException  {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(filename);
    }
    
    public RouletteSettings ReadSettings() throws Exception {
        
        Element root = document.getDocumentElement();
        
        String name = root.getAttribute("name");
        RouletteType table_type = RouletteType.Parse(root.getAttribute("table_type"));
        int min_bets_per_player = Integer.parseInt(root.getAttribute("min_bets_per_player"));
        int max_bets_per_player = Integer.parseInt(root.getAttribute("max_bets_per_player"));
        double init_sum_of_money = Double.parseDouble(root.getAttribute("init_sum_of_money"));
        
        ListNode players = root.getElementsByTagName("player");
        
        
        return new RouletteSettings(table_type, min_bets_per_player, max_bets_per_player, init_sum_of_money, 
    }
}
