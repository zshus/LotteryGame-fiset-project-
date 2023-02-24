package LotteryGameExChange;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class NumberListFrame extends JDialog implements ActionListener{
	private String str_number;
	private int num;
	private JButton btnBuy;
	private JButton btnHelp;
	private JButton btnAddBuy;		
	private Color blue=new Color(178,235,244);
	private Color pink=new Color(250,224,212);		
	private Vector<ListFrame> frameList= new Vector<>();
	private String[] count_num= {"A","B","C","D","E"};
	private final Insets size= new Insets(0, 1, 0, 1);	
	private JPanel pnlA;
	private JLabel blankLine;
	private JButton btnminiGame;
	private JButton btnAllAuto;
	public NumberListFrame(String str_number) {		
		this.str_number=str_number;
		init();		
		setDisplay();
		addListener();		
		showFrame();
		
	}
	private void init() {		
		pnlA= new JPanel(new GridLayout(0,1));
		btnHelp= new JButton("도움말!");
		btnHelp.setMargin(size);
		btnAddBuy=new JButton("추가 구매");
		btnAddBuy.setMargin(size);
		btnBuy= new JButton("구매하기");
		btnBuy.setMargin(size);
		blankLine= new JLabel(new ImageIcon("img\\linea.png"));
		btnminiGame= new JButton("미니게임 안내");
		btnminiGame.setMargin(size);
		btnAllAuto= new JButton("전체 자동");
		btnAllAuto.setMargin(size);
	}
	JPanel pnl1;
	JPanel pnl2;
	private void setDisplay() {
		pnl1= new JPanel(new BorderLayout());
		pnl1.setBorder(new LineBorder(Color.BLACK));
		pnl2= new JPanel();
		pnl2.setBackground(Color.white);		
		num= Integer.parseInt(str_number);
		for(int i=0; i<num;i++) {
			ListFrame list= new ListFrame(count_num[i]);
			list.getMode().setBackground(blue);
			pnlA.add(list);
			frameList.add(list);
		}
		pnl1.add(pnlA);		
		
		JPanel pnlC=new JPanel();
		pnlC.setBackground(Color.GRAY);
		pnlC.add(btnHelp);
		pnlC.add(btnminiGame);
		JLabel lbl= new JLabel("              "
				+ "                ");		
		pnlC.add(lbl);
		pnlC.add(btnAllAuto);
		pnlC.add(btnAddBuy);
		pnlC.add(btnBuy);		
		pnl1.add(pnlC,BorderLayout.SOUTH);
		
		pnl2.add(pnl1);
		add(pnl2,BorderLayout.CENTER);
		add(blankLine,BorderLayout.SOUTH);
	
	}
	private void addListener() {
		btnHelp.addActionListener(this);
		btnAddBuy.addActionListener(this);		
		btnBuy.addActionListener(this);
		btnminiGame.addActionListener(this);
		btnAllAuto.addActionListener(this);
		for(int i=0; i<frameList.size();i++) {			
			frameList.get(i).getBtnAuto().addActionListener(this);
			frameList.get(i).getBtnDelete().addActionListener(this);
			frameList.get(i).getBtnChange().addActionListener(this);
		}
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int choice=JOptionPane.showConfirmDialog(NumberListFrame.this, 
						"종료하시겠습니까?", "알림", 
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(choice==JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
	}
	private void showFrame() {
		Image imga=Toolkit.getDefaultToolkit().getImage("img\\toping.png");		
		setIconImage(imga);
		setTitle("소중한 로또 표");
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);	
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
//		if(e.getSource()==btnHelp) {
//			JOptionPane.showConfirmDialog(NumberListFrame.this, "* 도움말:\r\n" + 
//					"자동 버튼을 누르면 자동으로 선택된 번호가 나옵니다 . 만약 번호가마음에 안 든다면 다시 자동 버튼을 누르고 번호를 랜덤으로 선택 하시면 됩니다.\r\n"+
//					"수동으로 번호를 선택하기 원하시면 수동/수정 버튼을 눌러 원하시는 숫자를 클릭하시고 확인 버튼을 누르시면 됩니다. \r\n" + 
//					"구매하실 횟수를 줄이고 싶으면 삭제 번호를 누르시면 됩니다. 추가 구매하고 싶으면 추가 구매 버튼을누르시면 됩니다."  , "NOTICE",
//					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
//		}
		if(e.getSource()==btnHelp) {
			JOptionPane.showConfirmDialog(NumberListFrame.this, "* 도움말:\r\n" + 					 
					"[자동구매 안내]\n" +
					"자동 버튼을 누르면 자동으로 선택된 번호가 생성됩니다.\n" +
					"번호를 수정하고 싶다면 다시 자동 버튼을 누르면 됩니다.\r\n\r\n"+
					"[수동구매 안내]\n" +
					"수동/수정 버튼을 누르면 수동번호 선택 팝업창이 열립니다.\n" +
					"팝업창에서 원하는 번호를 클릭하면 번호를 선택할 수 있습니다.\n" +
					"확인 번호를 누르지 않고 팝업창에서 바로 나가면 번호 선택이 안됩니다.\n" +
					"번호 6개를 선택하고 확인 버튼을 누르면 번호가 선택집니다.\r\n\r\n" + 
					"* 삭제 버튼	                 :  해당 회차가 삭제됩니다.\n" +
					"* 추가 구매  버튼        :  게임의 구매횟수를 추가할 수 있습니다. (최대 5회입니다)\n" +
					"* 미니게임 안내  버튼:  미니게임의 관련 정보입니다.\n", "NOTICE",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
		}	
				
		for(int i=0; i<frameList.size();i++) {
			ListFrame list=frameList.get(i);
			JButton btnAotu =list.getBtnAuto();
			if(e.getSource()==btnAotu) {
				list.getMode().setText("자동");
				list.getMode().setBackground(pink);
				JLabel[] arr=list.getNumsArr();
				randomNums(arr);
			}else if(e.getSource()==list.getBtnDelete()) {
				ListFrame listRemove=frameList.remove(i);
				pnlA.remove(listRemove);
				reArray();
				pack();
				num--;
				int number=Integer.parseInt(str_number)-1;
				str_number=String.valueOf(number);
			}else if(e.getSource()==list.getBtnChange()) {
				JLabel[] arr=frameList.get(i).getNumsArr();
//				list.getMode().setText("수동");
//				list.getMode().setBackground(blue);
				new InputSelect(NumberListFrame.this,arr,list.getMode());
				setVisible(false);
			}
		}
		
		if(e.getSource()==btnAllAuto) {
			for(int i=0; i<frameList.size();i++) {
				ListFrame list=frameList.get(i);
				list.getMode().setText("자동");
				list.getMode().setBackground(pink);
				JLabel[] arr=list.getNumsArr();
				randomNums(arr);
			}
		}
		
		
		if(e.getSource()==btnAddBuy) {
			if(num<5) {
				num++;
				addNum();				
			}
			
		}
		if(e.getSource()==btnminiGame) {
			JOptionPane.showConfirmDialog(NumberListFrame.this, "* 미니게임 안내:\r\n" + 
					"구매하신 로또 결과로 컴퓨터 가상 대상과 승패를 가름니다.\r\n"+
					"사용자가 구매한 갯수와 동일한 갯수의 게임를 컴퓨터가 구매합니다. \r\n" + 
					"같은 당첨 번호로 나오는 결과에서 최고 등수가 높은 족의 승리입니다.\r\n\r\n" +
					"미니 게임에 참여하시려면 구매버튼을 눌러 뜨는 창의\"예\"버튼을 눌러 주세요!", "NOTICE",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
		}
	
		if(e.getSource()==btnBuy) {			
			if(checkLists()) {
				int choice= JOptionPane.showConfirmDialog(NumberListFrame.this, "가상 대상와 경기를 하겠습니까?\r\n\r\n"+
			"*: 경기에 대한 자세히 알고 싶으시면 \"취소\"버튼을 눌러\r\n"+
			"<미니게임 안애>을 확인 하세요!", 
						"질문", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(choice==JOptionPane.YES_OPTION) {
					Vector<ListFrame> Clist=ComputerList();					
					showResultFrame(Clist);
				}else if(choice==JOptionPane.NO_OPTION){
					showResultFrame();
				}
				
			}	
		}	
	}	
	private Vector<ListFrame> ComputerList() {
		 Vector<ListFrame> computerList= new Vector<>();
		Vector<String[]> vec=findNum();
		int size=vec.size();
		for(int i=0; i<size;i++) {
			ListFrame lf= new ListFrame(count_num[i]);
			JLabel[] arr=lf.getNumsArr();
			randomNums(arr);
			computerList.add(lf);
		}
		return computerList;
	}
	
	private void reArray() {
		for(int i=0; i<frameList.size();i++) {
			frameList.get(i).getCount().setText(count_num[i]);;
		}
	}
	
	private boolean checkLists() {
		boolean flag=true;
		if(frameList.size()==0){
			JOptionPane.showConfirmDialog(NumberListFrame.this, 
					"선택된 번호가 없으면 구매할 수 없습니다!", "알림",
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.WARNING_MESSAGE);
			return false;
		}else {
			for(ListFrame list:frameList) {
				for(JLabel a:list.getNumsArr()) {					
					if(a.getText().equals("")) {
						flag=false;
						JOptionPane.showConfirmDialog(NumberListFrame.this, 
								"빈칸 있으면 안됩니다!", "알림",
								JOptionPane.DEFAULT_OPTION, 
								JOptionPane.WARNING_MESSAGE);
						return flag;
					}					
				}
			}
		}
		return flag;
		
	}
	
	private void showResultFrame() {
		new ResultFrame(this);
		setVisible(false);
	}
	
	private void showResultFrame(Vector<ListFrame> Clist) {
		new ResultFrame(this, Clist);
		setVisible(false);
	}
	
	private void addNum() {
		if(num>Integer.parseInt(str_number)) {
			ListFrame list= new ListFrame(count_num[num-1]);
			list.getMode().setBackground(blue);			
			pnlA.add(list);						
			frameList.add(list);
			pack();
			list.getBtnAuto().addActionListener(this);
			list.getBtnDelete().addActionListener(this);
			list.getBtnChange().addActionListener(this);		
		}
		
	}

	public Vector<String[]> findNum() {
		Vector<String[]> listnums= new Vector<>();		
		for(ListFrame list: frameList) {
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
			if(flag) {
				listnums.add(strArr);			
			}
		}		
		return listnums;
	}
	private void randomNums(JLabel[] arr) {
		String s="";
		Vector<String> temp=new Vector<>();
		for(int i=0;i<arr.length;) {
			boolean flag=true;
			int num=(int)(Math.random()*45+1);
			String name=String.valueOf(num);			
			if(s.contains(name)) {
				flag=false;				
			}
			if(flag) {
				s+=name+",";

				temp.add(name);
				++i;
			}
		}
		Vector<Integer> tem=new Vector<>();
		for(String str: temp) {
			int i=Integer.parseInt(str);						
			tem.add(i);
			tem.sort((o1,o2)->(o1-o2));
		}
		Vector<String> vector=new Vector<String>();
		for(int i:tem) {
			String ss=String.valueOf(i);
			vector.add(ss);
		}
		for(int i=0;i<arr.length;i++) {
			arr[i].setText(vector.get(i));
		}

	}
	
	public void setNum(JLabel[] arr,Vector<String> vec) {
		for(int i=0;i<arr.length;i++) {
			if(vec.size()<i){
				arr[i].setText("");
			}else{
				arr[i].setText(vec.get(i));
			}			
		}

	}		

	public String[] getMode() {
		String[] modeArr=new String[frameList.size()];
		int count=0;
		for(ListFrame list: frameList) {
			String s1= list.getNumsArr()[0].getText();
			if(!s1.equals("")) {
				modeArr[count]=list.getMode().getText();
				count++;
			}			
		}		
		return modeArr;
	}

}

