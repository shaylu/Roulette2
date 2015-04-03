/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
        
        if (max_bets_per_player < min_bets_per_player)
            throw new Exception("Maximum bets are smaller than minimum bets.");
        
        int init_sum_of_money = Integer.parseInt(root.getAttribute("init_sum_of_money"));
        
        int humans = 0, computers = 0;
        NodeList players = root.getElementsByTagName("player");
        for (int i = 0; i < players.getLength(); i++) {
            Node node = players.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                RoulettePlayer.RoulettePlayerType type = 
                        RoulettePlayer.RoulettePlayerType.Parse(node.getAttributes().getNamedItem("type").getNodeValue());

                if (type == RoulettePlayer.RoulettePlayerType.COMPUTER)
                    computers++;
                else if (type == RoulettePlayer.RoulettePlayerType.HUMAN)
                    humans++;
            }
        }
        
        if (humans+computers > 6)
            throw new Exception("Number of human players and computer players are bigger than 6.");

        return new RouletteSettings(table_type, min_bets_per_player, max_bets_per_player, init_sum_of_money, humans, computers, name); 
    }
}
