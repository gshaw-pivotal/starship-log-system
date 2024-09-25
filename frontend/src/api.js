import {Log} from "./Log";

const API_URL = window.location.hostname === 'localhost' ? 'http://localhost:8080' : '';

export async function loginRequest(user) {
    const response = await fetch(`${API_URL}/login`, {
        method: 'POST',
        headers: {
            'content-type': 'application/json',
        },
        body: JSON.stringify(user)
    });
    if (response.ok) {
        let json = await response.json();
        return json.token;
    }

    return '';
}

export async function logoutRequest(user) {
    const response = await fetch(`${API_URL}/logout`, {
        method: 'POST',
        headers: {
            'content-type': 'application/json',
        },
        body: JSON.stringify(user)
    });
}

export async function getLogsRequest(token) {
    const response = await fetch(`${API_URL}/logs`, {
        method: 'GET',
        headers: {
            'X-API-KEY': token,
        },
    });
    if (response.ok) {
        let logList = [];
        let json = await response.json();
        for (let e of json) {
            logList.push(new Log(e.id, e.userId, e.logDate, e.posting, e.location, e.header, e.content));
        }
        return logList;
    } else {
        return null;
    }
}

export async function saveLogRequest(token, newLog) {
    const response = await fetch(`${API_URL}/log`, {
        method: 'POST',
        headers: {
            'X-API-KEY': token,
            'content-type': 'application/json',
        },
        body: JSON.stringify(newLog)
    });
    return response.ok;
}