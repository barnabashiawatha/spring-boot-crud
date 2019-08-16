'use strict';

import React from 'react';
import Login from './Login.js';
import Signup from './Signup.js';
import { Redirect } from 'react-router-dom';
import OAuth2RedirectHandler from './OAuth2RedirectHandler.js';
import { Switch, Route } from 'react-router-dom';

class App extends React.Component {
    render() {
        return (
            <Switch>
                <Route path='/login' exact component={Login}/>
                <Route path='/signup' exact component={Signup}/>
                <Route path='/oauth2/redirect' component={OAuth2RedirectHandler}/>
                <Redirect to='/login'/>
            </Switch>
        );
    }
}

export default App;