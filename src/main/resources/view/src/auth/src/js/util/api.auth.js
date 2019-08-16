'use strict';

import { SERVER_ADDRESS } from '../config/server.conf.js';

const SERVER_API_URL = SERVER_ADDRESS + '/auth';

export const auth = {

    login(loginRequest) {
        return fetch(`${SERVER_API_URL}/login`, {
            headers: {
                'Content-type': 'application/json',
            },
            method: 'POST',
            body: JSON.stringify(loginRequest),
        })
        .then(response => response.json())
        .then(json => {
            const token = json.accessToken;

            document.cookie = 'Authorization' + '=' + token + '; path=/';
        });
    },

    signup(signUpRequest) {
        return fetch(`${SERVER_API_URL}/signup`, {
            headers: {
                'Content-type': 'application/json',
            },
            method: 'POST',
            body: JSON.stringify(signUpRequest),
        });
    },

    logout() {
        document.cookie = 'Authorization' + '=' + '; path=/';
        location.replace('/login');
    }
};
