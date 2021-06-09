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


class selectMovie extends JFrame implements ActionListener {
	JLabel m_label, movie1, movie2, movie3;
	JPanel north, center, south, p1, p2, p3, p4;
	JButton bt_select01, bt_select02, bt_select03;
	ImageIcon image1, image2, image3;

	selectMovie() {
		setTitle("영화 선택");

		m_label = new JLabel("영화 선택");
		Font f1 = new Font("serif", Font.BOLD, 30);
		Font f2 = new Font("", Font.BOLD, 25);
		m_label.setFont(f1);
		m_label.setForeground(Color.ORANGE);

		north = new JPanel();
		center = new JPanel();
		south = new JPanel();
		north.setPreferredSize(new Dimension(900, 50));
		center.setPreferredSize(new Dimension(900, 410));
		south.setPreferredSize(new Dimension(900, 80));
		north.setLayout(new GridLayout(1, 1));
		center.setLayout(new GridLayout(1, 3));
		south.setLayout(new GridLayout(1, 3));

		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();

		image1 = new ImageIcon("images/movie01.PNG");
		image2 = new ImageIcon("images/movie02.PNG");
		image3 = new ImageIcon("images/movie03.PNG");

		movie1 = new JLabel("", image1, JLabel.CENTER);
		movie2 = new JLabel("", image2, JLabel.CENTER);
		movie3 = new JLabel("", image3, JLabel.CENTER);

		bt_select01 = new JButton("예매 하기");
		bt_select02 = new JButton("예매 하기");
		bt_select03 = new JButton("예매 하기");

		bt_select01.setBackground(Color.ORANGE);
		bt_select02.setBackground(Color.ORANGE);
		bt_select03.setBackground(Color.ORANGE);

		bt_select01.setFont(f2);
		bt_select02.setFont(f2);
		bt_select03.setFont(f2);

		p1.setBackground(Color.BLACK);
		p1.add(m_label);
		p2.add(movie1);
		p3.add(movie2);
		p4.add(movie3);

		north.add(p1);
		center.add(p2);
		center.add(p3);
		center.add(p4);
		south.add(bt_select01);
		south.add(bt_select02);
		south.add(bt_select03);

		bt_select01.addActionListener(this);
		bt_select02.addActionListener(this);
		bt_select03.addActionListener(this);

		pack();

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// 예매하기 버튼을 눌렀을 때
		if (obj.equals(bt_select01)) {
			ticketing t = new ticketing();
			t.txt_movie_n = "도굴";
			dispose();
		}
		// 예매하기 버튼을 눌렀을 때
		if (obj.equals(bt_select02)) {
			ticketing t = new ticketing();
			t.txt_movie_n = "이웃 사촌";
			dispose();
		}
		// 예매하기 버튼을 눌렀을 때
		if (obj.equals(bt_select03)) {
			ticketing t = new ticketing();
			t.txt_movie_n = "그랜 랜드";
			dispose();
		}
	}
}