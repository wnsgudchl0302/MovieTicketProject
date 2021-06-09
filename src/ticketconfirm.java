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

class ticketconfirm extends JFrame implements ActionListener {
	
	JLabel m_label, movie1, movie2, movie3;
	JPanel north, center, south, p1, p2, p3, p4;
	JButton bt_select01, bt_select02, bt_select03;
	ImageIcon image1, image2, image3;
	JTable table;
	JScrollPane scroll;
	JTextField txt_date, txt_time, txt_people_n, txt_seat;

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movieticket?serverTimezone=UTC";

	String userid = "root";
	String passwd = "1234";

	javaCjhModel model = new javaCjhModel();
	int selRow; // 마우스 이벤트. 어떤 레코드를 선택했는지 알수 있는 변수명.
	int idx; // 마우스를 누를때마다 idx값을 넣어주자~!

	Connection con;// 접속 정보를 가진 객체
	PreparedStatement pstmt;
	ResultSet rs;

	public ticketconfirm() {
		setTitle("예매 확인");
		m_label = new JLabel("예매 확인");
		Font f1 = new Font("serif", Font.BOLD, 30);
		Font f2 = new Font("", Font.BOLD, 18);
		m_label.setFont(f1);
		m_label.setForeground(Color.ORANGE);

		table = new JTable(model);
		scroll = new JScrollPane(table);
		bt_select01 = new JButton("예매 취소");
		bt_select02 = new JButton("처음으로");
		bt_select01.setBackground(Color.ORANGE);
		bt_select02.setBackground(Color.ORANGE);
		bt_select01.setFont(f2);
		bt_select02.setFont(f2);
		north = new JPanel();
		center = new JPanel();
		south = new JPanel();
		north.setPreferredSize(new Dimension(700, 50));
		center.setPreferredSize(new Dimension(700, 200));
		south.setPreferredSize(new Dimension(700, 50));
		north.setLayout(new GridLayout(1, 1));
		center.setLayout(new GridLayout(5, 1));
		south.setLayout(new GridLayout(1, 3));

		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);

		p1 = new JPanel();
		p1.add(m_label);
		p1.setBackground(Color.BLACK);
		north.add(p1);

		center.setLayout(new GridLayout(1, 1));
		center.add(scroll);

		south.add(bt_select02);
		south.add(bt_select01);

		bt_select01.addActionListener(this);
		bt_select02.addActionListener(this);
		// 윈도우 창 닫을때 DB와 연관된 모든 자원을 닫자!!
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeDB();
				System.exit(0);
			}
		});

		// 접속
		connect();
		// 리스트 가져오기
		getListAll();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selRow = table.getSelectedRow();
				System.out.println(selRow);

				String movie_n = (String) table.getValueAt(selRow, 0);
				String name = (String) table.getValueAt(selRow, 1);
				String phone = (String) table.getValueAt(selRow, 2);
				String date = (String) table.getValueAt(selRow, 3);
				String time = (String) table.getValueAt(selRow, 4);
				String people_n = (String) table.getValueAt(selRow, 5).toString();
				String seat = (String) table.getValueAt(selRow, 6);

				idx = (Integer) model.getValueAt(selRow, 7);
				System.out.println(idx);
			}
		});
	}

	public void getListAll() {
		String sql = "select * from moviee order by idx desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			Vector list = new Vector();
			while (rs.next()) {
				Vector record = new Vector();
				record.add(rs.getString("movie_n"));
				record.add(rs.getString("name"));
				record.add(rs.getString("phone"));
				record.add(rs.getString("date"));
				record.add(rs.getString("time"));
				record.add(rs.getInt("people_n"));
				record.add(rs.getString("seat"));
				record.add(rs.getInt("idx"));
				list.add(record);
			}
			model.setList(list);
			this.repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// 예매 취소 버튼
		if (obj.equals(bt_select01)) {
			delete();
			JOptionPane.showMessageDialog(getParent(), "예매 취소 완료");
			getListAll();
			table.updateUI();
		}
		// 처음으로 버튼
		if (obj.equals(bt_select02)) {
			javaCjh j = new javaCjh();
			dispose();
		}
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

	public boolean select(String category, String keyword) {
		boolean result = false;
		String sql = "select * from memberr where " + category + " like '%" + keyword + "%' order by idx desc";
		System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Vector list = new Vector();
			while (rs.next()) {
				// 한사람 정보를 담을 vector를 만들자!(가로백터) 지렁이 쭉쭉~!
				Vector record = new Vector();
				record.add(rs.getString("movie_n"));
				record.add(rs.getString("name"));
				record.add(rs.getString("phone"));
				record.add(rs.getString("date"));
				record.add(rs.getString("time"));
				record.add(rs.getInt("people_n"));
				record.add(rs.getString("seat"));
				record.add(rs.getInt("idx"));
				list.add(record);
			}
			// 테이블 모델에 방금 제조된 큰 백터를 넘기자!!
			model.setList(list);
			this.repaint();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delete() {
		int result = 0;
		String sql = "Delete from moviee where idx=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}