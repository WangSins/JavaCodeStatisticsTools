package priv.wsins.statistics;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Font;

@SuppressWarnings("serial")
public class FileTraverseJavaGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private int num;
	private String filePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FileTraverseJavaGUI frame = new FileTraverseJavaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FileTraverseJavaGUI() {
		setTitle("JAVA代码量统计工具");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 142);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		textField = new JTextField();
		contentPane.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("目录路径: ");
		contentPane.add(lblNewLabel, BorderLayout.WEST);

		JButton btnNewButton_1 = new JButton("打开");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jfc = new JFileChooser("C:\\");
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jfc.setDialogTitle("选择目录");
				int result = jfc.showOpenDialog(contentPane);
				if (result == JFileChooser.APPROVE_OPTION) {
					filePath = jfc.getSelectedFile().getAbsolutePath();
					textField.setText(filePath);
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnNewButton_1, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("统计");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				String path = textField.getText();
				File file = new File(path);
				num = 0;
				try {
					nums(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(contentPane, "总行数: " + num + "行");

			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
	}

	/**
	 * LineStatistics
	 * 
	 * @throws IOException
	 */
	private void nums(File file) throws IOException {

		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				nums(f);
			}
		} else {
			if (file.getName().endsWith(".java")) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				while (br.readLine() != null) {
					num++;
				}
				br.close();
			}

		}
	}

}
