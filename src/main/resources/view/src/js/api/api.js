'use strict';

import { SERVER_ADDRESS } from '../config/server.conf.js';

const SERVER_API_URL = SERVER_ADDRESS + '/api';

export const api = {

    request(options) {
        const headers = new Headers({
            'Content-type': 'application/json',
        });

        if (localStorage.getItem('access_token') != null) {
            headers.append('Authorization', 'Bearer ' + localStorage.getItem('access_token'));
        }

        const defaults = { headers: headers };
        options = Object.assign({}, defaults, options);

        fetch(options.url, options)
            .then(response => {
                return response.json();
            })
            .then(json => {
                if (!response.ok) {
                    return Promise.reject(json);
                }
                
                return json;
            })
    },

    findAll() {
        return this.request({
            url: `${SERVER_API_URL}/findall`,
        });
    },
    
    bulkcreate() {
        return this.request({
            url: `${SERVER_API_URL}/bulkcreate`,
        });
    },

    findCustomerById(id) {
        return this.request({
            url: `${SERVER_API_URL}/findbyid`,
            method: 'POST',

            //TODO change to a proper json object
            body: JSON.stringify({ id: id}),
        });
    },

    findCustomerByName(name) {
        return this.request({
            url: `${SERVER_API_URL}/findbyname`,
            method: 'POST',

            //TODO change to a proper json object
            body: JSON.stringify({ name: name }),
        });
    },

    deleteCustomerById(id) {
        return this.request({
            url: `${SERVER_API_URL}/deletebyid`,
            method: 'POST',

            //TODO change to a proper json object
            body: JSON.stringify({ id: id }),
        });
    },

    deleteCustomerByName(name) {
       return this.request({
            url: `${SERVER_API_URL}/deletebyname`,
            method: 'POST',
            body: JSON.stringify({ name: name }),
        });
    },

    clearRepository() {
        return this.request({
            url: `${SERVER_API_URL}/deleteall`,
            method: 'POST',
        });
    },

    saveCustomer(customer) {
        return this.request({
            url: `${SERVER_API_URL}/save`,
            method: 'POST',
            body: JSON.stringify({ name: customer.name, age: customer.age })
        });
    },

    updateCustomer(customer) {
        return this.request({
            url: `${SERVER_API_URL}/update`,
            method: 'POST',
            body: JSON.stringify({
                id: customer.id,
                name: customer.name,
                age: customer.age,
            }),
        });
    },

    logOut() {
       return this.request({
            url: `${SERVER_API_URL}`,
            method: 'POST',
        });
    },

    logIn(loginRequest) {
        return throws.request({
            url: `${SERVER_API_URL}/login`,
            body: loginRequest,
        });
    },

    register(user) {
    return this.request({
        url: `${SERVER_API_URL}/registration`,
        body: JSON.stringify(user),
    });
    }
};
