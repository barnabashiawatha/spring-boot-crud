'use strict';

import { auth } from './api/auth.js';

function signup() {
    let user = {
        name: $('.name').val(),
        email: $('.email').val(),
        password: $('.password').val(),
    };

    auth.signup(user);
    location.replace('/login');
}

window.signup = signup;