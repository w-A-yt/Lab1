package lab.w1;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class ShowGraph extends JFrame{
	
	private JLabel jLabel;
	private JTextField jTextField;
	private JButton jButton;

public ShowGraph()
{
	 super();
	 this.setSize(500, 300);
	 this.getContentPane().setLayout(null);
	 
	 this.add(getJLabel(), null);
	 this.add(getJTextField(), null);
	 this.add(getJButton(), null);
	 this.setTitle("ShowGraph");
}

private javax.swing.JLabel getJLabel() {
	   if(jLabel == null) {
	      jLabel = new javax.swing.JLabel();
	      jLabel.setBounds(50, 50, 53, 20);
	      jLabel.setText("请输入文件路径");
	   }
	   return jLabel;
	}

	private javax.swing.JTextField getJTextField() {
	   if(jTextField == null) {
	      jTextField = new javax.swing.JTextField();
	      jTextField.setBounds(96, 49, 160, 20);
	   }
	   return jTextField;
	}

	private javax.swing.JButton getJButton() {
	   if(jButton == null) {
	      jButton = new javax.swing.JButton();
	      jButton.setBounds(103, 110, 71, 27);
	      jButton.setText("OK");
	   }
	   return jButton;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShowGraph g=new ShowGraph();
		g.setVisible(true);
	}

}
