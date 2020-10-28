package familyaccounts;

import java.util.Scanner;

public class FamilyAccount {

    public static void main(String[] args){
        boolean isFlag = true;
        //记录收支明细
        String details = "\t收支\t\t账户金额\t\t本次收支金额\t\t说    明\n";
        //初始金额10000
        int balance = 10000;
        while(isFlag){
            System.out.println("--------------------家庭收支记账软件------------------");
            System.out.println("                    1     收支明细");
            System.out.println("                    2     登记收入");
            System.out.println("                    3     登记支出");
            System.out.println("                    4     退   出\n");
            System.out.print("                    请 输 入 (1-4):");

            //获取用户的选择
            char selection = Utility.readMenuSelection();
            switch (selection){
                //注意工具类接受的是‘1’，而不是1
                case '1':
                    System.out.println("--------------------当前收支明细记录------------------");
                    System.out.println(details);
                    System.out.println("---------------------------------------------------\n");
                    break;
                //收入
                case '2':
                    System.out.println("本次收入金额");
                    int inMoney = Utility.readNubmer();
                    System.out.println("本次收入说明");
                    String inInfo = Utility.readDescription();

                    //处理余额
                    balance += inMoney;

                    //拼接每条记录
                    details+="\t收入\t\t"+balance+"\t\t"+inMoney+"\t\t\t\t"+inInfo+"\n";

                    System.out.println("-----------------------登记完成---------------------\n");
                    break;

                //支出
                case '3':
                    System.out.println("本次支出金额");
                    int outMoney = Utility.readNubmer();
                    System.out.println("本次支出说明");
                    String outInfo = Utility.readDescription();

                    //处理余额
                    if(balance>=outMoney){
                        balance -= outMoney;
                        //拼接每条记录
                        details+="\t收入\t\t"+balance+"\t\t"+outMoney+"\t\t\t\t"+outInfo+"\n";
                        System.out.println("-----------------------登记完成---------------------\n");
                    }
                    else {
                        System.out.println("本次支出超过账户余额，支付失败");
                    }

                    break;
                case '4':
                    System.out.print("请确认是否退出(Y/N)");
                    char confirmSelection = Utility.readConfirmSelection();
                    if (confirmSelection=='Y'){
                        isFlag = false;
                    }
                    break;

            }
        }
    }
}
