//�� ��¥�� ǥ���ϴ� Ŀ���͸���¡ ������Ʈ
package cal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DateBox extends JPanel {
	JLabel la;
	MainFrame mainFrame;
	int index;
	
	public DateBox(MainFrame main) {
		this.mainFrame=main;
		this.index=index;
		
		this.setLayout(new BorderLayout());
		la=new JLabel("��¥");
		
		//���� ���콺 ������ ����
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pop();
			}
		});
		
		add(la, BorderLayout.NORTH);
		setPreferredSize(new Dimension(120, 120));
		setBackground(Color.WHITE);
	}
	
	public void pop(){
		int yy=mainFrame.yy;
		int mm=mainFrame.mm;
		int dd=Integer.parseInt(la.getText());
	
		JOptionPane.showMessageDialog(mainFrame, yy+"-"+mm+"-"+dd);
		//�гο��� �޽����� ���� �� ����
		
		for(int i=0;i<mainFrame.box.length;i++){
			if(mainFrame.box[i] != this){//���� �ƴ϶��
				mainFrame.box[i].setBackground(Color.WHITE);
			}else{
				mainFrame.box[i].setBackground(Color.PINK);
			}
		}
		
	}

}
