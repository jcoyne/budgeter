/*
 * SwimProgramApp.java
 */

package budgeter;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class BudgeterApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new BudgeterView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of SwimProgramApp
     */
    public static BudgeterApp getApplication() {
        return Application.getInstance(BudgeterApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
//        Student.createStudent("Justin", "Coyne");
//        Student.createStudent("Bob", "Dole");
//        Student.createStudent("Darren", "Coyne");
//        Account.createAccount("Food");
//        Account.createAccount("Clothing");
//        Account.createAccount("Housing");
//        Purchase.createPurchase("House Payment", new Date(), 1200.3);

        launch(BudgeterApp.class, args);
    }


}
