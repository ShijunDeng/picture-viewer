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
			// ����ļ����иı�
			if (cd != this.currentDirectory || this.currentDirectory == null) {
				// ����fileChooser������FileFilter
				FileFilter[] fileFilters = fileChooser
						.getChoosableFileFilters();
				File files[] = cd.listFiles();
				this.currentFiles = new ArrayList<File>();
				for (File file : files) {
					for (FileFilter filter : fileFilters) {
						// �����ͼƬ�ļ�
						if (filter.accept(file)) {
							// ���ļ��ӵ�currentFiles��
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
		// ��ȡ�Ŵ������С�ĳ˱�
		double enLargeRange = isEnlarge ? 1 + range : 1 - range;
		ImageIcon icon = (ImageIcon) frame.getLabel().getIcon();
		if (icon != null) {
			int width = (int) (icon.getIconWidth() * enLargeRange);
			int height = (int) ((icon.getIconHeight()) * enLargeRange);
			// ��ȡ�ı��С���ͼƬ
			ImageIcon newIcon = new ImageIcon(icon.getImage()
					.getScaledInstance(width, height, Image.SCALE_DEFAULT));
			// �ı���ʾ��ͼƬ
			frame.getLabel().setIcon(newIcon);
		}
	}

	
	public void last(ViewerFrame frame) {
		// ����д򿪰���ͼƬ���ļ���
		if (this.currentFiles != null && !this.currentFiles.isEmpty()) {
			int index = (this.currentFiles.indexOf(this.currentFile)
					+ this.currentFiles.size() - 1)
					% this.currentFiles.size();
			// ����һ��
			File file = (File) this.currentFiles.get(index);
			ImageIcon icon = new ImageIcon(file.getPath());
			frame.getLabel().setIcon(icon);
			this.currentFile = file;

		}
	}

	public void next(ViewerFrame frame) {
		// ����д򿪰���ͼƬ���ļ���
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
		// ��
		if (cmd.equals("��(O)")) {
			open(frame);
		}
		// �Ŵ�
		if (cmd.equals("�Ŵ�(M)")) {
			zoom(frame, true);
		}
		// ��С
		if (cmd.equals("��С(O)")) {
			zoom(frame, false);
		}
		// ��һ��
		if (cmd.equals("��һ��(X)")) {
			last(frame);
		}
		// ��һ��
		if (cmd.equals("��һ��(P)")) {
			next(frame);
		}
		// �˳�
		if (cmd.equals("�˳�(X)")) {
			System.exit(0);
		}
	}
}