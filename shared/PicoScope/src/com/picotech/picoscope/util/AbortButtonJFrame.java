/*******************************************************************************
 *
 * Filename:    AbortButtonJFrame.java
 *
 * Author:      HSM
 *
 * Description:
 *      JFrame object for an abort button.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope.util;

import com.picotech.picoscope.PicoScope;

/**
 *
 */
public class AbortButtonJFrame extends javax.swing.JFrame 
{

    private PicoScope picoScope;
    
    /** Creates new form AbortButtonJFrame */
    public AbortButtonJFrame(PicoScope pico_scope)
    {
        initComponents();
        picoScope = pico_scope;
    }

    /** This method is called from within the constructor to
     * initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        abortjButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Abort Button");
        setName("Abort Current Task"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        abortjButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        abortjButton.setText("Click to Abort");
        abortjButton.setToolTipText("Abort current task.");
        abortjButton.setBorder(null);
        abortjButton.setMaximumSize(new java.awt.Dimension(200, 100));
        abortjButton.setMinimumSize(new java.awt.Dimension(200, 100));
        abortjButton.setPreferredSize(new java.awt.Dimension(200, 100));
        abortjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abortjButtonActionPerformed(evt);
            }
        });
        getContentPane().add(abortjButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void abortjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abortjButtonActionPerformed
        // TODO add your handling code here:
        picoScope.setAbortButtonPressed(true);
        this.dispose();
    }//GEN-LAST:event_abortjButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abortjButton;
    // End of variables declaration//GEN-END:variables
}