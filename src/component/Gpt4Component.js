import React, { useState } from 'react';

function Gpt4Chat() {
    const [userInput, setUserInput] = useState('');
    const [loading, setLoading] = useState(false);
    const [response, setResponse] = useState('');

    const sendRequest = () => {
        setLoading(true);

        fetch('http://localhost:8080/gpt4', {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain'
            },
            body: userInput
        })
            .then(response => response.text())
            .then(data => {
                setResponse(data);
                setLoading(false);
            })
            .catch(error => {
                console.error('Error:', error);
                setLoading(false);
            });
    };

    return (
        <div>
            <h1>GPT-4 Chat</h1>

            <label htmlFor="userInput">Enter Your Message:</label><br />
            <textarea
                id="userInput"
                rows="5"
                cols="50"
                value={userInput}
                onChange={(e) => setUserInput(e.target.value)}
            ></textarea><br /><br />

            <button onClick={sendRequest}>Send Request</button><br /><br />

            {loading && <div>Loading...</div>}

            <div style={{overflow : 'auto'}}>
                <h2>Response:</h2>
                <p>{response}</p>
            </div>
        </div>
    );
}

export default Gpt4Chat;
