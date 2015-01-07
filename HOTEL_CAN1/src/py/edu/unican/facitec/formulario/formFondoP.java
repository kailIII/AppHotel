package py.edu.unican.facitec.formulario;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class formFondoP extends JPanel {
	 URL url = getClass().getResource("/py/edu/unican/facitec/imagen/Hoteleria.jpg");
	 Image image = new ImageIcon(url).getImage();
	
	
	public formFondoP() {
	}

	
	@Override
	public void paint(Graphics g ) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paint(g);
	}

}
