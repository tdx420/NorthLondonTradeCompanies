/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northlondontradecompanies.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import northlondontradecompanies.core.Administrator;
import northlondontradecompanies.core.Company;
import northlondontradecompanies.ui.event.CompanyEvent;
import northlondontradecompanies.ui.event.CompanyListener;

/**
 * <code>CompanyList</code> Class that extends <code>JList</code> to provide a list of objects. The class
 * implements CompanyListener interface and all its method, companyChanged(),
 * companyAdded() & companyRemoved().
 *
 * The class also defines an inner class called <code>CompanyListModel</code> to serve as
 * the model, i.e, the source of data to be shown in the list
 *
 * @author tdx_429
 */
public class CompanyList extends JList implements CompanyListener {

    /**
     * variable of a CompanyList model
     */
    private CompanyListModel model;

    /**
     * A model is added to the JList
     */
    public CompanyList() {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        model = new CompanyListModel();
        setModel(model); //JList method
    }

    /**
     * Abstract Overridden Method that reloads the data in the list Model(held
     * in CompanyListModel inner class) whenever a company is added. The
     * deselectListItems() method is also invoked since it is best to ensure
     * nothing is selected in the list, since the form remains in the "add" mode
     * to be ready to add new Company.
     *
     * @param event
     */
    @Override
    public void companyAdded(CompanyEvent event) {
        model.loadModel();
        deselectListItems();
    }

    /**
     * Abstract Overridden Method that reloads the data in the list Model(held
     * in CompanyListModel inner class) whenever a company is changed.
     *
     * @param event
     */
    @Override
    public void companyChanged(CompanyEvent event) {
        model.loadModel();

    }

    /**
     * Abstract Overridden Method that reloads the data in the list Model(held
     * in CompanyListModel inner class) whenever a company is removed. The
     * deselectListItems() method is also invoked since it is best to ensure
     * nothing is selected in the list, since the form should remain in the
     * "add" mode, after a removal of an company.
     *
     * @param event
     */
    @Override
    public void companyRemoved(CompanyEvent event) {
        model.loadModel();
        deselectListItems();
    }

    /**
     * This method ensures nothing is selected in the list. The
     * removeSelectionInterval() method de-selects all items in a list between
     * the range of indexes specified in the argument.
     */
    void deselectListItems() {
        removeSelectionInterval(0, model.getSize());
    }

    /**
     * Inner class to serve as model. It extends the java supplied
     * DefaultListModel class, and the purpose of the inner class is to obtain
     * the collection of companies, which is performed in loadModel() method.
     */
    private class CompanyListModel extends DefaultListModel {

        private Administrator admin;
        private List<Company> companies;

        /**
         * Constructor that initializes the class to act as a model. Model is a
         * data source , such as collection of objects, which in our case is
         * originally retrieved from a database file.
         */
        public CompanyListModel() {
            admin = Administrator.getInstance();
            loadModel();
        }

        /**
         * Standard method that obtains the collection of companies. Objects are
         * placed into an ArrayList so that the items in the collection are
         * indexed by their position, which is required by getElementAt(). The
         * items are sorted into natural ordering, which is by company's name.
         * The fireContentsChanged() method, which is inherited from
         * DefaultListModel, tells the JLlist component that it needs to refresh
         * its display of the items between the specified indexes.
         */
        public void loadModel() {
            companies = new ArrayList<Company>(admin.getCompanies());
            // System.out.println(companies);
            Collections.sort(companies);
            //System.out.println(companies);
            // System.out.println("\n");
            //System.out.println(admin.getCompanies());
            fireContentsChanged(this, 0, companies.size() - 1);  //defaulutListModel method
            //admin.saveData();
        }

        /**
         * Overridden method which provides the index as its argument. The
         * object at the index location in the ArrayList is obtained and
         * returned.
         *
         * @param index as its argument
         * @return
         */
        @Override
        public Object getElementAt(int index) {
            return companies.get(index);
        }

        /**
         * Override the getSize() method which provides the total number of
         * objects in the model, simply obtained by the size() method on the
         * collection.
         *
         * @return the total number of objects in the model
         */
        @Override
        public int getSize() {
            return companies.size();
        }
    }
}
