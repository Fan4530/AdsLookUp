package Practice;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Practice_RabbitMQ {
    public static void main(String [] agrs) throws Exception {
        topic_exchange();
        //publish_direct_exchange();
        //fanout_exchange();
    }

    public static void topic_exchange() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("e_topic_logs", "topic", true);
        String msg = "topic warn message cs502 demo";

        String routingKey1 = "99.warn";
        String routingKey2 = "99.warn";
        channel.basicPublish("e_topic_logs", routingKey1, null, msg.getBytes());
        System.out.println(" [x] sent : '" + msg + "'");
        System.out.println(" [] sent" + routingKey1 + "" + msg + "");

        channel.close();
        connection.close();
    }
    public static void publish_direct_exchange() throws Exception {
        //factory -> connection -> channel ->  ...

        // this factory is for generating new connection



        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        channel.exchangeDeclare("ex_cp109", "direct", true);
        String msg = "direct message from cp109 message";
        channel.basicPublish("ex_cp109", "109", null, msg.getBytes());
        System.out.println(" [x] Sent '" + msg + "'");
        channel.close();
        connection.close();

    }
    public static void fanout_exchange() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("q_cs502", "fanout", true);
        String message = "broadcast message ...";

        channel.basicPublish("q_cs502", "", null, message.getBytes());
        System.out.println(" [x] " + message + "");
        channel.close();
        connection.close();

    }
}
