package LotteryGameExChange;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class InputSelect extends JDialog{
	private JPanel pnlMain;
	private JLabel[] lbl_imgs=new JLabel[45];
	private JLabel lbl_img;
	private JButton ok;
	private NumberListFrame numberListFrame;
	private JLabel[] arr;
	private Vector<String> vec=new Vector<>();
	private JLabel mode;
	private boolean flag;
	public InputSelect(NumberListFrame numberListFrame,JLabel[] arr,JLabel mode) {
		this.numberListFrame=numberListFrame;
		this.arr=arr;
		this.mode=mode;
		init();
		setColor();
		setDisplay();
		addListener();
		showDlg();
	}
	private void init() {
		pnlMain=new JPanel(new GridLayout(0,5,1,1));
		pnlMain.setBorder(new EmptyBorder(5,5,5,5));
		pnlMain.setBackground(Color.WHITE);
		for(int i=0;i<lbl_imgs.length;i++) {
			String s=i+1+"";
			lbl_img=new JLabel(s,JLabel.CENTER);
			lbl_img.setBorder(new LineBorder(Color.GRAY,1));			
			lbl_img.setOpaque(true);
			lbl_img.setForeground(Color.WHITE);
			lbl_img.setFont(new Font(Font.SERIF,Font.BOLD,20));
			lbl_img.setBackground(Color.PINK);
			lbl_imgs[i]=lbl_img;
			pnlMain.add(lbl_img);
		}		
		ok=new JButton("확인");
		
	}
	private void setDisplay() {
		add(pnlMain);
		JPanel pnl1= new JPanel();
		pnl1.setBorder(new EmptyBorder(0,5,0,5));
		pnl1.setBackground(Color.lightGray);
		pnl1.add(ok);
		add(pnl1,BorderLayout.SOUTH);
		
	}
	private void addListener() {
		for(JLabel lbl:lbl_imgs) {
			lbl.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if(lbl.getBackground()==Color.ORANGE) {
						lbl.setBackground(Color.PINK);
						lbl.setBorder(new LineBorder(Color.GRAY,1));
						vec.remove(lbl.getText());						
					}else {
						if(vec.size()<6) {
							flag=true;
							lbl.setBackground(Color.ORANGE);
							lbl.setBorder(new BevelBorder(BevelBorder.LOWERED,Color.PINK,Color.GRAY));
							vec.add(lbl.getText());								
						}
						
					}
				}
			});
		}
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(vec.size()==6) {
					JLabel[] arrtemp=new JLabel[6];
					for(int i=0;i<6;i++) {
						arrtemp[i]=((JLabel) arr[i]);	
					}
					Vector<Integer> tem=new Vector<>();
					for(String s: vec) {
						int i=Integer.parseInt(s);						
						tem.add(i);
						tem.sort((o1,o2)->(o1-o2));
					}
					Vector<String> vector=new Vector<String>();
					for(int i:tem) {
						String s=String.valueOf(i);
						vector.add(s);
					}
					if(flag) {
						mode.setText("수동");
						mode.setBackground(new Color(178,235,244));
					}					
					numberListFrame.setNum(arrtemp,vector);
					numberListFrame.setVisible(true);
					dispose();
				}else {
					JOptionPane.showConfirmDialog(InputSelect.this, "고르신 숫자를 6개있어야 합니다!", "NOTICE",
							JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				numberListFrame.setVisible(true);
				dispose();
				
			}
		});
	}
	private void showDlg() {
		Image imga=Toolkit.getDefaultToolkit().getImage("C:\\JSP\\projects\\LotteryGamer\\img\\toping.png");		
		setIconImage(imga);
		setSize(400, 300);
		setTitle("마음에 드는 숫자를 고르십시오!");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private void setColor() {	
		for(int i=0;i<6;i++) {
			String s=arr[i].getText();
			for(JLabel lbl:lbl_imgs) {
				if(lbl.getText().equals(s.trim())) {					
					lbl.setBackground(Color.ORANGE);
					lbl.setBorder(new BevelBorder(BevelBorder.LOWERED,Color.PINK,Color.GRAY));
					vec.add(s.trim());
				}
			}
		}		
	}


}

