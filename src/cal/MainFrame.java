package cal;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener{
	JPanel p_north, p_center;
	JButton bt_prev, bt_next;
	JLabel la_title;
	//����Ʈ�ڽ� �迭����
	DateBox[] box=new DateBox[7*6];
	Calendar cal=Calendar.getInstance();
	//���� ��¥�� ����ϱ� ���� ����
	int yy, mm, dd;
	
	public MainFrame() {
		p_north = new JPanel();
		p_center = new JPanel();
		bt_prev = new JButton("��");
		bt_next = new JButton("��");
		la_title = new JLabel("2017�� 4��");
		la_title.setFont(new Font("����", Font.BOLD|Font.ITALIC, 28));
		
		p_north.add(bt_prev);
		p_north.add(la_title);
		p_north.add(bt_next);
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		yy=cal.get(Calendar.YEAR); 
		//get(int field)���� ��ȯ���� field�� Ŭ������ ������ ����� ��ȯ�Ǵ� ��찡 ����
		mm=cal.get(Calendar.MONTH);
		//���� 0������ �����ϱ� ������ ���α׷� ��ü�� �״�� �ϰ� ����� �� 1�� ���϶�
		dd=cal.get(Calendar.DATE);
		//System.out.println(yy+"-"+mm+"-"+dd);
		
		printDate();
		
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		setSize((120*7)+100, (120*6)+150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//��¥ ��� �޼��� ����
	public void printDate(){
		//���� ��¥�� �󺧿� ���
		la_title.setText(yy+"-"+(mm+1));
		//������ ��¥ �簢�� ������
		p_center.removeAll();
		
		/*�� ���� ���� ���Ϻ��� �����ϴ�?
		���� ����� 4�� 12�Ͽ� ����ȭ�� Ķ������  �ش���� 1�Ϸ� ���߰�, 
		�� ���Ͽ� ���� �������� ������. ��ġ rs�� ������ ������ ����*/
		cal.set(yy, mm, 1);
		int firstDay=cal.get(Calendar.DAY_OF_WEEK);
		//System.out.println((mm+1)+"�� ���� ������ "+firstDay);
		
		//�� ���� ������ ��¥ �˾Ƹ��߱�. ��? �ݺ����� ���� �˱�����.
		cal.set(yy,mm+1,0);
		int lastDay=cal.get(Calendar.DATE);
		
		int num=0;//���� ��µ� ��¥�� ����
		
		for(int i=0;i<box.length;i++){
			box[i]=new DateBox(this);
			p_center.add(box[i]);
			
			if(i>=firstDay-1){
				num++;
			} else {
				num =0;
			}
			
			if(num!=0){
				if(num<=lastDay){
					box[i].la.setText(Integer.toString(num));
				}else{
					box[i].la.setText("");
				}
			} else {
				box[i].la.setText("");
			}
			
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();		
		if(obj==bt_prev){//������
			mm--;
			if(mm<0){
				mm=11;
				yy--;
			}
		}else if(obj==bt_next){//������
			mm++;
			if(mm>11){
				mm=0;
				yy++;
			}
		}
		printDate();
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}

}
