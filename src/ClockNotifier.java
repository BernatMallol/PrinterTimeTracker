import java.beans.PropertyChangeListener;
import java.util.Observer;

//TODO: Mirar esto con detalle/ Alberto
public class ClockNotifier {
  private final Clock clockObservable;

  public ClockNotifier(Clock clock){
    this.clockObservable = clock;
  }

  public void addListener(Observer interval){
    clockObservable.addObserver(interval);
  }

  public void removeListener(Observer interval){
    clockObservable.deleteObserver(interval);
  }
}