import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
 

import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;


public class Haupt extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuItem start, list;
	JButton button1, button2;
	JTextArea TF1;
	JFrame jf2;
    public static void main(String[] args)
    {
    	Haupt starten= new Haupt();
    	starten.run();
    	
    }
    public void run()
    {
    	Provider provider = Provider.getCurrentProvider(true);
    	HotKeyListener listener =
        	    hotKey -> add();
    	
    	provider.register(KeyStroke.getKeyStroke("control shift PLUS"), listener);
		start = new JMenuItem("start");
		list = new JMenuItem("list");
		start.addActionListener(this);
		list.addActionListener(this);
		JFrame jf = new JFrame();
		JMenuBar jmb = new JMenuBar();
		JMenu jm = new JMenu("Menü");
		jm.add(start);
		jm.add(list);
		jmb.add(jm);
		jf.setJMenuBar(jmb);
		jf.setLayout(null);
		jf.setSize(200,200);
		
		jf.setVisible(true);
    }
    
	public void add()
	{
		jf2 = new JFrame("Eingabe");
		TF1 = new JTextArea();
		button1 = new JButton("OK");
		button1.addActionListener(this);
		jf2.setLayout(new GridLayout());
		jf2.add(TF1);
		jf2.add(button1);
		jf2.setSize(200, 200);
		jf2.setVisible(true);

	}
	public void write(String content)
	{
		LocalDateTime now = LocalDateTime.now();
    	DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm");
    	String s = now.format(df) + " "+content+"\n";
    	Path p = Path.of("save.txt");
    	try {
    		@SuppressWarnings("unused")
			Path filePath = Files.writeString(p, s, StandardOpenOption.APPEND);
    	} catch (IOException e)
    	{}
	}
	public void list()
	{
		try {
			Runtime.getRuntime().exec("notepad.exe " + "save.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.start)
		{
			System.out.println("Läuft");
			add();
		}
		if(e.getSource() == this.list)
		{
			System.out.println("Läuft");
			list();
		}
		if(e.getSource() == this.button1)
		{
			write(TF1.getText());
			jf2.setVisible(false);

		}
		
	}
}
