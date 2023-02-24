package LotteryGameExChange;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class SecondClass extends JDialog{
	private JLabel lbl_showcount;
	private JLabel lbl_logo;
	private ResultFrame resultFrame;
	private JPanel lbl_showNms;
	private String[] arrTargetNums;
	
	public SecondClass(ResultFrame resultFrame,String[] arrTargetNums) {
		this.arrTargetNums=arrTargetNums;
		this.resultFrame=resultFrame;		
		init();
		setDisplay();
		addListener();
		showFrame();
	}
	private void init() {
		
		String str=resultFrame.getStr();
		String[] arrMyNums=resultFrame.getMyNums();
		int count= resultFrame.getSecondCount();
		String s= String.valueOf(count)+"회";
		lbl_showcount=new JLabel(s,JLabel.CENTER);
		lbl_showcount.setFont(new Font(Font.SERIF,Font.BOLD,30));
		lbl_showcount.setOpaque(true);
		lbl_showcount.setBackground(Color.WHITE);
		lbl_logo= new JLabel(new ImageIcon("C:\\JSP\\projects\\LotteryGamer\\img\\secondCountImg.png"));
		lbl_showNms=new JPanel(new GridLayout(0,1));
		JPanel listTarget=makeTar(arrTargetNums,"타깃");
		listTarget.setBorder(new BevelBorder(BevelBorder.LOWERED,Color.lightGray,Color.LIGHT_GRAY));
		lbl_showNms.add(listTarget);
		
		
		JPanel list=makeShowSouth(arrMyNums,str);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED,Color.GRAY,Color.LIGHT_GRAY));
		
		lbl_showNms.add(list);
		lbl_showNms.setBackground(Color.WHITE);	
		
	}
	private void setDisplay() {
		JPanel pnlCenter= new JPanel(new GridLayout(2,1,0,0));
		pnlCenter.setBackground(Color.WHITE);
		pnlCenter.add(lbl_logo);
		pnlCenter.add(lbl_showcount);
		//add(lbl_showcount,BorderLayout.CENTER);
		add(pnlCenter,BorderLayout.CENTER);
		add(lbl_showNms,BorderLayout.SOUTH);
	}
	private void addListener() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				resultFrame.setVisible(true);
				dispose();
			}
		});
	}
	private void showFrame() {
		Image imga=Toolkit.getDefaultToolkit().getImage("C:\\JSP\\projects\\LotteryGamer\\img\\toping.png");		
		setIconImage(imga);
		setTitle("최소 2등 이상 되는 횟수");
		setSize(357, 320);			
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	private JPanel makeShowSouth(String[] arr,String str) {
		ListFrame list= new ListFrame();		
		list.getResult().setText(str);
		int i=0;
		for(JLabel lbl:list.getNumsArr()) {
			lbl.setText(arr[i]);
			for(int j=0;j<arrTargetNums.length;j++) {
				if(arrTargetNums[j].equals(arr[i]) && j==arrTargetNums.length-1){					
					lbl.setBackground(new Color(178,235,244));
				}else if(arrTargetNums[j].equals(arr[i]) ) {
					lbl.setBackground(Color.PINK);
				}
			}
			i++;
		}
			
		return list;	
	
	}
	
	private JPanel makeTar(String[] arr,String str) {
		JPanel pnl= new JPanel();
		pnl.setBackground(Color.WHITE);
		ListFrame list= new ListFrame();
		list.getResult().setText(str);
		int i=0;		
		for(JLabel lbl:list.getNumsArr()) {
			lbl.setText(arr[i]);
			i++;
		}
		list.getMode().setText("+");
		pnl.add(list);
		JLabel lblnum=new JLabel(arr[i],JLabel.CENTER);
		lblnum.setOpaque(true);
		lblnum.setPreferredSize(new Dimension(30,30));
		lblnum.setBorder(new LineBorder(Color.GRAY));
		pnl.add(lblnum);
		return pnl;
	}

	
	public static void main(String[] args) {
		//new SecondClass();

	}

	

}
