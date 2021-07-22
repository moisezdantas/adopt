require('dotenv').config();
var axios = require('axios');

const api = axios.create({
    baseURL: process.env.API
});

module.exports = { api }