'use strict';

import { api } from './api/api.js';

function register() {
    let user = {
        username: $('.username').val(),
        password: $('.password').val(),
    };

    api.register(user);
}

window.register = register;