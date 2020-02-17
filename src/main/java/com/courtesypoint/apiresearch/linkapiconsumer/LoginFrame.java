package com.courtesypoint.apiresearch.linkapiconsumer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kong.unirest.UnirestException;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private String accessToken;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLinkApiConsumer = new JLabel("Link API Consumer");
		lblLinkApiConsumer.setBounds(236, 49, 173, 22);
		lblLinkApiConsumer.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblLinkApiConsumer);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(162, 118, 77, 20);
		contentPane.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setText("Sample");
		usernameField.setBounds(248, 115, 246, 26);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(166, 152, 73, 20);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setText("1234");
		passwordField.setBounds(248, 149, 246, 26);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login(usernameField.getText(), new String(passwordField.getPassword()));
			}
		});
		btnLogin.setBounds(248, 219, 246, 29);
		contentPane.add(btnLogin);
	}

	private void login(String username, String password) {
		try {
			accessToken = LinkApiConsumer.getLinkApiAccessToken(username, password);
			if (accessToken != null) {
				MainFrame mainFrame = new MainFrame(accessToken);
				mainFrame.setVisible(true);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Bad Credentials!");
			}
		} catch (UnirestException e) {
			JOptionPane.showMessageDialog(null, "Connection refused: connect!");
		}

	}
}
