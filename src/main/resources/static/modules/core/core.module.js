var core = angular.module('core',['ui.router', 'ngResource']);

core.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $ulrRouterProvider){
	$stateProvider
		.state('home', {
			url: '',
			templateUrl: 'modules/core/views/home.html',
			controller: 'HomeCtrl',
			resolve: {
				todosResource: 'todosResource',
				todos: function(todosResource){
					return todosResource.query().$promise;
				}
			}
		});
}]);

core.factory('todosResource',['$resource', function($resource){
	return $resource('http://localhost:8080/todos');
}]);

core.controller('HomeCtrl',['$scope', 'todos', function($scope, todos){
	console.log(todos);	
	$scope.todos = todos;
}]);