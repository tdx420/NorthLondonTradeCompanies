/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northlondontradecompanies.ui;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import northlondontradecompanies.core.Administrator;

/**
 * This class holds the <code> CompanyTable</code> and another panel with an
 * TextField to facilitate searching for <I>service</I> attribute.
 *
 *
 * @author tdx_429
 */
public class CompanyTablePanel extends javax.swing.JPanel {

    /**
     * instance variable to reference Administrator object
     */
    // private Administrator admin;
    /**
     * A <code>DocumentListener</code> interface is registered to searchField,
     * and then its three abstract methods are implemented, to which newFilter()
     * method of <code>CompanyTable</code> is invoked, that allows user to
     * search for the service attribute. The table is updated accordingly if a search 
     * matches from the changes to the text typed in the searchField. 
     */
    public CompanyTablePanel() {
        initComponents();
        // admin = Administrator.getInstance();
        //  admin.addCompanyListener(companyTable);
        searchField.getDocument().addDocumentListener(
                new DocumentListener() {

                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        companyTable.newFilter(searchField.getText());
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        companyTable.newFilter(searchField.getText());
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        companyTable.newFilter(searchField.getText());
                    }
                });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        companyTable1 = new northlondontradecompanies.ui.CompanyTable();
        companyTable2 = new northlondontradecompanies.ui.CompanyTable();
        jPanel1 = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        searchLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        companyTable = new northlondontradecompanies.ui.CompanyTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        searchLabel.setText("Search for Services : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(searchLabel)
                .addGap(18, 18, 18)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(274, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchLabel)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setViewportView(companyTable);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private northlondontradecompanies.ui.CompanyTable companyTable;
    private northlondontradecompanies.ui.CompanyTable companyTable1;
    private northlondontradecompanies.ui.CompanyTable companyTable2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel searchLabel;
    // End of variables declaration//GEN-END:variables
}
