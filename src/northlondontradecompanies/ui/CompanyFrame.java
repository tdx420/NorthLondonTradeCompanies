/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northlondontradecompanies.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import northlondontradecompanies.core.Administrator;
import northlondontradecompanies.db.DAO;

/**
 * This main Frame of the system. The class extends from <code>JFrame</code>
 *
 * @author tdx_429
 */
public class CompanyFrame extends JFrame {

    /**
     * Constructor to setup the Frame. It creates a <code>JTabbedpane</code> to represent two
     * tabs, one for <code>CompanyPanel</code> (to represent <code>ComapnyList</code> and <code>CompanyEditor</code>)
     * and other for <code>CompanyTablePanel</code>(to represent <code>CompanyTable</code>). The <code>JTabbedPane</code> is
     * then added to <code>JFrame</code>.
     *
     */
    public CompanyFrame() {
        super("NorthLondonTradeCompanies");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //  setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //create tabbed pane
        JTabbedPane tabPane = new JTabbedPane();

        //create company panel for list and editing , add, delete
        CompanyPanel companyPanel = new CompanyPanel(this);
        //creae table panel 
        CompanyTablePanel companyTablePanel = new CompanyTablePanel();
        tabPane.add("Edit Companies", companyPanel);
        tabPane.add("Company Records", new JScrollPane(companyTablePanel));

        add(tabPane, BorderLayout.CENTER);

     //CompanyMenu menu = new CompanyMenu(this);
        //add(menu);
        //set components to their prefferd size
        pack();
        //place in the centre of the desktop
        setLocationRelativeTo(null);
        //make frame visible
        setVisible(true);
        // System.out.println(System.getProperty("user.dir"));
    }

}
