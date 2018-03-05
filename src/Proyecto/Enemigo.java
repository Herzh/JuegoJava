/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class Enemigo extends JLabel {

	int x, y;
	private Timer timer = null;
	private int contador;
	private boolean izq, der;

	public Enemigo(int x, int y) {

		this.x = x;
		this.y = y;
		contador = 0;

		izq = false;
		der = true;

		this.setText(null);
		this.setIcon(new ImageIcon(getClass().getResource("Enemigo.png")));
		this.setLocation(x, y);
		this.setSize(150, 180);
		this.setVisible(true);

		timer = new Timer(300, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mover();
			}
		});
		timer.start();
	}

	public void mover() {
		
		// Movimiento Der
		if (contador < 5 && der) {
			this.x += 10;
			this.setLocation(x, y);
			contador += 1;
			
		// Movimiento Izq
		} else if (contador < 5 && izq) {
			this.x -= 10;
			this.setLocation(x, y);
			contador++;
			
		// Cambio de direcion y avance
		} else {
			boolean aux = der;
			der = izq;
			izq = aux;
			contador = 0;
			y += 10;
			this.setLocation(x, y);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 100, 120);//tamaño de la imagen
	}

}
