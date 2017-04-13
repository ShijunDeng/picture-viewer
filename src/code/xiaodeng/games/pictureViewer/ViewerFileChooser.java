package code.xiaodeng.games.pictureViewer;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.io.File;



@SuppressWarnings("serial")
public class ViewerFileChooser extends JFileChooser {
	
	public ViewerFileChooser() {
		super();
		setAcceptAllFileFilterUsed(false);
		addFilter();
	}

	public ViewerFileChooser(String currentDirectoryPath) {
		super(currentDirectoryPath);
		setAcceptAllFileFilterUsed(false);
		addFilter();
	}

	
	private void addFilter() {
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".JPG",
						".JPEG", ".JPE", ".JFIF" },
						"JPEG (*.JPG;*.JPEG;*.JPE;*.JFIF)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".BMP" },
				"BMP (*.BMP)"));
				this.addChoosableFileFilter(new MyFileFilter(new String[] { ".GIF" },
				"GIF (*.GIF)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".TIF",
				".TIFF" }, "TIFF (*.TIF;*.TIFF)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".PNG" },
				"PNG (*.PNG)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".ICO" },
				"ICO (*.ICO)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".BMP",
				".JPG", ".JPEG", ".JPE", ".JFIF", ".GIF", ".TIF", ".TIFF",
				".PNG", ".ICO" }, "所有图形文件"));
	}

	class MyFileFilter extends FileFilter {
		String[] suffArr;
		String decription;

		public MyFileFilter() {
			super();
		}

	
		public MyFileFilter(String[] suffArr, String decription) {
			super();
			this.suffArr = suffArr;
			this.decription = decription;
		}

		public boolean accept(File f) {
			// 如果文件的后缀名合法，返回true
			for (String s : suffArr) {
				if (f.getName().toUpperCase().endsWith(s)) {
					return true;
				}
			}
			return f.isDirectory();
		}

	
		public String getDescription() {
			return this.decription;
		}
	}

}