(() => {
    const url = window.location.href;
    const regex = /token=(.*)/;
    const result = url.match(regex);

//     result != null 
//         ? localStorage.setItem('access_token', result[1]) 
//         : console.log('there is no any token');
        
document.cookie = 'Authorization' + '=' + "Bearer " + result[1] + '; path=/';
    location.replace("/");
})();