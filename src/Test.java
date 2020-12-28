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
                    InputStream inputStream=Class.forName("Test").getResourceAsStream("/products.xlsx");
                    ReadProductExcel readProductExcel = new ReadProductExcel(inputStream);
                    Product products[] = readProductExcel.readProductExcel(inputStream);
                    for(Product product:products){
                        System.out.println("商品号码：" + product.getProductId());
                        System.out.println("商品名称：" + product.getProductName());
                        System.out.println("商品价格：" + product.getPrice());
                        System.out.println("商品描述：" + product.getDesc());
                    }
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
