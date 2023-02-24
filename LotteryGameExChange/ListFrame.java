package LotteryGameExChange;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ListFrame extends JPanel {
	private JLabel result;
	private JLabel count;
	private JLabel mode;
	private JLabel numLabel;
	private JLabel[] numsArr=new JLabel[6];
	private String countstr;
	private final Insets size= new Insets(0, 1, 0, 1);	
	private JButton btnChange;
	private JButton btnDelete;
	private JButton btnAuto;
	
	public ListFrame() {
		makeLine_result();
	}
	public ListFrame(String countstr) {
		this.countstr=countstr;		
		makeLine_game();
	}

	public JLabel getResult() {
		return result;
	}

	public void setResult(JLabel result) {
		this.result = result;
	}

	public JLabel getCount() {
		return count;
	}

	public void setCount(JLabel count) {
		this.count = count;
	}

	public JLabel getMode() {
		return mode;
	}

	public void setMode(JLabel mode) {
		this.mode = mode;
	}

	public JLabel getNumLabel() {
		return numLabel;
	}

	public void setNumLabel(JLabel numLabel) {
		this.numLabel = numLabel;
	}

	public JLabel[] getNumsArr() {
		return numsArr;
	}

	public void setNumsArr(JLabel[] numsArr) {
		
		if(numsArr.length==6) {
			this.numsArr = numsArr;
		}else {
			JOptionPane.showConfirmDialog(ListFrame.this, "배열 길이는 5입니다.", "알림",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
		
	}

	public JButton getBtnChange() {
		return btnChange;
	}

	public void setBtnChange(JButton btnChange) {
		this.btnChange = btnChange;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnAuto() {
		return btnAuto;
	}

	public void setBtnAuto(JButton btnAuto) {
		this.btnAuto = btnAuto;
	}

	public static void main(String[] args) {
		

	}
	
	private void makeLine_game() {		
		setBackground(Color.WHITE);
		for(int j=0; j<numsArr.length;j++) {						
			if(j==0) {
				count= new JLabel(countstr);						
				mode=new JLabel("수동",JLabel.CENTER);
				mode.setOpaque(true);
				mode.setPreferredSize(new Dimension(30,30));

				add(count);
				add(mode);				
			}
				numLabel= new JLabel("",JLabel.CENTER);
				numLabel.setOpaque(true);												
				numLabel.setPreferredSize(new Dimension(30,30));
				numLabel.setBorder(new LineBorder(Color.GRAY));
				add(numLabel);
				
				
			if(j==5) {
				btnChange=new JButton("수동/수정");							
				btnChange.setMargin(size);
				btnDelete= new JButton("삭제");
				btnDelete.setMargin(size);
				btnAuto= new JButton("자동");
				btnAuto.setMargin(size);
				
				
				add(btnChange);
				add(btnDelete);
				add(btnAuto);		
			}			
			numsArr[j]=numLabel;
			
		}	
	}
	
	private void makeLine_result() {						
		setBackground(Color.WHITE);
		for(int j=0; j<numsArr.length;j++) {
			if(j==0) {
				result= new JLabel("낙첨",JLabel.CENTER);
				result.setPreferredSize(new Dimension(30,20));
				count=new JLabel();
				add(result);
				add(count);
					
			}			
			numLabel= new JLabel("",JLabel.CENTER);				
			numLabel.setOpaque(true);												
			numLabel.setPreferredSize(new Dimension(30,30));
			numLabel.setBorder(new LineBorder(Color.GRAY));
			add(numLabel);				
			if(j==5) {
				mode=new JLabel("",JLabel.CENTER);								
				add(mode);
						
			}			
			numsArr[j]=numLabel;	
				
		}
	}
	
	

}
