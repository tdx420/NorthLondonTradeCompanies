/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package northlondontradecompanies.db;

import java.util.Collection;
import northlondontradecompanies.core.Company;

/**
 * Interface specifying two methods getCompanies() and saveData() 
 * @author us084
 */
public interface DAO {
   public Collection<Company> getCompanies();
   public void saveData();
    
}
