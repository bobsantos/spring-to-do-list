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

core.factory('todoResource',['$resource', function($resource){
	return $resource('http://localhost:8080/todos/:id', {id: '@id'}, {
		'update': {
			method: 'PUT'
		},
		
		'delete': {
			method: 'DELETE'
		}
	});
}]);

core.controller('HomeCtrl',['$scope', 'todos', 'todosResource',
    'todoResource',
    function($scope, todos, todosResource, todoResource, validator){
		$scope.todos = todos;
		
		$scope.addToDo = function(){
			if(!$scope.todoForm.$invalid){
				var todo = { 
						title: $scope.newTodo.title, 
						description: $scope.newTodo.description,
						done: false
				};
				var promise = todosResource.save(todo).$promise;
				promise.then(function(res){
					$scope.todos.push(todo);
					$scope.newTodo = {};
					$scope.todoForm.$setPristine();
				});
				promise.catch(function(res){
					$scope.errorMsg = 'An error occured while saving...';
				});
			}
		}
		
		$scope.toggleDone = function(todo){
			delete todo._links;
			var promise = todoResource.update(todo).$promise;
			promise.then(function(res){
			});
			promise.catch(function(res){
				$scope.errorMsg = 'An error occured while updating...';
			});
		};
		
		$scope.delete = function(index){
			var todo = $scope.todos[index];
			var promise = todoResource.delete(todo).$promise;
			promise.then(function(res){
				$scope.todos.splice(index, 1);
			});
			promise.catch(function(res){
				$scope.errorMsg = 'An error occured while deleting...';
			});
		};
	}
]);