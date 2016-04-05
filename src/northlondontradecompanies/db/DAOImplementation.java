/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package northlondontradecompanies.db;

import java.util.Collection;
import northlondontradecompanies.core.Company;

/**
 * Class intended to help hide the data tier implementation from the business
 * tier. It implements DAO and its abstract methods.
 *
 * @author us084
 */
public class DAOImplementation implements DAO {

    private TextFileAccess database = null;

    /**
     * Constructor that instantiates the <code> TextFileAccess
     * </code> class, which is a singleton object.
     *
     * 
     */
    public DAOImplementation(String dbPath) {
        database = TextFileAccess.getInstance(dbPath);
    }

    /**
     * Overridden Abstract method that returns the list of company that was
     * originally retrieved from the database
     *
     */
    @Override
    public Collection<Company> getCompanies() {
        return database.getCompanies();
    }

    /**
     * Overridden Abstract method that is used to save the list of object to the
     * database file.
     */
    @Override
    public void saveData() {
        database.saveData();
    }
}
