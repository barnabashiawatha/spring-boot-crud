'use strict';

import { api } from './api/api.js';

async function findAll() {
    let result = '';

    await api.findAll().then( customers => {
        customers.map( customer => result += `name: ${customer.name} age: ${customer.age}<br>` );
    });

    $('.result').html(result);
}

async function bulkcreate() {
    await api.bulkcreate();

    showCustomers();
}

async function findCustomerById() {
    const id = $('.id_find').val();
    const customer = await api.findCustomerById(id);

    $('.result').html(`name: ${customer.name} age: ${customer.age}<br>`);
}

async function findCustomerByName() {
    const name = $('.name_find').val();
    let result = '';

    await api.findCustomerByName(name).then(customers => {
        customers.map( customer => result += `name: ${customer.name} age: ${customer.age}<br>` );
    });;

    $('.result').html(result);
}

async function deleteCustomerById() {
    const id = $('.id_delete').val();

    await api.deleteCustomerById(id);

    showCustomers();
}

async function deleteCustomerByName() {
    const name = $('.name_delete').val();
    await api.deleteCustomerByName(name);

    showCustomers();
}

function clearTable() {
    $('.result').html('');
}

async function clearRepository() {
    await api.clearRepository();

    showCustomers();
}

async function deleteThisCustomer(e) {
    const id = $(e.target).parent().siblings('.id').html();
    await api.deleteCustomerById(id);

    showCustomers();
}

function logOut() {
    api.logOut();
}

async function saveCustomer() {
    const customer = {
        id: $('.add-id').val(),
        name: $('.add-name').val(),
        age: $('.add-age').val(),
    };
    
    if (customer.id == '') {
        await api.saveCustomer(customer);

        showCustomers();
    } else {
        await api.updateCustomer(customer);

        showCustomers();
    }
}

async function showCustomers() {
    $('.customer').remove();
    const customers = await api.findAll();

    customers.map((customer, index) => {
        $('.customers')
            .append($('<tr></tr>').addClass('customer')
                .append($('<th></th>').html(`${index + 1}`))
                .append($('<th></th>').html(`${customer.id}`).addClass('id'))
                .append($('<th></th>').html(`${customer.name}`))
                .append($('<th></th>').html(`${customer.age}`))
                .append($('<th></th>')
                .append($('<button></button>').html('delete').click(deleteThisCustomer.bind(this)))))
    });
}

window.bulkcreate = bulkcreate;
window.findAll = findAll;
window.findCustomerById = findCustomerById;
window.findCustomerByName = findCustomerByName;
window.deleteCustomerById = deleteCustomerById;
window.deleteCustomerByName = deleteCustomerByName;
window.clearTable = clearTable;
window.clearRepository = clearRepository;
window.deleteThisCustomer = deleteThisCustomer;
window.logOut = logOut;
window.saveCustomer = saveCustomer;
window.showCustomers = showCustomers;

window.onload = showCustomers;
