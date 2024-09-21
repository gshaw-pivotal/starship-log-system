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