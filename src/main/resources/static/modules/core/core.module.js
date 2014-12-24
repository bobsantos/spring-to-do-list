var core = angular.module('core',[]);

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

core.controller('HomeCtrl',['$scope', 'todos', 'todosResource', function($scope, todos, todosResource){
	$scope.todos = todos;
	
	$scope.addToDo = function(){
		var todo = { title: $scope.newTodo.title, description: $scope.newTodo.description };
		var promise = todosResource.save(todo).$promise;
		promise.then(function(res){
			console.log(res);
			$scope.todos.push(todo);
			$scope.newTodo = {};
		});
		promise.catch(function(res){
			console.log(res);
			$scope.errorMsg = 'An error occured while saving...';
		});
	}
}]);