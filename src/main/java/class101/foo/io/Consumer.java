package class101.foo.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PostRepository postRepository;

    @RabbitListener(queues = "CREATE_POST_QUEUE") //큐를 계속 컨슘 하고 있는 상태
    public void handler(String message) throws JsonProcessingException {
        //json으로 되어있는 message를 Post 클래스 타입으로 변경
        Post post = objectMapper.readValue(message, Post.class);
        //메세지 큐에 있는 내용이 DB에 들억감감        postRepository.save(post);
    }

}
