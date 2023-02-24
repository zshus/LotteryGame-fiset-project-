package LotteryGameExChange;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ResultFrame extends JDialog implements ActionListener{	
	private String[] result;
	
	private String[] count_num= {"A","B","C","D","E"};
	private String[] modeArr;
	private JLabel title;
	private JLabel titlelbl;
	private JLabel target_title;
	private JLabel target_num;
	private int[] target;
	private Vector<String[]> listnums;
	private NumberListFrame numberListFrame;
	private JPanel pnlA;
	private JButton ok;
	private JButton reBuy;
	private JButton close;
	private Vector<ListFrame> Clist;
	private String[] CResult;
	private JButton miniGame;
	private boolean flag;
	private String[] myNums;
	private int[] arrTargetNum;
	private String str;
	private Vector<String[]> CArr;
	public ResultFrame(NumberListFrame numberListFrame) {		
		this.numberListFrame=numberListFrame;
		init();	
		setDisplay();
		addListener();
		showFrame();
	}
	public ResultFrame(NumberListFrame numberListFrame,Vector<ListFrame> Clist) {		
		this.numberListFrame=numberListFrame;
		this.Clist=Clist;
		init();
		CArr= ComputerNums();
		CResult=showNums(CArr,target);
		flag=true;						
		setDisplay();
		addListener();
		showFrame();		
	}

	private void init() {
		target=makeTarget();	
		listnums=numberListFrame.findNum();
		modeArr=numberListFrame.getMode();
		result=showNums(listnums,target);		
		ok =new JButton("확인");
		reBuy=new JButton("재구매");
		close= new JButton("종료");
		miniGame= new JButton("미니게임 결과");
	}
	private JPanel setMinigame() {
		JPanel pnl_ss=new JPanel();
		pnl_ss.setBackground(Color.WHITE);
		pnl_ss.add(ok);
		pnl_ss.add(Box.createHorizontalStrut(90));
		pnl_ss.add(miniGame);
		pnl_ss.add(reBuy);
		pnl_ss.add(close);
		return pnl_ss;
	}
	
	private JPanel setNomini() {
		JPanel pnl_ss=new JPanel();
		pnl_ss.setBackground(Color.WHITE);
		pnl_ss.add(ok);
		pnl_ss.add(Box.createHorizontalStrut(200));
		pnl_ss.add(reBuy);
		pnl_ss.add(close);
		return pnl_ss;
	}
	
	private void setDisplay() {
		JPanel bigpnl= new JPanel(new BorderLayout());
		bigpnl.setBackground(Color.WHITE);
		bigpnl.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));		
		target();				
		pnlA=new JPanel(new GridLayout(0,1));
		pnlA.setBorder(new LineBorder(Color.lightGray));
		int i;
		for(i=0;i<listnums.size();i++) {
			JPanel list=setLineinfo(i);			
			pnlA.add(list);
		}
		bigpnl.add(pnlA,BorderLayout.CENTER);		
		JPanel pnl_south= new JPanel(new BorderLayout());
		JLabel txt=new JLabel(new ImageIcon("img\\secondCount.png"),JLabel.LEFT);
		txt.setBorder(new EmptyBorder(10, 0, 0, 0));
		pnl_south.setBackground(Color.WHITE);
		pnl_south.add(txt,BorderLayout.NORTH);
		JPanel pnl_ss;
		if(flag) {
			pnl_ss=setMinigame();			
		}else {
			pnl_ss=setNomini();	
		}
			
		pnl_south.add(pnl_ss,BorderLayout.SOUTH);
		bigpnl.add(pnl_south,BorderLayout.SOUTH);
		
		add(bigpnl);
		
	}
	
	private void addListener() {		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				systemClose();
			}
		});		
		
		ok.addActionListener(this);
		reBuy.addActionListener(this);
		close.addActionListener(this);
		miniGame.addActionListener(this);
		
	}
	private void showFrame() {
		Image imga=Toolkit.getDefaultToolkit().getImage("img\\toping.png");		
		setIconImage(imga);
		pack();
		setTitle("당첨 결과를 확인하세요!");
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	private void systemClose() {
		int choice=JOptionPane.showConfirmDialog(ResultFrame.this, "종료하시겠습니까?", "알림", 
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(choice==JOptionPane.YES_OPTION) {
			System.exit(0);
		
		}
	}
	
	private JPanel setLineinfo(int i) {
		ListFrame list= new ListFrame();
		list.getCount().setText(count_num[i]);
		list.getResult().setText(result[i]);
		if(list.getResult().getText()=="1등") {
			list.getResult().setForeground(Color.RED);
		}else if(list.getResult().getText()!="낙첨") {
			list.getResult().setForeground(Color.ORANGE);
		}
		list.getMode().setText(modeArr[i]);
		for(int j=0;j<list.getNumsArr().length ;j++) {			
			list.getNumsArr()[j].setText(listnums.get(i)[j]);			
			int h=checkNum(listnums.get(i)[j]);
			if(h==1) {
				list.getNumsArr()[j].setBackground(Color.PINK);
			}else if(h==2){
				list.getNumsArr()[j].setBackground(new Color(178,235,244));
			}
		}
		return list;
	}
		
	private void target() {
		target_title=new JLabel("당첨 번호: ",JLabel.CENTER);
		JPanel pnl1= new JPanel();
		pnl1.setBackground(Color.WHITE);
		pnl1.add(target_title);
		for(int i=0;i<target.length;i++) {
			String s=String.valueOf(target[i]);
			if(i==6) {
				JLabel lbl=new JLabel("+",JLabel.CENTER);
				lbl.setPreferredSize(new Dimension(30,30));
				lbl.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
				pnl1.add(lbl);
				target_num=new JLabel(s,JLabel.CENTER);
			}else {
				target_num=new JLabel(s,JLabel.CENTER);
			}
			target_num.setOpaque(true);												
			target_num.setPreferredSize(new Dimension(30,30));
			target_num.setBorder(new LineBorder(Color.GRAY));
			pnl1.add(target_num);
		}
		
		titlelbl= new JLabel(new ImageIcon("img\\title4.png"));
		JPanel pnl2= new JPanel(new GridLayout(0,1));
		pnl2.setBackground(Color.WHITE);
		pnl2.add(pnl1);
		pnl2.add(titlelbl);
		
		add(pnl2,BorderLayout.NORTH);
	}

	private int[] makeTarget() {
		int[] arr= new int[7];
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(int i=1;i<46;i++) {
			list.add(i);
		}
		Random r= new Random();		
		for(int i=0;i<7;i++) {
			int random=r.nextInt(list.size());
			int temp=list.remove(random);
			arr[i]=temp;
		}		
		Arrays.sort(arr,0,arr.length-1);
		return arr;	
	}

	public String[] getMyNums() {
		return myNums;
	}

	private String[] showNums(Vector<String[]> listnums,int[] target) {
		String[] res= new String[listnums.size()];
		for(int i=0; i<listnums.size();i++) {
			String[] a=listnums.get(i);
			int count=0;
			int plus=0;
			for(String s:a) {				
				if(s!=null && !(s.equals(""))) {
					int num=Integer.parseInt(s);
					for(int j=0;j<target.length;j++) {
						if(j==target.length-1 && target[j]==num) {
							plus++;							
							
						}else if(target[j]==num) {
							count++;
							
						}
					}
				}				
			}						
			if(count==6) {
				res[i]="1등";
				myNums=a;
			}else if(count==5 && plus==1) {
				res[i]="2등";
				myNums=a;
			}else if(count==5) {
				res[i]="3등";
			}else if(count==4) {
				res[i]="4등";
			}else if(count==3) {
				res[i]="5등";
			}else{
				res[i]="낙첨";
			}
	
		}		
		return res;
	}

	private String[] getArrTargetNum() {
		 String[] targetArr=new  String[arrTargetNum.length];
		for(int i=0;i<arrTargetNum.length;i++) {
			targetArr[i]=String.valueOf(arrTargetNum[i]);
		}
		return targetArr;
	}

	public String getStr() {
		return str;
	}	

	public int getSecondCount() {		
		boolean flag=true;	
		int count=0;
		while(flag) {
			arrTargetNum=makeTarget();
			String[] strArr=showNums(listnums,arrTargetNum);
			for(String s: strArr) {				
				if(s.equals("2등")||s.equals("1등")) {
					str=s;
					flag=false;
				}
			}
			count++;
		}		
		return count;
	
	}
	private String win="";
	private String  grade="";
	
	public String getWin() {
		return win;
	}
	
	public String getGrade() {
		return grade;
	}
	
	private void PK() {				
		String SR="";
		String SCR="";		
		for(String s: result) {	SR+=s;}			
		for(String s1: CResult) {SCR+=s1;}		
		if((SR.contains("1등")&&!SCR.contains("1등"))||
			(SR.contains("2등")&&!SCR.contains("1등")&&!SCR.contains("2등"))||
			(SR.contains("3등")&&!SCR.contains("1등")&&!SCR.contains("2등")&&!SCR.contains("3등"))||
			(SR.contains("4등")&&!SCR.contains("1등")&&!SCR.contains("2등")&&!SCR.contains("3등")&&!SCR.contains("4등"))||
			(SR.contains("5등")&&!SCR.contains("1등")&&!SCR.contains("2등")&&!SCR.contains("3등")&&!SCR.contains("4등")&&!SCR.contains("5등"))) {
			win ="당신";
			grade=SR.contains("1등")?"1등":(SR.contains("2등")?"2등":(SR.contains("3등")?"3등":(SR.contains("4등")?"4등":(SR.contains("5등")?"5등":""))));
		}else if(SR.equals(SCR)||
				(SR.contains("1등")&&SCR.contains("1등"))||
				(SR.contains("2등")&&SCR.contains("2등"))||
				(SR.contains("3등")&&SCR.contains("3등"))||
				(SR.contains("4등")&&SCR.contains("4등"))||
				(SR.contains("5등")&&SCR.contains("5등"))) {
			win="";
		}else {
			win ="Computer";
			grade=SCR.contains("1등")?"1등":(SCR.contains("2등")?"2등":(SCR.contains("3등")?"3등":(SCR.contains("4등")?"4등":(SCR.contains("5등")?"5등":""))));
		}
		if(win.equals("")) {			
			int choice=JOptionPane.showConfirmDialog(ResultFrame.this, "비겼습니다!\n(PC 구매 정보를 확인하려면 \"예\"버튼을 누르세요!", "경기 경과", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			showPGGame(choice);
		}else {
			int choice=JOptionPane.showConfirmDialog(ResultFrame.this, win+"이/가 "+grade+"으로 이겼습니다!\n(PC 구매 정보를 확인하려면 \"예\"버튼을 누르세요!", "경기 경과", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			showPGGame(choice);
		}
		
		
	}
	
	private void showPGGame(int choice) {
		if(choice==JOptionPane.YES_OPTION) {
			new PKGame(this);
			dispose();
		}
	}
	
	public JPanel Cresult() {
		JPanel pnlB= new JPanel(new GridLayout(0,1));		
		for(int i=0;i< Clist.size();i++) {
			ListFrame list= new ListFrame();
			list.getCount().setText(count_num[i]);
			list.getResult().setText(CResult[i]);
			if(list.getResult().getText()=="1등") {
				list.getResult().setForeground(Color.RED);
			}else if(list.getResult().getText()!="낙첨") {
				list.getResult().setForeground(Color.ORANGE);
			}
			//list.getMode().setText("");
			for(int j=0;j<list.getNumsArr().length ;j++) {			
				list.getNumsArr()[j].setText(CArr.get(i)[j]);			
				int h=checkNum(CArr.get(i)[j]);
				if(h==1) {
					list.getNumsArr()[j].setBackground(Color.PINK);
				}else if(h==2){
					list.getNumsArr()[j].setBackground(new Color(178,235,244));
				}
			}						
			pnlB.add(list);
		}

		return pnlB;
		 
	}
	
	private Vector<String[]> ComputerNums(){
		Vector<String[]> listnums= new Vector<>();		
		for(ListFrame list: Clist) {
			JLabel[] arr=list.getNumsArr();
			boolean flag=true;
			String[] strArr=new String[arr.length];
			for(int i=0;i<arr.length;i++) {
				String s=arr[i].getText();
				if(!s.equals("")) {
					strArr[i]=s;					
				}else {
					flag=false;
				}
				
			}
			//System.out.println(Arrays.toString(strArr)+"\n");	
			if(flag) {
				listnums.add(strArr);			
			}
		}		
		return listnums;
	}
	
	private int checkNum(String s) {
		int h=0;
		if(s!=null && !(s.equals(""))) {
			int num=Integer.parseInt(s);
			for(int i=0;i<target.length;i++) {
				if(i==target.length-1 && target[i]==num) {			
					h=2;
				}else if(target[i]==num) {				
					h=1;
				}
			}	
		}		
		return h;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ok) {
			int count=getSecondCount();
			String[] arrTargetNums=getArrTargetNum();
			new SecondClass(ResultFrame.this,arrTargetNums);
			dispose();
		}else if(e.getSource()==reBuy) {
			new Login();
			dispose();
		}else if(e.getSource()==close) {
			systemClose();
		}else if (e.getSource()==miniGame) {
			PK();
			
		}
		
	}
	

}
