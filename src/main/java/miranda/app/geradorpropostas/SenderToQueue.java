package miranda.app.geradorpropostas;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Scheduled(fixedRate = 10000)
    public void scheduleFixedDelayTask() {

        sendEndpoint ();
    }

    @Value("${app.config.message.queue.topic}")
    private String endpoint;
    @Value("${aws.accessKeyId")
    private String awsAccessKeyId;
    @Value("${aws.secretKeyId}")
    private String awsSecretKeyId;

    public void sendEndpoint(){
        try {

            ObjectMapper mapper = new ObjectMapper();
            String s = mapper.writeValueAsString(GeradorDePropostas.gerarPropostaAprovada().toString());

            System.out.println(s);
            sqsClient().sendMessage(SendMessageRequest.builder()
                    .queueUrl(endpoint)
                    .messageBody(s)
                    .delaySeconds(0)
                    .build());


        } catch (SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
    public SqsClient sqsClient(){
        return SqsClient.builder()
                .region(Region.SA_EAST_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }

}
