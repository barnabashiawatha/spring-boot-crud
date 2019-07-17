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
        // return fetch(`${SERVER_API_URL}/findall`)
        //     .then( response => response.json())
        //         .then( customers => customers );
        
        return this.request({
            url: `${SERVER_API_URL}/findall`,
        });
    },
    
    bulkcreate() {
        // return fetch(`${SERVER_API_URL}/bulkcreate`);

        return this.request({
            url: `${SERVER_API_URL}/bulkcreate`,
        });
    },

    findCustomerById(id) {
        // return fetch(`${SERVER_API_URL}/findbyid`, {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'text/plain',
        //     },
        //     body: id,
        // })
        // .then( response  => response.json() )
        //     .then( customer => customer );

        return this.request({
            url: `${SERVER_API_URL}/findbyid`,
            method: 'POST',

            //TODO change to a proper json object
            body: JSON.stringify({ id: id}),
        });
    },

    findCustomerByName(name) {
        // return fetch(`${SERVER_API_URL}/findbyname`, {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'text/plain',
        //     },
        //     body: name,
        // })
        // .then( response => response.json() )
        //     .then( customers => customers );

        return this.request({
            url: `${SERVER_API_URL}/findbyname`,
            method: 'POST',

            //TODO change to a proper json object
            body: JSON.stringify({ name: name }),
        });
    },

    deleteCustomerById(id) {
        // return fetch(`${SERVER_API_URL}/deletebyid`, {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'text/plain',
        //     },
        //     body: id,
        // });

        return this.request({
            url: `${SERVER_API_URL}/deletebyid`,
            method: 'POST',

            //TODO change to a proper json object
            body: JSON.stringify({ id: id }),
        });
    },

    deleteCustomerByName(name) {
        // return fetch(`${SERVER_API_URL}/deletebyname`, {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'text/plain',
        //     },
        //     body: name,
        // });

        return this.request({
            url: `${SERVER_API_URL}/deletebyname`,
            method: 'POST',
            body: JSON.stringify({ name: name }),
        });
    },

    clearRepository() {
        // return fetch(`${SERVER_API_URL}/deleteall`, { method: 'POST' });

        return this.request({
            url: `${SERVER_API_URL}/deleteall`,
            method: 'POST',
        });
    },

    saveCustomer(customer) {
        // return fetch(`${SERVER_API_URL}/save`, {
        //     method: 'POST',
        //     headers: {
        //         'Accept': 'application/json',
        //         'Content-Type': 'application/json',
        //     },
        //     body: JSON.stringify({ name: customer.name, age: customer.age }),
        //     });

        return this.request({
            url: `${SERVER_API_URL}/save`,
            method: 'POST',
            body: JSON.stringify({ name: customer.name, age: customer.age })
        });
    },

    updateCustomer(customer) {
        // return fetch(`${SERVER_API_URL}/update`, {
        //     method: 'POST',
        //     headers: {
        //         'Accept': 'application/json',
        //         'Content-Type': 'application/json',
        //     },
        //     body: JSON.stringify({
        //         id: customer.id, 
        //         name: customer.name, 
        //         age: customer.age, 
        //     }),
        // });

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
        // return fetch(`${SERVER_API_URL}/logout`, {
        //     method: 'POST',
        // }).then( () => location.reload() );

        return this.request({
            url: `${SERVER_API_URL}`,
            method: 'POST',
        });
    },

    logIn(loginRequest) {
        // const requestBody = `username=${user.username}&password=${user.password}`;

        // return fetch(`${SERVER_ADDRESS}/login`, {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/x-www-form-urlencoded',
        //     },
        //     body: requestBody, 
        // }).then(() => location.replace(`${SERVER_ADDRESS}/`));

        return throws.request({
            url: `${SERVER_API_URL}/login`,
            body: loginRequest,
        });
    },

    register(user) {
    //     return fetch(`${SERVER_ADDRESS}/registration`, {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json',
    //         },
    //         body: JSON.stringify(user),
    //     }).then(() => location.replace(`${SERVER_ADDRESS}/`));

    return this.request({
        url: `${SERVER_API_URL}/registration`,
        body: JSON.stringify(user),
    });
    }
};
