package LotteryGameExChange;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PKGame extends JDialog {
	private JLabel msg;
	private JPanel Cres;
	private JLabel computer;
	private ResultFrame resultFrame;
	private JButton btn;
	public PKGame(ResultFrame resultFrame) {
		this.resultFrame=resultFrame;
		init();
		setDisplay();
		addListener();
		showFrame();
	}
	private void init() {
		String s=resultFrame.getWin();
		String grade=resultFrame.getGrade();
		if(s.equals("")) {
			msg=new JLabel("비겼습니다!",JLabel.CENTER);
		}else {
			msg=new JLabel(s+"이/가 "+grade+"으로 이겼습니다!",JLabel.CENTER);
		}
		msg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		msg.setForeground(new Color(204,61,61));
		msg.setBorder(new EmptyBorder(5, 0, 5, 0));
		Cres=resultFrame.Cresult();
		Cres.setBorder(new LineBorder(Color.LIGHT_GRAY));
		computer=new JLabel("computer의 구매 결과는 다음과 같습니다.",JLabel.CENTER);
		btn= new JButton("확인");
		btn.setMargin(new Insets(0, 5, 0, 5));
	}
	private void setDisplay() {
		JPanel pnl= new JPanel(new GridLayout(0,1));
		pnl.setBackground(Color.WHITE);
		pnl.add(msg);
		//pnl.add(new JLabel());
		pnl.add(computer);
		add(pnl,BorderLayout.NORTH);
		add(Cres);
		JPanel pnlbtn= new JPanel();
		pnlbtn.add(new JLabel());
		pnlbtn.add(Box.createHorizontalStrut(120));
		pnlbtn.add(btn);
		pnlbtn.add(Box.createHorizontalStrut(120));
		pnlbtn.add(new JLabel());
		add(pnlbtn,BorderLayout.SOUTH);
	}
	private void addListener() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
				resultFrame.setVisible(true);
			}
		});
		btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				resultFrame.setVisible(true);
				
			}
		});
	}
	private void showFrame() {
		Image imga=Toolkit.getDefaultToolkit().getImage("img\\toping.png");		
		setIconImage(imga);
		setTitle("경기결과");		
		//setSize(380, 330);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
