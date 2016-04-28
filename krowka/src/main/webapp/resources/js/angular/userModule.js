(function() {

	"use strict";

	var module = angular.module("UserModule", []);

	module.controller("UserController", function($scope) {
		$scope.$emit("select", "users");
	});

})();
