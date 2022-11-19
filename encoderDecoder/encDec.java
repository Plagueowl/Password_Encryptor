package encoderDecoder;
import java.io.*;


class UserInfo {
    private String userEncryptedPassword;
    private String  userSalt = PassBasedEnc.getSaltvalue(30);
    String userName;
    UserInfo(String userName1){

        this.userName = userName1;
    }
    UserInfo(String userName1,String userSalt1,String pass){
        this.userName = userName1;
        this.userSalt = userSalt1;
        this.userEncryptedPassword = pass;
    }
    public String getUserEncryptedPassword(){
        return userEncryptedPassword;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName2){
        this.userName = userName2;

    }
    public void setUserEncryptedPassword(String userEncryptedPassword) {
        this.userEncryptedPassword = userEncryptedPassword;
    }

    public String getUserSalt(){
        return userSalt;
    }
}


public class encDec {

    public static void main(String[] args) throws IOException {

        String pass = "P@S$w0rD1234";
        String  userSalt = PassBasedEnc.getSaltvalue(30);
        String enc = encrypt(pass,userSalt,100);
        System.out.println(enc);
        System.out.println(decrypt(enc,userSalt,100));



    }



    public static String encrypt(String ogPass, String salt,int iterations) {
        ogPass = hash(ogPass);
        String pass =(ogPass+salt).substring(0, 24);
        char[] enc = pass.toCharArray();
        int i = 0, j = 3,count = iterations;
        while(count>0){
            i = 0;j = 3;
            while (j < 24) {
                char temp = enc[i];
                enc[i] = enc[j];
                enc[j] = temp;
                if((i+1)%3==0){
                    i+=4;
                    j+=4;
                }
                else{
                    i += 1;
                    j += 1;
                }
            }
            leftRotate(enc,1,24);

            count--;
        }


        count = iterations;
        while(count>0){
            i = 0;
            j = 4;
            while (j < 24) {
                char temp = enc[i];
                enc[i] = enc[j];
                enc[j] = temp;
                if((i+1)%4==0){
                    i+=5;
                    j+=5;
                }
                else{
                    i += 1;
                    j += 1;
                }
            }
            leftRotate(enc,1,24);
            count--;
        }


        String s = new String(enc);
        return s;
    }


    public static String decrypt(String pass,String salt,int iterations){
        char[] dec = pass.toCharArray();
        int i = 0, j = 4,count = iterations;
        while(count>0){
            i = 0;j = 4;
            rightRotate(dec,1);
            while (j<24) {
                char temp = dec[i];
                dec[i] = dec[j];
                dec[j] = temp;

                if((i+1)%4==0){
                    i+=5;
                    j+=5;
                }
                else{
                    i += 1;
                    j += 1;
                }

            }
            count--;
        }
        i = 0;
        j = 3;
        count = iterations;

        while(count>0){
            i = 0;j = 3;
            rightRotate(dec,1);
            while (j<24) {
                char temp = dec[i];
                dec[i] = dec[j];
                dec[j] = temp;
                if((i+1)%3==0){
                    i+=4;
                    j+=4;
                }
                else{
                    i += 1;
                    j += 1;
                }
//                leftRotate(enc,1,24);
            }
            count--;
        }


//        System.out.println(dec);
//        System.out.println("Lenght " + dec.length);

        String s2 = new String(dec);
        s2 = s2.substring(0,s2.indexOf(salt.substring(0,6)));
        return hashRev(s2);
//        return s2;
    }
//    public static void hash(char[] arr){
//        for(int i = 0; i < arr.length; i++) {
//            char c = arr[i];
//            int a = c - '0';
//            arr[i] = (arr[i] * 10) + a;
//        }
//    }



    public static int gcd(int a, int b)
    {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
    public static void leftRotate(char arr[], int d, int n)
    {
        /* To handle if d >= n */
        d = d % n;
        int i, j, k;
        char temp;
        int g_c_d = gcd(d, n);
        for (i = 0; i < g_c_d; i++) {
            /* move i-th values of blocks */
            temp = arr[i];
            j = i;
            while (true) {
                k = j + d;
                if (k >= n)
                    k = k - n;
                if (k == i)
                    break;
                arr[j] = arr[k];
                j = k;
            }
            arr[j] = temp;
        }
    }
    public static void rightRotate(char[] arr,int n){
        for(int i = 0; i < n; i++){
            int j;
            char last;
            //Stores the last element of array
            last = arr[arr.length-1];

            for(j = arr.length-1; j > 0; j--){
                //Shift element of array by one
                arr[j] = arr[j-1];

            }
            arr[0] = last;
        }
    }
    public static String hash(String pass){
        String str,Newstr="";

        try {
            for (int i=0;i<pass.length();i++)
            {
                char ch=pass.charAt(i);
                switch (ch)
                {
                    case 'a':
                        Newstr=Newstr+"{";
                        break;
                    case 'b':
                        Newstr=Newstr+"}";
                        break;
                    case 'c':
                        Newstr=Newstr+"4";
                        break;
                    case 'd':
                        Newstr=Newstr+"~";
                        break;
                    case 'e':
                        Newstr=Newstr+"+";
                        break;
                    case 'f':
                        Newstr=Newstr+"-";
                        break;
                    case 'g':
                        Newstr=Newstr+"*";
                        break;
                    case 'h':
                        Newstr=Newstr+"1";
                        break;
                    case 'i':
                        Newstr=Newstr+"/";
                        break;
                    case 'j':
                        Newstr=Newstr+"\\";
                        break;
                    case 'k':
                        Newstr=Newstr+"?";
                        break;
                    case 'l':
                        Newstr=Newstr+"2";
                        break;
                    case 'm':
                        Newstr=Newstr+"3";
                        break;
                    case 'n':
                        Newstr=Newstr+"^";
                        break;
                    case 'o':
                        Newstr=Newstr+"(";
                        break;
                    case 'p':
                        Newstr=Newstr+")";
                        break;
                    case 'q':
                        Newstr=Newstr+"<";
                        break;
                    case 'r':
                        Newstr=Newstr+">";
                        break;
                    case 's' :
                        Newstr=Newstr+"=";
                        break;
                    case 't':
                        Newstr=Newstr+";";
                        break;
                    case 'u':
                        Newstr=Newstr+",";
                        break;
                    case 'v' :
                        Newstr=Newstr+"_";
                        break;
                    case 'w':
                        Newstr=Newstr+"[";
                        break;
                    case 'x' :
                        Newstr=Newstr+"]";
                        break;
                    case 'y':
                        Newstr=Newstr+":";
                        break;
                    case 'z' :
                        Newstr=Newstr+"\"";
                        break;
                    case '1':
                        Newstr=Newstr+"r";
                        break;
                    case '2':
                        Newstr=Newstr+"k";
                        break;
                    case '3':
                        Newstr=Newstr+"b";
                        break;
                    case '4':
                        Newstr = Newstr+"e";
                        break;
                    case '5':
                        Newstr = Newstr+"q";
                        break;
                    case '6':
                        Newstr = Newstr+"h";
                        break;
                    case '7':
                        Newstr = Newstr+"u";
                        break;
                    case '8' :
                        Newstr= Newstr+"y";
                        break;
                    case '9':
                        Newstr = Newstr+"w";
                        break;
                    case '0':
                        Newstr = Newstr+"z";
                        break;
                    default:
                        Newstr=Newstr+ch;
                        break;
                }
            }
        }
        catch(Exception ioe)
        {
            ioe.printStackTrace();
        }
        return Newstr;
    }
    public static String hashRev(String hashedPass) {
        String str, Newstr = "";
        try {
            for (int i = 0; i < hashedPass.length(); i++) {
                char ch = hashedPass.charAt(i);
                switch (ch) {
                    case '{':
                        Newstr = Newstr + "a";
                        break;
                    case '}':
                        Newstr = Newstr + "b";
                        break;
                    case '4':
                        Newstr = Newstr + "c";
                        break;
                    case '~':
                        Newstr = Newstr + "d";
                        break;
                    case '+':
                        Newstr = Newstr + "e";
                        break;
                    case '-':
                        Newstr = Newstr + "f";
                        break;
                    case '*':
                        Newstr = Newstr + "g";
                        break;
                    case '1':
                        Newstr = Newstr + "h";
                        break;
                    case '/':
                        Newstr = Newstr + "i";
                        break;
                    case '\\':
                        Newstr = Newstr + "j";
                        break;
                    case '?':
                        Newstr = Newstr + "k";
                        break;
                    case '2':
                        Newstr = Newstr + "l";
                        break;
                    case '3':
                        Newstr = Newstr + "m";
                        break;
                    case '^':
                        Newstr = Newstr + "n";
                        break;
                    case '(':
                        Newstr = Newstr + "o";
                        break;
                    case ')':
                        Newstr = Newstr + "p";
                        break;
                    case '<':
                        Newstr = Newstr + "q";
                        break;
                    case '>':
                        Newstr = Newstr + "r";
                        break;
                    case '=':
                        Newstr = Newstr + "s";
                        break;
                    case ';':
                        Newstr = Newstr + "t";
                        break;
                    case ',':
                        Newstr = Newstr + "u";
                        break;
                    case '_':
                        Newstr = Newstr + "v";
                        break;
                    case '[':
                        Newstr = Newstr + "w";
                        break;
                    case ']':
                        Newstr = Newstr + "x";
                        break;
                    case ':':
                        Newstr = Newstr + "y";
                        break;
                    case '\"':
                        Newstr = Newstr + "z";
                        break;
                    case 'r':
                        Newstr = Newstr + "1";
                        break;
                    case 'k':
                        Newstr = Newstr + "2";
                        break;
                    case 'b':
                        Newstr = Newstr + "3";
                        break;
                    case 'e':
                        Newstr = Newstr + "4";
                        break;
                    case 'q':
                        Newstr = Newstr + "5";
                        break;
                    case 'h':
                        Newstr = Newstr + "6";
                        break;
                    case 'u':
                        Newstr = Newstr + "7";
                        break;
                    case 'y':
                        Newstr = Newstr + "8";
                        break;
                    case 'w':
                        Newstr = Newstr + "9";
                        break;
                    case 'z':
                        Newstr = Newstr + "0";
                        break;
                    default:
                        Newstr = Newstr + ch;
                        break;
                }
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return Newstr;

    }

}


