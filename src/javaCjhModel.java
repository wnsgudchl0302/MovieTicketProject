import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class javaCjhModel extends AbstractTableModel {

	// ������ ���..
	Vector column = new Vector();// �÷������� ���� ����
	Vector list = new Vector(); // ���ڵ带 ���� ����(������� ������ ���� ���� )���ι���

	public javaCjhModel() {
		column.add("��ȭ����");
		column.add("�̸�");
		column.add("����ó");
		column.add("��¥");
		column.add("�ð�");
		column.add("�ο���");
		column.add("�¼�");
	}

	// �÷���
	public String getColumnName(int index) {
		return String.valueOf(column.get(index));
	}
	
	public void setList(Vector list) {
		this.list = list;
	}

	@Override
	public int getColumnCount() {
		// �÷� ������
		
		return column.size(); 
	}

	@Override
	public int getRowCount() {
		
		return list.size(); // ���ڵ� ������..
		
	}

	@Override
	public Object getValueAt(int row, int col) {
		Vector vec = (Vector) list.get(row);
		return vec.get(col);
	}
}
