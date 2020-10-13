import static java.lang.Thread.sleep;

public class Printer implements Visitor {

  public Printer() {
  }

  @Override
  public void print(Task task) {
    System.out.println("Task "+task.name+ "    child of "+ task.getParentProject()+ " "+ task.getStartTime()+ " "+ task.getEndTime()+" "+ task.getDuration().getSeconds());
  }

  @Override
  public void print(TaskManager taskManager) {
    System.out.println("Project "+taskManager.name+ "child of "+ taskManager.getParentProject()+" "+ taskManager.getDuration().getSeconds());
  }

  @Override
  public void print(Interval interval) {
    System.out.println("Interval      child of "+interval.getParentTask().name+" "+interval.getStartTime()+" "+interval.getEndTime()+" "+interval.getDuration().getSeconds());
  }

  public static void main(String[] args) throws InterruptedException {
    Printer printer=new Printer();

    Clock clock = new Clock();
    clock.startTick();
    ClockNotifier notify = new ClockNotifier(clock);
    TaskManager root= new TaskManager("main");
    Tracker task0 = root.createTrackers("Task1", TrackerType.TASK);
    Interval interval0 = ((Task) task0).createInterval();
    sleep(1000);
    Interval interval01 = ((Task) task0).createInterval();
    notify.addListener(interval0);
    sleep(1000);
    notify.addListener(interval01);

    interval0.stopInterval();
    sleep(5000);
    interval01.stopInterval();
    notify.removeListener(interval0);
    notify.removeListener(interval01);

    //task0 = printer.acce
    Visitable vis[]= {interval0,interval01, (Visitable) task0};
    for(Visitable v: vis){
      v.accept(printer);
    }

  }
}
