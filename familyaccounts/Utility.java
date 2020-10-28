package accounts;

import java.util.Scanner;

/**
 Utility工具类：
 将不同的功能封装为方法，就可以直接通过调用方法来使用功能
 */
public class Utility {
    private static Scanner scanner = new Scanner(System.in);
    /**
    用于界面菜单选择。该方法读取键盘，如果用户键入“1-4”中的任意字符，则方法返回用户键入的字符
     */
    public static char readMenuSelection(){
        char c = 0;
        for(;;){
            String str = scanner.next();
            c = str.charAt(0);
            if(c == '1'||c == '2'||c == '3'||c == '4'){
                break;
            }
            else{
                System.out.println("选择错误请重新输入");
            }
        }
        return c;
    }

    /**
     用于收入和指出金额的输入。该方法从键盘中读取一个不超过四位长度的整数，并将其作为方法的返回值
     */
     public static int readNubmer(){
         int money = 0;
         Scanner scanner = new Scanner(System.in);
         for (;;){
             money = scanner.nextInt();
             try {
                 if(0 <= money && money <= 9999){
                     break;
                 }
                 System.out.println("数字输入错误请重新输入");
             }catch (NumberFormatException e){
                 System.out.println("数字输入错误请重新输入");
             }
         }
         return money;
     }

    /**
     用于收入和支出说明的录入。该方法从键盘读取一个不超过8位长度的字符串
     */
    public static String readDescription(){
        String description ="";
        Scanner scanner = new Scanner(System.in);
        for (;;){
            description = scanner.next();
            if (description.length()<=8){
                break;
            }
            System.out.println("说明录入错误");
        }
        return description;
    }

    /**
     用于确认选择的输入，该方法从键盘中读取”Y“和”N“，并将其作为方法的返回值
     */
    public static char readConfirmSelection(){
        char c = 0;
        for(;;){
            String str = scanner.next().toUpperCase();
            c = str.charAt(0);
            if(c == 'Y'|| c == 'N'){
                break;
            }
            else{
                System.out.println("选择错误,请重新输入");
            }
        }
        return c;
    }
}
