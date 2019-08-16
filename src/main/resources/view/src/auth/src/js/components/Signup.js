'use strict';

import React from 'react';
import { Redirect } from 'react-router-dom';
import { Link } from 'react-router-dom';
import { auth } from '../util/api.auth.js';

class Signup extends React.Component {
    constructor(props) {
        super(props)

        this.onSubmitHandler = this.onSubmitHandler.bind(this);
        this.onFieldChangeHandler = this.onFieldChangeHandler.bind(this);
    }

    state = {
        name: '',
        email: '',
        password: '',
        authorized: false,
    }

    onSubmitHandler() {
        const user = {
            name: this.state.name,
            email: this.state.email,
            password: this.state.password,
        }

        auth.signup(user);
        this.setState({
            authorized: true,
        });
    }

    onFieldChangeHandler(e) {
        this.setState({
            [e.target.name]: e.currentTarget.value,
        });
    } 

    render() {
        if (this.state.authorized) return( <Redirect to='/login'/> ); 

        return (
            <div className="container">

                <div className="row justify-content-center align-items-center wrapper"> 
                    <div className="col-12 col-sm-10 col-md-7 col-lg-5 col-xl-4">

                        <form className="form">
                            <p className="title">Spring-boot-crud</p>
                            <input className="form-input name col" onChange={this.onFieldChangeHandler} type="input" name="name" placeholder="name" required/>
                            <input className="form-input email col" onChange={this.onFieldChangeHandler} type="email" name="email" placeholder="email" required/>
                            <input className="form-input password col" onChange={this.onFieldChangeHandler} type="password" name="password" placeholder="password" required/>
                            <button className="btn btn-primary" onClick={this.onSubmitHandler}>Register</button>
                            <Link className="btn btn-primary" to='/login'>Registration</Link>
                        </form>

                    </div>
                </div>
            </div>
        );
    }
}

export default Signup;