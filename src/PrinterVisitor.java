import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

public class PrinterVisitor implements VisitorPrint, Observer {
  final Timer timer = new Timer();
  private final TaskManager taskManager;

  public PrinterVisitor(TaskManager taskManager) {
    Clock clock = Clock.getInstance();
    this.taskManager = taskManager;
    clock.addObserver(this);
  }

  @Override
  public void print(Tracker tracker) {
    tracker.print(this);
  }

  @Override
  public void print(TaskManager taskManager) {
    for (Tracker tracker : taskManager.getTrackers()) {
      print(tracker);
    }
    System.out.println("activity:    " + taskManager.getName() + "    " + taskManager.getStartTimeToString() + "    " + taskManager.getEndTimeToString() + "    " + taskManager.getDuration().getSeconds());
  }


  @Override
  public void print(Task task) {
    if (task.isActive()) {
      for (Interval interval : task.getListIntervals()) {
        print(interval);
      }
      System.out.println("activity:    " + task.getName() + "    " + task.getStartTimeToString() + "    " + task.getEndTimeToString() + "    " + task.getDuration().getSeconds());
    }
  }

  @Override
  public void print(Interval interval) {
    if (interval.isInProgress()) {
      System.out.println("interval:            " + interval.getStartTimeToString() + "    " + interval.getEndTimeToString() + "    " + interval.getDuration().getSeconds());
    }
  }

  public void stopPrinting() {
    Clock clock = Clock.getInstance();
    clock.deleteObserver(this);
  }

  @Override
  public void update(Observable observable, Object o) {
    print(taskManager);
  }
}
