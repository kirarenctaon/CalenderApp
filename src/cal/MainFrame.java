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
	//데이트박스 배열선언
	DateBox[] box=new DateBox[7*6];
	Calendar cal=Calendar.getInstance();
	//현재 날짜를 계산하기 위한 변수
	int yy, mm, dd;
	
	public MainFrame() {
		p_north = new JPanel();
		p_center = new JPanel();
		bt_prev = new JButton("◀");
		bt_next = new JButton("▶");
		la_title = new JLabel("2017년 4월");
		la_title.setFont(new Font("돋움", Font.BOLD|Font.ITALIC, 28));
		
		p_north.add(bt_prev);
		p_north.add(la_title);
		p_north.add(bt_next);
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		yy=cal.get(Calendar.YEAR); 
		//get(int field)에서 반환형이 field면 클래스가 보유한 상수가 반환되는 경우가 많음
		mm=cal.get(Calendar.MONTH);
		//달은 0월부터 시작하기 때문에 프로그램 자체는 그대로 하고 출력은 꼭 1을 더하라
		dd=cal.get(Calendar.DATE);
		//System.out.println(yy+"-"+mm+"-"+dd);
		
		printDate();
		
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		setSize((120*7)+100, (120*6)+150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//날짜 출력 메서드 정의
	public void printDate(){
		//현재 날짜를 라벨에 출력
		la_title.setText(yy+"-"+(mm+1));
		//기존의 날짜 사각형 날리기
		p_center.removeAll();
		
		/*각 월이 무슨 요일부터 시작하니?
		현재 만드는 4월 12일에 최적화된 캘린더를  해당월의 1일로 맞추고, 
		그 요일에 무슨 요일인지 물어보면됨. 마치 rs를 끝으로 보내는 느낌*/
		cal.set(yy, mm, 1);
		int firstDay=cal.get(Calendar.DAY_OF_WEEK);
		//System.out.println((mm+1)+"의 시작 요일은 "+firstDay);
		
		//각 월의 마지막 날짜 알아맞추기. 왜? 반복문의 끝을 알기위해.
		cal.set(yy,mm+1,0);
		int lastDay=cal.get(Calendar.DATE);
		
		int num=0;//실제 출력될 날짜용 변수
		
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
		if(obj==bt_prev){//이전달
			mm--;
			if(mm<0){
				mm=11;
				yy--;
			}
		}else if(obj==bt_next){//다음달
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
