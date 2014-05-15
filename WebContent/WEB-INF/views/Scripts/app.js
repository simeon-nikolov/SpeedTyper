angular.module("SpeedTyperSPA", [])
	.config(["$routeProvider", function ($routeProvider) {
	    $routeProvider
			.when("/", 
			{ 
				templateUrl: "", 
				controller: HomeController 
			})
			.when("/rooms/", 
			{ 
				templateUrl: "", 
				controller: RoomsController 
			})
			.otherwise(
			{ 
				redirectTo: "/" }
			);
	}]);
