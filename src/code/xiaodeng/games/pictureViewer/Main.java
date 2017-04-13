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
			System.out.println("系统托盘不支持！");
		} else {
			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().createImage(
					"resource" + File.separator + "image" + File.separator
							+ "systemTray.jpg");
			PopupMenu popupMenu = new PopupMenu();
			MenuItem setItem = new MenuItem("设置");
			MenuItem helpItem = new MenuItem("帮助");
			MenuItem aboutItem = new MenuItem("关于");
			MenuItem exitItem = new MenuItem("退出");

			aboutItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null,
							"版本1.0 图片查看器\n由小邓开发  本人保留所有权利\n欢迎大家对本游戏提出建议",
							"关于游戏", JOptionPane.INFORMATION_MESSAGE);
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
			trayIcon = new TrayIcon(image, "图片查看器", popupMenu);
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