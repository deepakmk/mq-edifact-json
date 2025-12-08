package com.example;

public class Main {
    public static void main(String[] args) {
        try {
            RabbitMQClient client = new RabbitMQClient();
            String message = "UNA:+.?*'UNB+UNOA:4+5412345678908:14+8798765432106:14+20020102:1015+INT12346’UNH+1+ORDERS:D:96A:UN:EAN008'BGM+220+12345+9'DTM+137:20240815:102'NAD+BY+5412345000013::9'NAD+SU+4012345000094LIN+1++4000862141404:EN'PIA+1+ABC123:SA'QTY+21:500'PRI+AAA:800'UNT+9+1'";
            // if (args.length > 0) {
            // message = args[0];
            // }
            for (int i = 0; i < 100; i++) {
                client.sendMessage(message + i);
            }
            // String s = client.receiveMessage(ConfigLoader.getProperty("rabbitmq.queue"));
            // System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
