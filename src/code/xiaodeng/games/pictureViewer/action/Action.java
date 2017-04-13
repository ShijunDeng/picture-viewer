package code.xiaodeng.games.pictureViewer.action;

import code.xiaodeng.games.pictureViewer.ViewerFrame;
import code.xiaodeng.games.pictureViewer.ViewerService;


public interface Action {
	
	void execute(ViewerService service, ViewerFrame frame);
}
