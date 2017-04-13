package code.xiaodeng.games.pictureViewer;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import code.xiaodeng.games.pictureViewer.action.Action;

@SuppressWarnings("serial")
public class ViewerAction extends AbstractAction {
	private String actionName = "";
	private ViewerFrame frame = null;
	
	//�����������AbstractAction����Ӧ��org.crazyit.viewer.action����ĳ��Actionʵȫ
	private Action action = null;

	public ViewerAction() {
	
		super();
	}

	public ViewerAction(ImageIcon icon, String actionName, ViewerFrame frame) {
		// ���ø�������
		super("", icon);
		this.actionName = actionName;
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		ViewerService service = ViewerService.getInstance();
		Action action = getAction(this.actionName);
		//����Action��execute����
		action.execute(service, frame);
	}
	
	private Action getAction(String actionName) {
		try {
			if (this.action == null) {
				//����Actionʵ��
				Action action = (Action)Class.forName(actionName).newInstance();
				this.action = action;
			}
			return this.action;
		} catch (Exception e) {
			return null;
		}
	}
	
	
}