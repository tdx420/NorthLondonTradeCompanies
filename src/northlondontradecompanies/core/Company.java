/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package northlondontradecompanies.core;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Key business object class of the North London Trade Companies system which
 * represents all the data members of a single <code>company</code>. Mutator
 * Methods are set to have a default visibility. This prevents the user
 * interface or database from changing the state of any Company object except
 * through <code> Administrator</code> changeZooKeeper() method. The class
 * defines a natural ordering on the <I>name</I>
 * value of the designated Company.
 *
 * @author tdx_429
 */
public class Company implements Comparable<Company> {

    //Declare instance variables to hold company attributes
    /**
     * Company name
     */
    private String name;
    /**
     * Company location
     */
    private String location;
    /**
     * Services offered by Company
     */
    private String services;
    /**
     * Number of Employees that work in Company
     */
    private BigDecimal numberOfEmployees;
    /**
     * Daily Rate earnings of a company
     */
    private BigDecimal dailyRate;
    /**
     * General Description of the company
     */
    private String generalDescription;

    /**
     * Constructor to assign all Company attribute values. The constructor also
     * invokes private validate method that checks for any invalid entries made,
     * when the end user might want to invoke the company constructor.
     *
     * @param n The Company's name
     * @param loc The company location
     * @param serv The service offered by Company
     * @param numOfEmp The number of employees that work in Company
     * @param dailyRat Daily rate income of the company
     * @param genDesc General Description of the company
     *
     */
    public Company(String n, String loc, String serv, BigDecimal numOfEmp, BigDecimal dailyRat,
            String genDesc) {
        validate(n, loc, serv, numOfEmp, dailyRat, genDesc);
        name = n;
        location = loc;
        services = serv;
        numberOfEmployees = numOfEmp;
        dailyRate = dailyRat;
        generalDescription = genDesc;

    }

    Company(String n, String loc, String serv, BigDecimal numOfEmp, BigDecimal dailyRat) {
        name = n;
        location = loc;
        services = serv;
        numberOfEmployees = numOfEmp;
        dailyRate = dailyRat;
    }

    /**
     * Standard accessor method to return value of name attribute
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Default visibility mutator method to set the value of name attribute. It
     * is useful whenever a user may want to change the name attribute.
     *
     * @param name the name to set
     */
    void setName(String name) {
        validateName(name);
        this.name = name;
    }

    /**
     * Standard accessor method to return the value of the lcoation attribute
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Default visibility mutator method to set the value of location attribute.
     * Useful whenever a user may want to change the location attribute.
     *
     * @param location the location to set
     */
    void setLocation(String location) {
        validateLocation(location);
        this.location = location;
    }

    /**
     * Standard accessor method to return the value of the service attribute
     *
     * @return the services
     */
    public String getServices() {
        return services;
    }

    /**
     * Default visibility mutator method to set the value of the service
     * attribute Useful whenever a user may want to change the service
     * attribute.
     *
     * @param services the services to set
     */
    void setServices(String services) {
        validateServices(services);
        this.services = services;
    }

    /**
     * Standard Accessor method to return the the value of the number of
     * Employees attribute
     *
     * @return the numberOfEmployees
     */
    public BigDecimal getNumberOfEmployees() {
        return numberOfEmployees;
    }

    /**
     * Default visibility mutator method to set the value of the number of
     * Employees attribute Useful whenever a user may want to change the number
     * of Employee attribute.
     *
     * @param numberOfEmployees the numberOfEmployees to set
     */
    void setNumberOfEmployees(BigDecimal numberOfEmployees) {
        validateNumberOfEmployees(numberOfEmployees);
        this.numberOfEmployees = numberOfEmployees;
    }

    /**
     * Standard Accessor method to return the value of Daily rate attribute
     *
     * @return the dailyRate
     */
    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    /**
     * Default visibility mutator method to set the daily rate value attribute
     * Useful whenever a user may want to change the daily rate value.
     *
     * @param dailyRate the dailyRate to set
     */
    void setDailyRate(BigDecimal dailyRate) {
        validateDailyRate(dailyRate);
        this.dailyRate = dailyRate;
    }

    /**
     * Standard accessor method to return the value of general description
     * attribute.
     *
     * @return the generalDescription
     */
    public String getGeneralDescription() {
        return generalDescription;
    }

    /**
     * Default visibility mutator method to set the value of the general
     * description attribute. Useful whenever a user may want to change the
     * value of the general description attribute.
     *
     * @param generalDescription the generalDescription to set
     */
    void setGeneralDescription(String generalDescription) {
        validateGeneralDescription(generalDescription);
        this.generalDescription = generalDescription;
    }

    // Inner class to sort 
    /*
     public static class SortByNameLocation implements Comparator<Company> {
    
     public int compare(Company company1, Company company2){
        
     int result = company1.getName().compareTo(company2.getName());
     if(result !=0) return result;
       
     result = company1.getLocation().compareTo(company2.getLocation());
     if(result !=0) return result;
       
     result = company1.getGeneralDescription().compareTo(company2.getGeneralDescription());
     if(result !=0) return result;
       
     result = company1.getServices().compareTo(company2.getServices());
     if(result !=0) return result;
       
     result =  company1.getDailyRate().compareTo( company2.getDailyRate() );
     if(result !=0) return result;
       
     result = company1.getNumberOfEmployees().compareTo(company2.getNumberOfEmployees());
     if(result !=0) return result;
       
       
     return company1.hashCode()- company2.hashCode();
     }
     }
     */
    //Arrays.sort( arrayX, new Company.SortByNameLocation);
    /**
     * Sensible String representation of Company object that prints the name
     *
     */
    public String toString() {
        return getName();
    }

    /**
     * Defines the natural ordering of this company object class first by the
     * name, if both are equal, then by location, generalDescription, services,
     * dailyRate, numberOfEmployees attributes(in Ascending order). If it has reached this point then,
      it is either a same object, or two different objects having identical values. To be consistent with equals method, we perform a 
      * hashCode() check. 
     */
    @Override
    public int compareTo(Company otherCompany) {

        int result = getName().compareTo(otherCompany.getName());
        if (result != 0) {
            return result;
        }

        result = getLocation().compareTo(otherCompany.getLocation());
        if (result != 0) {
            return result;
        }

        result = getGeneralDescription().compareTo(otherCompany.getGeneralDescription());
        if (result != 0) {
            return result;
        }

        result = getServices().compareTo(otherCompany.getServices());
        if (result != 0) {
            return result;
        }

        result = getDailyRate().compareTo(otherCompany.getDailyRate());
        if (result != 0) {
            return result;
        }

        result = getNumberOfEmployees().compareTo(otherCompany.getNumberOfEmployees());
        if (result != 0) {
            return result;
        }

        return hashCode() - otherCompany.hashCode();

    }

    /**
     * protected helper method in the company class which performs some
     * validation checks for the name argument. If the <I>name<I> is empty, a
     * message will be generated to notify the user.
     *
     * @param name the name of the company
     */
    protected void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name  field is empty");
        }
    }

    /**
     * protected helper method in the company class which performs some
     * validation checks for the location argument. If the <I>location<I> is
     * empty, a message will be generated to notify the user.
     *
     * @param location location of the company
     */
    protected void validateLocation(String location) {
        if (location.isEmpty()) {
            throw new IllegalArgumentException("Location field is empty");
        }
    }

    /**
     * protected helper method in the company class which performs some
     * validation checks for the services argument. If the <I>services<I> is
     * empty, a message will be generated to notify the user.
     *
     * @param services services offered by the company
     */
    protected void validateServices(String services) {
        if (services.isEmpty()) {
            throw new IllegalArgumentException("Services field is empty");
        }
    }

    /**
     * protected helper method in the company class which performs some
     * validation checks for the numberOfEmployees argument. If the
     * <I>numOfEmployees<I> is empty, a message will be generated to notify the
     * user.
     *
     * @param numberOfEmployees number of employees employed company
     */
    protected void validateNumberOfEmployees(BigDecimal numberOfEmployees) {
        if (numberOfEmployees.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Number of Employees Must be greater than 0 & a valid number \nCurrent Value" + numberOfEmployees);
        }
    }

    /**
     * protected helper method in the company class which performs some
     * validation checks for the dailyRate argument. If the <I>dailyRate<I> is
     * empty, a message will be generated to notify the user.
     *
     * @param dailyRate daily Rate earnings of the company
     */
    protected void validateDailyRate(BigDecimal dailyRate) {
        if (dailyRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Daily Rate must be greater than 0 & a valid number \nCurrent Value: " + dailyRate);
        }

    }

    /**
     * protected helper method in the company class which performs some
     * validation checks for the generalDescription argument. If the
     * <I>generalDescription<I> is empty, a message will be generated to notify
     * the user.
     *
     * @param generalDescription the generalDescription of the company
     */
    protected void validateGeneralDescription(String generalDescription) {
        if (generalDescription.isEmpty()) {
            throw new IllegalArgumentException("General Description field is empty");
        }
    }

    /**
     * private helper method that helps constructor to avoid invoking each
     * individual validation method
     *
     */
    private void validate(String name, String location, String services, BigDecimal numberOfEmployees, BigDecimal dailyRate, String generalDescription) {

        validateName(name);
        validateLocation(location);
        validateServices(services);
        validateNumberOfEmployees(numberOfEmployees);
        validateDailyRate(dailyRate);
        validateGeneralDescription(generalDescription);
    }
}
