/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package northlondontradecompanies.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import northlondontradecompanies.core.Administrator;
import northlondontradecompanies.core.Company;
import northlondontradecompanies.core.ValidationException;

/**
 * Class that handles parsing of the data file and load records.
 * The class also handles mechanism to save the list of company records back to the database file. 
 * @author us084
 */
class TextFileAccess {

    private BufferedReader ip;
    /**
     * static variable to instantiate the TextFileAccess class as a singleton object
     */
    
    static TextFileAccess dataAccessor;
    private String dbLocation;
   
    
    /**
     * Static method to instantiate the <code>TextFileAccess</code> class. The
     * method checks to see if the variable instance is null, which it is the
     * very first time this method is invoked. If it is null a new <code>TextFileAccess</code>
     * object is created and assigned to the instance reference. If it is not
     * null, it will not create a new one ,and return the existing one
     * previously created.
     *
     */
    static TextFileAccess getInstance(String dbPath) {
        if (dataAccessor == null) {
            dataAccessor = new TextFileAccess(dbPath);
        }
        return dataAccessor;
    }
    /**
     * Private Constructor
     * @param dbPath 
     */
    private TextFileAccess(String dbPath) {
        dbLocation = dbPath;
        // System.out.println(a());
        // saveFile();
    }

    /**
     * Method that returns the collection of companies retrieved from the database file. 
     * @return 
     */
    Collection<Company> getCompanies() {
        return this.getData();
    }

    /**
     * Private Method that instantiate Company object with the values retrieved from the database file, and 
     * the Company objects are added to HashSet.
     * 
     * @return 
     */
    private Collection<Company> getData()  {
        Collection<Company> theData = new HashSet<Company>();

        String dataPath = dbLocation + File.separator + "TradeDatabase.db";
       
        try {
            ip = new BufferedReader(new FileReader(dataPath));
            String line = "";
            while ((line = ip.readLine()) != null) {

                StringTokenizer st = new StringTokenizer(line, ":");
                //  while(st.hasMoreTokens()){
                String name = st.nextToken();
                String loca = st.nextToken();
                String serv = st.nextToken();
                String numOfEmp = st.nextToken();
                String dailyRate = st.nextToken();
                String generalDescr = st.nextToken();
                Company company = new Company (name, loca, serv, new BigDecimal(numOfEmp),new BigDecimal(dailyRate),generalDescr);
                theData.add(company);
                //Administrator admin = Administrator.getInstance();
            }
            
        } catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage());
        } catch (IOException ioe) {
           System.out.println(ioe.getMessage());
        }  




        return theData;





        /**
         * try {
         *
         * scanner = new Scanner(new File(dataPath));
         *
         * }catch (FileNotFoundException fnf){
         * System.out.println(fnf.getMessage()); System.exit(0); }
         *
         * scanner.useDelimiter("\n");
         *
         * while(scanner.hasNext()){ String line = scanner.next().trim();
         * String[] bits = line.split(":");
         *
         * String name = bits[0]; String loca = bits[1]; String serv = bits[2];
         * BigDecimal numOfEmp =new BigDecimal(bits[3]); BigDecimal dailyRate =
         * new BigDecimal(bits[4]); String generalDescr= bits[5]; a= bits[4];
         * Company company = new Company (name, loca, serv, numOfEmp, dailyRate,
         * generalDescr); // Company company = new Company(name, loca, serv,
         * numOfEmp, dailyRate, generalDescr); theData.add(company);
         *
         * System.out.println(bits[0]); System.out.println(bits[1]);
         * System.out.println(bits[2]); System.out.println(bits[3]);
         * System.out.println(bits[4]);*
         */
    }
    
    
    /**
     * Method that saves the list of the objects retained , into the database file.
     * The method is overridden every time there is a change to database file, so the database file is updated.
     * 
     */
    public void saveData() {
        Administrator admin = Administrator.getInstance();

        String dataPath1 = dbLocation + File.separator + "TradeDatabase.db";
        Collection<Company> companies = admin.getCompanies();
        try {
            //FileWriter writer = new FileWriter(dataPath1);
            BufferedWriter ib = new BufferedWriter(new FileWriter(dataPath1));

            for (Company company : companies) {
                //  ib.write(company.toString());
                ib.write(company.getName() + ":" + company.getLocation() + ":" + company.getServices() + ":" + company.getNumberOfEmployees()
                        + ":" + company.getDailyRate() + ":" + company.getGeneralDescription());
                //ib.write("\n");
                ib.newLine();

                //    System.out.println(company.toString());

                // System.out.println("a");
            }

            ib.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        //return companies;
    }
}
/*
 public String a(){
 return   a;
 }
 */
