'use strict';

import { api } from './api/api.js';

function login() {
    const user = {
        username: $('.username').val(),
        password: $('.password').val(), 
    };

    api.logIn(user);
}

function loginVk() {
    window.name = 'fXD'
        VK.init({
        apiId: 7048268,
    });
    
    VK.Auth.login(65536);
 
}

window.login = login;
window.loginVk = loginVk;