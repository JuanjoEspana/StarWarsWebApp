'use strict'

var module = angular.module('starWars.controllers', []);
module.controller("PeopleController", ["$scope", "PeopleService",
    function($scope, PeopleService) {
        $scope.peopleDto = {            
            name: null,
            starships: []
        };
        $scope.starships = [];
        
        PeopleService.getAllPeople().then(function(value) {
        	console.log("Value: " + value)
			$scope.allPeople= value.data;
		}, function(reason) {
			console.log("error occured");
			console.log(reason);
		}, function(value) {
			console.log("no callback");
		});

		$scope.starships = [];
		$scope.peopleDto = {			
			name : null,
			starships : []
		};
    }
]);