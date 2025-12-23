import { useState } from "react";
import { createUser, transferMoney } from "./api/walletApi";

function App() {
  const [message, setMessage] = useState("");

  const handleCreateUsers = async () => {
    try {
      await createUser({ name: "Alice", balance: 100 });
      await createUser({ name: "Bob", balance: 50 });
      setMessage("✅ Users Alice and Bob created successfully");
    } catch (error) {
      setMessage("❌ Error creating users");
      console.error(error);
    }
  };

  const handleTransfer = async () => {
    try {
      await transferMoney({
        fromUserId: 1,
        toUserId: 2,
        amount: 50,
      });
      setMessage("💸 $50 transferred from Alice to Bob");
    } catch (error) {
      setMessage("❌ Transfer failed");
      console.error(error);
    }
  };

  return (
    <div style={{ padding: "30px", fontFamily: "Arial" }}>
      <h1>💰 Mini Wallet</h1>

      <button onClick={handleCreateUsers} style={{ marginRight: "10px" }}>
        Create Users
      </button>

      <button onClick={handleTransfer}>
        Transfer $50 from Alice to Bob
      </button>

      <p style={{ marginTop: "20px", fontWeight: "bold" }}>{message}</p>
    </div>
  );
}

export default App;
