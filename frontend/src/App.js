import {useState} from "react";

const LogUI = () => {
    const [accessToken, setAccessToken] = useState("");

    const [name, setName] = useState("");
    const [rank, setRank] = useState("");
    const [identity, setIdentity] = useState("");
    const [passcode, setPasscode] = useState("");

    const reset = () => {
        setName("");
        setRank("");
        setIdentity("");
        setPasscode("");
    }

    const login = () => {
    }

    const  loginScreen = () => {
        return (
            <div align="center">
                <h1 className="header-1">Starship Log System</h1>
                <h2 className="header-2">Access requires authentication</h2>
                <div>
                    <span className="label">Name:</span>
                    <input
                        className="input"
                        onChange={(e) => setName(e.target.value)}
                        value={name}
                    />
                </div>
                <div>
                    <span className="label">Rank:</span>
                    <input
                        className="input"
                        onChange={(e) => setRank(e.target.value)}
                        value={rank}
                    />
                </div>
                <div>
                    <span className="label">Identity:</span>
                    <input
                        className="input"
                        onChange={(e) => setIdentity(e.target.value)}
                        value={identity}
                    />
                </div>
                <div>
                    <span className="label">Passcode:</span>
                    <input
                        className="input"
                        onChange={(e) => setPasscode(e.target.value)}
                        type="password"
                        value={passcode}
                    />
                </div>
                <div>
                    <button
                        className="button"
                        onClick={login}
                    >Login</button>
                    <button
                        className="button"
                        onClick={reset}
                    >Reset</button>
                </div>
                <h3 className="header-3">All access is logged</h3>
                <h3 className="header-3">Unauthorized usage is prohibited</h3>
            </div>
        );
    }

    const actionScreen = () => {
        return (
            <p>Menu</p>
        );
    }

    return (
        <div>
            {(accessToken === "") ? (loginScreen()) : (actionScreen())}
        </div>
    );
}

function App() {
    return (
        <div>
            <LogUI/>
        </div>
    );
}

export default App;