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

class ticketing extends JFrame implements ActionListener, ItemListener {
	JLabel m_label, movie1, movie2, movie3, l_name, l_phone, l_phone1, l_phone2, m_label1, l_date, l_date1, l_date2,
			l_day, l_pers, l1, l2, l3, l4, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, l_time, l_seat, l_movie_name1,
			l_movie_name2, l_movie_name3;
	static JTextField txt_name, txt_phone1, txt_phone2, txt_phone3;
	JPanel north, center, south, west, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18,
			p19, p20, p21;
	JButton bt_select01, bt_select02, bt_select03, time01, time02, time03, time04, time05, time06, time07, time08,
			time09, time10, bt_re01, bt_re02;
	JButton[] s;
	ImageIcon image1, image2, image3;
	static JComboBox combo;
	CheckboxGroup mm;
	Checkbox m1, m2, m3, m4, m5, m6, m7, m8;
	int cnt1, cnt_m1, cnt_m2, cnt_m3, cnt_m4, cnt_m5, cnt_m6, cnt_m7, cnt_m8;
	boolean t;
	static String ss, txt_time, txt_people_n, txt_seat, txt_date, txt_movie_n;
	String[] txt_seat_arr;

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movieticket?serverTimezone=UTC";

	String userid = "root";
	String passwd = "1234";

	Connection con;// 접속 정보를 가진 객체
	PreparedStatement pstmt;
	ResultSet rs;
	String g;
	String sql;
	String category;

	int idx; // 마우스를 누를때마다 idx값을 넣어주자~!

	ticketing() {
		cnt1 = 0;
		cnt_m1 = 0;
		cnt_m2 = 0;
		cnt_m3 = 0;
		cnt_m4 = 0;
		cnt_m5 = 0;
		cnt_m6 = 0;
		cnt_m7 = 0;
		cnt_m8 = 0;
		t = false;
		txt_seat_arr = new String[35];
		txt_seat = "";
		txt_date = "2020  -  12  -";
		txt_movie_n = "";

		setTitle("예매 하기");

		m_label = new JLabel("예 매   하 기");
		Font f1 = new Font("serif", Font.BOLD, 20);
		Font f2 = new Font("", Font.BOLD, 20);
		Font f3 = new Font("휴먼엑스포", Font.BOLD, 25);
		Font f4 = new Font("", Font.BOLD, 17);
		Font f5 = new Font("", Font.BOLD, 13);
		Font f6 = new Font("", Font.BOLD, 10);
		m_label.setFont(f1);
		m_label.setForeground(Color.ORANGE);

		image1 = new ImageIcon("images/movie01.PNG");
		movie1 = new JLabel("", image1, JLabel.CENTER);

		north = new JPanel();
		center = new JPanel();
		center.setBackground(new Color(255, 220, 0));
		south = new JPanel();
		west = new JPanel();
		west.setBackground(new Color(255, 220, 0));
		north.setPreferredSize(new Dimension(700, 70));
		center.setPreferredSize(new Dimension(467, 450));
		south.setPreferredSize(new Dimension(700, 50));
		west.setPreferredSize(new Dimension(233, 400));
		north.setLayout(new GridLayout(2, 0));
		center.setLayout(new GridLayout(11, 3));
		south.setLayout(new GridLayout(1, 3));
		west.setLayout(new GridLayout(7, 1));

		l_name = new JLabel("이    름 ");
		l_phone = new JLabel("     연  락  처 ");
		l_phone1 = new JLabel("-");
		l_phone2 = new JLabel("-");
		l_name.setFont(f2);
		l_phone.setFont(f2);
		txt_name = new JTextField(10);
		l_date = new JLabel("날짜 선택");
		l_date.setFont(f3);
		l_date1 = new JLabel("2020 / 12");
		l_date1.setFont(f3);
		l_date2 = new JLabel("-");
		l_day = new JLabel("");
		combo = new JComboBox();
		for (int i = 1; i <= 31; i++) {
			combo.addItem("   " + Integer.toString(i));
		}
		combo.setMaximumRowCount(4);
		combo.setFont(f3);
		combo.setPreferredSize(new Dimension(100, 40));
		l1 = new JLabel();
		l2 = new JLabel();
		l_pers = new JLabel("인원 선택");
		l_pers.setFont(f3);

		l_time = new JLabel("시간 선택");
		l_time.setFont(f3);
		l3 = new JLabel();

		l_seat = new JLabel("좌석 선택");
		l_seat.setFont(f3);

		mm = new CheckboxGroup();
		m1 = new Checkbox("1명", mm, false);
		m2 = new Checkbox("2명", mm, false);
		m3 = new Checkbox("3명", mm, false);
		m4 = new Checkbox("4명", mm, false);
		m5 = new Checkbox("5명", mm, false);
		m6 = new Checkbox("6명", mm, false);
		m7 = new Checkbox("7명", mm, false);
		m8 = new Checkbox("8명", mm, false);

		m1.setFont(f4);
		m2.setFont(f4);
		m3.setFont(f4);
		m4.setFont(f4);
		m5.setFont(f4);
		m6.setFont(f4);
		m7.setFont(f4);
		m8.setFont(f4);
		txt_phone1 = new JTextField(9);
		txt_phone2 = new JTextField(9);
		txt_phone3 = new JTextField(9);

		bt_select01 = new JButton("처음으로");
		bt_select02 = new JButton("예매");
		bt_select03 = new JButton("뒤로 가기");
		bt_select01.setBackground(Color.ORANGE);
		bt_select02.setBackground(Color.ORANGE);
		bt_select03.setBackground(Color.ORANGE);
		bt_select01.setFont(f2);
		bt_select02.setFont(f2);
		bt_select03.setFont(f2);

		s = new JButton[35];
		s[0] = new JButton("A1");
		s[1] = new JButton("A2");
		s1 = new JLabel("          ");
		s[2] = new JButton("A3");
		s[3] = new JButton("A4");
		s[4] = new JButton("A5");
		s2 = new JLabel("          ");
		s[5] = new JButton("A6");
		s[6] = new JButton("A7");

		s[7] = new JButton("B1");
		s[8] = new JButton("B2");
		s3 = new JLabel("          ");
		s[9] = new JButton("B3");
		s[10] = new JButton("B4");
		s[11] = new JButton("B5");
		s4 = new JLabel("          ");
		s[12] = new JButton("B6");
		s[13] = new JButton("B7");

		s[14] = new JButton("C1");
		s[15] = new JButton("C2");
		s5 = new JLabel("          ");
		s[16] = new JButton("C3");
		s[17] = new JButton("C4");
		s[18] = new JButton("C5");
		s6 = new JLabel("          ");
		s[19] = new JButton("C6");
		s[20] = new JButton("C7");

		s[21] = new JButton("D1");
		s[22] = new JButton("D2");
		s7 = new JLabel("          ");
		s[23] = new JButton("D3");
		s[24] = new JButton("D4");
		s[25] = new JButton("D5");
		s8 = new JLabel("          ");
		s[26] = new JButton("D6");
		s[27] = new JButton("D7");

		s[28] = new JButton("E1");
		s[29] = new JButton("E2");
		s9 = new JLabel("          ");
		s[30] = new JButton("E3");
		s[31] = new JButton("E4");
		s[32] = new JButton("E5");
		s10 = new JLabel("          ");
		s[33] = new JButton("E6");
		s[34] = new JButton("E7");

		l4 = new JLabel();

		for (int i = 0; i < 35; i++) {
			s[i].setFont(f6);
		}
		time01 = new JButton("06:30");
		time02 = new JButton("08:10");
		time03 = new JButton("10:20");
		time04 = new JButton("13:00");
		time05 = new JButton("15:20");
		time06 = new JButton("18:40");
		time07 = new JButton("20:20");
		time08 = new JButton("22:30");
		time09 = new JButton("00:30");
		time10 = new JButton("02:10");
		time01.setFont(f5);
		time02.setFont(f5);
		time03.setFont(f5);
		time04.setFont(f5);
		time05.setFont(f5);
		time06.setFont(f5);
		time07.setFont(f5);
		time08.setFont(f5);
		time09.setFont(f5);
		time10.setFont(f5);

		bt_re01 = new JButton("다시 선택");
		bt_re02 = new JButton("다시 선택");

		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		add(west, BorderLayout.WEST);

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		p6 = new JPanel();
		p7 = new JPanel();
		p8 = new JPanel();
		p9 = new JPanel();
		p10 = new JPanel();
		p11 = new JPanel();
		p12 = new JPanel();
		p13 = new JPanel();
		p14 = new JPanel();
		p15 = new JPanel();
		p16 = new JPanel();
		p17 = new JPanel();
		p18 = new JPanel();
		p19 = new JPanel();
		p20 = new JPanel();
		p21 = new JPanel();

		p1.setBackground(Color.BLACK);
		p1.add(m_label);
		p2.setBackground(Color.ORANGE);
		p2.add(l_name);
		p2.add(txt_name);
		p2.add(l_phone);
		p2.add(txt_phone1);
		p2.add(l_phone1);
		p2.add(txt_phone2);
		p2.add(l_phone2);
		p2.add(txt_phone3);
		p3.setBackground(new Color(255, 220, 0));
		p4.setBackground(new Color(255, 220, 0));
		p5.setBackground(new Color(255, 220, 0));
		p3.add(l_date);
		p4.add(l_date1);
		p5.add(combo);
		p6.add(l1);
		p6.setBackground(new Color(255, 220, 0));
		p7.add(l_pers);
		p7.setBackground(new Color(255, 220, 0));
		p8.add(m1);
		p8.add(m2);
		p8.add(m3);
		p8.add(m4);
		p8.add(m5);
		p8.add(m6);
		p8.add(m7);
		p8.add(m8);
		p8.setBackground(new Color(255, 220, 0));
		p9.add(l2);
		p9.setBackground(new Color(255, 220, 0));

		p10.add(l_time);
		p11.add(l3);
		p12.add(time01);
		p12.add(time02);
		p12.add(time03);
		p12.add(time04);
		p12.add(time05);
		p13.add(time06);
		p13.add(time07);
		p13.add(time08);
		p13.add(time09);
		p13.add(time10);

		p14.add(l_seat);
		p15.add(s[0]);
		p15.add(s[1]);
		p15.add(s1);
		p15.add(s[2]);
		p15.add(s[3]);
		p15.add(s[4]);
		p15.add(s2);
		p15.add(s[5]);
		p15.add(s[6]);

		p16.add(s[7]);
		p16.add(s[8]);
		p16.add(s3);
		p16.add(s[9]);
		p16.add(s[10]);
		p16.add(s[11]);
		p16.add(s4);
		p16.add(s[12]);
		p16.add(s[13]);

		p17.add(s[14]);
		p17.add(s[15]);
		p17.add(s5);
		p17.add(s[16]);
		p17.add(s[17]);
		p17.add(s[18]);
		p17.add(s6);
		p17.add(s[19]);
		p17.add(s[20]);

		p18.add(s[21]);
		p18.add(s[22]);
		p18.add(s7);
		p18.add(s[23]);
		p18.add(s[24]);
		p18.add(s[25]);
		p18.add(s8);
		p18.add(s[26]);
		p18.add(s[27]);

		p19.add(s[28]);
		p19.add(s[29]);
		p19.add(s9);
		p19.add(s[30]);
		p19.add(s[31]);
		p19.add(s[32]);
		p19.add(s10);
		p19.add(s[33]);
		p19.add(s[34]);

		p20.add(bt_re01);
		p21.add(bt_re02);

		p9.setBackground(new Color(255, 220, 0));
		p10.setBackground(new Color(255, 220, 0));
		p11.setBackground(new Color(255, 220, 0));
		p12.setBackground(new Color(255, 220, 0));
		p13.setBackground(new Color(255, 220, 0));
		p14.setBackground(new Color(255, 220, 0));
		p15.setBackground(new Color(255, 220, 0));
		p16.setBackground(new Color(255, 220, 0));
		p17.setBackground(new Color(255, 220, 0));
		p18.setBackground(new Color(255, 220, 0));
		p19.setBackground(new Color(255, 220, 0));
		p20.setBackground(new Color(255, 220, 0));
		p21.setBackground(new Color(255, 220, 0));

		north.add(p1);
		north.add(p2);

		west.add(p3);
		west.add(p4);
		west.add(p5);
		west.add(p6);
		west.add(p7);
		west.add(p8);
		west.add(p9);

		center.add(p10);
		// center.add(p11);
		center.add(p12);
		center.add(p13);
		center.add(p20);
		center.add(p14);
		center.add(p15);
		center.add(p16);
		center.add(p17);
		center.add(p18);
		center.add(p19);
		center.add(p21);

		south.add(bt_select01);
		south.add(bt_select02);
		south.add(bt_select03);

		bt_select01.addActionListener(this);
		bt_select02.addActionListener(this);
		bt_select03.addActionListener(this);

		time01.addActionListener(this);
		time02.addActionListener(this);
		time03.addActionListener(this);
		time04.addActionListener(this);
		time05.addActionListener(this);
		time06.addActionListener(this);
		time07.addActionListener(this);
		time08.addActionListener(this);
		time09.addActionListener(this);
		time10.addActionListener(this);

		bt_re01.addActionListener(this);
		bt_re02.addActionListener(this);
		m1.addItemListener(this);
		m2.addItemListener(this);
		m3.addItemListener(this);
		m4.addItemListener(this);
		m5.addItemListener(this);
		m6.addItemListener(this);
		m7.addItemListener(this);
		m8.addItemListener(this);

		for (int i = 0; i < 35; i++) {
			s[i].addActionListener(this);
		}

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeDB();
				System.exit(0);
			}
		});

		// 접속
		connect();
		// 리스트 가져오기
		pack();

		setVisible(true);
	}

	public boolean connect() {
		boolean isConnect = false;
		try {
			// 접속 드라이버 로드
			Class.forName(driver);
			// 접속 시도
			con = DriverManager.getConnection(url, userid, passwd);
			isConnect = true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return isConnect;
	}

	public void closeDB() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public int regist() {
		int result = 0;
		sql = "Insert into moviee(movie_n,name,phone,date,time,people_n,seat)";
		sql = sql + "Value(?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, txt_movie_n);
			pstmt.setString(2, txt_name.getText());
			pstmt.setString(3, txt_phone1.getText() + "-" + txt_phone2.getText() + "-" + txt_phone3.getText());
			pstmt.setString(4, txt_date.concat(combo.getSelectedItem().toString()));
			pstmt.setString(5, txt_time);
			pstmt.setInt(6, Integer.parseInt(txt_people_n));
			pstmt.setString(7, txt_seat);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		// 처음으로 버튼을 눌렀을 때
		if (obj.equals(bt_select01)) {
			javaCjh c = new javaCjh();
			dispose();
		}
		// 예매하기 버튼을 눌렀을 때
		if (obj.equals(bt_select02)) {
			regist();
			JOptionPane.showMessageDialog(getParent(), "예매완료");
			javaCjh c = new javaCjh();
			dispose();
		}
		// 뒤로가기 버튼을 눌렀을 때
		if (obj.equals(bt_select03)) {
			selectMovie m = new selectMovie();
			dispose();
		}

		if (cnt1 == 0) {
			if (obj.equals(time01)) {
				cnt1++;
				txt_time = time01.getText();
				time01.setEnabled(false);
			}
			if (obj.equals(time02)) {
				cnt1++;
				txt_time = time02.getText();
				time02.setEnabled(false);
			}
			if (obj.equals(time03)) {
				cnt1++;
				txt_time = time03.getText();
				time03.setEnabled(false);
			}
			if (obj.equals(time04)) {
				cnt1++;
				txt_time = time04.getText();
				time04.setEnabled(false);
			}
			if (obj.equals(time05)) {
				cnt1++;
				txt_time = time05.getText();
				time05.setEnabled(false);
			}
			if (obj.equals(time06)) {
				cnt1++;
				txt_time = time06.getText();
				time06.setEnabled(false);
			}
			if (obj.equals(time07)) {
				cnt1++;
				txt_time = time07.getText();
				time07.setEnabled(false);
			}
			if (obj.equals(time08)) {
				cnt1++;
				txt_time = time08.getText();
				time08.setEnabled(false);
			}
			if (obj.equals(time09)) {
				cnt1++;
				txt_time = time09.getText();
				time09.setEnabled(false);
			}
			if (obj.equals(time10)) {
				cnt1++;
				txt_time = time10.getText();
				time10.setEnabled(false);
			}
		}
		if (obj.equals(bt_re01)) {
			cnt1 = 0;
			time01.setEnabled(true);
			time02.setEnabled(true);
			time03.setEnabled(true);
			time04.setEnabled(true);
			time05.setEnabled(true);
			time06.setEnabled(true);
			time07.setEnabled(true);
			time08.setEnabled(true);
			time09.setEnabled(true);
			time10.setEnabled(true);
		}
		if (t) {
			if (cnt_m1 == 1) {
				for (int i = 0; i < 35; i++) {
					if (obj.equals(s[i]))
						s[i].setEnabled(false);
				}
				cnt_m1++;
			}
			if (cnt_m2 == 2 || cnt_m2 == 3) {
				for (int i = 0; i < 35; i++) {
					if (obj.equals(s[i]))
						s[i].setEnabled(false);
				}
				cnt_m2++;
			}
			if (cnt_m3 >= 3 && cnt_m3 <= 5) {
				for (int i = 0; i < 35; i++) {
					if (obj.equals(s[i]))
						s[i].setEnabled(false);
				}
				cnt_m3++;
			}
			if (cnt_m4 >= 4 && cnt_m4 <= 7) {
				for (int i = 0; i < 35; i++) {
					if (obj.equals(s[i]))
						s[i].setEnabled(false);
				}
				cnt_m4++;
			}
			if (cnt_m5 >= 5 && cnt_m5 <= 9) {
				for (int i = 0; i < 35; i++) {
					if (obj.equals(s[i]))
						s[i].setEnabled(false);
				}
				cnt_m5++;
			}
			if (cnt_m6 >= 6 && cnt_m6 <= 11) {
				for (int i = 0; i < 35; i++) {
					if (obj.equals(s[i]))
						s[i].setEnabled(false);
				}
				cnt_m6++;
			}
			if (cnt_m7 >= 7 && cnt_m7 <= 13) {
				for (int i = 0; i < 35; i++) {
					if (obj.equals(s[i]))
						s[i].setEnabled(false);
				}
				cnt_m7++;
			}
			if (cnt_m8 >= 8 && cnt_m8 <= 15) {
				for (int i = 0; i < 35; i++) {
					if (obj.equals(s[i]))
						s[i].setEnabled(false);
				}
				cnt_m8++;
			}
			for (int i = 0; i < 35; i++) {
				txt_seat_arr[i] = s[i].getText();
				if (obj.equals(s[i]))
					txt_seat += txt_seat_arr[i] + " ";
			}
			if (obj.equals(bt_re02)) {

				for (int i = 0; i < 35; i++)
					s[i].setEnabled(true);

				System.out.println(cnt_m1);
				m1.setState(true);
				cnt_m1 = 1;

				txt_seat = "";
			}
		}
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {

			if (e.getItem().equals("1명")) {
				txt_people_n = "1";
				for (int i = 0; i < 35; i++)
					s[i].setEnabled(true);

				cnt_m1 = 1;
				t = true;
			}
			if (e.getItem().equals("2명")) {
				txt_people_n = "2";
				for (int i = 0; i < 35; i++)
					s[i].setEnabled(true);
				cnt_m2 = 2;
				t = true;
			}
			if (e.getItem().equals("3명")) {
				txt_people_n = "3";
				for (int i = 0; i < 35; i++)
					s[i].setEnabled(true);
				cnt_m3 = 3;
				t = true;
			}
			if (e.getItem().equals("4명")) {
				txt_people_n = "4";
				for (int i = 0; i < 35; i++)
					s[i].setEnabled(true);
				cnt_m4 = 4;
				t = true;
			}
			if (e.getItem().equals("5명")) {
				txt_people_n = "5";
				for (int i = 0; i < 35; i++)
					s[i].setEnabled(true);
				cnt_m5 = 5;
				t = true;
			}
			if (e.getItem().equals("6명")) {
				txt_people_n = "6";
				for (int i = 0; i < 35; i++)
					s[i].setEnabled(true);
				cnt_m6 = 6;
				t = true;
			}
			if (e.getItem().equals("7명")) {
				txt_people_n = "7";
				for (int i = 0; i < 35; i++)
					s[i].setEnabled(true);
				cnt_m7 = 7;
				t = true;
			}
			if (e.getItem().equals("8명")) {
				txt_people_n = "8";
				for (int i = 0; i < 35; i++)
					s[i].setEnabled(true);
				cnt_m8 = 8;
				t = true;
			}
		}
	}
}