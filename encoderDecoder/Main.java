package encoderDecoder;
import java.util.Scanner;
import java.util.*;
public class Main  {
    public static void main(String[] args) {
        String temp ;
        int choiceTemp;
        Scanner sc = new Scanner(System.in);
        List <UserInfo> lst = new ArrayList<UserInfo>();


        while(true){
            System.out.println("\n\nEnter Choice \n1. Create new id\n2. View all ids\n3. Delete id\n4. edit id\n5. View Entry");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.print("Enter Username: ");
                    temp = sc.nextLine();
                    temp = sc.nextLine();
                    System.out.print("Enter Password: ");
                    lst.add(createUser(temp,sc.nextLine()));
                    System.out.println("Entry added");
                    break;
                case 2:
                    System.out.println("Recent entries: ");
                    viewAllIds(lst);
                    break;
                case 3:
                    System.out.println("Which id should be deleted?");
                    choiceTemp = sc.nextInt();

                    lst.remove(choiceTemp-1);
                    System.out.println("Delete successful");
                    break;
                case 4:
                    System.out.println("Which id should be edited?");
                    choiceTemp = sc.nextInt();
                    editInfo(lst,choiceTemp-1);
                    System.out.println("Edit complete");
                    break;
                case 5:
                    System.out.println("Which entry do you want to view?");
                    choiceTemp = sc.nextInt();
                    System.out.println("The entry is: ");
                    printer(lst.get(choiceTemp-1));
                    break;
                default:
                    System.out.println("Invalid option");
            }

        }

    }
    public static void printer(UserInfo user){
        encDec encDec = new encDec();
        System.out.println("Username: "+user.getUserName());
        System.out.println("Encrypted password: "+user.getUserEncryptedPassword());
        System.out.println("Decrypted Password: "+encDec.decrypt(user.getUserEncryptedPassword(),user.getUserSalt(),100));
        System.out.println("Salt: "+user.getUserSalt());
    }


    public static UserInfo createUser(String UserName, String Password){
        UserInfo user = new UserInfo(UserName);
        encDec encDec = new encDec();
        user.setUserEncryptedPassword(encDec.encrypt(Password,user.getUserSalt(),100));
        return user;
    }


    public static void viewAllIds(List<UserInfo> list){
        System.out.println();
        for(int i = 0;i<list.size();i++){
            System.out.println(i+1+"."+list.get(i).getUserName());
        }
    }


    public static void editInfo(List <UserInfo> list, int index){
        String temp;
        encDec encDec = new encDec();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter new User Name: ");
        temp = sc.nextLine();
        list.get(index).setUserName(temp);
        System.out.print("Enter new Password: ");
        temp = sc.nextLine();
        list.get(index).setUserEncryptedPassword(encDec.encrypt(temp,list.get(index).getUserSalt(),100));

    }
}


