import java.util.Scanner;

public class ConvertToHours
{

    public static void main(String[]args){
    
    Scanner kbd = new Scanner(System.in);
    
    System.out.println("# of seconds:");
    int seconds = kbd.nextInt();
    kbd.close();
    int hours = 0;
    int minutes = 0;
    int remainingseconds = 0;
    int extraseconds = 0;
    double fractionalHours = 0.0;
     hours = seconds / 3600;
     remainingseconds = seconds % 3600;
     minutes = remainingseconds / 60;
     extraseconds = remainingseconds % 60;
     
     fractionalHours =(double)seconds / 3600;
     System.out.println("Hours: " + hours);
     System.out.println("Minutes: " + minutes);
     System.out.println("Seconds: " + extraseconds);
     System.out.println("Fractional Hours: " + fractionalHours);
          
    }

}
