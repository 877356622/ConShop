import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo=true;
        while(bo) {
            Scanner sc = new Scanner(System.in);

            System.out.println("请输入用户名");
            String username = sc.next();

            System.out.println("请输入密码");
            String password = sc.next();

            //开始读取文件
            InputStream in = Class.forName("Test").getResourceAsStream("/user.xlsx");
            ReadExcel readExcel = new ReadExcel(in);
            User users[] = readExcel.readExcel(in);

            //判断用户名和密码是否正确
            int i = 0;
            for (User user : users) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    System.out.println("登录成功");
                    i = 1;
                    bo=false;
                    break;
                }
            }
            if (i == 0) {
                System.out.println("用户名或密码错误");
            }
        }
    }
}
