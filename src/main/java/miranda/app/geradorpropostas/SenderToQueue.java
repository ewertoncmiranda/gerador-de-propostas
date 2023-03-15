package miranda.app.geradorpropostas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;


@Component
public class SenderToQueue {

    @Scheduled(fixedRate = 5000)
    public void scheduleFixedDelayTask() {
        String to = "Message";
        System.out.println(to);
        sendEndpoint (to);
    }

    @Value("${app.config.message.queue.topic}")
    private String endpoint;
    @Value("${aws.accessKeyId")
    private String awsAccessKeyId;
    @Value("${aws.secretKeyId}")
    private String awsSecretKeyId;

    public void sendEndpoint(String message){
        try {

            sqsClient().sendMessage(SendMessageRequest.builder()
                    .queueUrl(endpoint)
                    .messageBody("Hello world!")
                    .delaySeconds(0)
                    .build());


        } catch (SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }


    }
    public SqsClient sqsClient(){
        return SqsClient.builder()
                .region(Region.SA_EAST_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }

}
