import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import com.sun.tools.javac.Main;

//���� ������
public class javaCjh extends JFrame implements ActionListener {

	JLabel m_label;
	JPanel north, center, p1;
	JButton bt_select, bt_confirm;

	public javaCjh() {
		super("��ȭ ���� �ý���");
		m_label = new JLabel("��ȭ ���� �ý���");
		Font f1 = new Font("serif", Font.BOLD, 30);
		Font f2 = new Font("", Font.BOLD, 20);
		m_label.setFont(f1);
		m_label.setForeground(Color.ORANGE);

		north = new JPanel();
		center = new JPanel();
		north.setPreferredSize(new Dimension(400, 50));
		center.setPreferredSize(new Dimension(400, 200));
		north.setLayout(new GridLayout(1, 1));
		center.setLayout(new GridLayout(1, 2));

		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);

		p1 = new JPanel();
		bt_select = new JButton("��ȭ ����");
		bt_confirm = new JButton("���� Ȯ��");
		bt_select.setFont(f2);
		bt_confirm.setFont(f2);
		bt_select.setBackground(Color.ORANGE);
		bt_confirm.setBackground(Color.ORANGE);

		p1.setBackground(Color.BLACK);
		p1.add(m_label);
		north.add(p1);
		center.add(bt_select);
		center.add(bt_confirm);

		bt_select.addActionListener(this);
		bt_confirm.addActionListener(this);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// ��ȭ���� ��ư�� ������ ��
		if (obj.equals(bt_select)) {
			selectMovie m = new selectMovie();
			dispose();
		}
		// ����Ȯ�� ��ư�� ������ ��
		if (obj.equals(bt_confirm)) {
			ticketconfirm t = new ticketconfirm();
			dispose();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javaCjh c = new javaCjh();
	}

}






