import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class javaCjhModel extends AbstractTableModel {

	// 데이터 담기..
	Vector column = new Vector();// 컬럼정보를 가진 백터
	Vector list = new Vector(); // 레코드를 담을 백터(사람들의 정보를 담을 백터 )세로백터

	public javaCjhModel() {
		column.add("영화제목");
		column.add("이름");
		column.add("연락처");
		column.add("날짜");
		column.add("시간");
		column.add("인원수");
		column.add("좌석");
	}

	// 컬럼명
	public String getColumnName(int index) {
		return String.valueOf(column.get(index));
	}
	
	public void setList(Vector list) {
		this.list = list;
	}

	@Override
	public int getColumnCount() {
		// 컬럼 사이즈
		
		return column.size(); 
	}

	@Override
	public int getRowCount() {
		
		return list.size(); // 레코드 사이즈..
		
	}

	@Override
	public Object getValueAt(int row, int col) {
		Vector vec = (Vector) list.get(row);
		return vec.get(col);
	}
}
