

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

public class Interval implements Observer,Visitable  {
  private Task parentTask;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private Duration duration;
  private boolean inProgress;

  public void setDuration(Duration duration) {
    this.duration = duration;
  }
  public void setParentTask(Task parentTask){
    this.parentTask=parentTask;
  }

  public Task getParentTask() {
    return parentTask;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setInProgress(boolean inProgress) {
    this.inProgress = inProgress;
  }
  public Interval() {}
  public Interval(Task task, LocalDateTime startTime){
    this.parentTask=task;
    this.startTime=startTime;
    this.inProgress=true;
    this.duration = Duration.ZERO;
  }

  public boolean isInProgress() {
    return inProgress;
  }

  public Duration getDuration(){
    return duration;
  }
  public LocalDateTime getEndTime(){
    return endTime;
  }

  private Duration updateDuration() {
    return Duration.between(startTime, endTime);
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }
  public void setEndTime(LocalDateTime endTime){
    this.endTime=endTime;
  }

  public void stopInterval(){
    inProgress = false;
    parentTask.endInterval(this);
  }

  @Override
  public void update(Observable observable, Object time) {
    setEndTime((LocalDateTime) time);
    if (inProgress){
      duration = updateDuration();
    }
  }

  @Override
  public String toString() {
    return "{" +
        "parentTask :" + parentTask.getName() +
        ", startTime :" + startTime +
        ", endTime :" + endTime +
        ", duration :" + duration.getSeconds() +
        ", inProgress :" + inProgress +
        '}';
  }


  @Override
  public void accept(Visitor visitor) {
    visitor.print(this);
  }
}

