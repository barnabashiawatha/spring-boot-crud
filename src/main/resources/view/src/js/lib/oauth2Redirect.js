(() => {
    const url = window.location.href;
    const regex = /token=(.*)/;
    const result = url.match(regex);

    document.cookie = 'Authorization' + '=' + "Bearer " + result[1] + '; path=/';

    location.replace("/");
})();