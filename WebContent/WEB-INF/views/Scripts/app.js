angular.module("SpeedTyperSPA", ['ngRoute'])
	.config(["$routeProvider", function ($routeProvider) {
	    $routeProvider
			.when("/", 
			{ 
				templateUrl: "Scripts/partials/home.html", 
				controller: HomeController 
			})
			.when("/login", 
			{
				templateUrl: "Scripts/partials/login-form.html",
				controller: LoginController
			})
			.when("/register", 
			{
				templateUrl: "Scripts/partials/register-form.html",
				controller: RegisterController
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