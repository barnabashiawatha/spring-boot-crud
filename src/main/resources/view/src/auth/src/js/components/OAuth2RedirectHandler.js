'use strict';

import React from 'react';
import Redirect from 'react-router-dom';

class OAuth2RedirectHandler extends React.Component {
    render() {
        const url = window.location.href;
        const regex = /token=(.*)/;
        const result = url.match(regex);

        document.cookie = 'Authorization' + '=' + result[1] + '; path=/';

        location.replace("/");
    }
}

export default OAuth2RedirectHandler;