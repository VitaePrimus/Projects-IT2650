package code;

//This is a class specifically to manage time for the project.
//It keep track of the time since the program started, and the amount of time between frames.
//I can't possibly see why you would ever create more than one TimeManager object.
public class TimeManager {
    private long currentFrameTime;
    private long lastFrameTime;
    private final long startTime; //You can use the word final to indicate that a variable will never change once set

    public TimeManager(){
        startTime = System.nanoTime(); //When the TimeManager is created, it grabs a 'start' time from the system clock
        lastFrameTime = startTime;
        currentFrameTime = startTime;
    }

    public double timeSinceStart()
    {
        return (currentFrameTime - startTime) / 1000000000.0;
    }

    public double timeStamp()
    {
        lastFrameTime = currentFrameTime;
        currentFrameTime = System.nanoTime();
        return (currentFrameTime - lastFrameTime) / 1000000000.0;
    }

}
