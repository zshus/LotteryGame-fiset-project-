package LotteryGameExChange;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame  {
	private JLabel imgLabel;
	private JLabel welcomeLabel;
	private JLabel buyCountLabel;
	private JPanel amount;
	private JLabel buypnl;
	private JLabel guidepnl;
	private int count=0;
	private JLabel btn;
	private JLabel info;
	public Login() {
		init();
		setDisplay();
		addListener();
		showFrame();
		
	}
	private void init() {		
		imgLabel= new JLabel(new ImageIcon("C:\\JSP\\projects\\LotteryGamer\\img\\logo.png"));		
		imgLabel.setBorder(new EmptyBorder(new Insets(10, 0, 10, 0)));
		welcomeLabel= new JLabel(new ImageIcon("C:\\JSP\\projects\\LotteryGamer\\img\\title.png"));
		welcomeLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
		amount= new JPanel();
		amount.setBackground(Color.WHITE);
		for(int i=0; i<5;i++) {
			btn= new JLabel(new ImageIcon("C:\\JSP\\projects\\LotteryGamer\\img\\btn"+(i+1)+".png"));
			amount.add(btn);
		}
		buypnl= new JLabel(new ImageIcon("C:\\JSP\\projects\\LotteryGamer\\img\\구매버튼.png"));
		guidepnl= new JLabel(new ImageIcon("C:\\JSP\\projects\\LotteryGamer\\img\\게임안내.png"));
		info= new JLabel(new ImageIcon("C:\\JSP\\projects\\LotteryGamer\\img\\info.png"));
		
	}
	private void setDisplay() {
		JPanel pnl_center_south =new JPanel(new GridLayout(3,1));
		pnl_center_south.setBackground(Color.WHITE);	
		pnl_center_south.add(info);
		pnl_center_south.add(amount);
		
		JPanel pnl_center_north =new JPanel(new GridLayout(2,1));
		JLabel po= new JLabel();
		pnl_center_north.add(welcomeLabel);
		pnl_center_north.setBackground(Color.WHITE);
		
		JPanel pnl_center=new JPanel(new BorderLayout());
		pnl_center.setBackground(Color.WHITE);
		pnl_center.add(pnl_center_north,BorderLayout.NORTH);
		pnl_center.add(pnl_center_south,BorderLayout.SOUTH);
		
		JPanel btnpnl= new JPanel(new GridLayout(3,1,0,30));
		btnpnl.setBackground(Color.WHITE);
		
		btnpnl.add(buypnl);
		
		btnpnl.add(guidepnl);
		JPanel pnlAll= new JPanel(new BorderLayout());
		pnlAll.add(imgLabel,BorderLayout.NORTH);
		pnlAll.setBackground(Color.WHITE);
		pnlAll.add(pnl_center);
		pnlAll.add(btnpnl,BorderLayout.SOUTH);
		
		add(pnlAll);
		
	}
	private void addListener() {
		Component[] lbl=amount.getComponents();
		MouseListener mListenenr= new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				for(int i=0; i<lbl.length;i++) {				
					if(e.getSource()==lbl[i]) {
						count=i+1;
						((JLabel)lbl[i]).setIcon(new ImageIcon("C:\\JSP\\projects\\LotteryGamer\\img\\btn"+count+"."+count+".png"));
						
					}else{
						((JLabel)lbl[i]).setIcon(new ImageIcon("C:\\JSP\\projects\\LotteryGamer\\img\\btn"+(i+1)+".png"));	
					}
				}
			
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				for(int i=0; i<lbl.length;i++) {					
					((JLabel)lbl[i]).setCursor(new Cursor(Cursor.HAND_CURSOR));
					
				}
			}
		};
		
		
		for(Component label:lbl) {
			label.addMouseListener(mListenenr);
		}
		
		guidepnl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getX()>160 && e.getX()<260) {
					JOptionPane.showConfirmDialog(Login.this, "* 게임안내 : \r\n"+
							"구매하실 게임의 횟수가 적혀있는 숫자 버튼을 클릭하세요.\r\n" + 
							"선택한 번호를 변경하고 싶다면 변경하고 싶은 번호 버튼을 클릭하세요.\r\n" + 
							"숫자버튼을 선택하지 않으면 게임이 진행되지 않습니다.\r\n", 
							"게임 안내", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(e.getX()>160 && e.getX()<260) {
					((JLabel)e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
				}else {
					((JLabel)e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
					
			}
		});
		
		buypnl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getX()>160&&e.getX()<260) {
					String s=String.valueOf(count);
					if(count!=0) {
						new NumberListFrame(s);
						dispose();
					}else {
						showNotice();
					}
				}
				
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {									
				if(e.getX()>160&&e.getX()<260) {
					((JLabel)e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
				}else {
					((JLabel)e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int choice=JOptionPane.showConfirmDialog(Login.this, "종료하시겠습니까?", "알림", 
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(choice==JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
	}
	private void showFrame() {
		Image imga=Toolkit.getDefaultToolkit().getImage("C:\\JSP\\projects\\LotteryGamer\\img\\toping.png");		
		setIconImage(imga);
		setTitle("Lottery Game"); 
		setSize(450, 630);	
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void showNotice() {
		JOptionPane.showConfirmDialog(Login.this, "버튼으로 구매 수량을 결정하세요!", "NOTICE", 
				JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
	}

	public static void main(String[] args) {
		new Login();

	}

}

