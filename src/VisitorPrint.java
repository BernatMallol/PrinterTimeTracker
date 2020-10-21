public interface VisitorPrint {
  void print(Tracker tracker);
  void print(Task task);
  void print(TaskManager taskManager);
  void print(Interval interval);

}
