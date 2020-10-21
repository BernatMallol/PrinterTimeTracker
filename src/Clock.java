import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends Observable {
  private Observable observable;
  private final Timer clock;
  private static Clock uniqueInstance;

  private Clock() {
    observable = new Observable();
    clock = new Timer();
    startTick();
  }

  public static Clock getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new Clock();
    }
    return uniqueInstance;
  }

  private void startTick() {
    System.out.println("Clock is ticking");
    clock.schedule(new TimerTask() {
      @Override
      public void run() {
        setTime(LocalDateTime.now());
      }
    }, 0, 2000);
  }

  private void setTime(LocalDateTime time) {
    //System.out.println("Se ha enviado esto --> " + time.toString());
    setChanged();
    notifyObservers(time);
  }

  public void stopClock() {
    clock.cancel();
  }
}
