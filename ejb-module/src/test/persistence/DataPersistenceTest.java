/**
 * Created by SDOAX36 on 5/10/2015.
 */


import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

/**
 * Loads a DBUnit test set before every unit test.
 */
public abstract class DataPersistenceTest extends PersistenceTest {
    //private static final Logger logger = LoggerFactory.getLogger(DataSetPersistenceTest.class);

    @Before
    public void loadDataSet() throws Exception {
        //logger.info("Loading dataset");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass().getResource("/data.xml"));

        IDatabaseConnection connection = new DatabaseConnection(newConnection());
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory()); // Set factorytype in dbconfig to remove warning
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        connection.close();
    }
}