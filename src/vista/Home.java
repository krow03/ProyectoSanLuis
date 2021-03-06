package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import org.apache.commons.codec.digest.DigestUtils;

import gestores.GestorProveedores;
import gestores.GestorUsuarios;

import java.awt.event.KeyAdapter;

public class Home extends JFrame {

	private static GestorUsuarios gu = GestorUsuarios.getInstance();
	private static GestorProveedores gp = GestorProveedores.getInstance();
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;

	int xx, xy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// going to borrow code from a gist to move frame.

	/**
	 * Create the frame.
	 */
	public Home() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 476);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64,64,64));
		panel.setBounds(0, 0, 346, 490);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Bienvenido");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setBounds(129, 305, 113, 27);
		panel.add(lblNewLabel);

		JLabel movimiento = new JLabel("");

		movimiento.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xx = e.getX();
				xy = e.getY();
			}
		});
		movimiento.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				Home.this.setLocation(x - xx, y - xy);
			}
		});
		movimiento.setBounds(0, 0, 346, 275);
		movimiento.setVerticalAlignment(SwingConstants.TOP);
		movimiento.setIcon(new ImageIcon(Home.class.getResource("/images/Escudos.jpg")));
		panel.add(movimiento);

		JLabel lblWeGotYou = new JLabel("Centro Sanluis");
		lblWeGotYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeGotYou.setForeground(new Color(240, 248, 255));
		lblWeGotYou.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblWeGotYou.setBounds(111, 343, 141, 27);
		panel.add(lblWeGotYou);

		Button btnSign = new Button("SignUp");
		btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});

		btnSign.setForeground(Color.WHITE);
		btnSign.setBackground(new Color(241, 57, 83));
		btnSign.setBounds(395, 257, 283, 36);
		contentPane.add(btnSign);

		txtUser = new JTextField();
		txtUser.setBounds(395, 119, 283, 36);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(395, 92, 114, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(395, 168, 96, 14);
		contentPane.add(lblPassword);

		txtPass = new JPasswordField();
		txtPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		txtPass.setBounds(395, 195, 283, 36);
		contentPane.add(txtPass);

		JLabel lbl_close = new JLabel("X");
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				System.exit(0);
			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setBounds(693, 0, 37, 27);
		contentPane.add(lbl_close);
	}

	public void login() {
		System.out.println(txtPass.getText());
		if (gu.login(txtUser.getText(), txtPass.getText()) || gp.login(txtUser.getText(), txtPass.getText())) {
			try {
				
				//System.out.println(gu.getUserOnline().getApellidos());
				if (gu.getUserOnline() != null) {
					this.dispose();
					Main main = new Main();
					main.setUndecorated(true);
					main.setVisible(true);
				}
				if (gp.getProvOnline() != null) {
					this.dispose();
					ProveedoresPantalla pp = new ProveedoresPantalla();
					pp.setUndecorated(true);
					pp.setVisible(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "User or password error", "Error login", JOptionPane.ERROR_MESSAGE);
		}
	}

}
