package com.courtesypoint.apiresearch.linkapiconsumer;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.mashape.unirest.http.exceptions.UnirestException;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final String accessToken;
	private JLabel lblConnected;
	private JButton btnGoogle;
	private JButton btnRefresh;
	
	/**
	 * Create the frame.
	 */
	public MainFrame(final String accessToken) {
		this.accessToken = accessToken;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1354, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(34, 29, 69, 20);
		contentPane.add(lblStatus);
		
		String googleAccessToken = LinkApiConsumer.getGoogleAccessToken(accessToken);
		
		String strGoogleConnectionStatus = "Not Connected!";
		Color statusColor = Color.RED;
		boolean enableBtnGoogle = true;
		boolean showBtnRefresh = true;

		if (googleAccessToken != null && !googleAccessToken.isEmpty()) {
			strGoogleConnectionStatus = "Connected!";
			statusColor = Color.GREEN;
			enableBtnGoogle = false;
			showBtnRefresh = false;
		}
		
		lblConnected = new JLabel(strGoogleConnectionStatus);
		lblConnected.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConnected.setForeground(statusColor);
		lblConnected.setBounds(94, 29, 152, 20);
		contentPane.add(lblConnected);
		
		btnGoogle = new JButton("Google");
		btnGoogle.setEnabled(enableBtnGoogle);
		
		btnGoogle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String authorizationUrl = LinkApiConsumer.getGoogleAuthorizationUrl(accessToken);
					Desktop d = Desktop.getDesktop();
		    	    d.browse(new URI(authorizationUrl));
		    	    
				} catch (UnirestException e1) {
					e1.printStackTrace();
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGoogle.setBounds(34, 54, 115, 29);
		contentPane.add(btnGoogle);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Google API's", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(34, 117, 441, 268);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Link API's", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(580, 117, 441, 268);
		contentPane.add(panel_1);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setVisible(showBtnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String googleAccessToken = LinkApiConsumer.getGoogleAccessToken(accessToken);
				
				String strGoogleConnectionStatus = "Not Connected!";
				Color statusColor = Color.RED;
				boolean enableBtnGoogle = true;

				if (googleAccessToken != null && !googleAccessToken.isEmpty()) {
					strGoogleConnectionStatus = "Connected!";
					statusColor = Color.GREEN;
					enableBtnGoogle = false;	
					btnRefresh.setVisible(false);
				}
				
				lblConnected.setText(strGoogleConnectionStatus);
				lblConnected.setForeground(statusColor);
				btnGoogle.setEnabled(enableBtnGoogle);
			}
		});
		btnRefresh.setBounds(167, 54, 115, 29);
		contentPane.add(btnRefresh);
	}
}
