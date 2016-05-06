(function() {

	"use strict";

	var module = angular.module("RoleModule", []);

	module.controller("RoleController", ["$http", "$scope",
			function($http, $scope) {

		$scope.$emit("select", "roles");

		$scope.role = null;
		$scope.roles = [];

		$scope.loadRoles = function() {
			$http.get("api/roles").then(function(response) {
				$scope.roles = response.data;
			});
		};

		$scope.prepareNewRole = function() {
			$scope.role = {
				name: "",
				forGuest: false,
				parentRole: null
			};
		};

		$scope.createRole = function(role) {
			$http.post("api/role", role).then(function(response) {
				$scope.loadRoles();
				$("#modal-role-create").modal("hide");
			});
		};

		$scope.loadRoles();

	}]);

})();
