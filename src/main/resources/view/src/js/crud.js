'use strict';

import { auth } from './api/auth.js';
import { api } from './api/api.js';

async function findAll() {
    let result = '';

    await api.findAll().then( customers => {
        customers.map( customer => result += `name: ${customer.name} age: ${customer.age}<br>` );
    });
    
    $('.result').slideUp('slow');
    $('.result').html(result);
    $('.result').slideDown('slow');
}

async function bulkcreate() {
    await api.bulkcreate();

    showCustomers();
}

async function findCustomerById() {
    const id = $('.id_find').val();
    const customer = await api.findCustomerById(id);

    $('.result').slideUp('slow');
    $('.result').html(`name: ${customer.name} age: ${customer.age}<br>`);
    $('.result').slideDown('slow');
}

async function findCustomerByName() {
    const name = $('.name_find').val();
    let result = '';

    await api.findCustomerByName(name).then(customers => {
        customers.map( customer => result += `name: ${customer.name} age: ${customer.age}<br>` );
    });;

    $('.result').slideUp('slow');
    $('.result').html(result);
    $('.result').slideDown('slow');
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
    $('.result').slideUp('slow', () => {
        $('.result').html('');
    })
}

async function clearRepository() {
    $('.customers').slideUp('slow');
    await api.clearRepository();

}

async function deleteThisCustomer(e) {
    const id = $(e.target).parent().siblings('.id').html();
    $('.customers').slideUp('slow', async () => {
        await api.deleteCustomerById(id);
    });

    showCustomers();
}

function logout() {
    auth.logout();
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
    $('.customers').slideUp('fast', async () => {
        $('.customer').remove();
        const customers = await api.findAll();

        customers.map((customer, index) => {
            $('.customers')
                .append($('<tr></tr>').addClass('customer row justify-content-center table-customers-row')
                    .append($('<th></th>').addClass('col-1').html(`${index + 1}`))
                    .append($('<th></th>').addClass('col-1').html(`${customer.id}`).addClass('id'))
                    .append($('<th></th>').addClass('col-5').html(`${customer.name}`))
                    .append($('<th></th>').addClass('col-2').html(`${customer.age}`))
                    .append($('<th></th>').addClass('col-2')
                    .append($('<button></button>').addClass('btn btn-primary btn-delete').html('X').click(deleteThisCustomer.bind(this)))))
        });

    $('.customers').slideDown('slow');
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
window.logout = logout;
window.saveCustomer = saveCustomer;
window.showCustomers = showCustomers;

window.onload = showCustomers;
