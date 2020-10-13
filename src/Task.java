

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Task extends Tracker implements Visitable{

  private final TaskManager parentProject;
  private List<Interval> listIntervals;
  private boolean status;

  public Task(TaskManager parent, String name) {
    super(name);
    parentProject=parent;
    listIntervals = new ArrayList<Interval>();
    status = true;
  }

  public void endTask(){
    status = false;
    for (Interval interval : listIntervals){
      if (interval.isInProgress()){
        interval.stopInterval();
      }
    }
  }

  @Override
  public Duration getDuration() {
    return duration;
  }


  public LocalDateTime getStartTime() {
    if(listIntervals.get(0)!=null)
      return(listIntervals.get(0).getStartTime());
    return null;
  }

  public LocalDateTime getEndTime() {
    if(listIntervals.get(listIntervals.size()-1)!=null)
      return(listIntervals.get(listIntervals.size()-1).getStartTime());
    return null;
  }

  @Override
  public Tracker getTracker() {
    return parentProject;
  }

  @Override
  protected void updateDuration(Duration durationToAdd) {
    parentProject.updateDuration(durationToAdd);
  }

  public Interval createInterval() {

    Interval interval = new Interval(this, LocalDateTime.now());
    listIntervals.add(interval);
    return interval;
  }

  public void endInterval(Interval interval){
    duration = duration.plus(interval.getDuration());
    updateDuration(duration);
  }

  @Override
  public String toString() {
    return "{" +
        "parentProject=" + parentProject.getName() +
        ", listIntervals=" + listIntervals.toString() +
        ", status=" + status +
        ", name='" + name + '\'' +
        ", duration=" + duration +
        '}';
  }

  public TaskManager getParentProject() {
    return parentProject;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.print(this);
  }
}