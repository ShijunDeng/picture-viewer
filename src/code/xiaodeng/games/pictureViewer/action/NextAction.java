package code.xiaodeng.games.pictureViewer.action;

import code.xiaodeng.games.pictureViewer.ViewerFrame;
import code.xiaodeng.games.pictureViewer.ViewerService;

public class NextAction implements Action {

	public void execute(ViewerService service, ViewerFrame frame) {
		service.next(frame);
	}

}
