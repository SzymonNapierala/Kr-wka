(function() {

	"use strict";

	var module = angular.module("RoleModule", []);

	module.controller("RoleController", ["$http", "$scope", function($http, $scope) {
		$scope.$emit("select", "roles");
		$scope.roles = [];
		$scope.loadRoles = function() {
			$http.get("api/roles").then(function(response) {
				$scope.roles = response.data;
			});
		};
		$scope.loadRoles();
	}]);

})();
