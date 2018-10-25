'use strict'
var demoApp = angular.module('starWars', ['ui.bootstrap', 'starWars.controllers',
    'starWars.services', 'ngRoute'
]);
demoApp.constant("CONSTANTS", {    
    getAllPeople: "/people/getAllPeople",
    sort: "/people/sort"
});