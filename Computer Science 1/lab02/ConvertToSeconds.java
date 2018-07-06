/**
* A class that converts hours, minutes, and seconds into a total number of seconds.
*
* @ConnorNagel
*/



import java.util.Scanner;

public class ConvertToSeconds
{

    public static void main(String[]args){
    
    Scanner kbd = new Scanner(System.in);
    System.out.println("# of hours:");
    int hours = kbd.nextInt();
    System.out.println("# of minutes:");
    int minutes = kbd.nextInt();
    System.out.println("# of seconds:");
    int seconds = kbd.nextInt();
    kbd.close();
    int hoursInseconds = 0;
    int minutesInseconds = 0;
    int totalSeconds = 0;
    
     hoursInseconds = hours * 3600;
     minutesInseconds = minutes * 60;
     
     totalSeconds = hoursInseconds + minutesInseconds + seconds;
     

     
     System.out.println("Time in seconds: " + totalSeconds);
    
    }

}