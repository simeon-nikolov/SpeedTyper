angular.module("SpeedTyperSPA", [])
	.config(["$routeProvider", function ($routeProvider) {
	    $routeProvider
			.when("/", 
			{ 
				templateUrl: "Scripts/partials/home.html", 
				controller: HomeController 
			})
			.when("/rooms/", 
			{ 
				templateUrl: "Scripts/partials/all-rooms.html", 
				controller: RoomsController 
			})
			.otherwise(
			{ 
				redirectTo: "/" }
			);
	}]);
