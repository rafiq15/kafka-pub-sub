package publisher.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import publisher.service.MessagePublisher;

@Controller
public class MessageController {

    private final MessagePublisher messagePublisher;

    @Autowired
    public MessageController(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestParam("message") String message, Model model) {
        messagePublisher.publish(message);
        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }


}