package com.deitel.backjackclient;

import com.deitel.blackjack.BlackJack;
import com.deitel.blackjack.BlackJackService;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.xml.ws.BindingProvider;
        
/**
 * @author LordTusha Forbes
 */

public class BlackJackJFrameClient extends javax.swing.JFrame {
    
    private String playerCards, dealerCards;
    private ArrayList<JLabel> cardBoxes;
    private int currentPlayerCard, currentDealerCard;
    private BlackJack blackJackProxy;
    private BlackJackService blackJackService;
    
    private enum GameStatus {
    
        PUSH,
        LOSE,
        WIN,
        BLACKJACK;
    }
    
    public BlackJackJFrameClient() {
        initComponents();
    
        getContentPane().setBackground(new Color(0, 180, 0));
        
        try {
            
            blackJackService = new BlackJackService();
            blackJackProxy = blackJackService.getBlackJackPort();
            
            ((BindingProvider)blackJackProxy).getRequestContext()
                    .put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
        } catch (Exception e) {
            
            e.printStackTrace();
        }//end try...catch
        
        cardBoxes = new ArrayList<JLabel>();
        
        cardBoxes.add(dealerCard1);
        cardBoxes.add(dealerCard2);
        cardBoxes.add(dealerCard3);
        cardBoxes.add(dealerCard4);
        cardBoxes.add(dealerCard5);
        cardBoxes.add(dealerCard6);
        cardBoxes.add(dealerCard7);
        cardBoxes.add(dealerCard8);
        cardBoxes.add(dealerCard9);
        cardBoxes.add(dealerCard10);
        cardBoxes.add(dealerCard11);
        cardBoxes.add(playerCard1);
        cardBoxes.add(playerCard2);
        cardBoxes.add(playerCard3);
        cardBoxes.add(playerCard4);
        cardBoxes.add(playerCard5);
        cardBoxes.add(playerCard6);
        cardBoxes.add(playerCard7);
        cardBoxes.add(playerCard8);
        cardBoxes.add(playerCard9);
        cardBoxes.add(playerCard10);
        cardBoxes.add(playerCard11);
        
    }//end constructor
    
    private void dealerPlay() {
        
        try {
            
            String[] cards = dealerCards.split("\t");
            
            for (int i = 0; i < cards.length; i++)
                displayCard(i, cards[i]);
            
            while (blackJackProxy.getHandValue(dealerCards) < 17) {
                
                String newCard = blackJackProxy.dealCard();
                dealerCards += "\t" + newCard;
                displayCard(currentDealerCard, newCard);
                ++currentDealerCard;
                
                JOptionPane.showMessageDialog(null, "Dealer Takes a Card",
                        "Dealer's Turn", JOptionPane.INFORMATION_MESSAGE);
            }//end while
            
            int dealersTotal = blackJackProxy.getHandValue(dealerCards);
            int playersTotal = blackJackProxy.getHandValue(playerCards);
                    
            if (dealersTotal > 21) {
                
                gameOver(GameStatus.WIN);
                return;
            }//end if
            
            if (dealersTotal > playersTotal)
                gameOver(GameStatus.LOSE);
            else if (dealersTotal < playersTotal)
                gameOver(GameStatus.WIN);
            else
                gameOver(GameStatus.PUSH);
                
        } catch (Exception e) {
             
            e.printStackTrace();
        }//end try...catch
    }//end dealerPlay
    
    private void gameOver(GameStatus winner) {
        
        String[] cards = dealerCards.split("\t");
        
        for (int i = 0; i < cards.length; i++)
            displayCard(i, cards[i]);
        
        if (winner == GameStatus.WIN)
            statusJLabel.setText("You Win!!!");
        else if (winner == GameStatus.LOSE)
            statusJLabel.setText("You Lose!!!");
        else if (winner == GameStatus.PUSH)
            statusJLabel.setText("It's a Push!!!");
        else
            statusJLabel.setText("BlackJack!!!");
        
        int dealersTotal = blackJackProxy.getHandValue(dealerCards);
        int playersTotal = blackJackProxy.getHandValue(playerCards);
        
        dealerTotalJLabel.setText("Dealer: " + dealersTotal);
        playerTotalJLabel.setText("Player: " + playersTotal);
        
        standJButton.setEnabled(false);
        hitJButton.setEnabled(false);
        dealJButton.setEnabled(true);
        
    }//end gameOver
    
    private void displayCard(int card, String cardValue) {
        
        try {
            
            JLabel displayLabel = cardBoxes.get(card);
            
            if (cardValue.equals("")) {
                
                displayLabel.setIcon(new ImageIcon(getClass().getResource(
                        "cardback.png")));
                return;
            }//end if
            
            String face = cardValue.substring(0, cardValue.indexOf(" "));
            String suit = cardValue.substring(cardValue.indexOf(" ") + 1);
            
            char suitLetter = 'h';
            
            switch (Integer.parseInt(suit)) {
                
                case 0:
                    suitLetter = 'h';
                    break;
                case 1:
                    suitLetter = 'd';
                    break;
                case 2:
                    suitLetter = 'c';
                    break;
                case 3:
                    suitLetter = 's';
                    break;
            }//end switch
            
            displayLabel.setIcon(new ImageIcon(getClass().getResource(
                    face + suitLetter + ".png")));
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }//end try..catch
    }//end displayCard
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        standJButton = new javax.swing.JButton();
        hitJButton = new javax.swing.JButton();
        dealJButton = new javax.swing.JButton();
        dealerJLabel = new javax.swing.JLabel();
        playerJLabel = new javax.swing.JLabel();
        dealerTotalJLabel = new javax.swing.JLabel();
        statusJLabel = new javax.swing.JLabel();
        playerTotalJLabel = new javax.swing.JLabel();
        dealerCard7 = new javax.swing.JLabel();
        dealerCard1 = new javax.swing.JLabel();
        dealerCard3 = new javax.swing.JLabel();
        dealerCard4 = new javax.swing.JLabel();
        dealerCard2 = new javax.swing.JLabel();
        dealerCard8 = new javax.swing.JLabel();
        dealerCard5 = new javax.swing.JLabel();
        dealerCard10 = new javax.swing.JLabel();
        dealerCard11 = new javax.swing.JLabel();
        dealerCard9 = new javax.swing.JLabel();
        playerCard1 = new javax.swing.JLabel();
        dealerCard6 = new javax.swing.JLabel();
        playerCard2 = new javax.swing.JLabel();
        playerCard3 = new javax.swing.JLabel();
        playerCard4 = new javax.swing.JLabel();
        playerCard5 = new javax.swing.JLabel();
        playerCard6 = new javax.swing.JLabel();
        playerCard7 = new javax.swing.JLabel();
        playerCard8 = new javax.swing.JLabel();
        playerCard9 = new javax.swing.JLabel();
        playerCard10 = new javax.swing.JLabel();
        playerCard11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BlackJack");
        setBackground(new java.awt.Color(0, 180, 0));
        setName("blackJackJFrame"); // NOI18N
        setResizable(false);

        standJButton.setMnemonic('S');
        standJButton.setText("Stand");
        standJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                standJButtonActionPerformed(evt);
            }
        });

        hitJButton.setMnemonic('H');
        hitJButton.setText("Hit");
        hitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitJButtonActionPerformed(evt);
            }
        });

        dealJButton.setMnemonic('D');
        dealJButton.setText("Deal");
        dealJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dealJButtonActionPerformed(evt);
            }
        });

        dealerJLabel.setForeground(new java.awt.Color(255, 255, 255));
        dealerJLabel.setText("Dealer's Hand");

        playerJLabel.setForeground(new java.awt.Color(255, 255, 255));
        playerJLabel.setText("Player's Hand");

        dealerTotalJLabel.setForeground(new java.awt.Color(255, 255, 255));
        dealerTotalJLabel.setText("Dealer: ");

        statusJLabel.setForeground(new java.awt.Color(255, 255, 255));

        playerTotalJLabel.setForeground(new java.awt.Color(255, 255, 255));
        playerTotalJLabel.setText("Player: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(playerCard6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(playerCard7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(playerCard8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(playerCard9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(playerCard10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(playerCard11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(playerJLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(playerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playerCard5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(122, 122, 122)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(dealerTotalJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                .addComponent(statusJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(playerTotalJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dealerJLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dealerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dealerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dealerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dealerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dealerCard5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dealerCard6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dealerCard7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dealerCard8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dealerCard9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dealerCard10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dealerCard11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(128, 128, 128)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hitJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dealJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(standJButton))
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dealerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dealerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dealerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dealerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(dealerCard5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(standJButton)
                            .addComponent(dealerJLabel))
                        .addGap(18, 18, 18)
                        .addComponent(hitJButton)
                        .addGap(18, 18, 18)
                        .addComponent(dealJButton)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dealerCard6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dealerCard7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dealerCard8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dealerCard9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dealerCard10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dealerCard11, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(playerJLabel)
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(playerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerCard5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(statusJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dealerTotalJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playerTotalJLabel)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(playerCard11, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerCard10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(playerCard6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerCard7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerCard8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerCard9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void standJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_standJButtonActionPerformed

        standJButton.setEnabled(false);
        hitJButton.setEnabled(false);
        dealJButton.setEnabled(true);
        dealerPlay();
    }//GEN-LAST:event_standJButtonActionPerformed
    //end standJButtonActionPerformed
    
    private void hitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitJButtonActionPerformed

        String card = blackJackProxy.dealCard();
        playerCards += "\t" + card;
        
        displayCard(currentPlayerCard, card);
        ++currentPlayerCard;
        
        //int dealersTotal = blackJackProxy.getHandValue(dealerCards);
        int total = blackJackProxy.getHandValue(playerCards);
        
        if (total > 21)
            gameOver(GameStatus.WIN);
        else if (total == 21) {
            
            hitJButton.setEnabled(false);
            dealerPlay();
           
        }//end else if
    }//GEN-LAST:event_hitJButtonActionPerformed
    //end hitJButtonActionPerformed
    
    private void dealJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dealJButtonActionPerformed

        String card;
        
        for (int i = 0; i < cardBoxes.size(); i++)
            cardBoxes.get(i).setIcon(null);
        
        statusJLabel.setText("");
        dealerTotalJLabel.setText("");
        playerTotalJLabel.setText("");
        
        blackJackProxy.shuffle();
        
        playerCards = blackJackProxy.dealCard();
        displayCard(11, playerCards);
        card = blackJackProxy.dealCard();
        displayCard(12, card);
        playerCards += "\t" + card;
        
        dealerCards = blackJackProxy.dealCard();
        displayCard(0, dealerCards);
        card = blackJackProxy.dealCard();
        displayCard(1, "");
        dealerCards += "\t" + card;
        
        standJButton.setEnabled(true);
        hitJButton.setEnabled(true);
        dealJButton.setEnabled(false);
        
        int dealersTotal = blackJackProxy.getHandValue(dealerCards);
        int playersTotal = blackJackProxy.getHandValue(playerCards);
        
        if (playersTotal == dealersTotal && playersTotal == 21)
            gameOver(GameStatus.PUSH);
        else if (dealersTotal == 21)
            gameOver(GameStatus.LOSE);
        else if (playersTotal == 21)
            gameOver(GameStatus.BLACKJACK);
        
        currentDealerCard = 2;
        currentPlayerCard = 13;
    }//GEN-LAST:event_dealJButtonActionPerformed
    //end dealJButtonActionPerformed
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BlackJackJFrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BlackJackJFrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BlackJackJFrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BlackJackJFrameClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BlackJackJFrameClient().setVisible(true);
            
            }//end run
        }//end anonymous inner class Runnable
        );//end callto invokeLater
    }//end main
    
    // <editor-fold desc="Variables Declaration" defaultstate="collapsed">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dealJButton;
    private javax.swing.JLabel dealerCard1;
    private javax.swing.JLabel dealerCard10;
    private javax.swing.JLabel dealerCard11;
    private javax.swing.JLabel dealerCard2;
    private javax.swing.JLabel dealerCard3;
    private javax.swing.JLabel dealerCard4;
    private javax.swing.JLabel dealerCard5;
    private javax.swing.JLabel dealerCard6;
    private javax.swing.JLabel dealerCard7;
    private javax.swing.JLabel dealerCard8;
    private javax.swing.JLabel dealerCard9;
    private javax.swing.JLabel dealerJLabel;
    private javax.swing.JLabel dealerTotalJLabel;
    private javax.swing.JButton hitJButton;
    private javax.swing.JLabel playerCard1;
    private javax.swing.JLabel playerCard10;
    private javax.swing.JLabel playerCard11;
    private javax.swing.JLabel playerCard2;
    private javax.swing.JLabel playerCard3;
    private javax.swing.JLabel playerCard4;
    private javax.swing.JLabel playerCard5;
    private javax.swing.JLabel playerCard6;
    private javax.swing.JLabel playerCard7;
    private javax.swing.JLabel playerCard8;
    private javax.swing.JLabel playerCard9;
    private javax.swing.JLabel playerJLabel;
    private javax.swing.JLabel playerTotalJLabel;
    private javax.swing.JButton standJButton;
    private javax.swing.JLabel statusJLabel;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
    
}//end BlackJackJFrameClient
