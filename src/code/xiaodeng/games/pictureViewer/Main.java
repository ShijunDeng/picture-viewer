package code.xiaodeng.games.pictureViewer;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Main {
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		final TrayIcon trayIcon;
		if (!SystemTray.isSupported()) {
			System.out.println("ϵͳ���̲�֧�֣�");
		} else {
			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().createImage(
					"resource" + File.separator + "image" + File.separator
							+ "systemTray.jpg");
			PopupMenu popupMenu = new PopupMenu();
			MenuItem setItem = new MenuItem("����");
			MenuItem helpItem = new MenuItem("����");
			MenuItem aboutItem = new MenuItem("����");
			MenuItem exitItem = new MenuItem("�˳�");

			aboutItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null,
							"�汾1.0 ͼƬ�鿴��\n��С�˿���  ���˱�������Ȩ��\n��ӭ��ҶԱ���Ϸ�������",
							"������Ϸ", JOptionPane.INFORMATION_MESSAGE);
				}
			});

			exitItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			popupMenu.add(setItem);
			popupMenu.add(helpItem);
			popupMenu.add(aboutItem);
			popupMenu.addSeparator();
			popupMenu.add(exitItem);
			trayIcon = new TrayIcon(image, "ͼƬ�鿴��", popupMenu);
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}// end else if
		ViewerFrame f = new ViewerFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}