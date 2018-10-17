import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class Controller implements ActionListener{
	//all finals that will references to comands from model and viewers
	
	private Button btnMakeCourse=new Button("Create course !");;
	private Button btnDoneMakingCourses=new Button("I'm done create all courses !");
	private Button btnDoneMakingSingalCourse=new Button("I'm done Create course !");
	private Button btnDoneMakingShow=new Button("I'm done create show!");
	private Button btnDoneMakingSlot=new Button("I'm done create slots!");
	
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
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(DONE_CREATE_COURSE)){
			Node pane=((IView)e.getSource()).createNewShowPane();
			((IView)e.getSource()).setMainPane(pane);
			
		}
		else if(e.getActionCommand().equals(CREATE_COURSE)){
			Node pane=((IView)e.getSource()).createNewCoursePane();
			((IView)e.getSource()).setMainPane(pane);
			createNewCourse((IView)e.getSource());
		}
		else if(e.getActionCommand().equals(DONE_CREATE_SHOW)){
			int numberOfSlots=((IView)e.getSource()).getNumberOfSlots();
			Node pane=((IView)e.getSource()).createNewSlotPane(numberOfSlots);
			((IView)e.getSource()).setMainPane(pane);
			
		}
		else if(e.getActionCommand().equals(DONE_CREATE_SLOTS)){
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
