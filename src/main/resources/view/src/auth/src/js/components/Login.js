'use strict';

import React from 'react';
import { Link } from 'react-router-dom';
import { auth } from '../util/api.auth.js';

class Login extends React.Component {
    constructor(props) {
        super(props)

        this.onSubmitHandler = this.onSubmitHandler.bind(this);
        this.onFieldChangeHandler = this.onFieldChangeHandler.bind(this);
    }
    state = {
        email: '',
        password: '',
    }

    onSubmitHandler() {
        auth.login(this.state);
        // location.replace('/crud');
    }

    onFieldChangeHandler(e) {
        this.setState({
            [e.target.name]: e.currentTarget.value,
        });
    }

    render() {
        return (
            <div className="container">

                <div className="row justify-content-center align-items-center wrapper"> 
                    <div className="col-12 col-sm-10 col-md-7 col-lg-5 col-xl-4">

                        <form className="form">
                            <p className="title">Spring-boot-crudXXX</p>
                            <input className="form-input email col" onChange={this.onFieldChangeHandler} type="email" name="email" placeholder="email"/>
                            <input className="form-input password col" onChange={this.onFieldChangeHandler} type="password" name="password" placeholder="password"/>
                            <div className="row justify-content-between row__login">
                                {/* //TODO if this tag is a button tag then it sends get request by clicking. Figure out why */}
                                <div className="col-5 btn btn-primary" onClick={this.onSubmitHandler}>Sign in</div>
                                <Link className="col-5 btn btn-primary" to='/signup'>Registration</Link>
                            </div>
                            <a className="btn btn-primary" href="/oauth2/authorize/vk?redirect_uri=http://localhost:8080/oauth2/redirect">Sign in with vk.com</a>
                            <a className="btn btn-primary" href="/oauth2/authorize/google?redirect_uri=http://localhost:8080/oauth2/redirect">Sign in with google.com</a>
                        </form>

                    </div>
                </div>
            </div>
        );
    }
}

export default Login;