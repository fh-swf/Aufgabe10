package de.fhswf.verwaltung;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * Klasse JFrameShower, zeigt ein Fenster an.
 */
public class JFrameShower implements Runnable
{
   /** Das Hauptfenster der Anwendung. */
   private JFrame frame;

   /**
    * Standardkonstruktor des FrameShowers.
    * 
    * @param frame
    *           Der Frame, welcher angezeigt werden soll.
    */
   public JFrameShower(JFrame windowframe)
   {
      frame = windowframe;
      EventQueue.invokeLater(this);
   }

   /**
    * Run-Funktion, wird beim Start aufgerufen.
    */
   public void run()
   {
      // Setzt das Fenster auf preferred Size...
      // frame.pack();
      frame.setVisible(true);
   }
}
