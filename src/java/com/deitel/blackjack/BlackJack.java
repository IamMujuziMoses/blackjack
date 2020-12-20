package com.deitel.blackjack;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.Random;
import com.sun.xml.ws.developer.servlet.HttpSessionScope;

/**
 * @author LordTusha Forbes
 */

@HttpSessionScope
@WebService()
public class BlackJack {

    private ArrayList<String> deck;
    private static final Random randomObject = new Random();
    
    @WebMethod(operationName = "dealCard")
    public String dealCard() {
        
        String card = "";
        card = deck.get(0);
        deck.remove(0);
        return card;
        
    }//end dealCard
    
    @WebMethod(operationName = "shuffle")
    public void shuffle() {
        
        deck = new ArrayList<String>();
        
        for (int face = 1; face <= 13; face++)
            for (int suit = 0; suit <= 3; suit++)
                deck.add(face + " " + suit);
        
        String tempCard;
        int index;
        
        for (int i = 0; i < deck.size(); i++) {
            
            index = randomObject.nextInt(deck.size() - 1);
            
            tempCard = deck.get(i);
            deck.set(i, deck.get(index));
            deck.set(index, tempCard);
        }//end for
    }//end shuffle
    
    @WebMethod(operationName = "getHandValue")
    public int getHandValue(@WebParam(name = "hand") String hand) {
        
        String[] cards = hand.split("\t");
        
        int total = 0, face, aceCount = 0;
        
        for (int i = 0; i < cards.length; i++) {
            
            face = Integer.parseInt(cards[i].substring(0, cards[i].indexOf(" ")));
            
            switch (face) {
                case 1:
                    ++aceCount;
                    break;
                case 11:
                case 12:
                case 13:
                    total += 10;
                    break;
                default:
                    total += face;
                    break;
            }//end switch
        }//end for
        
        if (aceCount > 0) {
            
            if (total + 11 + aceCount -1 <= 21)
                total += 11 + aceCount - 1;
            else
                total += aceCount;
        }//end if
        return total;
    }//end getHandValue
    
}//end BlackJack
