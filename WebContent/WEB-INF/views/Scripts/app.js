angular.module("SpeedTyperSPA", [])
	.config(["$routeProvider", function ($routeProvider) {
	    $routeProvider
			.when("/", 
			{ 
				templateUrl: "Scripts/partials/home.html", 
				controller: HomeController 
			})
			.when("/login", 
			{
				template: "Scripts/partials/login-form.html",
				controller: LoginController
			})
			.when("/register", 
			{
				template: "Scripts/partials/register-form.html",
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
