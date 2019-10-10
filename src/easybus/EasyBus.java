
package easybus;

import javax.swing.UIManager;


public class EasyBus {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
           catch(Exception e)
                   {
                       System.out.println("Error");
                   }
       // new loginForm().setVisible(true);
       //new loginForm().setVisible(true);
        //new DataBaseHandler().getTimings("begin");
        //new loginTest().setVisible(true);
        //System.out.println(new AdminTest().getMaximumNbOfBuses());
       // new employeeTest(new DataBaseHandler().getEmployee("ahmad")).setVisible(true);
      new loginTest().setVisible(true);

    }
    
}
