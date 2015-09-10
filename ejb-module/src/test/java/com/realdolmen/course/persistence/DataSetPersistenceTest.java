package com.realdolmen.course.persistence;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loads a DBUnit test set before every unit test.
 */
public abstract class DataSetPersistenceTest extends PersistenceTest {
    private static final Logger logger = LoggerFactory.getLogger(DataSetPersistenceTest.class);

    @Before
    public void loadDataSet() throws Exception {
        logger.info("Loading dataset");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass().getResource("/data.xml"));
        /*
        * <passenger id="1000" firstName="Sam" lastName="Smets" dateOfBirth = "1993-12-31" ssn="1111" frequentFlyerMiles="9999" lastFlight="2015-09-09 16:15:15" passengerType="REGULAR"/>
          <passenger id="2000" firstName="Jos" lastName="Soj" dateOfBirth = "1982-02-01" ssn="2222" frequentFlyerMiles="8888" lastFlight="2015-09-09 16:15:15" passengerType="OCCASIONAL"/>
          <passenger id="3000" firstName="Rik" lastName="Kir" dateOfBirth = "1985-04-01" ssn="3333" frequentFlyerMiles="7777" lastFlight="2015-09-09 16:15:15" passengerType="OCCASIONAL"/>
        * */
        IDatabaseConnection connection = new DatabaseConnection(newConnection());
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory()); // Set factorytype in dbconfig to remove warning
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        connection.close();
    }
}
