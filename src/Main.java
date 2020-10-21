import static java.lang.Thread.sleep;

public class Main {
  public static void main(String[] args) throws InterruptedException {

    TaskManager root = new TaskManager("root");
    PrinterVisitor printerVisitor = new PrinterVisitor(root);
    //printerVisitor.print(root);
    Task transportation = new Task(root, "transportation");
    Task firstList = new Task(root,"first list");
    Task secondList = new Task(root, "second list");


    root.addChild(transportation);



    Interval transportationInterval = transportation.createInterval();
    sleep(4000);
    transportationInterval.stopInterval();
    sleep(2000);
    root.addChild(firstList);
    Interval firstListInterval =  firstList.createInterval();
    sleep(6000);
    root.addChild(secondList);
    Interval secondListInterval =  secondList.createInterval();
    sleep(4000);
    firstListInterval.stopInterval();
    sleep(2000);
    secondListInterval.stopInterval();
    sleep(2000);
    transportationInterval = transportation.createInterval();
    sleep(4000);
    transportationInterval.stopInterval();

    Clock.getInstance().stopClock();
    printerVisitor.stopPrinting();

  }
}
