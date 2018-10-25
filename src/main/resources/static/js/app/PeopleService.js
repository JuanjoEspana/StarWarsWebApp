
'use strict'
angular.module('starWars.services', []).factory('PeopleService', 
		["$http", "CONSTANTS", function($http, CONSTANTS) {
    var service = {};

    service.getAllPeople = function() {
        return $http.get(CONSTANTS.getAllPeople);
    }
    
    service.sort = function(field, order){
    	return $http.get(CONSTANTS.sort+"/"+field+"/"+order)
    }
    return service;
}]);