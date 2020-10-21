import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Tracker implements Element {
  protected String name;
  protected LocalDateTime startTime;
  protected LocalDateTime endTime;

  public Tracker(String name){
    this.name = name;
  }

  protected abstract void updateParentEndTime(LocalDateTime endTime);
  public String getName() {
    return this.name;
  }
  public abstract Duration getDuration();
  public abstract String getStartTimeToString();
  public abstract String getEndTimeToString();
  public abstract LocalDateTime getStartTime();
  public abstract LocalDateTime getEndTime();

  @Override
  public void print(VisitorPrint visitorPrint) {
    visitorPrint.print(this);
  }
}
