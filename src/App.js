import { BrowserRouter as Router, Link, Route, Routes } from 'react-router-dom';
import Gpt4Component from "./component/Gpt4Component";

function Home() {
    return <h2>Home</h2>;
}

function App() {
    return (
        <Router>
            <div>
                <nav>
                    <ul>
                        <li>
                            <Link to="/">Home</Link>
                        </li>
                        <li>
                            <Link to="/gpt4">GPT-4 Component</Link>
                        </li>
                    </ul>
                </nav>

                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/gpt4" element={<Gpt4Component />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
