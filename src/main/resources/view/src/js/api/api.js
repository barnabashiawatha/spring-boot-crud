'use strict';

import { SERVER_ADDRESS } from '../config/server.conf.js';

const SERVER_API_URL = SERVER_ADDRESS + '/api';

export const api = {

    async request(options) {
        const headers = new Headers({
            'Content-type': 'application/json',
        });

        const defaults = { headers: headers };
        options = Object.assign({}, defaults, options);

        return await fetch(options.url, options)
            .then(response => response.json())
            .then(json => json);
    },

    findAll() {
        return this.request({
            url: `${SERVER_API_URL}/findall`,
        });
    },
    
    bulkcreate() {
        return fetch(`${SERVER_API_URL}/bulkcreate`);
    },

    findCustomerById(id) {
        return this.request({
            url: `${SERVER_API_URL}/findbyid?id=${id}`,
        });
    },

    findCustomerByName(name) {
        return this.request({
            url: `${SERVER_API_URL}/findbyname?name=${name}`,
        });
    },

    deleteCustomerById(id) {
        return fetch(`${SERVER_API_URL}/deletebyid?id=${id}`);
    },

    deleteCustomerByName(name) {
       return fetch(`${SERVER_API_URL}/deletebyname?name=${name}`);
    },

    clearRepository() {
       return fetch(`${SERVER_API_URL}/deleteall`);
    },

    saveCustomer(customer) {
        return fetch(`${SERVER_API_URL}/save`, {
            headers: {
                'Content-type': 'application/json',
            },
            method: 'POST',
            body: JSON.stringify({ name: customer.name, age: customer.age })
        })
    },

    updateCustomer(customer) {
        return fetch(`${SERVER_API_URL}/update`, {
           headers: {
                'Content-type': 'application/json',
            },
            method: 'POST',
            body: JSON.stringify({
                id: customer.id,
                name: customer.name,
                age: customer.age,
            }),
        });
    },
};
