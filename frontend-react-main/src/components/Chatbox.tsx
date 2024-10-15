import React, { useState } from "react";
import axios from "axios";
import { v4 as uuidv4 } from "uuid";
import { useWallet } from "@solana/wallet-adapter-react";

interface ChatBoxProps {
  setonChat: (isTrue:any) => void;
  walletState: string | null; // Callback to notify Home component about chat updates
}

interface Message {
  id: string;
  content: string;
  sender: "user" | "assistant";
}

const ChatBox: React.FC<ChatBoxProps> = ({ setonChat, walletState }) => {
  const [message, setMessage] = useState("");
  const [chatId, setChatId] = useState<string | null>(null);
  const [messages, setMessages] = useState<Message[]>([]);
  const { publicKey } = useWallet();

  const handleSend = async () => {
    if (!message.trim()) return; // Avoid sending empty messages

    if (!chatId) {
      // Ensure chatId is set
      const id = uuidv4();
      setChatId(id);
    }

    // Append walletId to the message (assuming walletId is available here)
    const walletId = publicKey?.toBase58(); // Replace with actual walletId
    const messageWithWalletId = `${message} (walletId: ${walletId})`;

    try {
      // Send the user message
      const response = await axios.post("http://localhost:8080/api/v1/assistants", {
        chatId: 1,
        userMessage: messageWithWalletId,
        walletId: walletId
      });

      // Add the user message to the messages state
      setMessages(prevMessages => [
        ...prevMessages,
        { id: uuidv4(), content: message, sender: "user" }
      ]);

      // Add the assistant response to the messages state
      const assistantResponse = response.data; // Assuming response.data contains the assistant's message
      setMessages(prevMessages => [
        ...prevMessages,
        { id: uuidv4(), content: assistantResponse, sender: "assistant" }
      ]);

      // Clear the message input after sending
      setMessage("");

      // Notify the Home component to refresh data
      setonChat((prevState: boolean) => !prevState);     } catch (error) {
      console.error("There was a problem with the axios operation:", error);
    }
  };

  const handleEndChat = async () => {
    try {
      if (chatId) {
        await axios.post("http://localhost:8080/api/v1/end-chat", { id: chatId });
      }
      // Clear chat ID
      setChatId(null);
    } catch (error) {
      console.error("There was a problem with ending the chat:", error);
    }
  };

  return (
    <div className="chat-box" style={{ display: 'flex', flexDirection: 'column', height: '80vh', width: '100%', maxWidth: '600px', margin: '0 auto', border: '1px solid #ccc', borderRadius: '8px', boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)' }}>
      <div className="header" style={{ padding: '10px', background: '#f5f5f5', borderBottom: '1px solid #ddd', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <span>Assistant</span>
        <button onClick={handleEndChat} style={{ padding: '5px 10px', border: 'none', borderRadius: '4px', backgroundColor: '#ff6f6f', color: '#fff', cursor: 'pointer' }}>
          End Chat
        </button>
      </div>
      <div className="messages" style={{ flex: 1, padding: '10px', overflowY: 'scroll', background: '#fff' }}>
        {messages.map(message => (
          <div
            key={message.id}
            className={`message ${message.sender === "user" ? "user-message" : "assistant-message"}`}
            style={{
              padding: '10px',
              marginBottom: '10px',
              borderRadius: '8px',
              background: message.sender === "user" ? '#d1e7dd' : '#f8d7da',
              alignSelf: message.sender === "user" ? 'flex-end' : 'flex-start',
              maxWidth: '80%',
            }}
          >
            {message.content}
          </div>
        ))}
      </div>
      <div className="input-box" style={{ padding: '10px', borderTop: '1px solid #ddd', display: 'flex', alignItems: 'center', background: '#f5f5f5' }}>
        <input
          type="text"
          placeholder="Message"
          value={message}
          onChange={e => setMessage(e.target.value)}
          style={{ flex: 1, padding: '10px', border: '1px solid #ddd', borderRadius: '4px', marginRight: '10px' }}
        />
        <button onClick={handleSend} style={{ padding: '10px 15px', border: 'none', borderRadius: '4px', backgroundColor: '#007bff', color: '#fff', cursor: 'pointer' }}>
          Send
        </button>
      </div>
    </div>
  );
};

export default ChatBox;
