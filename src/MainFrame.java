import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;


public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.setSize(460,330);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 424, 250);
		
		getContentPane().add(tabbedPane);
		
		JPanel loginPanel = new JPanel();
		tabbedPane.addTab("Login", null, loginPanel, null);
		loginPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Accounts:");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(101, 61, 100, 30);
		loginPanel.add(lblNewLabel);
		
		 Functions fn = new Functions();
	     ArrayList<User> UserList = fn.readUserFile();
	     String UserNameList[] = new String[UserList.size()];
	     for (int i = 0; i < UserList.size(); i++){
	    	 UserNameList[i] = UserList.get(i).getUsername();
	     }
		JComboBox ListOfUsers = new JComboBox(UserNameList);
		ListOfUsers.setBounds(211, 61, 100, 30);
		loginPanel.add(ListOfUsers);
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String UserAcc = (String)ListOfUsers.getSelectedItem();
				String UserRole;
				for (User user : UserList) {
					if(UserAcc.equals(user.getUsername())) {
						if(user.getRole().equals("admin")) {
							AdminFrame amf = new AdminFrame(user);
							amf.setVisible(true);
						}else if(user.getRole().equals("customer")) {
							CustomerFrame cf = null;
							try {
								cf = new CustomerFrame(user);
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
							cf.setVisible(true);
						}
					}
				}
			}
		});
		btnSubmit.setBounds(169, 105, 89, 23);
		loginPanel.add(btnSubmit);	
		
	}
}
