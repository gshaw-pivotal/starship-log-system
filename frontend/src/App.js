import {useState} from "react";

import {getLogsRequest, loginRequest, logoutRequest} from './api';
import {User} from "./User";

const LogUI = () => {
    const [accessToken, setAccessToken] = useState("");

    const [name, setName] = useState("");
    const [rank, setRank] = useState("");
    const [identity, setIdentity] = useState("");
    const [passcode, setPasscode] = useState("");

    const [loginError, setLoginError] = useState(false);

    const [newLogEditable, setNewLogEditable] = useState(true);

    const [logHeader, setLogHeader] = useState("");
    const [logDate, setLogDate] = useState("");
    const [logPosting, setLogPosting] = useState("");
    const [logLocation, setLogLocation] = useState("");
    const [logContent, setLogContent] = useState("");

    const [userLogs, setUserLogs] = useState([]);
    const [haveUserLogs, setHaveUserLogs] = useState(false);

    const reset = () => {
        setName("");
        setRank("");
        setIdentity("");
        setPasscode("");
        setAccessToken("");

        setLoginError(false);
    }

    const login = async () => {
        const user = new User(name, rank, identity, passcode);
        const token = await loginRequest(user);

        if (token.length === 0) {
            setLoginError(true);
            setAccessToken("")
        } else {
            setLoginError(false);
            setAccessToken(token);
        }
    }

    const logout = async () => {
        const user = new User(name, rank, identity, passcode);
        await logoutRequest(user);

        reset();
    }

    const saveNewLog = async () => {}

    const clearLog = () => {
        setLogHeader("");
        setLogDate("");
        setLogPosting("");
        setLogLocation("");
        setLogContent("");

        setNewLogEditable(true);
    }

    const getUserLogs = () => {
        if (!haveUserLogs) {
            setHaveUserLogs(true);
            getLogsRequest(accessToken)
                .then(res => setUserLogs(res));
        }
    }

    const loginScreen = () => {
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
                {loginError ? (
                    <div>
                        <span className="login-error">Access Denied</span>
                    </div>
                ) : ('')}
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
        getUserLogs();
        return (
            <div>
                <div align="center">
                    <h1 className="header-1">Starship Log System</h1>
                    <div>
                        <button
                            className="button"
                            onClick={logout}
                        >Logout
                        </button>
                    </div>
                </div>
                <hr/>
                <div>
                    <div className="left-log-block">
                        <h3 className="header-3 title-center">Existing Recorded Logs</h3>
                    </div>
                    <div className="right-log-block">
                        <h3 className="header-3 title-center">New / Current Log</h3>
                        <div>
                            <span className="label">Header:</span>
                            <input
                                className="input"
                                disabled={!newLogEditable}
                                onChange={(e) => setLogHeader(e.target.value)}
                                value={logHeader}
                            />
                        </div>
                        <div>
                            <span className="label">Date:</span>
                            <input
                                className="input"
                                disabled={!newLogEditable}
                                onChange={(e) => setLogDate(e.target.value)}
                                value={logDate}
                            />
                        </div>
                        <div>
                            <span className="label">Posting:</span>
                            <input
                                className="input"
                                disabled={!newLogEditable}
                                onChange={(e) => setLogPosting(e.target.value)}
                                value={logPosting}
                            />
                        </div>
                        <div>
                            <span className="label">Location:</span>
                            <input
                                className="input"
                                disabled={!newLogEditable}
                                onChange={(e) => setLogLocation(e.target.value)}
                                value={logLocation}
                            />
                        </div>
                        <div>
                            <span className="label label-area">Content:</span>
                            <textarea
                                className="input input-area"
                                disabled={!newLogEditable}
                                onChange={(e) => setLogContent(e.target.value)}
                                value={logContent}
                            />
                        </div>
                        <div align="center">
                            <button
                                className="button"
                                disabled={!newLogEditable}
                                onClick={saveNewLog}
                            >Save Log
                            </button>
                            <button
                                className="button"
                                onClick={clearLog}
                            >Clear Log
                            </button>
                        </div>
                    </div>
                </div>
            </div>
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