import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Calculatrice extends JFrame{
	Container content;
	JButton buttons[] = new JButton[12];
	JButton operations[] = new JButton[5];
	JLabel ecran;
	double val1 = 0;
	double val2 = 0;
	String opt = "";
	ImageIcon icon = new ImageIcon("icon.png");

	public Calculatrice()
	{
		// ---- Quelques propriétés sur JFrame ----
		this.setTitle("Calculatrice");
		this.setIconImage(icon.getImage());

		// ---- Resisement de JFrame ----
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);

		// ---- Initialisation du Container ----
		content = this.getContentPane();
		content.setLayout(new BorderLayout(5, 5));

		// ---- Initialisation des tables des boutons ----
		for (int i = 0; i < 12; i++)
			buttons[i] = new JButton();
		for (int i = 0; i < 5; i++)
			operations[i] = new JButton();

		// ---- Ajout de la zone de Text ----
		JPanel txt = new JPanel();
		ecran = new JLabel();
		ecran.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,30));
		txt.setPreferredSize(new Dimension(300, 50));
		txt.add(ecran);

		// ---- Création de la grille des numéros ----
		JPanel numeros = new JPanel();
		numeros.setLayout(new GridLayout(4, 3, 5, 5));
		numeros.setPreferredSize(new Dimension(200, 200));

		// ---- Ajout des boutons à la grille ----
		for (int i = 9; i >= 0 ; i--) 
		{
			buttons[i].setText(Integer.toString(i));
			buttons[i].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,30));
			// ____ ActionListener pour chaque bouton ____
			buttons[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					String txt = evt.getActionCommand();
					ecran.setText(ecran.getText() + txt);
				}
			});
			numeros.add(buttons[i]);
		}
		// ____ Boutons de la virgule et égal ____
		buttons[10].setText(".");
		buttons[11].setText("=");
		buttons[11].setBackground(new Color(187, 172, 172));

		numeros.add(buttons[10]);
		numeros.add(buttons[11]);

		// ---- Création de la grille des opérations ----
		JPanel op = new JPanel();
		op.setLayout(new GridLayout(4, 1, 5, 5));
		op.setPreferredSize(new Dimension(60, 200));

		// ---- Ajout des boutons à la grille ----
		// _____ Soustraction - _____
		operations[0].setText("-");
		op.add(operations[0]);
		// _____ Division / _____
		operations[1].setText("/");
		op.add(operations[1]);
		// _____ Multiplication x _____
		operations[2].setText("x");
		op.add(operations[2]);
		// _____ Addition + _____
		operations[3].setText("+");
		op.add(operations[3]);

		// ---- Police des boutons ----
		for (int i = 0; i < 11; i++)
		{
			buttons[i].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,30));
			buttons[i].setBackground(new Color(250, 240, 230));
		}
		for (int i = 0; i < 4; i++)
		{
			operations[i].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,30));
			operations[i].setBackground(new Color(240, 255, 255));
		}

		// ---- Ajout du bouton C (Pour effacer l'ecran) ----
		operations[4].setText("C");
		operations[4].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
		operations[4].setBackground(new Color(233, 201, 177));

		// ---- ActionListener pour le bouton Clean ----
		operations[4].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
				{
					ecran.setText("");
				}
		});

		// ---- ActionListener pour le bouton "." (Virgule)----
		buttons[10].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
				{
					ecran.setText(ecran.getText() + ".");
				}
		});

		// ---- Ajout des éléments au conteneur ----
		content.add(txt, BorderLayout.NORTH); // Zone de texte
		content.add(operations[4], BorderLayout.SOUTH); // Grille du bouton Clean
		content.add(op, BorderLayout.EAST); // Grille des opérations
		content.add(numeros, BorderLayout.CENTER); // Grille des numeros
		
		// ---- ActionListener pour les boutons des operations ---- 	
		// ____ Soustraction ____
		operations[0].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
				{
					val1 = Double.parseDouble(ecran.getText());
					opt = "-";
					ecran.setText("");
				}
		});
		// ____ Division ____
		operations[1].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
				{
					val1 = Double.parseDouble(ecran.getText());
					opt = "/";
					ecran.setText("");
				}
		});
		// ____ Multiplication ____
		operations[2].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
				{
					val1 = Double.parseDouble(ecran.getText());
					opt = "x";
					ecran.setText("");
				}
		});
		// ____ Addition ____
		operations[3].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
				{
					val1 = Double.parseDouble(ecran.getText());
					opt = "+";
					ecran.setText("");
				}
		});
		// ____ Le résultat "=" ____
		buttons[11].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
				{
					val2 = Double.parseDouble(ecran.getText());
					switch (opt) 
					{
						case "-" :
							ecran.setText(Double.toString((val1 - val2)));
							break;
						case "/" :
							if (val2 == 0)
							{
								// ecran.setForeground(Color.red);
								ecran.setText("ERROR !");
								// ecran.setForeground(Color.black);
							}
							else
								ecran.setText(Double.toString((val1 / val2)));
							break;
						case "x" :
							ecran.setText(Double.toString((val1 * val2)));
							break;
						case "+" :
							ecran.setText(Double.toString((val1 + val2)));
							break;
					}
				}
		});

		// ---- Fonction de fermeture ----
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


// ************************************ Main ************************************
	
}