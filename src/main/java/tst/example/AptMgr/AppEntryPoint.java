package tst.example.AptMgr;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.formdev.flatlaf.IntelliJTheme;



public class AppEntryPoint 
{
	
	
	private static final Logger logger = LoggerFactory.getLogger(AppEntryPoint.class);
	
    public static void main( String[] args )
    {
    	IntelliJTheme.setup( AppEntryPoint.class.getResourceAsStream("/nord.theme.json" ) );
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					logger.error("cannot draw the Main frame : "+e);
				}
			}
		});
    }
}
