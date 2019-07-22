'use strict';

import { auth } from './api/auth.js';

function login() {
    const user = {
        email: $('.email').val(),
        password: $('.password').val(), 
    };

    auth.login(user);
    location.replace('/crud');
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