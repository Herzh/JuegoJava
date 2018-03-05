/*
 * To change this license header, choose License Headers in Project Properties.
 */
package Proyecto;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * @author Herzh
 */
public class Bala extends JLabel {

	private pantalla pantalla1;
	int x, y;
	private Timer timer = null;

	
	public Bala(pantalla g, int x) {
		pantalla1 = g;
		this.x = x + 20;
		y = 500;
		this.setText(null);
		this.setIcon(new ImageIcon(getClass().getResource("bala.png")));
		this.setLocation(x, 600);
		this.setSize(20, 60);
		this.setVisible(true);
		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mover();
			}
		});
		timer.start();
	}

	public void mover() {
		y -= 40;
		this.setLocation(x, y);

		if (y != -60) {
			y -= 10;
			this.setLocation(x, y);
			int hayColision = colision();
			if (hayColision >= 0) {
				timer.stop();
				pantalla1.desaparecerEnemigo(hayColision, this);
			}
		} else {
			timer.stop();
			pantalla1.desaparecerBala(this);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 20, 60);
	}

	private int colision() {
		int collision = -1;
		for (int i = 0; i < pantalla1.enemigos.size(); i++) {
			Enemigo m = (Enemigo) pantalla1.enemigos.get(i);
			if (getBounds().intersects(m.getBounds())) {
				collision = i;
			}
		}
		return collision;
	}
}
