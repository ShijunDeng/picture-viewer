package code.xiaodeng.games.pictureViewer;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.util.List;
import java.util.ArrayList;

public class ViewerService {
	private static ViewerService service = null;
	private ViewerFileChooser fileChooser = new ViewerFileChooser();
	private double range = 0.1;
	private File currentDirectory = null;
	private List<File> currentFiles = null;
	private File currentFile = null;

	private ViewerService() {
	}

	public static ViewerService getInstance() {
		if (service == null) {
			service = new ViewerService();
		}
		return service;
	}

	public void open(ViewerFrame frame) {
		if (fileChooser.showOpenDialog(frame) == ViewerFileChooser.APPROVE_OPTION) {
			this.currentFile = fileChooser.getSelectedFile();
			String name = this.currentFile.getPath();
			File cd = fileChooser.getCurrentDirectory();
			// 如果文件夹有改变
			if (cd != this.currentDirectory || this.currentDirectory == null) {
				// 或者fileChooser的所有FileFilter
				FileFilter[] fileFilters = fileChooser
						.getChoosableFileFilters();
				File files[] = cd.listFiles();
				this.currentFiles = new ArrayList<File>();
				for (File file : files) {
					for (FileFilter filter : fileFilters) {
						// 如果是图片文件
						if (filter.accept(file)) {
							// 把文件加到currentFiles中
							this.currentFiles.add(file);
						}
					}
				}
			}
			ImageIcon icon = new ImageIcon(name);
			frame.getLabel().setIcon(icon);
		}
	}

	public void zoom(ViewerFrame frame, boolean isEnlarge) {
		// 获取放大或者缩小的乘比
		double enLargeRange = isEnlarge ? 1 + range : 1 - range;
		ImageIcon icon = (ImageIcon) frame.getLabel().getIcon();
		if (icon != null) {
			int width = (int) (icon.getIconWidth() * enLargeRange);
			int height = (int) ((icon.getIconHeight()) * enLargeRange);
			// 获取改变大小后的图片
			ImageIcon newIcon = new ImageIcon(icon.getImage()
					.getScaledInstance(width, height, Image.SCALE_DEFAULT));
			// 改变显示的图片
			frame.getLabel().setIcon(newIcon);
		}
	}

	
	public void last(ViewerFrame frame) {
		// 如果有打开包含图片的文件夹
		if (this.currentFiles != null && !this.currentFiles.isEmpty()) {
			int index = (this.currentFiles.indexOf(this.currentFile)
					+ this.currentFiles.size() - 1)
					% this.currentFiles.size();
			// 打开上一个
			File file = (File) this.currentFiles.get(index);
			ImageIcon icon = new ImageIcon(file.getPath());
			frame.getLabel().setIcon(icon);
			this.currentFile = file;

		}
	}

	public void next(ViewerFrame frame) {
		// 如果有打开包含图片的文件夹
		if (this.currentFiles != null && !this.currentFiles.isEmpty()) {
			int index = (this.currentFiles.indexOf(this.currentFile) + 2)
					% this.currentFiles.size();

			File file = (File) this.currentFiles.get(index);
			ImageIcon icon = new ImageIcon(file.getPath());
			frame.getLabel().setIcon(icon);
			this.currentFile = file;
		}
	}

	
	public void menuDo(ViewerFrame frame, String cmd) {
		// 打开
		if (cmd.equals("打开(O)")) {
			open(frame);
		}
		// 放大
		if (cmd.equals("放大(M)")) {
			zoom(frame, true);
		}
		// 缩小
		if (cmd.equals("缩小(O)")) {
			zoom(frame, false);
		}
		// 上一个
		if (cmd.equals("上一个(X)")) {
			last(frame);
		}
		// 下一个
		if (cmd.equals("下一个(P)")) {
			next(frame);
		}
		// 退出
		if (cmd.equals("退出(X)")) {
			System.exit(0);
		}
	}
}