(function() {

	"use strict";

	var module = angular.module("RoleModule", []);

	module.controller("RoleController", function($scope) {
		$scope.$emit("select", "roles");
	});

})();
