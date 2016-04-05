/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northlondontradecompanies.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import northlondontradecompanies.core.Administrator;
import northlondontradecompanies.core.Company;
import northlondontradecompanies.ui.event.CompanyEvent;
import northlondontradecompanies.ui.event.CompanyListener;

/**
 * The class is used to display read-only table in a tab that will be generated
 * in a <code>CompanyFrame</code> class. The class extends <code>JTable</code>
 * and includes an inner class CompanyTableModel that extends AbstractTableModel
 * to manage the data. The class implements <code>CompanyListener</code>
 * interface and all its method, companyChanged(), companyAdded() &
 * companyRemoved(). The techniques displayed are similar to
 * <code>CompanyList</code> class.
 *
 * @author tdx_429
 */
public class CompanyTable extends JTable implements CompanyListener {

    private Administrator admin;
    TableRowSorter<CompanyTableModel> sorter;
    CompanyTableModel model;

    /**
     * Constructor that adds a model to the JTable. The model is also passed to
     * the argument of <code>TableRowSorter</code> which will handle the
     * search mechanism to search for attribute <I>service</I> in the table. The
     * sorter instance of <code>TableRowSorter</code> is then passed to
     * setRowSorter() method of <code>JTable</code>. The Constructor also
     * obtains the <code>Administrator</code> object to call its
     * addCompanyListener() method ,and the instance companyList of <code>CompanyList</code> is passed in its
     * argument so that it listens to events. Since the table also needs to
     * listen to when a list from a table is selected, the constructor also
     * invokes a <code>ListSeelctionListener</code> class which extends
     * <code>EventListener</code> and its valueChanged() method, which is passed in
     * addListSelectionListener() method of <code>JTable</code>. When a list is selected, JoptionPane
     * dialog box is used to show user all the attributes of a
     * particular company, including the generalDescription
     */
    public CompanyTable() {
        model = new CompanyTableModel();
        setModel(model);
        sorter = new TableRowSorter<CompanyTableModel>(model);
        setPreferredScrollableViewportSize(new Dimension(700, 100));
        setFillsViewportHeight(true);
        setRowSorter(sorter);
        admin = Administrator.getInstance();
        admin.addCompanyListener(this);

        getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = getSelectedRow(); //get the table row
                int column = getSelectedColumn(); //get the table column 
                if (row >= 0 && column >= 0) {

                    //int rowNo = getSelectedRow();
                    JOptionPane.showMessageDialog(null,
                            "Name: " + getValueAt(getSelectedRow(), 0).toString() + "\n"
                            + "Address :" + getValueAt(getSelectedRow(), 1).toString() + "\n"
                            + "Services :" + getValueAt(getSelectedRow(), 2).toString() + "\n"
                            + "Number Of Employees :" + getValueAt(getSelectedRow(), 3).toString() + "\n"
                            + "Daily Rate :" + getValueAt(getSelectedRow(), 4).toString() + "\n"
                            + "Description :" + model.getGeneralDescription(), "Company", JOptionPane.PLAIN_MESSAGE);

                    //removeSelectionInterval(row,column);
                    getSelectionModel().clearSelection();
                }

            }
        });
    }

    //also used in panel , add action performed 
    /**
     * Abstract Overridden Method that reloads the data in the Table Model(held
     * in CompanyTableModel inner class) whenever a company is added.
     *
     * @param event
     */
    @Override
    public void companyAdded(CompanyEvent event) {
        model.loadModel();
    }

    /**
     * Abstract Overridden Method that reloads the data in the Table Model(held
     * in CompanyTableModel inner class) whenever a company is changed.
     *
     * @param event
     */
    @Override
    public void companyChanged(CompanyEvent event) {
        model.loadModel();
    }

    /**
     * Abstract Overridden Method that reloads the data in the Table Model(held
     * in CompanyTableModel inner class) whenever a company is removed.
     *
     * @param event
     */
    @Override
    public void companyRemoved(CompanyEvent event) {
        model.loadModel();
    }

    /**
     * This method is used so that a user can search for services attribute from
     * the table. The method use regexFilter() method of <code> RowFilter</code> class to filter out
     * entries from the model so that they are not shown in the view. 
     *
     * @param text
     */
    public void newFilter(String text) {
        RowFilter<CompanyTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            
            rf = RowFilter.regexFilter("(?i)" + text, 2);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    /**
     * Inner class to serve as model. The data is obtained from the
     * Administrator class into an ArrayList, and the getRowCount(),
     * getColoumnCount(0, getValueAt() and getColumnName() methods are
     * ovverridden to provide table with various table values.
     */
    private class CompanyTableModel extends AbstractTableModel {

        private List<Company> companies;
        private Administrator admin;

        /**
         * Constructor that initializes the class to act as a model. Model is a
         * data source , such as collection of objects, which in our case is
         * originally retrieved from a database file.
         */
        public CompanyTableModel() {
            admin = Administrator.getInstance();
            loadModel();
        }

        /**
         * Standard method that obtains the collection of companies. Objects are
         * placed into an ArrayList so that the items in the collection are
         * indexed by their position. The fireContentsChanged() method, which is
         * inherited from AbstractTableModel, tells the JTable component that it
         * needs to refresh its display of the items.
         */
        private void loadModel() {
            companies = new ArrayList<Company>(admin.getCompanies());
            Collections.sort(companies);
            fireTableStructureChanged();
        }

        /**
         * This method returns the total number of rows in the table, which will
         * correspond to the total numbers of items in the ArrayList Object.
         *
         * @return the size of the arrayList
         */
        @Override
        public int getRowCount() {
            return companies.size();
        }

        /**
         * This method returns the number of columns that should be represented
         * in the table.
         *
         * @return the total number of columns in table, which in this case is 5
         */

        @Override
        public int getColumnCount() {
            return 5;
        }

        /**
         * This method provides two arguments, being the rowIndex and the column
         * index. The method retrieves the text from the ArrayList object using
         * the specified row index. A switch statement is used to determine the
         * column index, so the compiler will check that something is returned
         * in all cases, so a default is also added.
         *
         * @param rowIndex
         * @param columnIndex
         * @return
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

            switch (columnIndex) {
                case 0:
                    return companies.get(rowIndex).getName();
                case 1:
                    return companies.get(rowIndex).getLocation();
                case 2:
                    return companies.get(rowIndex).getServices();
                case 3:
                    return companies.get(rowIndex).getNumberOfEmployees().toPlainString();
                case 4:
                    return companies.get(rowIndex).getDailyRate().toPlainString();

                default:
                    throw new IllegalStateException();
            }
        }

        /**
         * Private helper method that returns the generalDescription of a
         * particular Company. This is used because we do not want to display
         * the generalDescroption in the table, but may want to display the
         * generalDescription when a particular list from a table is clicked.
         *
         * @return
         */
        private String getGeneralDescription() {
            int rowNo = getSelectedRow();
            return companies.get(rowNo).getGeneralDescription();
        }

        /**
         * This method sets the column headers of the table.
         *
         * @param columnIndex
         * @return
         */
        public String getColumnName(int columnIndex) {
            String[] columnHeaders = {"Name", "Location", "Services", "Number Of Employees", "Daily Rate"};
            return columnHeaders[columnIndex];
        }

    }

}
