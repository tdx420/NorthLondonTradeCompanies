/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northlondontradecompanies.ui.event;

import java.util.EventObject;
import northlondontradecompanies.core.Company;

/**
 * The class is intended to provide an way of notifying whenever a change to the
 * collection of companies object held by <code>Administrator</code> changes, and thus then
 * be reflected in the list of items displayed. The class extends<code>  EventObject</code> ,
 * which is a Java supplied class intended for sub-classing by different event
 * listeners.
 *
 * @author tdx_429
 */
public class CompanyEvent extends EventObject {

    /**
     * Constructor requires a <code> Company</code>  object to be passed in and which is in
     * turn passed to the superclass constructor(which accepts any object)
     *
     * @param company
     */
    public CompanyEvent(Company company) {
        super(company);
    }

    /**
     * This method save client objects inconvenience of having to cast the
     * getSource() method which returns the source of the event as an<code>  Object</code> ,
     * and which we know must be of <code> Company</code>  type.
     *
     * Abstract method
     *
     */
    public Company getCompany() {
        return (Company) getSource();
    }

}
