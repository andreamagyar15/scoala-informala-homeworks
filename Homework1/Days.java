/**
 * Implement a Number of Days in a Month calculator using a switch statement.
 */
    import java.util.Scanner;
    public class Days {
        public static void main(String[] args){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the year");
            int year = scanner.nextInt();
            System.out.println("Please enter the month");
            int month=scanner.nextInt();
            if(month>0 && month<=12){
                switch(month) {
                    case 2:
                        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                            System.out.println("29");
                            break;
                        } else {
                            System.out.println("28");
                            break;
                        }
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        System.out.println("30");
                        break;
                    default:
                        System.out.println("31");
                }
            }else{
                System.out.println("Invalid month");
            }
        }

}
