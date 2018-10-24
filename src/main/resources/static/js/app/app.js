'use strict'
var demoApp = angular.module('starWars', ['ui.bootstrap', 'starWars.controllers',
    'starWars.services'
]);
demoApp.constant("CONSTANTS", {    
    getAllPeople: "/people/getAllPeople/"
});