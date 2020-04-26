package dataExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;


/**
 * Class for retrieving data from the XML file holding the address list.
 */
public class JDBCAddressBookDataSource implements AddressBookDataSource {

   public static final String CREATE_TABLE =
           "CREATE TABLE IF NOT EXISTS address ("
                   + "idx INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE," // from https://stackoverflow.com/a/41028314
                   + "name VARCHAR(30),"
                   + "street VARCHAR(30),"
                   + "suburb VARCHAR(20),"
                   + "phone VARCHAR(10),"
                   + "email VARCHAR(30)" + ");";

   private static final String INSERT_PERSON = "INSERT INTO address (name, street, suburb, phone, email) VALUES (?, ?, ?, ?, ?);";

   private static final String GET_NAMES = "SELECT name FROM address";

   private static final String GET_PERSON = "SELECT * FROM address WHERE name=?";

   private static final String DELETE_PERSON = "DELETE FROM address WHERE name=?";

   private static final String COUNT_ROWS = "SELECT COUNT(*) FROM address";

   private Connection connection;

   private PreparedStatement addPerson;

   private PreparedStatement getNameList;

   private PreparedStatement getPerson;

   private PreparedStatement deletePerson;

   private PreparedStatement rowCount;

   public JDBCAddressBookDataSource() {
      connection = DBConnection.getInstance();
      try {
         Statement st = connection.createStatement();
         st.execute(CREATE_TABLE);
         /* BEGIN MISSING CODE */
         addPerson = connection.prepareStatement(INSERT_PERSON);
         getNameList = XXXXXX.XXXXXX(XXXXXX);
         getPerson = XXXXXX.XXXXXX(XXXXXX);
         deletePerson = XXXXXX.XXXXXX(XXXXXX);
         rowCount = XXXXXX.XXXXXX(XXXXXX);
         /* END MISSING CODE */
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
   }

   /**
    * @see AddressBookDataSource#addPerson(Person)
    */
   public void addPerson(Person p) {
      try {
         /* BEGIN MISSING CODE */
         addPerson.setString(1, p.getName());
         addPerson.XXXXXX(XXXXXX,XXXXXX);
         addPerson.XXXXXX(XXXXXX,XXXXXX);
         addPerson.XXXXXX(XXXXXX,XXXXXX);
         addPerson.XXXXXX(XXXXXX,XXXXXX);
         addPerson.XXXXXX();
         /* END MISSING CODE */
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
   }

   /**
    * @see dataExercisenew.AddressBookDataSource#nameSet()
    */
   public Set<String> nameSet() {
      Set<String> names = new TreeSet<String>();
      ResultSet rs = null;

      /* BEGIN MISSING CODE */
      try {
         rs = XXXXXX.XXXXXX();
         while (rs.next()) {
            XXXXXX.XXXXXX(XX.XXXXXX(XXXXXX));
         }
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      /* END MISSING CODE */

      return names;
   }

   /**
    * @see AddressBookDataSource#getPerson(String)
    */
   public Person getPerson(String name) {
      Person p = new Person();
      ResultSet rs = null;
      /* BEGIN MISSING CODE */
      try {
         getPerson.XXXXXX(XXXXXX, XXXXXX);
         rs = XXXXXX.XXXXXX();
         rs.XXXXXX();
         p.setName(rs.getString("name"));
         p.XXXXXX(XXXXXX.XXXXXX("XXXXXX"));
         p.XXXXXX(XXXXXX.XXXXXX("XXXXXX"));
         p.XXXXXX(XXXXXX.XXXXXX("XXXXXX"));
         p.XXXXXX(XXXXXX.XXXXXX("XXXXXX"));
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      /* END MISSING CODE */
      return p;
   }

   /**
    * @see AddressBookDataSource#getSize()
    */
   public int getSize() {
      ResultSet rs = null;
      int rows = 0;

      /* BEGIN MISSING CODE */
      try {
         rs = XXXXXX();
         rs.XXXXXX();
         rows = rs.XXXXXX(XXXXXX);
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      /* END MISSING CODE */

      return rows;
   }

   /**
    * @see AddressBookDataSource#deletePerson(String)
    */
   public void deletePerson(String name) {
      /* BEGIN MISSING CODE */
      try {
         deletePerson.XXXXXX(XXXXXX, XXXXXX);
         deletePerson.XXXXXX();
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      /* END MISSING CODE */
   }

   /**
    * @see AddressBookDataSource#close()
    */
   public void close() {
      /* BEGIN MISSING CODE */
      try {
         XXXXXX.XXXXXX();
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      /* END MISSING CODE */
   }
}
