(function() {

	"use strict";

	var module = angular.module("UserModule", []);

	module.controller("UserController", ["$http", "$scope",
			function($http, $scope) {

		$scope.$emit("select", "users");

		$scope.user = null;
		$scope.users = [];

		$scope.loadUsers = function() {
			$http.get("api/users").then(function(response) {
				$scope.users = response.data;
			});
		};

		$scope.prepareNewUser = function() {
			$scope.user = {
				firstName: "",
				lastName: "",
				username: "",
				password: "",
				passwordRepeat: "",
				roles: []
			};
		};

		$scope.prepareUser = function(id) {
			$scope.user = {
				firstName: "",
				lastName: "",
				username: "",
				password: "",
				passwordRepeat: "",
				roles: []
			};
			$http.get("api/user/" + id).then(function(response) {
				$scope.user = response.data;
			});
		};

		$scope.createUser = function(user) {
			$http.post("api/user", user).then(function(response) {
				$scope.loadUsers();
				$("#modal-user-create").modal("hide");
			});
		};

		$scope.updateUser = function(user) {
			$http.put("api/user/" + user.id, user).then(function(response) {
				$scope.loadUsers();
				$("#modal-user-update").modal("hide");
			});
		};

		$scope.deleteUser = function(user) {
			$http.delete("api/user/" + user.id, user).then(function(response) {
				$scope.loadUsers();
				$("#modal-user-delete").modal("hide");
			});
		};

		$scope.loadUsers();

	}]);

})();
