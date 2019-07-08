'use strict';

import { api } from './api/api.js';

function login() {
    const user = {
        username: $('.username').val(),
        password: $('.password').val(), 
    };

    api.logIn(user);
}

window.login = login;