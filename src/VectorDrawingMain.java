import view.VectorDrawingApp;
import javax.swing.*;

/**
 * The main entry point for the application, which launches the VectorDrawingApp GUI drawing software.
 */
public class VectorDrawingMain{
    /**
     * The main method of the application.
     */
    public static void main(String[] args) {
        try {
            // Set the look and feel to Nimbus if available
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Use Swing's Event Dispatch Thread to ensure GUI runs on the main thread
        SwingUtilities.invokeLater(() -> {
            // Create an instance of VectorDrawingApp and initialize it
            VectorDrawingApp jFrame = new VectorDrawingApp();
            jFrame.init();
            jFrame.setVisible(true);
        });
    }
}
