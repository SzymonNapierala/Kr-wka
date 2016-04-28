(function() {

	"use strict";

	var module = angular.module("ConfigurationModule", ["ngRoute"]);

	module.config(["$routeProvider", function($routeProvider) {
		$routeProvider.when("/roles", {
			templateUrl: "configuration?page=roles",
			controller: "RoleController",
		}).when("/users", {
			templateUrl: "configuration?page=users",
			controller: "UserController",
		}).otherwise({
			redirectTo: "/roles"
		});
	}]);

	module.controller("ConfigurationController", function($scope) {
		$scope.$on("select", function(event, data) {
			$scope.active = data;
		});
	});

})();
