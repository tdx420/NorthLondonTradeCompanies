/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northlondontradecompanies.ui.event;

import java.util.EventListener;

/**
 * Interface that extends <code> EventListener</code> , which is a Java supplied
 * interface intended for sub-classing by different event listeners. Three
 * methods are specified for when a <code>Company</code>  is added, changed or removed.
 * The argument for each of these method is </code> CompanyEvent</code> .
 *
 * @author tdx_429
 */
public interface CompanyListener extends EventListener {

    public void companyAdded(CompanyEvent event);

    public void companyChanged(CompanyEvent event);

    public void companyRemoved(CompanyEvent event);

}
