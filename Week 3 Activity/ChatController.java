import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatController {
    private final ChatModel model;
    private final ChatView view;

    public ChatController(ChatModel model, ChatView view) {
        this.model = model;
        this.view = view;

        // Add action listener to the send button
        view.getSendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO:send message
				sendMessage();
				
            }
        });

        // Add Enter key functionality
        view.getInputField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO:send message
				sendMessage();
            }
        });
    }

    private void sendMessage() {
        // TODO
		String message = view.getInputField().getText();
		if (!message.trim().isEmpty()){
			// update model
			model.addMessage(message);
			//update view
			view.getChatArea().append("You: " + message + "\n");
			view.getInputField().setText("");
		}
    }

    public static void main(String[] args) {
        // Create MVC components
        ChatModel model = new ChatModel();
        ChatView view = new ChatView();

	// TODO: Create controller
		new ChatController(model, view);

        // Make the GUI visible
        view.setVisible(true);
    }
}
