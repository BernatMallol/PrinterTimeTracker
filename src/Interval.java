import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;

public class Interval implements Observer, Element{
  private final Task parentTask;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private boolean inProgress;

  public Interval(Task task, LocalDateTime startTime){
    this.parentTask=task;
    this.startTime=startTime;
    this.inProgress=true;
  }

  public void setInProgress(boolean inProgress) {
    this.inProgress = inProgress;
  }

  public boolean isInProgress() {
    return inProgress;
  }

  public Duration getDuration(){
    return Duration.between(startTime, endTime);
  }
  public LocalDateTime getEndTime(){
    return endTime;
  }

  public String getEndTimeToString(){
    return endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public void setEndTime(LocalDateTime endTime){
    this.endTime=endTime;
  }

  public Task getParentTask() {
    return parentTask;
  }

  public String getStartTimeToString() {
    return startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void stopInterval(){
    inProgress = false;
    //parentTask.intervalUpdated(this.endTime);
    parentTask.endInterval(this);
  }

  @Override
  public void update(Observable observable, Object time) {
    setEndTime((LocalDateTime) time);
    if (inProgress){
      parentTask.intervalUpdated(this.endTime);
    }
  }

  @Override
  public String toString() {
    return "{" +
        "parentTask :" + parentTask.getName() +
        ", startTime :" + startTime +
        ", endTime :" + endTime +
        ", duration :" + getDuration() +
        ", inProgress :" + inProgress +
        '}';
  }

  @Override
  public void print(VisitorPrint visitorPrint) {
    visitorPrint.print(this);

  }
}
