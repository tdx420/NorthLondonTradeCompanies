/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northlondontradecompanies.core;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import northlondontradecompanies.db.DAO;
import northlondontradecompanies.db.DAOImplementation;
import northlondontradecompanies.ui.CompanyList;
import northlondontradecompanies.ui.event.CompanyEvent;
import northlondontradecompanies.ui.event.CompanyListener;

/**
 * This class will store the data associated with the <code>Company</code>(in
 * collection objects). Because there should only be one set of data to model
 * the objects of <code>Company</code>, we only ever want to create one
 * <code>Administrator</code> object (known as singleton), to communicate with the User
 * Interface or database. The intention is that user interface classes can only
 * communicate with the core system through the <code>Administrator</code> class.
 *
 * @author tdx_429
 */
public class Administrator {

    /**
     * private static variable for the singleton object so that outside classes
     * does not have direct access to it, but static so it is able to access it
     * without needing a Administrator object.
     */
    private static Administrator instance;
    /**
     * Instance variable to store a collection of listeners. The Administrator
     * class sores the collection of Company objects so it is a natural place to
     * maintain references to objects that want to know about new, changed or
     * remove companies(i.e, companyListeners) and to notify them whenever these
     * event occurs
     */
    private Collection<CompanyListener> companyListeners;
    /**
     *
     */
    private DAO dao;
    /**
     * Collection to hold the companies
     */
    Collection<Company> companies;

    /**
     * Private Constructor which allows only the class itself to instantiate its
     * own objects.
     *
     */
    private Administrator() {

        // companies =new  HashSet<Company> (dao.getCompanies());
        companyListeners = new ArrayList<CompanyListener>();
        String dbPath = System.getProperty("user.dir") + File.separator + "database";
        dao = new DAOImplementation(dbPath);
        //companies = new ArrayList<Company>(dao.getCompanies());
        companies = dao.getCompanies();
        // createExampleCompanies();
    }

    /**
     * Static method to instantiate the <code>Administrator</code> class. The
     * method checks to see if the variable instance is null, which it is the
     * very first time this method is invoked. If it is null a new <code>Administrator</code>
     * object is created and assigned to the instance reference. If it is not
     * null, it will not create a new one ,and return the existing one
     * previously created.
     *
     */
    public static Administrator getInstance() {

        if (instance == null) {
            instance = new Administrator();
        }
        return instance;
    }

    /**
     * Method to add a new company. The method requires the appropriate data as
     * its arguments, and instantiates a <code>Company</code> objects, adds it to the
     * collection and returns it. The <code>company</code> constructor is enclosed in a try
     * catch block, and any exception that might occur is trapped. A code is
     * generated suitable of <code>ValidationException</code> by retrieving the text of
     * caught exception which is passed to the constructor of
     * <code>ValidationException</code>, which is our custom created Exception class. Because
     * the <code>ValidationException</code> is a checked exception, the method signature
     * needs to specify that it can potentially be thrown. The
     * NumberFormatException is catched before the IllegalArgumentException
     * since the former is a subclass of the latter. The method also invokes private
     * fireCompanyCreated() method once the company has been added to the collection.
     *
     * @param name
     * @param location
     * @param services
     * @param numberOfEmployees
     * @param dailyRate
     * @param generalDescription
     *
     * @throws ValidationException
     */
    public Company createCompany(String name, String location, String services, String numberOfEmployees, String dailyRate, String generalDescription) throws ValidationException {
        try {
            //Collection<Company> companies = dao.getCompanies();
            Company company = new Company(name, location, services, new BigDecimal(numberOfEmployees), new BigDecimal(dailyRate), generalDescription);
            companies.add(company);
            fireCompanyCreated(company);
            saveData();
            return company;

        } catch (NumberFormatException ex) {
            throw new ValidationException("is not valid");
        } catch (IllegalArgumentException ex) {
            throw new ValidationException(ex.getMessage());
        }
    }

    /**
     * Method that requires an existing <code>Company</code> object and its new data as its
     * arguments, which is then used to modify the provided object. The
     * statements are enclosed in try..catch block for the same reason as for
     * createCompany() method. The method also invokes private fireCompanyChanged() method
     * once the company attributes has been changed.
     *
     * @param company
     * @param name
     * @param location
     * @param services
     * @param numberOfEmployees
     * @param dailyRate
     * @param getDescription
     * @throws ValidationException
     */
    public void changeCompany(Company company, String name, String location, String services, String numberOfEmployees, String dailyRate, String getDescription) throws ValidationException {
        try {
            company.setName(name);
            company.setLocation(location);
            company.setServices(services);
            company.setNumberOfEmployees(new BigDecimal(numberOfEmployees));
            company.setDailyRate(new BigDecimal(dailyRate));
            company.setGeneralDescription(getDescription);
            fireCompanyChanged(company);
            saveData();

        } catch (NumberFormatException ex) {
            throw new ValidationException("is not valid");

        } catch (IllegalArgumentException ex) {
            throw new ValidationException(ex.getMessage());
        }
    }

    /**
     * Method that requires a <code>Company</code> object as its arguments and removes it
     * from collection. The method also invokes private fireCompanyRemoved() method once the
     * company has been removed from the collection.
     *
     * @param company
     */
    public void removeCompany(Company company) {

        companies.remove(company);
        //   System.out.println( getCompanies());
        fireCompanyRemoved(company);
        saveData();

    }

    /**
     * Method that calls the saveData() method of DAOImplementation, which is used to save collection of object
     * data back to database file for data persistence. 
     */
    public void saveData() {
        dao.saveData();
    }

    /**
     * Method that returns the collection of Companies. This method use
     * unmodifialbleCollection() method, which is a static utility method of the Collections
     * class which returns a read-only version of the collection specified in
     * its argument. This prevents privacy leak.
     *
     *
     */
    public Collection<Company> getCompanies() {
        return Collections.unmodifiableCollection(companies);
    }

    /**
     * private method that
     *
     * @param company
     */
    /**
     * Private helper method that notifies all registered listeners whenever a
     * new Company is added. The method creates a CompanyEvent and then iterates
     * over each registered listener to notify when a new company is created.
     *
     * @param company
     */
    private void fireCompanyCreated(Company company) {
        CompanyEvent event = new CompanyEvent(company);
        for (CompanyListener listener : companyListeners) {
            listener.companyAdded(event);
        }
    }

    /**
     * Private helper method that notifies all registered listeners whenever a
     * new Company is changed. The method creates a CompanyEvent and then
     * iterates over each registered listener to notify when a new company is
     * changed.
     *
     * @param company
     */
    private void fireCompanyChanged(Company company) {
        CompanyEvent event = new CompanyEvent(company);
        for (CompanyListener listener : companyListeners) {
            listener.companyChanged(event);
        }
    }

    /**
     * Private helper method that notifies all registered listeners whenever a
     * new Company is removed. The method creates a CompanyEvent and then
     * iterates over each registered listener to notify when a new company is
     * created.
     *
     * @param company
     */
    private void fireCompanyRemoved(Company company) {
        CompanyEvent event = new CompanyEvent(company);
        for (CompanyListener listener : companyListeners) {
            listener.companyRemoved(event);
        }
    }

    /**
     * Standard public method to help client objects to register themselves as
     * listeners
     *
     * @param listener
     */
    public void addCompanyListener(CompanyListener listener) {
        companyListeners.add(listener);
    }

}
