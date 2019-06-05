package Lab7;

import java.util.Scanner;

public class App {


    private static final String login = "uddiadmin";
    private static final String password = "12345";

    public static void main(String[] args) {
        JuddiPubl publisher = new JuddiPubl(login, password);
        JuddiBrows browser = new JuddiBrows(login, password);
        String businessKey, serviceName, wsdl, serviceKey;

        String menu = "Choose action: \n 1) publish service \n 2) call service \n 3) exit";

        Scanner scan = new Scanner(System.in);
        String choice = "";
        while (!"3".equals(choice)) {
            System.out.println(menu);
            choice = scan.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter business key: ");
                    businessKey = scan.next().trim();
                    System.out.println("Enter service name: ");
                    serviceName = scan.next().trim();
                    System.out.println("Enter wsdl url: ");
                    wsdl = scan.next().trim();
                    System.out.println("Service key: " + publisher.publish(businessKey, serviceName, wsdl));
                    break;
                case "2":
                    System.out.println("Enter service name: ");
                    serviceName = scan.next().trim();
                    System.out.println("Enter service key: ");
                    serviceKey = scan.next().trim();
                    browser.callService(serviceName, serviceKey);
                    break;
            }
        }
    }

}
