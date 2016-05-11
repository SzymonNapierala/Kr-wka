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

		$scope.prepareRole = function(id) {
			$scope.role = {
				name: "",
				forGuest: false,
				parentRole: null
			};
			$scope.parentRoles = [];
			for (var r = 0; r < $scope.roles.length; ++r) {
				if ($scope.roles[r].id != id) {
					$scope.parentRoles.push($scope.roles[r]);
				}
			}
			$http.get("api/role/" + id).then(function(response) {
				if (response.data.parentRole !== null) {
					for (var r = 0; r < $scope.parentRoles.length; ++r) {
						if ($scope.parentRoles[r].id == response.data.parentRole.id) {
							response.data.parentRole = $scope.parentRoles[r];
						}
					}
				}
				$scope.role = response.data;
			});
		};

		$scope.createRole = function(role) {
			$http.post("api/role", role).then(function(response) {
				$scope.loadRoles();
				$("#modal-role-create").modal("hide");
			});
		};

		$scope.updateRole = function(role) {
			$http.put("api/role/" + role.id, role).then(function(response) {
				$scope.loadRoles();
				$("#modal-role-update").modal("hide");
			});
		};

		$scope.deleteRole = function(role) {
			$http.delete("api/role/" + role.id, role).then(function(response) {
				$scope.loadRoles();
				$("#modal-role-delete").modal("hide");
			});
		};

		$scope.loadRoles();

	}]);

})();
