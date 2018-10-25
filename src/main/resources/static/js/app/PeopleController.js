'use strict'

var module = angular.module('starWars.controllers', []);

module.controller("PeopleController", ["$scope", "PeopleService",
    function($scope, PeopleService) {

		$scope.orderByField = 'name';
		$scope.reverseSort = false;
		$scope.createReverseSort = false;
        PeopleService.getAllPeople().then(function(value) {
        	console.log("Value: " + value)
			$scope.allPeople= value.data;
		}, function(reason) {
			console.log("error occured");
			console.log(reason);
		}, function(value) {
			console.log("no callback");
		});

		$scope.sort = function(){
	        PeopleService.sort($scope.orderByField, $scope.reverseSort)
        	.then(function(value){
        		$scope.allPeople = value.data;
        	}, function(reason) {
    			console.log("error occured");
    			console.log(reason);
    		}, function(value) {
    			console.log("no callback");
    		});
		}
		
		$scope.dateSort = function(){
	        PeopleService.sort($scope.orderByField, $scope.createReverseSort)
        	.then(function(value){
        		$scope.allPeople = value.data;
        	}, function(reason) {
    			console.log("error occured");
    			console.log(reason);
    		}, function(value) {
    			console.log("no callback");
    		});
		}
    }
]);

