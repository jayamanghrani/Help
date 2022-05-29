/**
 * 
 */
package ticketTraceWebApp.config;

/**
 * @author 738566
 *
 */
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet
{
  static Logger logger = Logger.getLogger(Log4jInit.class.getName());

  public void init() {
  //  String prefix = getServletContext().getRealPath("/wls/middleware/oracle_home/user_projects/domains/prd_ccm_dom/servers/CcmAdmin/upload/");

 //   PropertyConfigurator.configure(prefix + "log4j.properties");
    PropertyConfigurator.configure("/wls/middleware/oracle_home/user_projects/domains/prd_ccm_dom/servers/CcmAdmin/upload/log4j.properties");
    logger.info("*******************LOG4J initialized**********************");
  }
}