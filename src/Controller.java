import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class Controller implements EventHandler<MyActionEvent>{
	//all finals that will references to comands from model and viewers
	public static final String CREATE_COURSE="Create course";
	public static final String DONE_CREATE_COURSE="Done create course";
	public static final String DONE_CREATE_SHOW="Done create show";
	public static final String DONE_CREATE_SLOTS="Done create slots";
	

	
	private ArrayList<IView> viewers=new ArrayList<>();
	private IModel model;
	
	public Controller(IModel model) {
		this.model=model;
		model.registerListener(this);
	}
	@Override
	public void handle(MyActionEvent e)  {
		
		if(e.getMsg().equals(DONE_CREATE_COURSE)){
			Node pane=((IView)e.getSource()).createNewShowPane();
			((IView)e.getSource()).setMainPane(pane);
			
		}
		else if(e.getMsg().equals(CREATE_COURSE)){
			Node pane=((IView)e.getSource()).createNewCoursePane();
			((IView)e.getSource()).setMainPane(pane);
			createNewCourse((IView)e.getSource());
		}
		else if(e.getMsg().equals(DONE_CREATE_SHOW)){
			int numberOfSlots=((IView)e.getSource()).getNumberOfSlots();
			Node pane=((IView)e.getSource()).createNewSlotPane(numberOfSlots);
			((IView)e.getSource()).setMainPane(pane);
			
		}
		else if(e.getMsg().equals(DONE_CREATE_SLOTS)){
			Node pane=((IView)e.getSource()).courseMenuPane();
			((IView)e.getSource()).setMainPane(pane);
		}
	}
	private void createNewCourse(IView source) {
		
		
	}
	public void addViewer(ScheduleJFX viewer) {
		viewers.add(viewer);
		
	}
	
	

	
}
