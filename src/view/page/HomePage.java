package view.page;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import global.Global;
import model.user.User;
import view.Window;
import view.page.route.Route;
import view.widget.LoginButton;
import view.widget.ProfileWidget;

public class HomePage extends Page implements ActionListener {
	@Serial
	private static final long serialVersionUID = 1L;

	@Override
	protected String getTitle() { return "홈"; }
	
	public HomePage() {
		super(new ProfileWidget(), new LoginButton(), false);
	}

	@Override
	protected void buildContent() {
		final int btnW = 200;
		final int btnH = 100;
		
		final int btnPanelW = Window.WIDTH / 2;
		final int btnPanelH = 100;
		final int btnPanelLTx = Window.WIDTH / 4;
		final int btnPanelLTy = Window.HEIGHT / 2 - btnPanelH;
		
		JPanel buttonPanel = new JPanel(new BorderLayout());
		
		JButton searchButton = new JButton("항공편조회");
		searchButton.setPreferredSize(new Dimension(btnW, btnH));
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchButton.addActionListener(new Route());
		
		JButton myButton = new JButton("마이페이지");
		myButton.setPreferredSize(new Dimension(btnW, btnH));
		myButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		myButton.addActionListener(User.isLogged() ? new Route() : this);
		
		buttonPanel.add(searchButton, BorderLayout.WEST);
		buttonPanel.add(myButton, BorderLayout.EAST);
		buttonPanel.setBounds(btnPanelLTx, btnPanelLTy, btnPanelW, btnPanelH);
		buttonPanel.setOpaque(false);

		JLabel backgroundLabel = new JLabel(Global.background);
		backgroundLabel.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(buttonPanel);
		panel.add(backgroundLabel);
		
		add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		String msg = null;

		if (c.equals("마이페이지")) msg = "먼저 로그인 해주세요!";

		if (msg == null) return;
		JOptionPane.showMessageDialog(this, msg);
	}
}