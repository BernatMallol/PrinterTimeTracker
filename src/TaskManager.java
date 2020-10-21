import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TaskManager extends Tracker implements Element{


  private TaskManager parentProject;

  private List<Tracker> trackers;


  public TaskManager(String name) {
    super(name);
    trackers = new ArrayList<Tracker>();
  }

  @Override
  protected void updateParentEndTime(LocalDateTime endTime) {
    if (parentProject == null) {
      this.endTime = endTime;
    } else {
      this.endTime = endTime;
      parentProject.updateParentEndTime(endTime);
    }
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }


  public List<Tracker> getTrackers() {
    return trackers;
  }

  public TaskManager(TaskManager parentProject, String name) {
    super(name);
    trackers = new ArrayList<Tracker>();
    this.parentProject = parentProject;
  }

  public void setTrackers(List<Tracker> trackers) {
    this.trackers = trackers;
  }

  @Override
  public Duration getDuration() {
    Duration duration = Duration.ZERO;
    for (Tracker tracker : trackers) {
      duration = duration.plus(tracker.getDuration());
    }
    return duration;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  @Override
  public String getStartTimeToString() {
    if (startTime != null) {
      return startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    return "null";
  }


  public void setEndTime(LocalDateTime endTime) {
    if (parentProject != null) {
      parentProject.setEndTime(endTime);
    }
    this.endTime = endTime;
  }

  public LocalDateTime getEndTime() {
    return this.endTime;
  }

  @Override
  public String getEndTimeToString() {
    if (endTime != null) {
      return endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    return "null";
  }

  public void addChild(Tracker child) {
    trackers.add(child);
  }



}
